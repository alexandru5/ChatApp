package chatapp.dao.controllers;


import chatapp.dao.interfaces.UserTypeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTypeController {

    @Autowired
    UserTypeRepoInterface repo;
}