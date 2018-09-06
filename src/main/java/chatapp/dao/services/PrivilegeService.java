package chatapp.dao.services;

import chatapp.dao.repositories.PrivilegeRepoInterface;
import chatapp.entities.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService {

    @Autowired
    PrivilegeRepoInterface repo;

    public void insertPrivilege(Privilege p) {
        repo.save(p);
    }

    public void deletePrivilegeById(int id) {
        repo.deleteById(id);
    }

    public Privilege findPrivilegeById(int id) {
        return repo.findByPrivilegeID(id).orElse(null);
    }

    public Privilege findPrivilegeWith(String containing) {
        return repo.findByPrivilegeNameContains(containing).orElse(null);
    }
}
