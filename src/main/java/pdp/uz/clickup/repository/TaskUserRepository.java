package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.TaskUser;

import java.util.Optional;

public interface TaskUserRepository extends JpaRepository<TaskUser, Long> {

    Optional<TaskUser> findByTaskIdAndUserId(Long task_id, Long user_id);

    void deleteByTaskIdAndUserId(Long task_id, Long user_id);

}