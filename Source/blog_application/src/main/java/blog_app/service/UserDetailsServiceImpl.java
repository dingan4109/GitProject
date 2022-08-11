package blog_app.service;

import blog_app.entity.Account;
import blog_app.repository.AccountRepo;
import blog_app.repository.AccountRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    AccountRoleRepo accountRoleRepo;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepo.findAccountByAccountName(accountName);

        if(account == null) {
            throw new UsernameNotFoundException("Account " + accountName + " does not exist!");
        }

        List<String> roles = accountRoleRepo.findAllRoleByAccount(accountName);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(String role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }

        UserDetails userDetails = new User(accountName,account.getAccountPassword(),grantedAuthorities);
        return userDetails;
    }
}
