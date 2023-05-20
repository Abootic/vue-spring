package com.example.vuespring2.Repositories.Interface;

import com.example.vuespring2.Entities.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
   Optional<Users>  findByEmail(String email);

    Optional<Users>   findByUsername(String username);
}
