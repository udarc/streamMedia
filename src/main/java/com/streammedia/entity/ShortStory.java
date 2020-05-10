package com.streammedia.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Short story.
 */
@Table(name = "ShortStory")
@Entity(name = "ShortStory")
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class ShortStory {
    @Id
    @Column(name = "short_story_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int shortStoryId;
    private String title;
    private String author;
    private String cover;
    @Column(name = "publication_date")
    private LocalDateTime publicationDate;
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;
}
