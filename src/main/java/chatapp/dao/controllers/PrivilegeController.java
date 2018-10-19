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
    @PutMapping("/private/create")
    public void createPrivilege(@RequestBody Privilege privilege) {
            privilegeService.insertPrivilege(privilege);
    }

    @Transactional
    @DeleteMapping("/private/deleteById/{id}")
    public void deletePrivilegeById(@PathVariable int id) throws PrivilegeNotFoundException {
        privilegeService.deletePrivilegeById(id);
    }


    @GetMapping("/private/findById/{id}")
    public Privilege findPrivilegeById(@PathVariable int id) {
        return privilegeService.findPrivilegeById(id);
    }

    @GetMapping("/private/findContaining")
    public Privilege findPrivilegeContains(@RequestParam("containing") String containing) {
        return privilegeService.findPrivilegeWith(containing);
    }

    @GetMapping("/private/findPrivilegesOfUserInGroup/{idUser}/{idGroup}")
    public Set<Privilege> findPrivilegesOfUserInGroup(@PathVariable("idUser") int idUser, @PathVariable("idGroup") int idGroup) throws UserTypeNotFoundException,
                                                                            UserNotFoundException, GroupNotFoundException {
        return privilegeService.findPrivilegesOfUserInGroup(idUser, idGroup);

    }
}
