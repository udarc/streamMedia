package com.streammedia.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
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
@JsonIgnoreProperties({"films"})
public class Genre implements Serializable {
    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private  int genreId;
    @NotBlank(message = "Title is required!")
    @Size(min = 3, max = 100 ,message = "Title minlength 3, maxlength 100." )
    private String title;

    @NotNull(message = "Description is required!")
    @Size(min = 4, max = 2000, message = "Description minlength 4, maxlength 2000." )
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Column(name = "updated_at",nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "genres")
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
