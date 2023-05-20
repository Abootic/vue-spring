package com.example.vuespring2.Mapper.Interface;

import com.example.vuespring2.Entities.Dto.RolesDto;

import com.example.vuespring2.Entities.Model.Roles;

public interface RoleMapper {
    RolesDto mapRolesToRolesDto(Roles roles);
    Roles mapRolesDtoToRoles(RolesDto rolesDto);
}
