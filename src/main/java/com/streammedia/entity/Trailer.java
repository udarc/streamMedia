package com.streammedia.entity;

import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The type Trailer.
 * @author Jeanne
 */
//Lombok annotations
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
//Hibernate Annotations
@Entity(name = "Trailer")
@Table(name = "Trailer",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"title", "author"})
)
public class Trailer {
    @Id
    @Column(name = "trailer_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int trailerId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "duration")
    private LocalTime duration;

    @Column(name = "cover")
    private String cover;

    @Column(name= "pub_date", nullable = false)
//    @EqualsAndHashCode.Exclude TODO add it back for testing
    private LocalDateTime publicationDate;

    @Column(name = "links")
    private String link;

    @Column(name = "video")
    private String video;

    @Column(name = "summary", nullable = false)
    private String summary;

    @CreationTimestamp
    @Column(name = "created_at")
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")

    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public Trailer(String title, String author, LocalTime duration, LocalDateTime publicationDate, String summary, LocalDateTime createdAt, LocalDateTime updatedAt, User user) {
        this.title = title;
        this.author = author;
        this.duration = duration;
        this.publicationDate = publicationDate;
        this.summary = summary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }
}
