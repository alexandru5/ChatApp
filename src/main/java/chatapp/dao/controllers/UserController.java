package chatapp.dao.controllers;

import chatapp.dao.services.UserService;
import chatapp.entities.User;
import chatapp.exceptions.EmailExistsException;
import chatapp.exceptions.NoUserWithActivationTokenException;
import chatapp.exceptions.WrongParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/existsByEmail")
    public boolean existsByEmail(@RequestParam("email") String email) {
        return userService.existsByEmail(email);
    }

    @PutMapping("/create")
    public void createUser(@RequestBody User user) throws EmailExistsException, WrongParametersException {
        userService.createUser(user);
    }

    @PutMapping("/updateusername")
    public void updateUserName(@RequestParam("id") int id, @RequestParam("name") String name) {
        userService.updateUserName(id, name);
    }

    @PutMapping("/updatemail")
    public void updateEmail(@RequestParam("id") int id, @RequestParam("email") String email) {
        userService.updateEmail(id, email);
    }

    @PutMapping("/updatephoneno")
    public void updatePhoneNo(@RequestParam("id") int id, @RequestParam("phoneNo") String phoneNo) {
        userService.updatePhoneNo(id, phoneNo);
    }

    @PutMapping("/updatenotificationtype")
    public void updateNotificationType(@RequestParam("id") int id, @RequestParam("notificationType") String notificationType) {
        userService.updateNotificationType(id, notificationType);
    }

    @PutMapping("/updateactivity")
    public void updateUserActivity(@RequestParam("id") int id, @RequestParam("isActive") boolean isActive) {
        userService.updateActivity(id, isActive);
    }

    @PutMapping("/activate")
    public void activateUser(@RequestParam("id") int id, @RequestParam("activationToken") String activationToken)
                        throws NoUserWithActivationTokenException {
        userService.activateUser(id, activationToken);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam("us") User us) {
        userService.deleteUser(us);
    }

    @DeleteMapping("/deletebyid")
    public void deleteUserByID(@RequestParam("id") int id) {
        userService.deleteUserByID(id);
    }

    @GetMapping("/findbyid")
    public User findUserByID(@RequestParam("id") int id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/findbyemail")
    public User findUserByEmail(@RequestParam("email") String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/findbyusername")
    User findUserByUserName(@RequestParam("username") String username) {
        return userService.findUserByUserName(username);
    }

    @GetMapping("/findbyphoneno")
    User findUserByPhoneNo(@RequestParam("phoneno") String phoneNo) {
        return userService.findUserByPhoneNo(phoneNo);
    }

    @GetMapping("/findallingroup")
    public List<User> findAllUsersInGroup(@RequestParam("id") int id) {
        return userService.findUsersInGroup(id);
    }

    @GetMapping("/findactiveingroup")
    public List<User> findActiveUsersInGroup(@RequestParam("id") int id) {
        return userService.findActiveUsersInGroup(id);
    }

    @GetMapping("/findall")
    public List<User> findAll() {
        return userService.findAllUsers();
    }

    @RequestMapping("/sendmail")
    public void sendMail(User us) {
        userService.sendMail(us);
    }

    @GetMapping("/check")
    public boolean checkPass(@RequestParam("id") int id, @RequestParam("password") String password) {
        return userService.checkUserPassword(id, password);
    }

}
