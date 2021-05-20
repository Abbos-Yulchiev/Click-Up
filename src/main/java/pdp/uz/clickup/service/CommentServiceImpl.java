package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.Comment;
import pdp.uz.clickup.entity.Task;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.CommentDTO;
import pdp.uz.clickup.repository.CommentRepository;
import pdp.uz.clickup.repository.TaskRepository;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    final TaskRepository taskRepository;
    final CommentRepository commentRepository;

    public CommentServiceImpl(TaskRepository taskRepository, CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public ApiResponse add(CommentDTO commentDTO) {
        final Optional<Task> optionalTask = taskRepository.findById(commentDTO.getTaskId());
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task not found", false);
        }

        Comment comment = new Comment(
                commentDTO.getName(), optionalTask.get()
        );
        commentRepository.save(comment);

        return new ApiResponse("Comment saved", true);
    }

    @Override
    public ApiResponse edit(Long commentId, CommentDTO commentDTO) {
        final Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent()){
            return new ApiResponse("Comment not found", false);
        }
        final Comment comment = optionalComment.get();

        final Optional<Task> optionalTask = taskRepository.findById(commentDTO.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("Task not found", false);
        }
        comment.setName(commentDTO.getName());
        comment.setTask(optionalTask.get());
        commentRepository.save(comment);
        return new ApiResponse("Comment saved", true);
    }

    @Override
    public ApiResponse delete(Long commentId) {
        final Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent()){
            return new ApiResponse("Comment not found" ,false);
        }
        final Comment comment = optionalComment.get();
        commentRepository.delete(comment);
        return new ApiResponse("Comment deleted!", true);
    }
}
