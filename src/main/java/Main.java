package main.java;

import main.java.dao.UserRepoInterface;
import main.java.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("main.java.dao")
@EntityScan("main.java.entities")
public class Main implements CommandLineRunner {
    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepoInterface userRepo;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //User us = new User ("Ion", "Dodo@gnmail.com", "qweqwrasd", "sdqe324435rsdf46543f", "phone", new java.util.Date());
        //userRepo.save(us);
        List<User> rep = userRepo.findAll();
        System.out.println("DataSource: " + dataSource);
        for (User u : rep) {
            System.out.println(u);
        }

        System.exit(0);
    }
}
