package chatapp.dao.services;

import chatapp.dao.repositories.UserRepoInterface;
import chatapp.entities.User;
import chatapp.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepoInterface repo;

    public boolean exists(int id) {
        return repo.existsById(id);
    }

    @Transactional
    public User insertUser(User us) {
        us.setCreatedAt(new Date());
        return repo.save(us);
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
}
