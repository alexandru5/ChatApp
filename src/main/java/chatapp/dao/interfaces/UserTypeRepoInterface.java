package chatapp.dao.interfaces;

import chatapp.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepoInterface extends JpaRepository<UserType, Integer> {

    UserType findByTypeID(int id);
}
