package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import pdp.uz.clickup.entity.WorkSpaceUser;

import java.util.List;
import java.util.Optional;

public interface WorkspaceUserRepository extends JpaRepository<WorkSpaceUser, Long> {

    List<WorkSpaceUser> findByWorkSpaceId(Long workSpace_id);

    List<WorkSpaceUser> findAllByUserId(Long user_id);

    Optional<WorkSpaceUser> findByWorkSpaceIdAndUserId(Long workSpace_id, Long user_id);

    @Transactional
    @Modifying
    void deleteByWorkSpaceIdAndUserId(Long workSpace_id, Long user_id);
}
