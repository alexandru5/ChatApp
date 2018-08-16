package chatapp.dao.interfaces;

import chatapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoInterface extends JpaRepository<User, Integer> {

    User findByUserID(int id);
}
