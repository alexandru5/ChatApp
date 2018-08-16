package chatapp.dao.repositories;


import chatapp.dao.interfaces.UserRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserRepository {

    @Autowired
    UserRepoInterface repo;

    @GetMapping("/findbyuserid")
    public Optional findByUserId(int id){
        Optional result = repo.findById(id);
        return result;
    }

}
