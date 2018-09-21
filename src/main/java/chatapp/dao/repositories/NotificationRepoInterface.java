package chatapp.dao.repositories;

import chatapp.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface NotificationRepoInterface extends JpaRepository<Notification, Integer> {

    Optional<Notification> findById(Integer integer);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notification n SET n.updatedAt = :date  WHERE n.notificationID = :id")
    void changeUpdatedAt(@Param("id") int id, @Param("date") Date date);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notification n SET n.frequency = :freq  WHERE n.notificationID = :id")
    void changeFrequency(@Param("id") int id, @Param("freq") int freq);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notification n SET n.notifiationMsg = :message  WHERE n.notificationID = :id")
    void changeMessage(@Param("id") int id, @Param("message") String message);
}
