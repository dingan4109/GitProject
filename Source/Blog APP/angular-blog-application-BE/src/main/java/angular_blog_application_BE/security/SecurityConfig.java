package angular_blog_application_BE.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http
//                .authorizeRequests().antMatchers("/blogs/update/**", "/blogs/create", "/blogs/delete/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .loginProcessingUrl("/doLogin")
//                .defaultSuccessUrl("/blogs", true)
//                .failureUrl("/login?error=true")
//                .usernameParameter("account")
//                .passwordParameter("password")
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.authorizeRequests().antMatchers("/**").permitAll();
    }
}
