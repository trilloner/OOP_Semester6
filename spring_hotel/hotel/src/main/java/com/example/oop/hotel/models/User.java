package com.example.oop.hotel.models;

import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "email", unique = true)
    private String email;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    public User setRoleAndPassword(Roles role, String password) {
        this.setPassword(password);
        this.setRole(role);
        return this;
    }
}
