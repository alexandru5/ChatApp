package chatapp.dao.services;

import chatapp.MagicEncoder.PasswordEncoder;
import chatapp.dao.repositories.UserRepoInterface;
import chatapp.entities.User;
import chatapp.exceptions.*;
import chatapp.tokens.TokenGenerator;
import chatapp.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepoInterface repo;

    @Autowired
    JavaMailSender sender;

    @Autowired
    GroupService groupService;

    private PasswordEncoder passwordEncoder;

    public boolean exists(int id) {
        return repo.existsById(id);
    }

    public boolean existsByUsername(String username) {
        return repo.existsByUserName(username);
    }

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    public User insertUser(User us) {
        return repo.save(us);
    }

    public void createUser(User user) throws WrongParametersException, EmailExistsException, UsernameExistsException {
        UserValidator validator = new UserValidator();
        if (!validator.validate(user)) throw new WrongParametersException();

        if (existsByEmail(user.getEmail())) throw new EmailExistsException();

        if (existsByUsername(user.getUserName())) throw new UsernameExistsException();

        TokenGenerator tokenGenerator = new TokenGenerator();
        user.setActivationToken(tokenGenerator.getToken());
        passwordEncoder = new PasswordEncoder();
        user.setCreatedAt(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User insertedUser = insertUser(user);
        sendMail(insertedUser);
    }

    public boolean checkUserPassword(int id, String passwordToBeChecked) throws UserNotFoundException {
        User us = findUserByID(id);

        if (us == null) throw new UserNotFoundException();
        passwordEncoder = new PasswordEncoder();
        return passwordEncoder.matches(passwordToBeChecked, us.getPassword());
    }

    public void deleteUser(User us) {
        repo.delete(us);
    }

    public void updateUserName(int id, String newName) throws UserNotFoundException, NotValidUserNameException {
        if (!repo.existsById(id)) throw new UserNotFoundException();

        UserValidator validator = new UserValidator();

        if (validator.validName(newName))
            repo.updateUserName(id, newName);
        else
            throw new NotValidUserNameException();
    }

    public void updateEmail(int id, String email) throws UserNotFoundException, NotValidEmailException {
        if (!repo.existsById(id)) throw new UserNotFoundException();

        UserValidator validator = new UserValidator();

        if (validator.validEmail(email))
            repo.updateEmail(id, email);
        else
            throw new NotValidEmailException();
    }

    public void updatePhoneNo(int id, String phoneNo) throws UserNotFoundException, NotValidPhoneNoException {
        if (!repo.existsById(id)) throw new UserNotFoundException();

        UserValidator validator = new UserValidator();

        if (validator.validPhoneNo(phoneNo))
            repo.updatePhoneNo(id, phoneNo);
        else
            throw new NotValidPhoneNoException();
    }

    public void updateNotificationType(int id, String notificationType) throws UserNotFoundException, NotValidNotificationTypeException {
        if (!repo.existsById(id)) throw new UserNotFoundException();

        UserValidator validator = new UserValidator();

        if (validator.validNotificationType(notificationType))
            repo.updateNotificationType(id, notificationType);
        else
            throw new NotValidNotificationTypeException();
    }

    public void updateActivity(int id, boolean isActive) throws UserNotFoundException {
        if (!repo.existsById(id))
            throw new UserNotFoundException();
        else
            repo.updateActivity(id, isActive);
    }

    public void deleteUserByID(int id) {
        repo.deleteById(id);
    }


    public User findUserByID(int id) {
        return repo.findByUserID(id).orElse(null);
    }

    public User findUserByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    public User findUserByPhoneNo(String phoneNo) {
        return repo.findByPhoneNo(phoneNo).orElse(null);
    }

    public User findUserByUserName(String username) {
        return repo.findByUserName(username).orElse(null);
    }

    public User findUserByActivationToken(String activationToken) {
        return repo.findByActivationToken(activationToken).orElse(null);
    }

    public User findUserByIdAndActivationToken(int id, String activationToken) {
        return repo.findByUserIDAndActivationToken(id, activationToken).orElse(null);
    }

    public List<User> findAllUsers() {
        return repo.findAll();
    }

    public List<User> findUsersInGroup(int id) throws GroupNotFoundException {
        if (groupService.exists(id))
            return repo.findByGroupID(id);
        else
            throw new GroupNotFoundException();
    }

    public List<User> findActiveUsersInGroup(int id) throws GroupNotFoundException {
        if (groupService.exists(id))
            return repo.findActiveUsersInGroup(id);
        else
            throw new GroupNotFoundException();
    }

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

    public void activateUser(int id, String activationToken) throws UserNotFoundException {
        User user = findUserByIdAndActivationToken(id, activationToken);

        if(user == null) throw new UserNotFoundException();

        updateActivity(id, true);
    }

}
