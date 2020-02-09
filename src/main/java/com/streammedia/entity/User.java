package com.streammedia.entity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.time.*;


/**
 * The type User.
 * https://docs.oracle.com/javaee/5/api/javax/persistence/Column.html
 */
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "created_at",nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at",nullable = false)
    private LocalDate updateAt;

    //https://www.baeldung.com/hibernate-one-to-many
}
