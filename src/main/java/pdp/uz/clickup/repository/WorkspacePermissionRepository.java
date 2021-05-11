package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import pdp.uz.clickup.entity.WorkspacePermission;
import pdp.uz.clickup.entity.WorkspaceRole;
import pdp.uz.clickup.entity.enums.WorkspacePermissionName;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, Long> {

    boolean existsByWorkspacePermissionAndWorkSpaceRole(WorkspacePermissionName workspacePermission, WorkspaceRole workSpaceRole);

    boolean existsByWorkspacePermission(WorkspacePermissionName workspacePermission);

    @Transactional
    @Modifying
    void deleteByWorkSpaceRoleIdAndWorkspacePermission(Long workSpaceRole_id, WorkspacePermissionName workspacePermission);
}
