package com.example.demo;

import com.example.demo.entities.Message;
import com.example.demo.entities.Post;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.PostRepository;
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
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {


        Role userRole = new Role();
        userRole.setRole("USER");

        roleRepository.save(userRole);

        User user = new User();
        user.setEmail("adam@gmail.com");
        user.setEnabled(true);
        user.setFirstName("Adam");
        user.setLastName("Sandler");
        user.setPassword("Adam");
        user.setUsername("Adam");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        User user2 = new User();
        user2.setEmail("aidan@gmail.com");
        user2.setEnabled(true);
        user2.setFirstName("Aidan");
        user2.setLastName("Gillen");
        user2.setPassword("Aidan");
        user2.setUsername("Aidan");
        user2.setRoles(Arrays.asList(userRole));
        userRepository.save(user2);

        User user3 = new User();
        user3.setEmail("al@gmail.com");
        user3.setEnabled(true);
        user3.setFirstName("Al");
        user3.setLastName("Pacino");
        user3.setPassword("Al");
        user3.setUsername("Al");
        user3.setRoles(Arrays.asList(userRole));
        userRepository.save(user3);

        User user4 = new User();
        user4.setEmail("bruce@gmail.com");
        user4.setEnabled(true);
        user4.setFirstName("Bruce");
        user4.setLastName("Willis");
        user4.setPassword("Bruce");
        user4.setUsername("Bruce");
        user4.setRoles(Arrays.asList(userRole));
        userRepository.save(user4);

        User user5 = new User();
        user5.setEmail("chris@gmail.com");
        user5.setEnabled(true);
        user5.setFirstName("Chris");
        user5.setLastName("Tucker");
        user5.setPassword("Chris");
        user5.setUsername("Chris");
        user5.setRoles(Arrays.asList(userRole));
        userRepository.save(user5);

        User user6 = new User();
        user6.setEmail("denzel@gmail.com");
        user6.setEnabled(true);
        user6.setFirstName("Denzel");
        user6.setLastName("Washington");
        user6.setPassword("Denzel");
        user6.setUsername("Denzel");
        user6.setRoles(Arrays.asList(userRole));
        userRepository.save(user6);

        Post post = new Post();
        post.setUser(user);
        post.setText("Adam Richard Sandler was born September 9, 1966 in Brooklyn, New York, to Judith (Levine), a teacher at a nursery school, and Stanley Alan Sandler");
        postRepository.save(post);

        Post post1 = new Post();
        post1.setUser(user2);
        post1.setText("Aidan Gillen is an Irish actor. He is best known for portraying Petyr \"Littlefinger\" Baelish in the HBO series");
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setUser(user3);
        post2.setText("One of the greatest actors in all of film history, Al Pacino established himself during one of cinema's most vibrant decades");
        postRepository.save(post2);

        Post post3 = new Post();
        post3.setUser(user4);
        post3.setText("Actor and musician Bruce Willis is well known for playing wisecracking or hard-edged characters, often in spectacular action films.");
        postRepository.save(post3);

        Post post4 = new Post();
        post4.setUser(user5);
        post4.setText("Tucker was born in Atlanta, Georgia, to Mary Louise (Bryant) and Norris Tucker, who owned a janitorial service.");
        postRepository.save(post4);

        Post post5 = new Post();
        post5.setUser(user6);
        post5.setText("Denzel Hayes Washington, Jr. was born on December 28, 1954 in Mount Vernon, New York. He is the middle of three children of a beautician mother, Lennis, from Georgia.");
        postRepository.save(post5);




    }
}
