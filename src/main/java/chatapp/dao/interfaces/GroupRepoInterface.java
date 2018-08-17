package chatapp.dao.interfaces;

import chatapp.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepoInterface extends JpaRepository<Group, Integer> {

    Group findByGroupID(int id);
}
