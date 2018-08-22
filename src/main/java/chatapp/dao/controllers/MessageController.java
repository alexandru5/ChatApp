package chatapp.dao.controllers;

import chatapp.dao.interfaces.MessageRepoInterface;
import chatapp.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    MessageRepoInterface repo;

    public Message getMessageByID(int id) {
        return repo.findByMessageID(id);
    }

    public List<Message> getMessagesForUser(int id) {
        return repo.findAllByUserID(id);
    }
}
