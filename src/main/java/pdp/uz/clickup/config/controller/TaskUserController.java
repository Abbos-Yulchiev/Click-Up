package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.service.TaskUserService;


import java.util.UUID;

@RestController
@RequestMapping("/taskUser")
public class TaskUserController {

    final TaskUserService taskUserService;

    public TaskUserController(TaskUserService taskUserService) {
        this.taskUserService = taskUserService;
    }

    @PostMapping("/assign")
    public HttpEntity<?> assignUser(@RequestParam Long taskId, @RequestParam Long userId) {
        final ApiResponse response = taskUserService.assignUser(taskId, userId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping("/remove")
    public HttpEntity<?> removeUser(@RequestParam Long taskId, @RequestParam Long userId) {
        final ApiResponse response = taskUserService.removeUser(taskId, userId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


}

