package chatapp.auth_api;

import chatapp.MagicEncoder.PasswordEncoder;
import chatapp.dao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecureUserDetailService implements UserDetailsService {

    final UserService userServ;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public SecureUserDetailService(UserService userServ) {
        this.userServ = userServ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        chatapp.entities.User user = null;
        if (userServ != null)
            user = userServ.findUserByUserName(username);

        if (user == null) throw  new UsernameNotFoundException(username);

        return User.withUsername(user.getUserName())
                   .password(user.getPassword())
                   .accountLocked(user.isActive())
                   .roles("user")
                   .build();
    }

    private String encode(String pass) {
        return passwordEncoder.encode(pass);
    }


}
