package chatapp.dao.controllers;

import chatapp.dao.interfaces.NotificationRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    NotificationRepoInterface repo;
}