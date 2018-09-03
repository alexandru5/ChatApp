package chatapp.dao.repositories;

import chatapp.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepoInterface extends JpaRepository<Notification, Integer> {

    Notification findByNotificationID(int id);
}
