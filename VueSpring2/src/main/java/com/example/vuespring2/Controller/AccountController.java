package com.example.vuespring2.Controller;

import com.example.vuespring2.Entities.Dto.UserDto;
import com.example.vuespring2.Services.Interface.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Account")
@SecurityRequirement(name = "Bearer")

public class AccountController {
    private UserService _userService;
    public AccountController(UserService userService){
        _userService=userService;
    }
    @GetMapping("/GetAll")
    public ResponseEntity GatAll(){
        try{
            var res=_userService.GetAll();
            if(res.getStatus().getSuccess()){
                return new ResponseEntity(res.getData(), HttpStatus.OK);
            }
            return new ResponseEntity(res.getStatus().getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/Register")

    public ResponseEntity Register(@RequestBody UserDto entity){
try{
    var res=_userService.addUser(entity);
    if(res.getStatus().getSuccess()){
        return new ResponseEntity(res.getData(), HttpStatus.OK);
    }
    return new ResponseEntity(res.getStatus().getMessage(),HttpStatus.BAD_REQUEST);

}catch (Exception ex){
    return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
}
    }
}
