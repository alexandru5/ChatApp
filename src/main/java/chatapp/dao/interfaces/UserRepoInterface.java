package chatapp.dao.interfaces;

import chatapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepoInterface extends JpaRepository<User, Integer> {

    Optional<User> findByUserID(int id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
