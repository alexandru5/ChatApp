package chatapp.dao.interfaces;

import chatapp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepoInterface extends JpaRepository<Message, Integer> {

    Message findByMessageID(int id);

    @Query("SELECT m FROM Message m WHERE m.user.userID = :id")
    List<Message> findAllByUserID(@Param("id") int id);
}
