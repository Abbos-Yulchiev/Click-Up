package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.WorkspaceDTO;

@Service
public class WorkspaceServiceImpl implements WorkspaceService{


    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDTO) {
        return null;
    }

    @Override
    public ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO) {
        return null;
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, Long ownerId) {
        return null;
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {
        return null;
    }
}
