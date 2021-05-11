package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.Space;

public interface SpaceRepository extends JpaRepository<Space, Long> {
}
