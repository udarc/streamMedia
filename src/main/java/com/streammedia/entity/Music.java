package com.streammedia.entity;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.*;

/**
 * The type Music.
 */
@Table(name = "Music")
@Entity(name = "Music")
@Data
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int musicId;

    private  String title;
    @Column(name = "music_video")
    private String video;
    @Column(name = "music_cover")
    private String cover;
    private String artist;
    private LocalTime duration;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
}
