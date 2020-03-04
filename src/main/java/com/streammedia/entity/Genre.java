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
 * The type Genre.
 *
 * @author Jeanne
 * @version 1.0
 */
@Getter
@Setter
@Entity(name = "Genre")
@Table(name = "Genre")
public class Genre {
    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private  int genreId;
    private String title;
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDate updatedAt;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "genres")
    private Set<Film> films = new HashSet<>();

    /**
     * Add film.
     * https://thoughts-on-java.org/hibernate-tips-map-bidirectional-many-many-association/
     * @param film the film
     */
    public void addFilm(Film film) {
        this.films.add(film);
        film.getGenres().add(this);
    }

    /**
     * Remove film.
     *
     * @param film the film
     */
    public void removeFilm(Film film) {
        this.films.remove(film);
        film.getGenres().remove(this);
    }
}
