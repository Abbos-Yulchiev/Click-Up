package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}