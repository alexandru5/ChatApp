package chatapp.dao.controllers;

import chatapp.dao.services.PrivilegeService;
import chatapp.dao.services.UserTypeService;
import chatapp.entities.Privilege;
import chatapp.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    UserTypeService userTypeService;

    @Transactional
    @PutMapping("/create")
    public void createPrivilege(@RequestBody Privilege privilege) {
            privilegeService.insertPrivilege(privilege);
    }

    @Transactional
    @DeleteMapping("/deleteById")
    public void deletePrivilegeById(@RequestParam("id") int id) {
        privilegeService.deletePrivilegeById(id);
    }


    @GetMapping("/findById")
    public Privilege findPrivilegeById(@RequestParam("id") int id) {
        return privilegeService.findPrivilegeById(id);
    }

    @GetMapping("/findContaining")
    public Privilege findPrivilegeContains(@RequestParam("containing") String containing) {
        return privilegeService.findPrivilegeWith(containing);
    }

    @GetMapping("/findPrivilegesOfUserInGroup")
    public Set<Privilege> findPrivilegesOfUserInGroup(int idUser, int idGroup) {
        UserType userType = userTypeService.findUserTypeOfUserInGroup(idUser, idGroup);

        if (userType != null)
            return userType.getPrivileges();
        else
            return null;
    }
}
