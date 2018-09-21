package chatapp.dao.controllers;


import chatapp.dao.services.GroupService;
import chatapp.entities.Group;
import chatapp.exceptions.GroupNotFoundException;
import chatapp.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    @PutMapping("/create")
    public void createGroup(@RequestBody Group group) {
        group.setCreatedAt(new Date());
        groupService.insertGroup(group);

    }

    @Transactional
    @DeleteMapping("/deleteById")
    public void deleteGroupByID(@Param("id") int id) {
        groupService.deleteGroupByID(id);
    }

    @Transactional
    @PutMapping("/changePrivacy")
    public void changePrivacy(@Param("id") int id, @Param("isPrivate") boolean isPrivate) throws GroupNotFoundException {
        groupService.updateGroupPrivacy(id, isPrivate);
    }

    @Transactional
    @PutMapping("/changeName")
    public void changeGroupName(@Param("id") int id, @Param("name") String name) throws GroupNotFoundException {
        groupService.updateGroupName(id, name);
    }

    @GetMapping("/findById")
    public Group getGroupByID(@RequestParam("id") int id) {
        return groupService.getGroupByID(id);
    }

    @GetMapping("/findCreatedBy")
    public List<Group> getGroupsCreatedBy(@RequestParam("id") int id) throws UserNotFoundException {
        return groupService.getGroupsCreatedBy(id);
    }

    @GetMapping("/findGroupsOfUser")
    public List<Group> getGroupsOfUser(@RequestParam("id") int id) throws UserNotFoundException {
        return groupService.getGroupsOfUser(id);
    }

    @GetMapping("/findPrivateGroupsOfUser")
    public List<Group> getPrivateGroupsOfUser(@RequestParam("id") int id) throws UserNotFoundException {
        return groupService.getPrivateGroupsOfUser(id);
    }

    @GetMapping("/findPublicGroupsOfUser")
    public List<Group> getPublicGroupsOfUser(@RequestParam("id") int id) throws UserNotFoundException {
        return groupService.getPublicGroupsOfUser(id);
    }
}
