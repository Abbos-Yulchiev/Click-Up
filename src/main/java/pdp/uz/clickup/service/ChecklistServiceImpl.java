package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.CheckList;
import pdp.uz.clickup.entity.CheckListItem;
import pdp.uz.clickup.entity.Task;
import pdp.uz.clickup.entity.User;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.ChecklistDTO;
import pdp.uz.clickup.payload.ChecklistItemDTO;
import pdp.uz.clickup.repository.ChecklistItemRepository;
import pdp.uz.clickup.repository.ChecklistRepository;
import pdp.uz.clickup.repository.TaskRepository;
import pdp.uz.clickup.repository.UserRepository;

import java.util.Optional;

@Service
public class ChecklistServiceImpl implements ChecklistService{

    final TaskRepository taskRepository;
    final ChecklistRepository checklistRepository;
    final ChecklistItemRepository checklistItemRepository;
    final UserRepository userRepository;

    public ChecklistServiceImpl(TaskRepository taskRepository, ChecklistRepository checklistRepository, ChecklistItemRepository checklistItemRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.checklistRepository = checklistRepository;
        this.checklistItemRepository = checklistItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse add(ChecklistDTO checklistDTO) {

        final Optional<Task> optionalTask = taskRepository.findById(checklistDTO.getTaskId());
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task not found", false);
        }
        final Task task = optionalTask.get();

        CheckList checklist = new CheckList(
                checklistDTO.getName(), task
        );

        checklistRepository.save(checklist);

        return new ApiResponse("Checklist added!", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        final Optional<CheckList> optionalChecklist = checklistRepository.findById(id);
        if (!optionalChecklist.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        checklistRepository.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }

    @Override
    public ApiResponse addChecklistItem(ChecklistItemDTO checklistItemDTO) {

        final Optional<CheckList> optionalChecklist = checklistRepository.findById(checklistItemDTO.getChecklistId());
        if (!optionalChecklist.isPresent()) {
            return new ApiResponse("Checklist not found", false);
        }

        Optional<pdp.uz.clickup.entity.User> optionalUser = userRepository.findById(checklistItemDTO.getAssignedUser());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("Assigned user not found", false);
        }

        CheckListItem checklistItem = new CheckListItem(
                checklistItemDTO.getName(), optionalChecklist.get(), checklistItemDTO.isResolved(),
                optionalUser.get()
        );
        checklistItemRepository.save(checklistItem);
        return new ApiResponse("Checklist Item added", true);
    }

    @Override
    public ApiResponse removeChecklistItem(Long checklistItemId) {

        final Optional<CheckListItem> optionalChecklistItem = checklistItemRepository.findById(checklistItemId);
        if (!optionalChecklistItem.isPresent()) {
            checklistItemRepository.deleteById(checklistItemId);
            return new ApiResponse("Removed", true);
        }
        return new ApiResponse("Not found", false);
    }

    @Override
    public ApiResponse assignUserToChecklistItem(Long checklistItemId, Long userId) {

        final Optional<CheckListItem> optionalChecklistItem = checklistItemRepository.findById(checklistItemId);
        if (!optionalChecklistItem.isPresent()) {
            return new ApiResponse("Checklist Item not found", false);
        }
        final CheckListItem checklistItem = optionalChecklistItem.get();

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found", false);
        }

        checklistItem.setUser(optionalUser.get());
        return new ApiResponse("user assigned", true);
    }


}
