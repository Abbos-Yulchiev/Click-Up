package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.CheckList;

public interface ChecklistRepository extends JpaRepository<CheckList, Long> {
}
