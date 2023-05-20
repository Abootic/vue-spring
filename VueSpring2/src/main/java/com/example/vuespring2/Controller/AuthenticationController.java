package com.example.vuespring2.Controller;
import com.example.vuespring2.Entities.Dto.LoginRequest;
import com.example.vuespring2.Wrapper.Interface.ResultGeneric;
import com.example.vuespring2.Wrapper.Impl.ResultGenericImpl;
import com.example.vuespring2.config.Jwt.JwtServices.JwtServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {
private  final JwtServices _jwtServices;
private final UserDetailsService _userDetails;
private  final AuthenticationManager _authenticationManager;
@Autowired
    public AuthenticationController(JwtServices jwtServices,AuthenticationManager authenticationManager,UserDetailsService userDetails) {
        _jwtServices = jwtServices;
        _authenticationManager=authenticationManager;
        _userDetails=userDetails;
    }


/*
   @Operation(
           description = "RequsetApi",
           responses = {
                   @ApiResponse(responseCode = "400",ref = "badRequestAPI"),
                   @ApiResponse(responseCode = "500",ref = "internalServerErrorAPI"),
                   @ApiResponse(responseCode = "200",ref = "successFully"),
                }
   )
*/
@PostMapping("/signIn")

  public ResponseEntity signIn(@RequestBody LoginRequest request){
 /*   try{*/
var u=new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());

var r=_authenticationManager.authenticate(u);

var userD= _userDetails.loadUserByUsername(request.getUsername());

    final String token =_jwtServices.generateToken(userD);

//_jwtServices.generateToken(u.getPrincipal().getClass();))
return new ResponseEntity( token,HttpStatus.OK);
 /*   }catch (Exception e){
        return new  ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

    }*/


    }

    /*@PostMapping("/profile")
    @Operation(
            description = "RequsetApi",
            responses = {
                    @ApiResponse(responseCode = "400",ref = "badRequestAPI"),
                    @ApiResponse(responseCode = "500",ref = "internalServerErrorAPI"),
                    @ApiResponse(responseCode = "200",ref = "successFully"),
            }
    )
    @CrossOrigin("*")
    public ResponseEntity<?>Profile(@RequestBody Profile profile){
       System.out.println("Name  "+profile.getName());
       System.out.println("Address  "+profile.getAddress());
       System.out.println("TaskNumber  "+profile.getTaskNumber());
       System.out.println("phoneNumber  "+profile.getTaskNumber());
        return new ResponseEntity<>(new WebResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "SuccessFully sign in"
        ),HttpStatus.OK
        );
    }*/
}
