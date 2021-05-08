package pdp.uz.clickup.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.WorkspaceDTO;
import pdp.uz.clickup.service.WorkspaceService;

@RestController
@RequestMapping(value = "/api/workspace")
public class WorkspaceController {

    final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public HttpEntity<?> addWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {

        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> editWorkspace(@PathVariable Long id, @RequestBody WorkspaceDTO workspaceDTO) {

        ApiResponse apiResponse = workspaceService.editWorkspace(id, workspaceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping(value = "/changeOwner/{id}")
    public HttpEntity<?> changeOwnerWorkspace(@PathVariable Long id,
                                              @RequestParam Long ownerId) {

        ApiResponse apiResponse = workspaceService.changeOwnerWorkspace(id, ownerId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping(value = "/delete/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long id) {

        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
