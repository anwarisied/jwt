package com.rm.roadmaps.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email(message = "should be a correct email")
    @NotBlank(message = "email cannot be blank")
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "password cannot be blank")
    @NotNull
    @Column(nullable = false)
    private String password;
}
