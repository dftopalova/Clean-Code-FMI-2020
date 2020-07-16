package com.telebeer.beertag.controllers.MVCControllers;

import com.telebeer.beertag.models.entities.*;
import com.telebeer.beertag.repositories.contracts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {
    private UserDetailsManager userDetailsManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Username/password can't be empty!");
            return "register";
        }

        if(userDetailsManager.userExists(user.getUserName())) {
            model.addAttribute("error", "User with same username already exists!");
            return "register";
        }


        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        org.springframework.security.core.userdetails.User newUserWithAuthorities =
                new org.springframework.security.core.userdetails.User(
                        user.getUserName(), passwordEncoder.encode(user.getPassword()), authorities);


        userDetailsManager.createUser(newUserWithAuthorities);

        User newUser = userRepository.getByUsername(newUserWithAuthorities.getUsername());
        updateUserInfo(user, newUserWithAuthorities, newUser);

        return "index";
    }

    @GetMapping("/register-confirmation")
    public String registerConfirmation() {
        return "register-confirmation";
    }

    private void updateUserInfo(@ModelAttribute @Valid User user, org.springframework.security.core.userdetails.User newUserWithAuthorities, User newUser) {
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUserName(newUserWithAuthorities.getUsername());
        newUser.setPassword(newUserWithAuthorities.getPassword());
        userRepository.updateUser(newUser);
    }
}
