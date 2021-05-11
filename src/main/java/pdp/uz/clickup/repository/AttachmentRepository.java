package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
