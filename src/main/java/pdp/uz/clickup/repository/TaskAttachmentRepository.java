package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.TaskAttachment;

import java.util.Optional;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {

    Optional<TaskAttachment> findByTaskIdAndAttachmentId(Long task_id, Long attachment_id);

}
