package chatapp.dao.services;

import chatapp.dao.repositories.MessageRepoInterface;
import chatapp.entities.*;
import chatapp.exceptions.GroupNotFoundException;
import chatapp.exceptions.NotificationNotFoundException;
import chatapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepoInterface repo;

    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    public void createMessage(MessageDTO msg) throws GroupNotFoundException, UserNotFoundException, NotificationNotFoundException {
        Group group = groupService.getGroupByID(msg.getGroupID());

        if (group == null) throw new GroupNotFoundException();

        User user = userService.findUserByID(msg.getUserID());

        if (user == null) throw new UserNotFoundException();

        Notification notification = notificationService.findNotificationById(msg.getNotificationID());

        if (notification == null) throw new NotificationNotFoundException();

        Message message = new Message(group, user, msg.getMessage(), new Date(), notification);
        repo.save(message);
    }

    public void deleteMessageById(int id) {
        repo.deleteByMessageID(id);
    }

    public void deleteMessagesOfUser(int id) throws UserNotFoundException {
        if (userService.exists(id))
            repo.deleteByUserID(id);
        else
            throw new UserNotFoundException();
    }

    public void deleteMessagesFromGroup(int id) throws GroupNotFoundException {

        if (groupService.exists(id))
            repo.deleteByGroupID(id);
        else
            throw new GroupNotFoundException();
    }

    public Message findMessageById(int id) {
        return repo.findByMessageID(id).orElse(null);
    }

    public List<Message> findMessagesByUserId(int id) throws UserNotFoundException {
        if (userService.exists(id))
            return repo.findAllByUserID(id);
        else
            throw new UserNotFoundException();
    }
}
