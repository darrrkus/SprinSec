package com.tvsolutions.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username", length = 30, nullable = false)
    private String username;
    @Column(name = "password", length = 80, nullable = false)
    private String password;
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @ManyToMany(
//            fetch = FetchType.EAGER
    )
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


}
