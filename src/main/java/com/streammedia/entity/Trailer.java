package com.streammedia.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@EqualsAndHashCode
public class Trailer {
    @Id
    @Column(name = "trailer_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int trailerId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "duration")
    private String duration;

    @Column(name = "cover")
    private String cover;

    @Column(name= "pub_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "links")
    private String links;

    @Column(name = "video")
    private String video;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "created_at" , nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "username",referencedColumnName = "username",nullable = false)
    private User user;
}
