package chatapp.dao.repositories;

import chatapp.dao.interfaces.MessageRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRepository {
    @Autowired
    MessageRepoInterface repo;

}
