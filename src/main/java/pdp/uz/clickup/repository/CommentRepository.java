package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.clickup.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
