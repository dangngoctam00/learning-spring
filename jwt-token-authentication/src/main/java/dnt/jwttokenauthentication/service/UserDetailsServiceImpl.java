package dnt.jwttokenauthentication.service;

import dnt.jwttokenauthentication.domain.User;
import dnt.jwttokenauthentication.repository.UserRepository;
import dnt.jwttokenauthentication.security.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("loadUserByUsername function is called");
        User user = userRepo.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
        return new CustomUserDetails(user);
    }
}