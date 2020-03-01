package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name="games")
public class Game extends Auditable {

    @ManyToMany
    @Getter
    @Setter
    private Set<Player> players = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @NotNull
    @Getter
    @Setter
    private GameMode gameMode;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    List<Round> rounds = new ArrayList<>();

    @Getter
    @Setter
    private Boolean hasEllen = false;

    @NotNull
    @Getter
    @Setter
    @ManyToOne
    private Player leader;

    @ManyToMany(cascade = CascadeType.ALL)
    private Map<Player, Stat> playerStats = new HashMap<>();

    @Getter
    @Setter
    private int numRounds = 10;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private GameStatus gameStatus;

    @ManyToMany
    @Getter
    @Setter
    private Set<Player> readyPlayers = new HashSet<>();
}
