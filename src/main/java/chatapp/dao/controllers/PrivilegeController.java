package chatapp.dao.controllers;

import chatapp.dao.repositories.PrivilegeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivilegeController {

    @Autowired
    PrivilegeRepoInterface repo;
}
