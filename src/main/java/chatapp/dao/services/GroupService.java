package chatapp.dao.services;

import chatapp.dao.repositories.GroupRepoInterface;
import chatapp.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepoInterface repo;

    @Transactional
    public void insertGroup(Group g) {
        g.setCreatedAt(new Date());
        repo.save(g);
    }

    @Transactional
    public void deleteGroup(Group g) {
        repo.delete(g);
    }

    @Transactional
    public void deleteGroupByID(int id) {
        repo.deleteById(id);
    }

    public Optional<Group> getGroupByID(int id) {
        return repo.findByGroupID(id);
    }

    public List<Group> getGroupsCreatedBy(int id) {
        return repo.findAllByCreatedBy(id);
    }

    public List<Group> getGroupsOfUser(int id) {
        return repo.findGroupsOfUser(id);
    }


}
