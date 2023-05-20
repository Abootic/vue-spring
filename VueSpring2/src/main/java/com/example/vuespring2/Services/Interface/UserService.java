package com.example.vuespring2.Services.Interface;

import com.example.vuespring2.Entities.Dto.UserDto;
import com.example.vuespring2.Wrapper.Interface.ResultGeneric;

import java.util.List;

public interface UserService {
    ResultGeneric<UserDto>addUser(UserDto userDto);
    ResultGeneric<List<UserDto>> GetAll();
   // UserDetails loadUserByUsername(String username);
    ResultGeneric<UserDto>findById(Long id);
}
