package com.example.vuespring2.Services.Interface;

import com.example.vuespring2.Entities.Dto.RolesDto;

import com.example.vuespring2.Wrapper.Interface.ResultGeneric;

public interface RoleService {
    ResultGeneric<RolesDto> add(RolesDto roles);
}
