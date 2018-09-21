package chatapp.dao.services;

import chatapp.dao.repositories.PrivilegeRepoInterface;
import chatapp.entities.Privilege;
import chatapp.entities.UserType;
import chatapp.exceptions.GroupNotFoundException;
import chatapp.exceptions.PrivilegeNotFoundException;
import chatapp.exceptions.UserNotFoundException;
import chatapp.exceptions.UserTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PrivilegeService {

    @Autowired
    PrivilegeRepoInterface repo;

    @Autowired
    UserTypeService userTypeService;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    public void insertPrivilege(Privilege p) {
        repo.save(p);
    }

    public void deletePrivilegeById(int id) throws PrivilegeNotFoundException {
        if (!repo.existsById(id))
            throw new PrivilegeNotFoundException();
        repo.deleteById(id);
    }

    public Privilege findPrivilegeById(int id) {
        return repo.findByPrivilegeID(id).orElse(null);
    }

    public Privilege findPrivilegeWith(String containing) {
        return repo.findByPrivilegeNameContains(containing).orElse(null);
    }

    public Set<Privilege> findPrivilegesOfUserInGroup(int idUser, int idGroup) throws UserTypeNotFoundException,
            UserNotFoundException, GroupNotFoundException {
        if (!userService.exists(idUser)) throw new UserNotFoundException();
        if (!groupService.exists(idGroup)) throw new GroupNotFoundException();

        UserType userType = userTypeService.findUserTypeOfUserInGroup(idUser, idGroup);
        if (userType == null)
            throw new UserTypeNotFoundException();
        else
            return userType.getPrivileges();

    }
}
