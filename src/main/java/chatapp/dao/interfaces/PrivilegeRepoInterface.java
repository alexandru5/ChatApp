package chatapp.dao.interfaces;

import chatapp.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepoInterface extends JpaRepository<Privilege, Integer> {

    Privilege findByPrivilegeID(int id);
}
