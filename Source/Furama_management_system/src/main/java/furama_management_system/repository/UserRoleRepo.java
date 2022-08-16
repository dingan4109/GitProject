package furama_management_system.repository;

import furama_management_system.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole,Integer> {
    @Query("SELECT ur.role.roleName FROM UserRole ur WHERE ur.user.username = :username")
    List<String> findAllRoleByUsername(@Param("username") String username);
}
