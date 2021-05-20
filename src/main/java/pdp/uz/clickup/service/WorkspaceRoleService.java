package pdp.uz.clickup.service;


import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.WorkspaceRoleDTO;

public interface WorkspaceRoleService {
    ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO);

}
