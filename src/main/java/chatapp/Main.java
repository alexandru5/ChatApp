package chatapp;

import chatapp.dao.interfaces.NotificationRepoInterface;
import chatapp.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    NotificationRepoInterface userRepo;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //User us = userRepo.findByUserID(5);//= new User ("Ion", "Dodo@gnmail.com", "qweqwrasd", "sdqe324435rsdf46543f", "phone", new java.util.Date());
        Notification g = userRepo.findByNotificationID(1);
        //userRepo.save(us);
        //userRepo.deleteAll();
        //User rep = userRepo.findAll();
        //userRepo.deleteById(us.getUserID());
        System.out.println(g);

        System.exit(0);
    }
}
