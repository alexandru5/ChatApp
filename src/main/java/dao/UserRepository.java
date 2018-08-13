package main.java.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserRepository {
    @Autowired
    UserRepoInterface repo;

    @RequestMapping("/findbyuserid")
    public Optional findByUserId(@RequestParam("UserID") int id){
        Optional result = repo.findById(id);
        return result;
    }
}
