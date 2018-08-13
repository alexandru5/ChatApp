package main.java.dao;

import main.java.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepoInterface extends JpaRepository<Message, Integer> {

    Message findByMessageID(@Param("MessageID") int id);
    Message save(Message msg);
}
