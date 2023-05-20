package com.example.vuespring2.Entities.Dto;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RolesDto {
    @Nonnull
    private String name;
}
