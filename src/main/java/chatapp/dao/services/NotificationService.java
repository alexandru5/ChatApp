package chatapp.dao.services;

import chatapp.dao.repositories.NotificationRepoInterface;
import chatapp.entities.Notification;
import chatapp.exceptions.NotificationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationService {

    @Autowired
    NotificationRepoInterface repo;

    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(new Date());
        notification.setUpdatedAt(new Date());

        repo.save(notification);
        return notification;
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

    public void changeUpdatedAt(int id) throws NotificationNotFoundException {
        if (!repo.existsById(id)) throw new NotificationNotFoundException();
        repo.changeUpdatedAt(id, new Date());
    }

    public void changeFrequency(int id, int freq) throws NotificationNotFoundException {
        if (!repo.existsById(id)) throw new NotificationNotFoundException();
        repo.changeFrequency(id, freq);
    }

    public void changeMessage(int id, String message) throws NotificationNotFoundException {
        if (!repo.existsById(id)) throw new NotificationNotFoundException();
        repo.changeMessage(id, message);
    }
    public Notification findNotificationById(int id) {
        return repo.findById(id).orElse(null);
    }



}
