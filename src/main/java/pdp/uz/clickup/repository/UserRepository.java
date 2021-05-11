package pdp.uz.clickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.clickup.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(value = "select u.id, u.full_name, u.system_role_name, u.created_at, u.email, u.email_code,  u.password, " +
            " u.account_non_expired, u.account_non_locked, u.credentials_non_expired, u.enabled " +
            "from users u " +
            "join work_space_user wsu on u.id = wsu.user_id " +
            "join workspace_role wsr on wsu.id = wsr.work_space_id " +
            "where wsu.work_space_id = ?1 " +
            "and wsr.name = 'ROLE_MEMBER'",
            nativeQuery = true)
    List<User> getMemberList(Long work_space_id);

    @Query(value = "select u.id, u.full_name, u.system_role_name, u.created_at, u.email, u.email_code,  u.password, " +
            " u.account_non_expired, u.account_non_locked, u.credentials_non_expired, u.enabled " +
            "from users u " +
            "join work_space_user wsu on u.id = wsu.user_id " +
            "join workspace_role wsr on wsu.id = wsr.work_space_id " +
            "where wsu.work_space_id = ?1 " +
            "and wsr.name = 'ROLE_GUEST'",
            nativeQuery = true)
    List<User> getGuestList(Long work_space_id);
}
