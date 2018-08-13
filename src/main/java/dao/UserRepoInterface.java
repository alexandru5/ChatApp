package main.java.dao;

import main.java.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoInterface extends JpaRepository<User, Integer> {

    User findByUserID(@Param("UserID") int id);
    User save(User user);
}
