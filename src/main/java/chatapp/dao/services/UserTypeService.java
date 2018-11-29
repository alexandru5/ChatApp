package chatapp.dao.services;

import chatapp.dao.repositories.UserTypeRepoInterface;
import chatapp.entities.UserType;
import chatapp.exceptions.GroupNotFoundException;
import chatapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {

    @Autowired
    UserTypeRepoInterface repo;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    public void insertUserType(UserType userType) {
        repo.save(userType);
    }

    public void deleteUserTypeById(int id) {
        repo.deleteById(id);
    }

    public UserType findUserTypeById(int id) {
        return repo.findByTypeID(id).orElse(null);
    }

    public List<UserType> findUserTypesOfUser(int idUser) throws UserNotFoundException {
        if (!userService.exists(idUser)) throw new UserNotFoundException();
        return repo.findUserTypesOfUser(idUser);
    }

    public UserType findUserTypeOfUserInGroup(int idUser, int idGroup) throws UserNotFoundException, GroupNotFoundException {
        if (!userService.exists(idUser)) throw new UserNotFoundException();
        if (!groupService.exists(idGroup)) throw new GroupNotFoundException();

        return repo.findUserTypeOfUserInGroup(idUser, idGroup).orElse(null);
    }
}
