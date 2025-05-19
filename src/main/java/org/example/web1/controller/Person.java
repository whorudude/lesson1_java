package org.example.web1.controller;

import jakarta.persistence.*;

@Entity
@Table(name = "Persons")
public class Person {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "PersonID")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Age")
    private int age;
    //делаем регистрацию, добавить еще переменные для лог/пар

    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
