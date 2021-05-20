package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.CommentDTO;
import pdp.uz.clickup.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(CommentDTO commentDTO) {
        final ApiResponse response = commentService.add(commentDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/edit")
    public HttpEntity<?> edit(@RequestParam Long commentId, @RequestBody CommentDTO commentDTO) {
        final ApiResponse response = commentService.edit(commentId, commentDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping("/delete/{commentId}")
    public HttpEntity<?> delete(@PathVariable Long commentId) {
        final ApiResponse response = commentService.delete(commentId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
