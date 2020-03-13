package com.psych.game.controller;


import com.psych.game.model.*;
import com.psych.game.repositories.GameRepository;
import com.psych.game.repositories.PlayerRepository;
import com.psych.game.repositories.QuestionRepository;
import com.psych.game.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev-test")
public class DevTestController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/populate")
    public String populateDB() {

        for (Player player: playerRepository.findAll()) {
            player.getGames().clear();
            playerRepository.save(player);
        }

        gameRepository.deleteAll();
        playerRepository.deleteAll();
        questionRepository.deleteAll();

        Player sanjay = new Player.Builder().setAlias("sanjay")
                .setEmail("sanjay.panchal@email3.com")
                .setPicURL("picURL")
                .setSaltedHashPassword("ThisisSalted")
                .setPsychFaceURL("This is Face").build();
        playerRepository.save(sanjay);

        Player sanjay2 = new Player.Builder().setAlias("sanjay2")
                .setEmail("sanjay.panchal2@email.com")
                .setPicURL("picURL2")
                .setSaltedHashPassword("ThisisSalted2")
                .setPsychFaceURL("This is Face2").build();
        playerRepository.save(sanjay2);

        questionRepository.save(new Question(
                "What is most important psych",
                "No Answer is correct",
                GameMode.IS_THIS_HISTORY
        ));

        Game game = new Game();
        game.setGameMode(GameMode.IS_THIS_HISTORY);
        game.setLeader(sanjay);
        game.getPlayers().add(sanjay);
        gameRepository.save(game);

        return "Populated data";
    }

    // for players

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // for questions

    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable(name="id") Long id) {
        return questionRepository.findById(id).orElseThrow();
    }

    // for users

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(name="id") Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    // for games

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable(name="id") Long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    @GetMapping("/")
    public String helloWorld () {
        return "Hello, World";
    }
}
