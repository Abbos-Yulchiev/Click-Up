package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.ChecklistDTO;
import pdp.uz.clickup.payload.ChecklistItemDTO;
import pdp.uz.clickup.service.ChecklistService;

@RestController
@RequestMapping("/checklist")
public class ChecklistController {

    ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody ChecklistDTO checklistDTO) {
        final ApiResponse add = checklistService.add(checklistDTO);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        final ApiResponse add = checklistService.delete(id);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @PostMapping("/addChecklistItem")
    public HttpEntity<?> addChecklistItem(@RequestBody ChecklistItemDTO checklistItemDTO) {
        final ApiResponse add = checklistService.addChecklistItem(checklistItemDTO);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @DeleteMapping("/removeChecklistItem/{id}")
    public HttpEntity<?> removeChecklistItem(@PathVariable Long id) {
        final ApiResponse add = checklistService.removeChecklistItem(id);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @PostMapping("/assignUserToChecklistItem")
    public HttpEntity<?> assignUserToChecklistItem(@RequestParam Long checklistItemId, @RequestParam Long userId){
        final ApiResponse add = checklistService.assignUserToChecklistItem(checklistItemId, userId);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }
}
