package com.tvsolutions.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", length = 100)
    private String title;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "description")
    private String Description;
    @ManyToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Collection<Author> authors;

}
