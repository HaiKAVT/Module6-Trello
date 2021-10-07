package com.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 15)
    @Column(unique = true)
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column(unique = true)
    private String email;

    private String image;

    @NotEmpty
    private String nickname;

    private int phone;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

}
