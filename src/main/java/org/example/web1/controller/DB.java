package org.example.web1.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DB extends JpaRepository<Person, Integer> {

    Optional<Person> findByName(String name);

    Person findByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);

}
