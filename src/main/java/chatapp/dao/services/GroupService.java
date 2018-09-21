package chatapp.dao.services;

import chatapp.dao.repositories.GroupRepoInterface;
import chatapp.entities.Group;
import chatapp.exceptions.GroupNotFoundException;
import chatapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepoInterface repo;

    @Autowired
    UserService userService;

    public boolean exists(int id) {
        return repo.existsById(id);
    }

    public void insertGroup(Group g) {
        repo.save(g);
    }

    public void deleteGroupByID(int id) {
        repo.deleteByGroupID(id);
    }

    public void updateGroupPrivacy(int id, boolean isPrivate) throws GroupNotFoundException {
        if (repo.existsById(id))
            repo.updatePrivacyOfGroup(id, isPrivate);
        else
            throw new GroupNotFoundException();
    }

    public void updateGroupName(int id, String name) throws GroupNotFoundException {
        if (repo.existsById(id))
            repo.updateGroupName(id, name);
        else
            throw new GroupNotFoundException();
    }

    public Group getGroupByID(int id) {
        return repo.findByGroupID(id).orElse(null);
    }

    public List<Group> getGroupsCreatedBy(int id) throws UserNotFoundException {
        if (userService.exists(id))
            return repo.findAllByCreatedBy(id);
        else
            throw new UserNotFoundException();
    }

    public List<Group> getGroupsOfUser(int id) throws UserNotFoundException {
        if (userService.exists(id))
            return repo.findGroupsOfUser(id);
        else
            throw new UserNotFoundException();
    }

    public List<Group> getPublicGroupsOfUser(int id) throws UserNotFoundException {
        if (userService.exists(id))
            return repo.findPublicGroupsOfUser(id);
        else
            throw new UserNotFoundException();
    }

    public List<Group> getPrivateGroupsOfUser(int id) throws UserNotFoundException {
        if (userService.exists(id))
            return repo.findPrivateGroupsOfUser(id);
        else
            throw new UserNotFoundException();
    }


}
