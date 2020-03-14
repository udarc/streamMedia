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
import java.time.LocalDateTime;

/**
 * The type FAQ.
 *
 * @author Jeanne
 * @version 1.0
 * @since 2020-2-28
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "FAQ")
@Table(name = "faq")
public class FAQ {
    @Id
    @Column(name = "faq_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int faqId;


    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;
    @Column(name = "description")
    private String description;


    //    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;


    /**
     * Instantiates a new Faq.
     */
    public FAQ() {
    }

    /**
     * Instantiates a new Faq.
     *
     * @param title       the title
     * @param category    the category
     * @param description the description
     * @param createdAt   the created at
     * @param user        the user
     */
    public FAQ(String title, String category, String description, LocalDateTime createdAt, User user) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.createdAt = createdAt;
        this.user = user;
    }
}