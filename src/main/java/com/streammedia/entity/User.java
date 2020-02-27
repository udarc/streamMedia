package com.streammedia.entity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * The type User.
 * https://docs.oracle.com/javaee/5/api/javax/persistence/Column.html
 * @author Jeanne
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "User")
@Table(name = "SM_Users")
public class User implements Serializable {


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int userId;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "picture")
    private String picture;

    @Column(name = "gender")
    private String gender;

    @Column(name = "biography")
    private String biography;

    @Column(name = "created_at",nullable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private LocalDate createdAt;

    @Column(name = "updated_at",nullable = false)
    @EqualsAndHashCode.Exclude
    private LocalDate updateAt;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<Role>();

    //Trailers
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Trailer> trailers = new HashSet<>();

    //Films
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Film> films = new HashSet<>();

    //FAQ
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<FAQ> faqs = new HashSet<>();



    /**
     * Instantiates a new User.
     *
     * @param userName  the user name
     * @param email     the email
     * @param password  the password
     * @param createdAt the created at
     * @param updateAt  the update at
     */
    public User(String userName, String email, String password, LocalDate createdAt, LocalDate updateAt) {
        this.username = userName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updateAt = LocalDate.now();

    }

    /**
     * Add role.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        roles.add(role);
    }
    public void removeRole(Role role){
        roles.remove(role);
    }
    //https://www.baeldung.com/hibernate-one-to-many
}
