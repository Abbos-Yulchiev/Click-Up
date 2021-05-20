package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.CheckListItem;

public interface ChecklistItemRepository extends JpaRepository<CheckListItem, Long> {
}
