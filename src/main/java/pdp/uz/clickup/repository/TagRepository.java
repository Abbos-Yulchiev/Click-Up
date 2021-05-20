package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
        }
