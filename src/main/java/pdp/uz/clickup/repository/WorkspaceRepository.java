package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.WorkSpace;

public interface WorkspaceRepository extends JpaRepository<WorkSpace, Long> {

    boolean existsByIdAndName(Long id, String name);
}
