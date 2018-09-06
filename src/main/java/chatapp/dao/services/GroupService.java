package chatapp.dao.services;

import chatapp.dao.repositories.GroupRepoInterface;
import chatapp.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepoInterface repo;

    @Transactional
    public void insertGroup(Group g) {
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

    public void updateGroupPrivacy(int id, boolean isPrivate) {
        repo.updatePrivacyOfGroup(id, isPrivate);
    }

    public void updateGroupName(int id, String name) {
        repo.updateGroupName(id, name);
    }

    public Group getGroupByID(int id) {
        return repo.findByGroupID(id).orElse(null);
    }

    public List<Group> getGroupsCreatedBy(int id) {
        return repo.findAllByCreatedBy(id);
    }

    public List<Group> getGroupsOfUser(int id) {
        return repo.findGroupsOfUser(id);
    }

    public List<Group> getPublicGroupsOfUser(int id) {
        return repo.findPublicGroupsOfUser(id);
    }

    public List<Group> getPrivateGroupsOfUser(int id) {
        return repo.findPrivateGroupsOfUser(id);
    }


}
