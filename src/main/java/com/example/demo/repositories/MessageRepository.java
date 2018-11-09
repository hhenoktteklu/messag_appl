package com.example.demo.repositories;

import com.example.demo.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    Message findById(long id);
}
