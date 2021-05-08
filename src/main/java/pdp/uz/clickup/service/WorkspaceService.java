package pdp.uz.clickup.service;

import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.WorkspaceDTO;

public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO);

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO);

    ApiResponse changeOwnerWorkspace(Long id, Long ownerId);

    ApiResponse deleteWorkspace(Long id);
}
