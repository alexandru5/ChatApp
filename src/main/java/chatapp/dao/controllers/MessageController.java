package chatapp.dao.controllers;

import chatapp.dao.services.MessageService;
import chatapp.entities.Message;
import chatapp.entities.MessageDTO;
import chatapp.exceptions.GroupNotFoundException;
import chatapp.exceptions.NotificationNotFoundException;
import chatapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Transactional
    @PutMapping("/create")
    public void createMessage(@RequestBody MessageDTO msg) throws GroupNotFoundException, UserNotFoundException,
                                                                    NotificationNotFoundException {
        messageService.createMessage(msg);
    }

    @Transactional
    @DeleteMapping("/deleteById")
    public void deleteMessageById(@RequestParam("id") int id) {
        messageService.deleteMessageById(id);
    }

    @Transactional
    @DeleteMapping("/deleteMessagesOfUser")
    public void deleteMessagesOfUser(@RequestParam("id") int id) throws UserNotFoundException {
        messageService.deleteMessagesOfUser(id);
    }

    @Transactional
    @DeleteMapping("/deleteMessagesFromGroup")
    public void deleteMessagesFromGroup(@RequestParam("id") int id) throws GroupNotFoundException {
        messageService.deleteMessagesFromGroup(id);
    }

    @GetMapping("/findById")
    public Message findMessageById(@RequestParam("id") int id) {
        return messageService.findMessageById(id);
    }

    @GetMapping("/findMessagesOfUser")
    public List<Message> findMessagesOfUser(@RequestParam("id") int id) throws UserNotFoundException {
        return messageService.findMessagesByUserId(id);
    }
}
