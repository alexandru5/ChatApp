package chatapp.dao.controllers;

import chatapp.dao.services.NotificationService;
import chatapp.entities.Notification;
import chatapp.exceptions.NotificationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Transactional
    @PutMapping("/create")
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @Transactional
    @DeleteMapping("/deleteById")
    public void deleteNotificationById(@RequestParam("id") int id) {
        notificationService.deleteById(id);
    }

    @Transactional
    @PutMapping("/changeUpdatedAt")
    public void changeUpdatedAt(@RequestParam("id") int id) throws NotificationNotFoundException {
        notificationService.changeUpdatedAt(id);
    }

    @Transactional
    @PutMapping("/changeFrequency")
    public void changeFrequency(@RequestParam("id") int id, @RequestParam("freq") int freq) throws NotificationNotFoundException {
        notificationService.changeFrequency(id, freq);
    }

    @Transactional
    @PutMapping("/changeMessage")
    public void changeMessage(@RequestParam("id") int id, @RequestParam("message") String message) throws NotificationNotFoundException {
        notificationService.changeMessage(id, message);
    }

    @GetMapping("/findById")
    public Notification findNotificationById(int id) {
        return notificationService.findNotificationById(id);
    }
}
