package com.example.vuespring2.Mapper.Impl;

import com.example.vuespring2.Entities.Dto.UserDto;
import com.example.vuespring2.Entities.Model.Users;
import com.example.vuespring2.Mapper.Interface.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserMapperImpl implements UserMapper {
    public Users mapUserDtoToUser(UserDto userDto){
        Users userEntity = new Users();
        userEntity.setName(userDto.getName());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setGender(userDto.getGender());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setUserType(userDto.getUserType());
        userEntity.setCreateAt(LocalDateTime.now());
        userEntity.setImage(userDto.getImage());
        return userEntity;
    }
    public   UserDto mapUserToUserDto(Users userEntity){
        UserDto userDto=    UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .userType(userEntity.getUserType())
                .name(userEntity.getName())
                .gender(userEntity.getGender())
                .password(userEntity.getPassword())
                .username(userEntity.getUsername())
                .image(userEntity.getImage())


                .build();
        return  userDto;
    }
}
