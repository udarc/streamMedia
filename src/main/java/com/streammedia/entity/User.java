package com.streammedia.entity;
import lombok.*;
import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private int user_id;
    private String username;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private LocalDate date;
    private String picture;
    private String biography;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
