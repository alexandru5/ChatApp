package chatapp.dao.repositories;

import chatapp.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepoInterface extends JpaRepository<Privilege, Integer> {

    Optional<Privilege> findByPrivilegeID(int id);
    Optional<Privilege> findByPrivilegeNameContains(String name);
}
