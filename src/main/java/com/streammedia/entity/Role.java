package com.streammedia.entity;

import lombok.*;


import java.time.*;

@Getter
@Setter
public class Role {
    private  int role_id;
    private String name;
    private LocalDate createdAt;
    private LocalDate updated_at;
}
