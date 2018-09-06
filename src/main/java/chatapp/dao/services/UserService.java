package chatapp.dao.services;

import chatapp.MagicEncoder.PasswordEncoder;
import chatapp.dao.repositories.UserRepoInterface;
import chatapp.entities.User;
import chatapp.exceptions.EmailExistsException;
import chatapp.exceptions.NoUserWithActivationTokenException;
import chatapp.exceptions.WrongParametersException;
import chatapp.tokens.TokenGenerator;
import chatapp.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepoInterface repo;

    @Autowired
    JavaMailSender sender;

    private PasswordEncoder passwordEncoder;

    public boolean exists(int id) {
        return repo.existsById(id);
    }
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    @Transactional
    public User insertUser(User us) {
        return repo.save(us);
    }

    @Transactional
    public void createUser(User user) throws WrongParametersException, EmailExistsException {
        UserValidator validator = new UserValidator();
        if (!validator.validate(user)) {
            throw new WrongParametersException("Wrong user body");
        }

        if (existsByEmail(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress:" + user.getEmail());
        }

        TokenGenerator tokenGenerator = new TokenGenerator();
        user.setActivationToken(tokenGenerator.getToken());
        passwordEncoder = new PasswordEncoder();
        user.setCreatedAt(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User insertedUser = insertUser(user);
        sendMail(insertedUser);
    }

    public boolean checkUserPassword(int id, String passwordToBeChecked) {
        User us = findUserByID(id);
        passwordEncoder = new PasswordEncoder();
        return passwordEncoder.matches(passwordToBeChecked, us.getPassword());
    }
    @Transactional
    public void deleteUser(User us) {
        repo.delete(us);
    }

    @Transactional
    public void updateUserName(int id, String newName) {
        if (!repo.existsById(id))
            return;

        UserValidator validator = new UserValidator();

        if (validator.validName(newName))
            repo.updateUserName(id, newName);
    }

    @Transactional
    public void updateEmail(int id, String email) {
        if (!repo.existsById(id))
            return;

        UserValidator validator = new UserValidator();

        if (validator.validEmail(email))
            repo.updateEmail(id, email);
    }

    @Transactional
    public void updatePhoneNo(int id, String phoneNo) {
        if (!repo.existsById(id))
            return;

        UserValidator validator = new UserValidator();

        if (validator.validPhoneNo(phoneNo))
            repo.updatePhoneNo(id, phoneNo);
    }

    @Transactional
    public void updateNotificationType(int id, String notificationType) {
        if (!repo.existsById(id))
            return;

        UserValidator validator = new UserValidator();

        if (validator.validNotificationType(notificationType))
            repo.updateNotificationType(id, notificationType);
    }

    @Transactional
    public void updateActivity(int id, boolean isActive) {
        if (repo.existsById(id))
            repo.updateActivity(id, isActive);
    }

    @Transactional
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

    public List<User> findUsersInGroup(int id) {
        return repo.findByGroupID(id);
    }

    public List<User> findActiveUsersInGroup(int id) {
        return repo.findActiveUsersInGroup(id);
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
    public void activateUser(int id, String activationToken) throws NoUserWithActivationTokenException {
        User user = findUserByIdAndActivationToken(id, activationToken);

        if(user == null)
            throw new NoUserWithActivationTokenException("No user with this activation token!");

        updateActivity(id, true);
    }

}
