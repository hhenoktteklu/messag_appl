package com.example.demo.repositories;

import com.example.demo.entities.Message;
import com.example.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
