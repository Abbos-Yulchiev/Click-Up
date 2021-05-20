package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.clickup.entity.User;
import pdp.uz.clickup.entity.WorkSpace;
import pdp.uz.clickup.payload.*;
import pdp.uz.clickup.security.CurrentUser;
import pdp.uz.clickup.service.WorkspaceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/workspace")
public class WorkspaceController {

    final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    /**
     * Adding Workspace
     *
     * @param workspaceDTO
     * @param user
     * @return
     */
    @PostMapping
    public HttpEntity<?> addWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO, @CurrentUser User user) {

        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    /**
     * NAME, COLOR, AVATAR O'ZGARISHI MUMKIN
     *
     * @param id
     * @param workspaceDTO
     * @return
     */
    @PutMapping(value = "/{id}")
    public HttpEntity<?> editWorkspace(@PathVariable Long id, @RequestBody WorkspaceDTO workspaceDTO) {

        ApiResponse apiResponse = workspaceService.editWorkspace(id, workspaceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * * Changing Workspace Owner
     *
     * @param id
     * @param newOwnerId
     * @return
     */

    @PutMapping(value = "/workspace/{id}")
    public HttpEntity<?> changeOwnerWorkspace(@PathVariable Long id,
                                              @RequestParam Long newOwnerId) {
        ApiResponse apiResponse = workspaceService.changeOwnerWorkspace(id, newOwnerId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * Deleting Workspace
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long id) {

        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * Adding, Editing or Remove Workspace
     *
     * @param id
     * @param memberDTO
     * @return
     */
    @PostMapping("/addOrEditOrRemove/{id}")
    public HttpEntity<?> addOrEditOrRemoveWorkspace(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {

        ApiResponse apiResponse = workspaceService.addOrEditOrRemoveWorkspace(id, memberDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    /*@PostMapping(value = "/addMember")
    public HttpEntity<?> addMember(@RequestBody AddMemberDTO addMemberDTO) {

        ApiResponse apiResponse = workspaceService.addMember(addMemberDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }*/

    @PutMapping("/join")
    public HttpEntity<?> joinToWorkspace(@RequestParam Long id,
                                         @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.joinToWorkspace(id, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * Getting WorkspaceUser's members all information
     *
     * @param workspaceId
     * @return
     */
    @GetMapping(value = "/workspaceMemberList/{workspaceId}")
    public HttpEntity<?> getWorkspaceMemberList(@PathVariable Long workspaceId) {

        List<User> userList = workspaceService.getMemberList(workspaceId);
        return ResponseEntity.ok(userList);
    }

    /**
     * Getting WorkspaceUser's guests all information
     *
     * @param workspaceId
     * @return
     */
    @GetMapping(value = "/workspaceGuestList/{workspaceId}")
    public HttpEntity<?> getWorkspaceGuestList(@PathVariable Long workspaceId) {

        List<User> userList = workspaceService.getWorkspaceGuestList(workspaceId);
        return ResponseEntity.ok(userList);
    }

    /**
     * Getting WorkspaceList
     *
     * @return
     */
    @GetMapping
    public HttpEntity<?> getWorkspaceList(@CurrentUser User user) {

        List<WorkSpace> workSpaceList = workspaceService.getWorkspaceList(user);
        return ResponseEntity.ok(workSpaceList);
    }

    /**
     * New Role adding to Workplace
     *
     * @param workspaceRoleDTO
     * @return
     */
    @PostMapping(value = "/addRole")
    public HttpEntity<?> addRole(@RequestBody WorkspaceRoleDTO workspaceRoleDTO) {

        ApiResponse apiResponse = workspaceService.addRole(workspaceRoleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 402 : 409).body(apiResponse);
    }

    /**
     * Adding permission to Role
     *
     * @param roleId
     * @param rolePermissionDTO
     * @return
     */
    @PostMapping(value = "/addPermissionToRole/{roleId}")
    public HttpEntity<?> addOrPermissionToRole(@Valid @PathVariable Long roleId, @RequestBody RolePermissionDTO rolePermissionDTO) {

        ApiResponse apiResponse = workspaceService.addPermissionToRole(roleId, rolePermissionDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @GetMapping(value = "/member/{workSpaceId}")
    public HttpEntity<?> getMemberAndGuest(@PathVariable Long workSpaceId) {

        List<MemberDTO> memberDTOList = workspaceService.getMemberAndGuest(workSpaceId);
        return ResponseEntity.ok(memberDTOList);
    }
}
