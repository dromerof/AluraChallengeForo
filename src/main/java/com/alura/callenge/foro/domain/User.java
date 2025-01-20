package com.alura.callenge.foro.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;
}
