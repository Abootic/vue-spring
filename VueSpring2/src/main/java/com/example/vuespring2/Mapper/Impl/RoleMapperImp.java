package com.example.vuespring2.Mapper.Impl;

import com.example.vuespring2.Entities.Dto.RolesDto;
import com.example.vuespring2.Entities.Model.Roles;
import com.example.vuespring2.Mapper.Interface.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperImp implements RoleMapper {
    @Override
    public RolesDto mapRolesToRolesDto(Roles roles) {
        RolesDto rolesDto= RolesDto.builder().name(roles.getName()).build();
        return  rolesDto;
    }

    @Override
    public Roles mapRolesDtoToRoles(RolesDto rolesDto) {
        Roles roles = Roles.builder().name(rolesDto.getName()).build();
        return roles;
    }
}
