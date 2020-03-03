package com.streammedia.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Film.
 * @author Jeanne
 * https://www.baeldung.com/hibernate-many-to-many
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "Film")
@Table(name = "Film")
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private  int filmId;
    
//    @Column(name = "title", nullable = false)
    private String title;

//    @Column(name = "director")
    private String director;

//    @Column(name = "duration")
    private String duration;

//    @Column(name = "cover")
    private String cover;

    @Column(name= "pub_date", nullable = false)
    private LocalDate publicationDate;

//    @Column(name = "link")
    private String link;

    @Column(name = "video")
    private String video;

    @Column(name = "episode")
    private String episode;

    @Column(name = "summary", nullable = false)
    private String summary;


    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "FilmGenre",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    Set<Genre> genres = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "FilmCrew",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "crew_id") }
    )
    Set<Crew> crews = new HashSet<>();
    
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDate updatedAt;


    /**
     * Instantiates a new Film.
     */
    public Film() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
}
