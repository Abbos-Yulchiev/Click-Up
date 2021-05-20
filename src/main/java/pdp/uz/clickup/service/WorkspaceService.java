package pdp.uz.clickup.service;

import pdp.uz.clickup.entity.User;
import pdp.uz.clickup.entity.WorkSpace;
import pdp.uz.clickup.payload.*;

import java.util.List;

public interface WorkspaceService {


    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO);

    ApiResponse addPermissionToRole(Long roleId, RolePermissionDTO rolePermissionDTO);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);

    /*ApiResponse addMember(AddMemberDTO addMemberDTO);*/

    List<User> getMemberList(Long workspaceId);

    List<User> getWorkspaceGuestList(Long workspaceId);

    List<WorkSpace> getWorkspaceList(User user);

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse joinToWorkspace(Long id, User user);

    ApiResponse changeOwnerWorkspace(Long id, Long ownerId);

    List<MemberDTO> getMemberAndGuest(Long workSpaceId);
}
