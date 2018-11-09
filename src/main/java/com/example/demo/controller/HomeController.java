package com.example.demo.controller;

import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Arrays;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String registration(Model model){
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistration(User user){
        Role role = roleRepository.findByRole("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return "redirect:/";
    }
    @RequestMapping("/add")
    public String addMessage(Model model){
        model.addAttribute("message", new Message());
        return "message";
    }
    @PostMapping("/processmessage")
    public String processMessage(Message message, Model model){
        message.setUsername(getUser());
       messageRepository.save(message);
        return "redirect:/";
    }

    @RequestMapping("/messagedetail/{id}")
    public String messageDetail(Model model, @PathVariable("id") long id){
       model.addAttribute("message",messageRepository.findById(id));
        Message messbyId = messageRepository.findById(id);
        if(getUser().equalsIgnoreCase(messbyId.getUsername())){
            model.addAttribute("check", true);
        } else {
            model.addAttribute("check", false);
        }
       return "messagedetail";
    }

    @RequestMapping("/updatemessage/{id}")
    public String updateMessage(Model model, @PathVariable("id") long id){
        model.addAttribute("message", messageRepository.findById(id));
        return "message";
    }

    protected String getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        return currentusername;
    }

}
