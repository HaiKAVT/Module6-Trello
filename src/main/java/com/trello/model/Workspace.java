package com.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String type;

    private String privacy;

    @ManyToOne
    private User owner;

    @OneToMany
    private Set<MemberWorkspace> members;

    @OneToMany
    private Set<Board> boards;

    private String privacy;
}
