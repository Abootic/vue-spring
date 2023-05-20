package com.example.vuespring2.Mapper.Interface;

import com.example.vuespring2.Entities.Dto.UserDto;
import com.example.vuespring2.Entities.Model.Users;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    UserDto mapUserToUserDto(Users userEntity);
    Users mapUserDtoToUser(UserDto userDto);
}
