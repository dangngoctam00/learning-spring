package dnt.userdetailsservice.controller;

import dnt.userdetailsservice.domain.User;
import dnt.userdetailsservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class GeneralController {
    private UserService userService;

    @Autowired
    public GeneralController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String landingPage() {
        return "landingPage";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("success")
    public String success() {
        return "success";
    }

    @GetMapping("register")
    public String register(ModelMap model) {
        model.put("user" ,new User());
        return "register";
    }

    @PostMapping("register")
    public String registerSubmit(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }
}
