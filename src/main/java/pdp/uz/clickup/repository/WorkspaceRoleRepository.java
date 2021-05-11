package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.clickup.entity.WorkspaceRole;

public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, Long> {

    boolean existsByNameAndWorkSpaceId(String name, Long workSpace_id);

    @Query(value = "select users.id\n" +
            "from workspace_role roles\n" +
            "join work_space_user users on roles.id = users.work_space_role_id\n" +
            "join work_space space on users.work_space_id = space.id\n" +
            "where roles.name = 'ROLE_OWNER' and space.id = ?1", nativeQuery = true)
    Long finOwnerByWorkSpaceId(Long id);

    @Query(value = "select wr.id from workspace_role wr where wr.name = 'ROLE_MEMBER'", nativeQuery = true)
    Long memberRoleId();

}
