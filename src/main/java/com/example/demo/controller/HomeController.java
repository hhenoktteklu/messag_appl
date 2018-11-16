package com.example.demo.controller;

import com.example.demo.entities.Message;
import com.example.demo.entities.Post;
import com.example.demo.entities.Role;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postRepository.findAll());
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
    @RequestMapping("/addpost")
    public String addMessage(Model model, Principal pr){
        model.addAttribute("post", new Post());
        return "post";
    }
    @PostMapping("/processpost")
    public String processMessage(Post post, Model model){
        post.setUsername(getUser());
        User user = userRepository.findByUsername(getUser());
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/";
    }

    @RequestMapping("/messagedetail/{id}")
    public String messageDetail(Model model, @PathVariable("id") long id){
       model.addAttribute("message",messageRepository.findById(id));
        Message messbyId = messageRepository.findById(id);
        if(getUser().equalsIgnoreCase(messbyId.getUsername())){
            model.addAttribute("currentuser", true);
        } else {
            model.addAttribute("currentuser", false);
        }
       return "messagedetail";
    }

    @RequestMapping("/updatemessage/{id}")
    public String updateMessage(Model model, @PathVariable("id") long id){
        model.addAttribute("message", messageRepository.findById(id));
        return "post";
    }

    protected String getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        return currentusername;
    }

    @RequestMapping("/privatemessage/{id}")
    public String messagePage(@PathVariable("id")long id,Model model){
        model.addAttribute("user", userRepository.findById(id));
        List<Message> messages = new ArrayList<Message>(messageRepository.findAllBySentFromAndSentTo(getUser(),userRepository.findById(id).getUsername()));
        if(!messageRepository.findAllBySentTo(getUser()).isEmpty()){
            for (Message msg: messageRepository.findAllBySentTo(getUser())) {
                if(msg.getSentFrom()!=userRepository.findById(id).getUsername()){
                    continue;
                }
                messages.add(msg);
            }
        }
        model.addAttribute("msgs", messages);
        model.addAttribute("message", new Message());
        return "privatemessage";
    }

    @RequestMapping("/privatemessageprocess")
    public String privateMessageProcess(Message message, @RequestParam("uid")long uId){
        User user = userRepository.findById(uId);
        message.setUsername(getUser());
        message.setSentFrom(getUser());
        message.setSentTo(user.getUsername());
        messageRepository.save(message);
        System.out.println("from privatemessageprocess: "+message);
        userRepository.save(user);
        return "redirect:/privatemessage/"+uId;
    }

}
