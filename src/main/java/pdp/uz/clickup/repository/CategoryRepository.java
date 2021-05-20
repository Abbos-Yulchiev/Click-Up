package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
