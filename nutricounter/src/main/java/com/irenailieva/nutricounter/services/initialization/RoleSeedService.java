package com.irenailieva.nutricounter.services.initialization;

import com.irenailieva.nutricounter.entities.Role;
import com.irenailieva.nutricounter.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleSeedService {

    private static final String[] ROLE_NAMES = {"CHIEF ADMIN", "ADMIN", "USER"};

    private final RoleService roleService;

    @Autowired
    public RoleSeedService(RoleService roleService) {
        this.roleService = roleService;
    }

    @EventListener
    public List<Role> onApplicationEvent(ContextRefreshedEvent event) {

        List<Role> allRoles = this.roleService.findAllRoles();

        if (allRoles.size() == 0) {
            for (String roleName : ROLE_NAMES) {
                this.roleService.createNewRole(roleName);
            }
        }

        return this.roleService.findAllRoles();
    }
}
