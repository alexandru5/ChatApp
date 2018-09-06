package chatapp.dao.repositories;

import chatapp.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepoInterface extends JpaRepository<Notification, Integer> {

    @Override
    Optional<Notification> findById(Integer integer);

}
