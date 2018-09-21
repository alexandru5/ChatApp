package chatapp.dao.repositories;

import chatapp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepoInterface extends JpaRepository<Message, Integer> {
    void deleteByMessageID(int id);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Message m WHERE m.user.userID = :id")
    void deleteByUserID(@Param("id") int id);


    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Message m WHERE m.group.groupID = :id")
    void deleteByGroupID(@Param("id") int id);

    Optional<Message> findByMessageID(int id);

    @Query("SELECT m FROM Message m WHERE m.user.userID = :id")
    List<Message> findAllByGroupID(@Param("id") int id);

    @Query("SELECT m FROM Message m WHERE m.group.groupID = :id")
    List<Message> findAllByUserID(@Param("id") int id);
}
