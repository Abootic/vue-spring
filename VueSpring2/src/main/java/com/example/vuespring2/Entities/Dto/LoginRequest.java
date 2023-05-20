package com.example.vuespring2.Entities.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"username","password"})
public class LoginRequest {
    @JsonProperty("username")
    private  String username;
    @JsonProperty("password")
    private  String password;

}
