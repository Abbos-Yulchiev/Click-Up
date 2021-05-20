package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
