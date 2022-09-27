package angular_blog_application_BE.repository;

import angular_blog_application_BE.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepo extends JpaRepository<Role,Long> {
    @Query("SELECT ar.role.name FROM AccountRole AS ar WHERE ar.account.name = :name")
    List<String> findAllRoleByAccount(@Param("name") String name);
}
