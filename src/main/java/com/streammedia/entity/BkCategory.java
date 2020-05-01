package com.streammedia.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name="BKCategory")
@Entity(name = "BKCategory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BkCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "bkCategory_id")
    private int bkCategoryId;

    private String title;
    private String description;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

    /**
     * Add book.
     *
     * @param book the book
     */
    public void addBook(Book book) {
        this.books.add(book);
        book.getCategories().add(this);
    }

    /**
     * Remove book.
     *
     * @param book the book
     */
    public void removeBook(Book book) {
        this.books.remove(book);
        book.getCategories().remove(this);
    }
}
