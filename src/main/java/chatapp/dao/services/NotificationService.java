package chatapp.dao.services;

import chatapp.dao.repositories.NotificationRepoInterface;
import chatapp.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    NotificationRepoInterface repo;

    public Notification findNotificationById(int id) {
        return repo.findById(id).orElse(null);
    }


}
