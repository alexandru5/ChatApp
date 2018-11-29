package chatapp.auth_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@EnableWebMvc
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String USER = "user";
    private final int strength = 12;
    private final String ADMIN = "admin";
    private final String MODERATOR = "moderator";
    private final String usernamesQuery = "SELECT Name, Password, isActive from `User` where Name=?";
    private final String authoritiesQuery = "SELECT a.Name, b.TypeName FROM `User` a JOIN IsIn c ON a.UserID = c.UserID JOIN UserType b ON b.TypeID = c.TypeID WHERE a.Name=?";

   /* @Autowired
    UserService userService;
*/

   @Autowired
   SecureUserDetailService secureUserDetailService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    .authorizeRequests()
                        .antMatchers("/user/public/*")
                        .permitAll()
                    .and()
                    .authorizeRequests()
                        .antMatchers("/user/admin/*").hasAuthority(ADMIN)
                        .antMatchers("/user/moderator/*").hasAuthority(MODERATOR)
                        .antMatchers("/user/private/*").hasAuthority(USER)
                        .anyRequest().authenticated()
                    .and()
                        .formLogin()
                    .and()
                        .csrf().disable();
    }

    @Bean
    public PasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder(strength);
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passEncoder());
        authenticationProvider.setUserDetailsService(secureUserDetailService);
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SecureUserDetailService())
            .and()
            .authenticationProvider(authProvider());
    }
}
