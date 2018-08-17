package chatapp.dao.repositories;


import chatapp.dao.interfaces.UserRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRepository {

    @Autowired
    UserRepoInterface repo;
}
