package chatapp.auth_api;

import chatapp.dao.services.UserService;
import chatapp.dao.services.UserTypeService;
import chatapp.entities.UserType;
import chatapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecureUserDetailService implements UserDetailsService {

    @Autowired
    UserService userServ;

    @Autowired
    UserTypeService userTypeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        chatapp.entities.User user = null;
        if (userServ != null)
            user = userServ.findUserByUserName(username);

        if (user == null) throw  new UsernameNotFoundException(username);

        List<UserType> types = null;
        List<String> userTypesOfUser = null;

        try {
            types = userTypeService.findUserTypesOfUser(user.getUserID());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        userTypesOfUser = types.stream().map(UserType::getTypeName).collect(Collectors.toList());

        System.out.println("AICIIIII:::" + types);
        return User.withUsername(user.getUserName())
                   .password(user.getPassword())
                   .accountLocked(user.isActive())
                   .roles(userTypesOfUser.toArray(new String[userTypesOfUser.size()]))
                   .build();
    }

}
