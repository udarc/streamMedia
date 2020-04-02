package com.streammedia.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.ejb.EJBs;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Book.
 * https://howtoprogramwithjava.com/hibernate-manytomany-unidirectional-bidirectional/
 */
@Table(name = "Book")
@Entity(name = "Book")
@Getter
@Setter
@EqualsAndHashCode
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int bookId;
    private  String title;
    @Column(name = "isbn")
    private  String ISBN;
    private  String author;

    @Column(name = "pub_date")
    private LocalDateTime publicationDate;
    private String edition;
    private String cover;
    private String summary;
    private String publisher;
    @Column(name = "page_number")
    private int pageNumber;

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
