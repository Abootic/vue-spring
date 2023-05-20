package com.example.vuespring2.Services.Impl;

import com.example.vuespring2.Entities.Dto.RolesDto;

import com.example.vuespring2.Mapper.Interface.RoleMapper;
import com.example.vuespring2.Repositories.Interface.RoleRepository;
import com.example.vuespring2.Services.Interface.RoleService;
import com.example.vuespring2.Wrapper.Interface.ResultGeneric;
import com.example.vuespring2.Wrapper.Impl.ResultGenericImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository _roleRepository;

    private final RoleMapper _roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,RoleMapper roleMapper) {
        _roleRepository = roleRepository;
        _roleMapper=roleMapper;
    }

    @Override
    public ResultGeneric<RolesDto> add(RolesDto rolesDto) {
        try{

            var res=_roleRepository.save(_roleMapper.mapRolesDtoToRoles(rolesDto));
            return ResultGenericImpl.success("added successfully",200);
        }catch (Exception ex){
            return ResultGenericImpl.fail(ex.getMessage(),500);
        }
    }
}
