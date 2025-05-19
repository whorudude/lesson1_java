package org.example.web1.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DB extends JpaRepository<Person, Integer> {
    Person findByName(String name);
    Person findByLoginAndPassword(String login, String password);
    boolean existsByLogin(String login);
}
