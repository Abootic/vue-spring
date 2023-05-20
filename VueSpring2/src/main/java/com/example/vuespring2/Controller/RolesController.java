package com.example.vuespring2.Controller;

import com.example.vuespring2.Entities.Dto.RolesDto;
import com.example.vuespring2.Services.Interface.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Account")
public class RolesController {
    @Autowired
    private final RoleService _roleService;

    public RolesController(RoleService roleService) {
        _roleService = roleService;
    }
    @PostMapping("/AddRole")
    @Operation(
            description = "RequsetApi",
            responses = {
                    @ApiResponse(responseCode = "400",ref = "badRequestAPI"),
                    @ApiResponse(responseCode = "500",ref = "internalServerErrorAPI"),
                    @ApiResponse(responseCode = "200",ref = "successFully"),
            }
    )
    public ResponseEntity Add(@RequestBody RolesDto entity){
        var res=_roleService.add(entity);
        if(res.getStatus().getSuccess()){
            return new ResponseEntity(res.getStatus().getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity(res.getStatus().getMessage(), HttpStatus.BAD_REQUEST);
    }
}
