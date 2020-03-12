package com.psych.game.model;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="players")
public class Player extends User {

    @NotBlank
    @Getter
    @Setter
    private String alias;

    @Getter
    @Setter
    private String psychFaceURL;

    @Getter
    @Setter
    private String picURL;

    @Getter
    @Setter
    @OneToOne(cascade=CascadeType.ALL)
    @JsonManagedReference
    private Stat stat = new Stat();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "players")
    @JsonIdentityReference
    private Set<Game> games = new HashSet<>();

    public Player(){}

    private Player(Builder builder) {
        setEmail(builder.email);
        setAlias(builder.alias);
        setPsychFaceURL(builder.psychFaceURL);
        setPicURL(builder.picURL);
        setSaltedHashPassword(builder.saltedHashPassword);
    }

    public static class Builder {

        private @Email @NotBlank String email;
        private @NotBlank String alias;
        private String psychFaceURL;
        private String picURL;
        private @NotBlank  String saltedHashPassword;

        public Builder () {}

        public Builder setEmail (String email) {
            this.email = email;
            return this;
        }

        public Builder setAlias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder setPsychFaceURL(String psychFaceURL) {
            this.psychFaceURL = psychFaceURL;
            return this;
        }

        public Builder setPicURL(String picURL) {
            this.picURL = picURL;
            return this;
        }

        public Builder setSaltedHashPassword(String saltedHashPassword) {
            this.saltedHashPassword = saltedHashPassword;
            return this;
        }

        public Player build() {
            Player player = new Player(this);
            return player;
        }
    }

}
