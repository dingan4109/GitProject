package angular_blog_application_BE.service;

import angular_blog_application_BE.entity.Account;
import angular_blog_application_BE.repository.AccountRepo;
import angular_blog_application_BE.repository.AccountRoleRepo;
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
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    AccountRoleRepo accountRoleRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Account account = accountRepo.findAccountByName(name);
        if(account == null) {
            throw new UsernameNotFoundException("Account " + name + " does not exist!");
        }

        List<String> roles = accountRoleRepo.findAllRoleByAccount(name);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(String role: roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }

        UserDetails userDetails = new User(name, account.getPassword(), grantedAuthorities);
        return userDetails;
    }
}
