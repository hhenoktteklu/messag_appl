package com.example.demo;

import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void run(String... args) throws Exception {


        Message message = new Message();
        message.setDate("11/10/2018");
        message.setSentBy("John");
        message.setText("Java programming is fun");
        messageRepository.save(message);

        Role userRole = new Role();
        userRole.setRole("USER");

        roleRepository.save(userRole);

        User user = new User();
        user.setEmail("John@gmail.com");
        user.setEnabled(true);
        user.setFirstName("John");
        user.setLastName("Dwell");
        user.setPassword("user");
        user.setUsername("user");
        user.setRoles(Arrays.asList(userRole));
        user.setMessages(Arrays.asList(message));

        userRepository.save(user);
    }
}
