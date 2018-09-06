package chatapp.dao.controllers;


import chatapp.dao.services.PrivilegeService;
import chatapp.dao.services.UserTypeService;
import chatapp.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/userType")
public class UserTypeController {

    @Autowired
    UserTypeService serviceUserType;

    @Autowired
    PrivilegeService servicePrivilege;

    @Transactional
    @PutMapping("/create")
    public void createUserType(@RequestBody UserType userType) {
        serviceUserType.insertUserType(userType);
    }

    @Transactional
    @DeleteMapping("/deleteById")
    public void deleteUserTypeById(@RequestParam("id") int id) {
        serviceUserType.deleteUserTypeById(id);
    }

    @GetMapping("/findById")
    public UserType findUserTypeById(@RequestParam("id") int id) {
        return serviceUserType.findUserTypeById(id);
    }

    @GetMapping("/findUserTypeOfUserInGroup")
    public UserType findUserTypeOfUserInGroup(int idUser, int idGroup) {
        return serviceUserType.findUserTypeOfUserInGroup(idUser, idGroup);
    }
}