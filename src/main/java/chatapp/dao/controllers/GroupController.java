package chatapp.dao.controllers;


import chatapp.dao.services.GroupService;
import chatapp.entities.Group;
import chatapp.exceptions.GroupNotFoundException;
import chatapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Transactional
    @PutMapping("/private/create")
    public void createGroup(@RequestBody Group group) {
        group.setCreatedAt(new Date());
        groupService.insertGroup(group);

    }

    @Transactional
    @DeleteMapping("/private/deleteById/{id}")
    public void deleteGroupByID(@PathVariable int id) {
        groupService.deleteGroupByID(id);
    }

    @Transactional
    @PutMapping("/private/changePrivacy/{id}")
    public void changePrivacy(@PathVariable int id, @RequestParam("isPrivate") boolean isPrivate) throws GroupNotFoundException {
        groupService.updateGroupPrivacy(id, isPrivate);
    }

    @Transactional
    @PutMapping("/private/changeName/{id}")
    public void changeGroupName(@PathVariable int id, @RequestParam("name") String name) throws GroupNotFoundException {
        groupService.updateGroupName(id, name);
    }

    @GetMapping("/private/findById/{id}")
    public Group getGroupByID(@PathVariable int id) {
        return groupService.getGroupByID(id);
    }

    @GetMapping("/private/findCreatedBy/{id}")
    public List<Group> getGroupsCreatedBy(@PathVariable int id) throws UserNotFoundException {
        return groupService.getGroupsCreatedBy(id);
    }

    @GetMapping("/private/findGroupsOfUser/{id}")
    public List<Group> getGroupsOfUser(@PathVariable int id) throws UserNotFoundException {
        return groupService.getGroupsOfUser(id);
    }

    @GetMapping("/private/findPrivateGroupsOfUser/{id}")
    public List<Group> getPrivateGroupsOfUser(@PathVariable int id) throws UserNotFoundException {
        return groupService.getPrivateGroupsOfUser(id);
    }

    @GetMapping("/private/findPublicGroupsOfUser/{id}")
    public List<Group> getPublicGroupsOfUser(@PathVariable int id) throws UserNotFoundException {
        return groupService.getPublicGroupsOfUser(id);
    }
}
