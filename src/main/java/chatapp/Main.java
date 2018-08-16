package chatapp;

import chatapp.dao.interfaces.UserRepoInterface;
import chatapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    UserRepoInterface userRepo;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //User us = new User ("Ion", "Dodo@gnmail.com", "qweqwrasd", "sdqe324435rsdf46543f", "phone", new java.util.Date());
        // userRepo.save(us);
        userRepo.deleteAll();
        List<User> rep = userRepo.findAll();
        //userRepo.deleteById(us.getUserID());

        for (User u : rep) {
            System.out.println(u);
        }
        //System.out.println(rep);

        System.exit(0);
    }
}
