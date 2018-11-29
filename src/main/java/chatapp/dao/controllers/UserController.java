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
    @PutMapping("/public/create")
    public void createUser(@RequestBody User user) throws EmailExistsException, WrongParametersException, UsernameExistsException {
        userService.createUser(user);
    }

    @Transactional
    @PutMapping("/private/updateUsername/{id}")
    public void updateUserName(@PathVariable int id, @RequestParam("name") String name) throws UserNotFoundException,
                                                                                                     NotValidUserNameException {
        userService.updateUserName(id, name);
    }

    @Transactional
    @PutMapping("/private/updateEmail/{id}")
    public void updateEmail(@PathVariable int id, @RequestParam("email") String email) throws UserNotFoundException,
                                                                                                NotValidEmailException {
        userService.updateEmail(id, email);
    }

    @Transactional
    @PutMapping("/private/updatePhoneNo/{id}")
    public void updatePhoneNo(@PathVariable int id, @RequestParam("phoneNo") String phoneNo) throws UserNotFoundException,
                                                                                                          NotValidPhoneNoException {
        userService.updatePhoneNo(id, phoneNo);
    }

    @Transactional
    @PutMapping("/private/updateNotificationType/{id}")
    public void updateNotificationType(@PathVariable int id, @RequestParam("notificationType") String notificationType)
                                                                                        throws UserNotFoundException,
                                                                                               NotValidNotificationTypeException {
        userService.updateNotificationType(id, notificationType);
    }

    @Transactional
    @PutMapping("/private/updateActivity/{id}")
    public void updateUserActivity(@PathVariable int id, @RequestParam("isActive") boolean isActive)
                                                                throws UserNotFoundException {
        userService.updateActivity(id, isActive);
    }

    @Transactional
    @PutMapping("/private/activate/{id}")
    public void activateUser(@PathVariable int id, @RequestParam("activationToken") String activationToken)
                                                                                        throws UserNotFoundException {
        userService.activateUser(id, activationToken);
    }

    /*@Transactional
    @DeleteMapping("/private/delete")
    public void deleteUser(@RequestParam("us") User us) {
        userService.deleteUser(us);
    }*/

    @Transactional
    @DeleteMapping("/private/deleteById/{id}")
    public void deleteUserByID(@PathVariable int id) {
        userService.deleteUserByID(id);
    }

    @GetMapping("/moderator/findById/{id}")
    public User findUserByID(@PathVariable int id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/private/findByEmail")
    public User findUserByEmail(@RequestParam("email") String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/admin/findByUsername/{username}")
    User findUserByUserName(@PathVariable String username) {
        return userService.findUserByUserName(username);
    }

    @GetMapping("/private/findByPhoneNo")
    User findUserByPhoneNo(@RequestParam("phoneNo") String phoneNo) {
        return userService.findUserByPhoneNo(phoneNo);
    }

    @GetMapping("/private/findAllInGroup")
    public List<User> findAllUsersInGroup(@RequestParam("id") int id) throws GroupNotFoundException {
        return userService.findUsersInGroup(id);
    }

    @GetMapping("/private/findActiveInGroup")
    public List<User> findActiveUsersInGroup(@RequestParam("id") int id) throws GroupNotFoundException {
        return userService.findActiveUsersInGroup(id);
    }

    @GetMapping("/private/findAll")
    public List<User> findAll() {
        return userService.findAllUsers();
    }

    @RequestMapping("/private/sendMail")
    public void sendMail(@RequestBody User us) {
        userService.sendMail(us);
    }

    @GetMapping("/private/check")
    public boolean checkPass(@RequestParam("id") int id, @RequestParam("password") String password) throws UserNotFoundException {
        return userService.checkUserPassword(id, password);
    }

}
