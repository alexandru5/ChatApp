package chatapp.dao.repositories;

import chatapp.dao.interfaces.MessageRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MessageRepository {
    @Autowired
    MessageRepoInterface repo;

    @GetMapping("/findbymessageid")
    public Optional findByMessageId(int id){
        Optional result = repo.findById(id);
        return result;
    }
}
