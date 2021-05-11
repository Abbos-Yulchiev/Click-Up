package pdp.uz.clickup.service;

import pdp.uz.clickup.entity.User;
import pdp.uz.clickup.entity.WorkSpace;
import pdp.uz.clickup.payload.*;

import java.util.List;

public interface WorkspaceService {


    /**TODO
     * Workspace edit qilish,           +++
     * ownerini o'zgartish,             +++
     * member va mehmonlarini ko'rish,  +++
     * Workspacelari ro'yxatini olish,  +++
     * Workspace ga role qo'shish       +++
     * va Workspace rolelarini permisison berish yoki olib tashlash +++
     * kabi amallarni bajaruvchi method larni yozing.*/

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO);

    ApiResponse addPermissionToRole(Long roleId, RolePermissionDTO rolePermissionDTO);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);

    /*ApiResponse addMember(AddMemberDTO addMemberDTO);*/

    List<User> getMemberList(Long workspaceId);

    List<User> getWorkspaceGuestList(Long workspaceId);

    List<WorkSpace> getWorkspaceList();

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse joinToWorkspace(Long id, User user);

    ApiResponse changeOwnerWorkspace(Long id, Long ownerId);
}
