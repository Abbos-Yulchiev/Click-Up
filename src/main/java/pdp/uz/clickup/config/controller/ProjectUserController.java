package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.service.ProjectUserService;

@RestController
@RequestMapping("/projectUser")
public class ProjectUserController {

    final ProjectUserService projectUserRepository;

    public ProjectUserController(ProjectUserService projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestParam Long memberId, @RequestParam Long projectId, @RequestParam Long taskId) {
        final ApiResponse response = projectUserRepository.addMember(memberId, projectId, taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("/remove")
    public HttpEntity<?> remove(@RequestParam Long memberId, @RequestParam Long projectId, @RequestParam Long taskId) {
        final ApiResponse response = projectUserRepository.removeMember(memberId, projectId, taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
