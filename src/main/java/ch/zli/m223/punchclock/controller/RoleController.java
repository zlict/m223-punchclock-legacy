package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.service.RoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}
