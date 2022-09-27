package angular_blog_application_BE.repository;

import angular_blog_application_BE.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findAccountByName(String account);
}
