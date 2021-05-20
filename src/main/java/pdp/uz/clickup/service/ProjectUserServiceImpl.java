package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.ProjectUser;
import pdp.uz.clickup.entity.Projects;
import pdp.uz.clickup.entity.Task;
import pdp.uz.clickup.entity.User;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.repository.ProjectRepository;
import pdp.uz.clickup.repository.ProjectUserRepository;
import pdp.uz.clickup.repository.TaskRepository;
import pdp.uz.clickup.repository.UserRepository;

import java.util.Optional;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    final UserRepository userRepository;
    final TaskRepository taskRepository;
    final ProjectRepository projectRepository;
    final ProjectUserRepository projectUserRepository;

    public ProjectUserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, ProjectRepository projectRepository, ProjectUserRepository projectUserRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectUserRepository = projectUserRepository;
    }


    @Override
    public ApiResponse addMember(Long memberId, Long projectId, Long taskId) {
        Optional<pdp.uz.clickup.entity.User> optionalUser = userRepository.findById(memberId);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("user not found", false);
        }

        final Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task not found", false);
        }

        final Optional<Projects> optionalProject = projectRepository.findById(projectId);
        if (!optionalProject.isPresent())
            return new ApiResponse("Project not found", false);

        ProjectUser projectUser = new ProjectUser(
                optionalProject.get(), optionalUser.get(), optionalTask.get()
        );
        projectUserRepository.save(projectUser);
        return new ApiResponse("User added to Project", true);
    }

    @Override
    public ApiResponse removeMember(Long memberId, Long projectId, Long taskId) {

        Optional<pdp.uz.clickup.entity.User> optionalUser = userRepository.findById(memberId);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("user not found", false);
        }
        User user = optionalUser.get();

        final Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task not found", false);
        }
        final Task task = optionalTask.get();

        final Optional<Projects> optionalProject = projectRepository.findById(projectId);
        if (!optionalProject.isPresent())
            return new ApiResponse("Project not found", false);

        final Projects project = optionalProject.get();

        final boolean existsByProjectAndMemberAndTask = projectUserRepository.existsByProjectsAndUserAndTask(project, user, task);

        if (existsByProjectAndMemberAndTask) {
            projectUserRepository.deleteByProjectsAndUserAndTask(project, user, task);
        }
        return new ApiResponse("Removed", true);
    }
}
