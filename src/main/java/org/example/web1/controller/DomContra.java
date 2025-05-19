package org.example.web1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class DomContra {

    // 1. Создаёшь таблицу используя SQL
    // 2. Создаёшь образ таблицы в Джаве, ещё один файл с аннотацией @Entity
    // 3. Создаёшь интерфейс (ещё один специальный класс) с аннотацией @Repository что будет отвечать именно за работу с базой
    // 4. Декларируешь в этом классе тот самый интерфейс с @Autowired и через него сохраняешь/достаёшь из базы

    // Вместо этого у тебя будет база данных, по сути ты просто задекларируешь здесь сам обьект (без =)
    // И используя аннотацию @Autowired ты заставишь спринг его внутри задекларировать самому
    List<Person> persons = new ArrayList<>();

    @Autowired
    public DB baza;


    @PostMapping("registration")
    public String registration(@RequestBody Person person) throws NoSuchAlgorithmException {
        if (baza.existsByLogin(person.getLogin())) {
            return "Login already exists";
        }
        else {
            String sha3Hex = hash(person.getPassword());

            person.setPassword(sha3Hex);

            baza.save(person);
            return "Registration successful";
        }
    }

    private static String hash(String password) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashbytes);
        return sha3Hex;
    }

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password) throws NoSuchAlgorithmException {
        Person person = baza.findByLoginAndPassword(login, hash(password));
        if (person != null) {
            return "Salom, " + person.getName();
        }
        else {
            return "Invalid login or password";
        }
    }

    @GetMapping("/find")
    public String findByName(@RequestParam String name) {
        Optional<Person> person = baza.findByName(name);
        if (person.isPresent()) {
            return "found " + person.get().getName() + " age " + person.get().getAge();
        }
        else {
            return "not found";
        }
    }

    // сосёшь
    @GetMapping("/home")
    public String home(@RequestParam("place") String place) {
        return "пошла сосать " + place;
    }

    // сохраняешь здесь обьект в базу данных
    @PostMapping("/razvrat")
    public void razvrat(@RequestBody Person person) {
        baza.save(person);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Integer id) {
        baza.deleteById(id);
        return "user is udalёn " + id;
    }

    // выводишь из базы данных
    @GetMapping("/all")
    public List<Person> getAll() {
        return baza.findAll();
    }
}
