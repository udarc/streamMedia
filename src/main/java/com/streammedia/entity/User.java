package com.streammedia.entity;
import lombok.*;
import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String picture;
    private String gender;
    private String biography;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
