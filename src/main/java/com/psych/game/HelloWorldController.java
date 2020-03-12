package com.psych.game;


import com.psych.game.model.Game;
import com.psych.game.model.GameMode;
import com.psych.game.model.Player;
import com.psych.game.model.Question;
import com.psych.game.repositories.GameRepository;
import com.psych.game.repositories.QuestionRepository;
import com.psych.game.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev-test")
public class HelloWorldController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/populate")
    public String populateDB() {

        Player sanjay = new Player.Builder().setAlias("sanjay")
                .setEmail("sanjay.panchal@email3.com")
                .setPicURL("picURL")
                .setSaltedHashPassword("ThisisSalted")
                .setPsychFaceURL("This is Face").build();
        playerRepository.save(sanjay);
//
//        Player sanjay2 = new Player.Builder().setAlias("sanjay2")
//                .setEmail("sanjay.panchal2@email.com")
//                .setPicURL("picURL2")
//                .setSaltedHashPassword("ThisisSalted2")
//                .setPsychFaceURL("This is Face2").build();
//        playerRepository.save(sanjay2);

//        questionRepository.save(new Question(
//                "What is most important psych",
//                "No Answer is correct",
//                GameMode.IS_THIS_HISTORY
//        ));

        Game game = new Game();
        game.setGameMode(GameMode.IS_THIS_HISTORY);
        game.setLeader(sanjay);
        game.getPlayers().add(sanjay);
        gameRepository.save(game);

        return "Populated data";
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable(name="id") Long id) {
        return questionRepository.findById(id).orElseThrow();
    }
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("games/{id}")
    public Game getGameById(@PathVariable(name="id") Long id) {
        return gameRepository.findById(id).orElseThrow();
    }
    @GetMapping("/")
    public String helloWorld () {
        return "Hello, World";
    }
}
