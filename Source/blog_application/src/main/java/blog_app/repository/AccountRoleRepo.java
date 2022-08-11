package blog_app.repository;

import blog_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepo extends JpaRepository<Role,Integer> {
    @Query("SELECT ar.role.roleName FROM AccountRole ar WHERE ar.account.accountName = :accountName")
    List<String> findAllRoleByAccount(@Param("accountName") String username);
}
