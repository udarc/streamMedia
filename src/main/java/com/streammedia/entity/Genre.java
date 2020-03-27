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
@EqualsAndHashCode
@ToString
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
    @EqualsAndHashCode.Exclude
    private LocalDate createdAt;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Column(name = "updated_at",nullable = false)
    private LocalDate updatedAt;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "genres")
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
//
//    /**
//     * Remove film.
//     *
//     * @param film the film
//     */
//    public void removeFilm(Film film) {
//        this.films.remove(film);
//        film.getGenres().remove(this);
//    }
}
