package com.iudigital.appbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String full_name;
        private String username;
        private String email;
        private String password;
        private String created_at;
        private String updated_at;



        @NotBlank
        @Column(columnDefinition = "varchar(30) default 'ACTIVE'")
        @Enumerated(value = EnumType.STRING)
        private Status status;

        @NotBlank
        @Column(columnDefinition = "varchar(30) default 'USER'")
        @Enumerated(value = EnumType.STRING)
        private Role role;





}
