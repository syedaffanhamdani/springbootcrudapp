package com.test.crud.CustomerCRUD.controller;

import com.test.crud.CustomerCRUD.persistence.User;
import com.test.crud.CustomerCRUD.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller public class UserController {

    @GetMapping("/signup") public String signup(User user) {
        return "add-user";
    }

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/") public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @PostMapping("/addUser") public String addUser(@Valid User user, BindingResult result, Model model) {
        if ( result.hasErrors() ) {
            return "add-user";
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("getUser") public String getUser(@Param("firstName") String firstName) {
        userRepository.findByFirstName(firstName);
        for (User user : userRepository.findByFirstName(firstName)) {
            System.out.println(user.getEmail());
        }
        if ( userRepository.findByFirstName(firstName).isEmpty() ) {
            System.out.println("No user found with Name " + firstName);
        }
        return "index";

    }

    @GetMapping("/delete/{id}") public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid User Id"));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }


    @GetMapping("/edit/{id}") public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}") public ModelAndView updateUser(@PathVariable("id") long id, @Valid User user,
            BindingResult result, Model model) {
        if ( result.hasErrors() ) {
            user.setId(id);
            return new ModelAndView("update-user");
        }
        User user1 = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user1.setFirstName(user.getFirstName());
        user1.setLastName((user.getLastName()));
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
        model.addAttribute("users", userRepository.findAll());
        return new ModelAndView("index");
    }

}
