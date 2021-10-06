package com.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    @NotEmpty(message = "not null")
    @Size(min = 2,max = 10,message = "name > 2 and < 10")
    @Column(unique = true)
    private String userName;
    @NotEmpty(message = "not null")
    @Size(min = 6,message = "password greater than 6")
    private String password;
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-z]+\\.[a-z]+$", message = "wrong email")
    private String email;
    private String image;
    @Size(min = 3,message = "nick name greater than 3")
    private String nickName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;



}
