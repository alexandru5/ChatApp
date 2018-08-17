package chatapp.dao.repositories;

import chatapp.dao.interfaces.PrivilegeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivilegeRepository {

    @Autowired
    PrivilegeRepoInterface repo;
}
