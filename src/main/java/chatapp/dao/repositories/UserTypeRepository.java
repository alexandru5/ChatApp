package chatapp.dao.repositories;


import chatapp.dao.interfaces.UserTypeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTypeRepository {

    @Autowired
    UserTypeRepoInterface repo;
}