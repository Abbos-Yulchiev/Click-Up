package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.Projects;

public interface ProjectRepository extends JpaRepository<Projects, Long> {
}
