package com.example.vuespring2.Services.Impl;

import com.example.vuespring2.Entities.Dto.UserDto;
import com.example.vuespring2.Entities.Model.Users;
import com.example.vuespring2.Mapper.Interface.UserMapper;
import com.example.vuespring2.Repositories.Interface.UserRepository;
import com.example.vuespring2.Services.Interface.UserService;
import com.example.vuespring2.Wrapper.Interface.ResultGeneric;
import com.example.vuespring2.Wrapper.Impl.ResultGenericImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository _userRepository;

    private  UserMapper _userMapper;
    private PasswordEncoder _passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper,PasswordEncoder passwordEncoder) {
        _userRepository = userRepository;
        _userMapper=userMapper;
        _passwordEncoder=passwordEncoder;
    }
    // private com.example.vuespring2.Ioc.RoleRepository _roleRepository;




  /*  public UserServiceImpl(com.example.vuespring2.Ioc.UserRepository userRepository,com.example.vuespring2.Ioc.RoleRepository roleRepository,PasswordEncoder passwordEncoder){
        _userRepository=userRepository;
        _roleRepository=roleRepository;
        _passwordEncoder=passwordEncoder;

    }
*/


    @Override
    public ResultGeneric<UserDto> addUser(UserDto userDto)  {
        try {
            Users userEntity =_userMapper.mapUserDtoToUser(userDto);

            String pass=_passwordEncoder.encode(userDto.getPassword());
            userEntity.setPassword(pass);

          /*  var cheakuser=_userRepository.findByUsername(userEntity.getUsername());
            if (cheakuser!=null)  return   ResultGenericImpl.fail("user already exsit",500);*/
           // com.example.vuespring2.Ioc.Role role=_roleRepository.findByname(userDto.getUserType());
          /*  if(role!=null){
                userEntity.setRoles(Arrays.asList(role));
            }*/
            var res= _userRepository.save(userEntity);

            if(res!=null){
                var map=_userMapper.mapUserToUserDto(res);
                return ResultGenericImpl.success(map);
            }
            return   ResultGenericImpl.fail("user not add",500);
        }catch (Exception e){
            return    ResultGenericImpl.fail("something error in adduser  "+e.getMessage(),500);
        }

    }

    @Override
    public ResultGeneric<List<UserDto>> GetAll() {
        List<Users> userEntity =_userRepository.findAll();
        var res=  userEntity.stream().map((u) ->_userMapper.mapUserToUserDto(u)).collect(Collectors.toList());
        if(res.isEmpty()){
            return   ResultGenericImpl.fail("no user data",500);
        }
        return ResultGenericImpl.success(res);
    }




  /*  @Override
    public UserEntity loadUserByUsername(String username) {

        UserEntity user=_userRepository.findByUsername(username);

        if (user!=null) {
            var authuser=new com.example.vuespring2.Ioc.User(
                    user.getUsername(),
                    user.getPassword(),
//                   user.getAuthorities()


                    user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
            return user;
        }
        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        throw new UsernameNotFoundException("invalid username or passssssssswsord");
    }*/

    @Override
    public ResultGeneric<UserDto> findById(Long id) {
        try {
            Users user=_userRepository.findById(id).get();
            if(user!=null){
                UserDto userDto =_userMapper.mapUserToUserDto(user);
                return  ResultGenericImpl.success(userDto);
            }
            return   ResultGenericImpl.fail("user not Exit",500);

        }catch (Exception ex){
            return   ResultGenericImpl.fail("something error "+ex.getMessage(),500);
        }
    }
}
