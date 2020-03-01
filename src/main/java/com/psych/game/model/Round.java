package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="rounds")
public class Round extends Auditable {

    @ManyToOne
    @Getter
    @Setter
    private Game game;

    @ManyToOne
    @Getter
    @Setter
    private Question question;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    private Map<Player, PlayerAnswer> playerAnswers = new HashMap<>();

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    private Map<Player, PlayerAnswer> selectedAnswers = new HashMap<>();

    @Getter
    @Setter
    @NotNull
    private int roundNumber;

    @Getter
    @Setter
    @ManyToOne()
    private EllenAnswer ellenAnswer;

}
