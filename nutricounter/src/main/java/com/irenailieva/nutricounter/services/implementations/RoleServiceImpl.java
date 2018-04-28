package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.Role;
import com.irenailieva.nutricounter.repositories.RoleRepository;
import com.irenailieva.nutricounter.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role findByName(String roleName) {
        return this.roleRepository.findFirstByName(roleName);
    }

    @Override
    public Role createNewRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return this.roleRepository.saveAndFlush(role);
    }
}
