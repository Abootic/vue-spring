package com.example.vuespring2.Entities.Dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Long id;

    private String name;
    private String user_name;
    private String email;

    private String  address_en;

    private  String tax_number;

    private String phone;
}
