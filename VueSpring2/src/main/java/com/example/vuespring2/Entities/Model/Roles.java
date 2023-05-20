package com.example.vuespring2.Entities.Model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Nonnull
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<Users> userEntity =new ArrayList<>();
}
