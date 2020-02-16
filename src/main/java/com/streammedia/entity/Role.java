package com.streammedia.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.time.*;
import java.util.Set;

@Getter
@Setter

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

    @Column(name = "username")
    private String username;
    @Column(name = "create_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

}
