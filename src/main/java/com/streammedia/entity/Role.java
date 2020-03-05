package com.streammedia.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.*;
import java.util.Set;

/**
 * The type Role.
 * @author Jeanne
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "Role")
@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private  int roleId;


    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "username",referencedColumnName = "username",nullable = false)
    private User user;

//    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
//    @EqualsAndHashCode.Exclude
    @Column(name = "updated_at")
    private LocalDate updatedAt;

}
