package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.clickup.entity.User;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.TaskDTO;
import pdp.uz.clickup.security.CurrentUser;
import pdp.uz.clickup.service.TaskService;

@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

    /**
     * Status va tasklarni qo'shish, Task statusini o'zgartirish,
     * Task ga file biriktirish, biriktirilgan file ni o'chirish,
     * Task ga comment yoki tag qo'shish uni edit qilish va o'chirish,
     * task ga user assign qilish va  assign qilingan user ni olib tashlash
     * kabi amallarni bajaruvchi method larni yozing.
     */

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping(value = "/add")
    public HttpEntity<?> addTask(@CurrentUser User user, @RequestBody TaskDTO taskDTO) {

        ApiResponse apiResponse = taskService.addTask(taskDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



}
