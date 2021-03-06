package com.streammedia.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Film.
 * https://thoughts-on-java.org/ultimate-guide-association-mappings-jpa-hibernate/
 *
 * @author Jeanne https://www.baeldung.com/hibernate-many-to-many
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

    private String title;

    private String director;

    private LocalTime duration;

    private String cover;

    @Column(name= "pub_date")
    private LocalDateTime publicationDate;

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
    /**
     * The Genres.
     */
    @ManyToMany(fetch=FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "FilmGenre",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Genre> genres = new HashSet<>();

    /**
     * The Crews.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH })
    @JoinTable(
            name = "FilmCrew",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "crew_id") }
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Crew> crews = new HashSet<>();
    
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDate updatedAt;

    /**
     * Add crew.
     *
     * @param crew the crew
     */
    public void addCrew(Crew crew) {
        this.crews.add(crew);
        crew.getFilms().add(this);
    }

    /**
     * Remove crew.
     *
     * @param crew the crew
     */
    public void removeCrew(Crew crew) {
        this.crews.remove(crew);
        crew.getFilms().remove(this);
    }

    /**
     * Add genre.
     *
     * @param genre the genre
     */
    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getFilms().add(this);
    }

    /**
     * Remove genre.
     *
     * @param genre the genre
     */
    public void removeGenre(Genre genre) {
            genres.remove(genre);
            genre.getFilms().remove(this);
        }

}
