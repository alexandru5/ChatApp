package chatapp.dao.controllers;

import chatapp.dao.services.PrivilegeService;
import chatapp.entities.Privilege;
import chatapp.exceptions.GroupNotFoundException;
import chatapp.exceptions.PrivilegeNotFoundException;
import chatapp.exceptions.UserNotFoundException;
import chatapp.exceptions.UserTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    @Autowired
    PrivilegeService privilegeService;



    @Transactional
    @PutMapping("/create")
    public void createPrivilege(@RequestBody Privilege privilege) {
            privilegeService.insertPrivilege(privilege);
    }

    @Transactional
    @DeleteMapping("/deleteById")
    public void deletePrivilegeById(@RequestParam("id") int id) throws PrivilegeNotFoundException {
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
    public Set<Privilege> findPrivilegesOfUserInGroup(int idUser, int idGroup) throws UserTypeNotFoundException,
                                                                            UserNotFoundException, GroupNotFoundException {
        return privilegeService.findPrivilegesOfUserInGroup(idUser, idGroup);

    }
}
