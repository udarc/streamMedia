package com.streammedia.entity;

import lombok.*;


import java.time.*;

@Getter
@Setter
public class Role {
    private  int roleId;
    private String name;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
