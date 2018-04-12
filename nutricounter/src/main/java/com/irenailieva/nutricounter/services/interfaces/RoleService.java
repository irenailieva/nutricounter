package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    Role findByName(String roleName);

    void createNewRole(String roleName);

    void deleteRole(String chiefAdminRoleName);
}
