package chatapp;

import chatapp.dao.interfaces.NotificationRepoInterface;
import chatapp.dao.controllers.MessageController;
import chatapp.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    NotificationRepoInterface userRepo;

    //@Autowired
    //MessageRepoInterface mr;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
