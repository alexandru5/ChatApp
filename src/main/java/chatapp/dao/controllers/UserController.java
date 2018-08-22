package chatapp.dao.controllers;

import chatapp.dao.interfaces.UserRepoInterface;
import chatapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepoInterface repo;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insertUserIntoDB(@RequestParam("us") User us) {
        repo.save(us);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("us") User us) {
        repo.delete(us);
    }

    @RequestMapping(value = "/deletebyid", method = RequestMethod.DELETE)
    public void deleteUserByID(@RequestParam("id") int id) {
        //Optional<User> s = repo.findByUserID(id);
        repo.deleteById(id);
    }

    @RequestMapping("/findbyid")
    public Optional<User> findUserByID(@RequestParam("id") int id) {
        return repo.findByUserID(id);
    }

    @RequestMapping("/findbyemail")
    public Optional<User> findUserByEmail(@RequestParam("email") String email) {
        return repo.findByEmail(email);
    }

    @RequestMapping("/findallingroup")
    public List<User> findAllUsersInGroup(@RequestParam("id") int id) {
        return repo.getUsersByGroupID(id);
    }


}
