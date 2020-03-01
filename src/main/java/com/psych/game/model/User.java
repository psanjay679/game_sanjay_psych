package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Auditable {

    @Email
    @NotBlank
    @Column(unique = true)
    @Getter @Setter
    private String email;


    @NotBlank
    @Getter @Setter
    private String saltedHashPassword;

    @ManyToMany
    @Getter @Setter
    private Set<Role> roles = new HashSet<>();

}
