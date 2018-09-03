package chatapp.dao.controllers;

import chatapp.dao.services.UserService;
import chatapp.entities.User;
import chatapp.tokens.TokenGenerator;
import chatapp.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JavaMailSender sender;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void createUser(@RequestBody User user) {

        UserValidator validator = new UserValidator();
        if (!validator.validate(user)) {
            return;
        }

        TokenGenerator tokenGenerator = new TokenGenerator();
        user.setActivationToken(tokenGenerator.getToken());
        user.setCreatedAt(new Date());
        User insertedUser = userService.insertUser(user);
        sendMail(insertedUser);
    }

    @RequestMapping(value = "/updateusername", method = RequestMethod.PUT)
    public void updateUserName(@RequestParam("id") int id, @RequestParam("name") String name) {
        userService.updateUserName(id, name);
    }

    @RequestMapping(value = "/updatemail", method = RequestMethod.PUT)
    public void updateEmail(@RequestParam("id") int id, @RequestParam("email") String email) {
        userService.updateEmail(id, email);
    }

    @RequestMapping(value = "/updatephoneno", method = RequestMethod.PUT)
    public void updatePhoneNo(@RequestParam("id") int id, @RequestParam("phoneNo") String phoneNo) {
        userService.updatePhoneNo(id, phoneNo);
    }

    @RequestMapping(value = "/updatenotificationtype", method = RequestMethod.PUT)
    public void updateNotificationType(@RequestParam("id") int id, @RequestParam("notificationType") String notificationType) {
        userService.updateNotificationType(id, notificationType);
    }

    @RequestMapping(value = "/updateactivity", method = RequestMethod.PUT)
    public void updateUserActivity(@RequestParam("id") int id, @RequestParam("isActive") boolean isActive) {
        userService.updateActivity(id, isActive);
    }

    @RequestMapping(value = "/activate")
    public void activateUser(@RequestParam("id") int id, @RequestParam("activationToken") String activationToken) {
        User user = userService.findUserByIdAndActivationToken(id, activationToken);

        if(user == null)
            return;

        userService.updateActivity(id, true);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("us") User us) {
        userService.deleteUser(us);
    }

    @RequestMapping(value = "/deletebyid", method = RequestMethod.DELETE)
    public void deleteUserByID(@RequestParam("id") int id) {
        userService.deleteUserByID(id);
    }

    @RequestMapping(value = "/findbyid", method = RequestMethod.GET)
    public User findUserByID(@RequestParam("id") int id) {
        return userService.findUserByID(id);
    }

    @RequestMapping(value = "/findbyemail", method = RequestMethod.GET)
    public User findUserByEmail(@RequestParam("email") String email) {
        return userService.findUserByEmail(email);
    }

    @RequestMapping(value = "/findbyusername", method = RequestMethod.GET)
    User findUserByUserName(@RequestParam("username") String username) {
        return userService.findUserByUserName(username);
    }

    @RequestMapping(value = "/findbyphoneno", method = RequestMethod.GET)
    User findUserByPhoneNo(@RequestParam("phoneno") String phoneNo) {
        return userService.findUserByPhoneNo(phoneNo);
    }

    @RequestMapping(value = "/findallingroup", method = RequestMethod.GET)
    public List<User> findAllUsersInGroup(@RequestParam("id") int id) {
        return userService.findUsersInGroup(id);
    }

    @RequestMapping(value = "/findactiveingroup", method = RequestMethod.GET)
    public List<User> findActiveUsersInGroup(@RequestParam("id") int id) {
        return userService.findActiveUsersInGroup(id);
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAllUsers();
    }

    @RequestMapping("/sendmail")
    public void sendMail(User us) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String validationLink = "http://localhost:8080/user/activate?id=" + us.getUserID()
                                + "&activationToken=" + us.getActivationToken();

        try {
            helper.setTo(us.getEmail());
            helper.setText(validationLink);
            helper.setSubject("Registration Confirmation ChatApp");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
    }

}
