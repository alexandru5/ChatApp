package chatapp.dao.controllers;

import chatapp.dao.services.UserService;
import chatapp.entities.User;
import chatapp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Transactional
    @GetMapping("/existsByEmail")
    public boolean existsByEmail(@RequestParam("email") String email) {
        return userService.existsByEmail(email);
    }

    @Transactional
    @PutMapping("/create")
    public void createUser(@RequestBody User user) throws EmailExistsException, WrongParametersException {
        userService.createUser(user);
    }

    @Transactional
    @PutMapping("/updateUsername")
    public void updateUserName(@RequestParam("id") int id, @RequestParam("name") String name) throws UserNotFoundException,
                                                                                                     NotValidUserNameException {
        userService.updateUserName(id, name);
    }

    @Transactional
    @PutMapping("/updateEmail")
    public void updateEmail(@RequestParam("id") int id, @RequestParam("email") String email) throws UserNotFoundException,
                                                                                                    NotValidEmailException {
        userService.updateEmail(id, email);
    }

    @Transactional
    @PutMapping("/updatePhoneNo")
    public void updatePhoneNo(@RequestParam("id") int id, @RequestParam("phoneNo") String phoneNo) throws UserNotFoundException,
                                                                                                          NotValidPhoneNoException {
        userService.updatePhoneNo(id, phoneNo);
    }

    @Transactional
    @PutMapping("/updateNotificationType")
    public void updateNotificationType(@RequestParam("id") int id, @RequestParam("notificationType") String notificationType) throws UserNotFoundException,
                                                                                                                                     NotValidNotificationTypeException {
        userService.updateNotificationType(id, notificationType);
    }

    @Transactional
    @PutMapping("/updateActivity")
    public void updateUserActivity(@RequestParam("id") int id, @RequestParam("isActive") boolean isActive)
                                                                throws UserNotFoundException {
        userService.updateActivity(id, isActive);
    }

    @Transactional
    @PutMapping("/activate")
    public void activateUser(@RequestParam("id") int id, @RequestParam("activationToken") String activationToken)
                                                                                        throws UserNotFoundException {
        userService.activateUser(id, activationToken);
    }

    @Transactional
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam("us") User us) {
        userService.deleteUser(us);
    }

    @Transactional
    @DeleteMapping("/deleteById")
    public void deleteUserByID(@RequestParam("id") int id) {
        userService.deleteUserByID(id);
    }

    @GetMapping("/findById")
    public User findUserByID(@RequestParam("id") int id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/findByEmail")
    public User findUserByEmail(@RequestParam("email") String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/findByUsername")
    User findUserByUserName(@RequestParam("username") String username) {
        return userService.findUserByUserName(username);
    }

    @GetMapping("/findByPhoneNo")
    User findUserByPhoneNo(@RequestParam("phoneno") String phoneNo) {
        return userService.findUserByPhoneNo(phoneNo);
    }

    @GetMapping("/findAllInGroup")
    public List<User> findAllUsersInGroup(@RequestParam("id") int id) throws GroupNotFoundException {
        return userService.findUsersInGroup(id);
    }

    @GetMapping("/findActiveInGroup")
    public List<User> findActiveUsersInGroup(@RequestParam("id") int id) throws GroupNotFoundException {
        return userService.findActiveUsersInGroup(id);
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAllUsers();
    }

    @RequestMapping("/sendMail")
    public void sendMail(@RequestBody User us) {
        userService.sendMail(us);
    }

    @GetMapping("/check")
    public boolean checkPass(@RequestParam("id") int id, @RequestParam("password") String password) throws UserNotFoundException {
        return userService.checkUserPassword(id, password);
    }

}
