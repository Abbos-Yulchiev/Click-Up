package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.WorkSpace;
import pdp.uz.clickup.entity.WorkspaceRole;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.WorkspaceRoleDTO;
import pdp.uz.clickup.repository.WorkspaceRepository;
import pdp.uz.clickup.repository.WorkspaceRoleRepository;

import java.util.Optional;

@Service
public class WorkspaceRoleServiceImpl implements WorkspaceRoleService {

    final WorkspaceRepository workspaceRepository;
    final WorkspaceRoleRepository workspaceRoleRepository;

    public WorkspaceRoleServiceImpl(WorkspaceRepository workspaceRepository, WorkspaceRoleRepository workspaceRoleRepository) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
    }

    @Override
    public ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO) {

        final Optional<WorkSpace> optionalWorkspace = workspaceRepository.findById(workspaceRoleDTO.getWorkSpaceId());
        if (!optionalWorkspace.isPresent()) {
            return new ApiResponse("Workspace not found", false);
        }

        WorkspaceRole workspaceRole = new WorkspaceRole(
                workspaceRoleDTO.getName(), optionalWorkspace.get(), null
        );
        workspaceRoleRepository.save(workspaceRole);

        return new ApiResponse("Workspace role added", true);
    }
}
