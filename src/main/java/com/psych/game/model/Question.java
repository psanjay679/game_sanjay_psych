package com.psych.game.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="questions")
public class Question extends Auditable{

    @NotNull
    @Getter @Setter
    private String question;

    @NotNull
    @Getter @Setter
    private String correctAnswer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @Getter @Setter
    @JsonManagedReference
    private Set<EllenAnswer> ellenAnswers = new HashSet<>();

    @NotNull
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private GameMode gameMode;

    public Question(){}

    public Question(String question, String correctAnswer, GameMode gameMode) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.gameMode = gameMode;
    }
}
