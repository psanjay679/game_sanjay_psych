package com.psych.game.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Game game;

    @ManyToOne
    @Getter
    @Setter
    @JsonIdentityReference
    private Question question;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Map<Player, PlayerAnswer> submittedAnswers = new HashMap<>();

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Map<Player, PlayerAnswer> selectedAnswers = new HashMap<>();

    @Getter
    @Setter
    @NotNull
    private int roundNumber;

    @Getter
    @Setter
    @ManyToOne()
    @JsonIdentityReference
    private EllenAnswer ellenAnswer;

}
