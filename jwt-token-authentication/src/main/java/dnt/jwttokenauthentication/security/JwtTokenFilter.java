package dnt.jwttokenauthentication.security;

import dnt.jwttokenauthentication.domain.User;
import dnt.jwttokenauthentication.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRepository userRepo;

    @Autowired
    public JwtTokenFilter(JwtTokenUtils jwtTokenUtil, UserRepository userRepo) {
        this.jwtTokenUtils = jwtTokenUtil;
        this.userRepo = userRepo;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = httpServletRequest.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtils.validate(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        User userDetails = userRepo
                .findByUsername(jwtTokenUtils.getUsername(token))
                .orElse(null);
        CustomUserDetails user = userDetails == null ? null : new CustomUserDetails(userDetails);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                                                        user, null,
                                                                        user == null ?
                                                                                null : user.getAuthorities()
                                                                );
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
