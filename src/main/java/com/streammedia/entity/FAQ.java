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

/**
 * The type FAQ.
 * @author Jeanne
 */
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
@Entity(name = "FAQ")
@Table(name = "faq")
public class FAQ {
    @Id
    @Column(name = "faq_id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private  int faqId;


    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;
    @Column(name = "description")
    private String description;


//    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
//    @EqualsAndHashCode.Exclude
    @Column(name = "updated_at",nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

//    /**
//     * Instantiates a new Faq.
//     */
//    public FAQ() {
//        this.createdAt = LocalDate.now();
//        this.updatedAt = LocalDate.now();
//    }
}
