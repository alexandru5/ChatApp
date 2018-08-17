package chatapp.dao.repositories;


import chatapp.dao.interfaces.GroupRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupRepository {

    @Autowired
    GroupRepoInterface repo;
}
