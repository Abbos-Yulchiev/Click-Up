package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.ProjectUser;
import pdp.uz.clickup.entity.Projects;
import pdp.uz.clickup.entity.Task;
import pdp.uz.clickup.entity.User;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

    boolean existsByProjectsAndUserAndTask(Projects projects, User user, Task task);

    boolean deleteByProjectsAndUserAndTask(Projects projects, User user, Task task);
}
