package com.tvsolutions.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "lastname", length = 50)
    private String lastname;
}
