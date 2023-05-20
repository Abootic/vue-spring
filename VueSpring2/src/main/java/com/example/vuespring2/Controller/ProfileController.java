package com.example.vuespring2.Controller;

import com.example.vuespring2.Entities.Dto.ProfileDto;
import com.example.vuespring2.Wrapper.Impl.ResultGenericImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @PostMapping("/create-customer")
    @CrossOrigin("*")
    public ResponseEntity<?> createCustomer(@RequestBody ProfileDto profile){
        try {
            System.out.println("Name  " + profile.getName());
            System.out.println("username  " + profile.getUser_name());
            System.out.println("email  " + profile.getEmail());
            System.out.println("Address  " + profile.getAddress_en());
            System.out.println("TaskNumber  " + profile.getTax_number());
            System.out.println("phoneNumber  " + profile.getTax_number());
            var res=  ResultGenericImpl.successAsync("arrive successfully",200);
            return new ResponseEntity(res,HttpStatus.OK);
        }catch (Exception ex){
            var res=  ResultGenericImpl.successAsync(ex.getMessage(),500);
            return new ResponseEntity(res,HttpStatus.BAD_REQUEST);
        }
    }
}
