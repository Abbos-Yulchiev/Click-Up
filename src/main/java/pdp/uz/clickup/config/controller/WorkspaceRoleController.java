package pdp.uz.clickup.config.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.clickup.service.WorkspaceRoleService;

@RestController
@RequestMapping("/workspaceRole")
public class WorkspaceRoleController {

    WorkspaceRoleService workspaceRoleService;

    public WorkspaceRoleController(WorkspaceRoleService workspaceRoleService) {
        this.workspaceRoleService = workspaceRoleService;
    }



}
