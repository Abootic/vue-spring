package com.example.vuespring2.Entities.Dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {
    @Nullable
    private Long id;
    @NotEmpty(message = "enter the name")

    private  String name;
    @NotEmpty(message = "enter the username")
    private String username;
    @Nullable
    private String userType;
    @NotEmpty(message = "enter the email")
    private String email;
    @NotEmpty(message = "enter the password")
    private String password;
    @NotEmpty(message = "enter the gender")
    private String gender;
    @Nullable
    private String image;
}