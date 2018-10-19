package chatapp.auth_api;

import chatapp.dao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@EnableWebMvc
@Configuration
@ComponentScan
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String USER = "user";
    private final String ADMIN = "admin";
    private final String usernamesQuery = "SELECT Name, Password, isActive from `User` where Name=?";
    private final String authoritiesQuery = "SELECT a.Name, b.TypeName FROM `User` a JOIN IsIn c ON a.UserID = c.UserID JOIN UserType b ON b.TypeID = c.TypeID WHERE a.Name=?";

    @Autowired
    UserService userService;


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                    .antMatchers("/user/private/*").hasRole(USER)
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .and()
                    .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SecureUserDetailService(userService));
    }
}
