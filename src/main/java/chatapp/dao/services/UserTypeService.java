package chatapp.dao.services;

import chatapp.dao.repositories.UserTypeRepoInterface;
import chatapp.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTypeService {

    @Autowired
    UserTypeRepoInterface repo;

    public void insertUserType(UserType userType) {
        repo.save(userType);
    }

    public void deleteUserTypeById(int id) {
        repo.deleteById(id);
    }

    public UserType findUserTypeById(int id) {
        return repo.findByTypeID(id).orElse(null);
    }

    public UserType findUserTypeOfUserInGroup(int idUser, int idGroup) {
        return repo.findUserTypeOfUserInGroup(idUser, idGroup).orElse(null);
    }
}
