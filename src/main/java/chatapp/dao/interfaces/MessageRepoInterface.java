package chatapp.dao.interfaces;

import chatapp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepoInterface extends JpaRepository<Message, Integer> {

    Message findByMessageID(int id);
    Message save(Message msg);
}
