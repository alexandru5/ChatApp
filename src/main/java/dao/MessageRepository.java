package main.java.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MessageRepository {
    @Autowired
    MessageRepoInterface repo;

    @RequestMapping("/findbymessageid")
    public Optional findByMessageId(@RequestParam("MessageID") int id){
        Optional result = repo.findById(id);
        return result;
    }
}
