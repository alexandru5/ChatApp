package chatapp.dao.controllers;


import chatapp.dao.services.GroupService;
import chatapp.entities.Group;
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

    @DeleteMapping("/deleteById")
    public void deleteGroupByID(@Param("id") int id) {
        groupService.deleteGroupByID(id);
    }

    @GetMapping("/getByID")
    public Group getGroupByID(@RequestParam("id") int id) {
        return groupService.getGroupByID(id);
    }

    @GetMapping("/getCreatedBy")
    public List<Group> getGroupsCreatedBy(@RequestParam("id") int id) {
        return groupService.getGroupsCreatedBy(id);
    }

    @GetMapping("/getGroupsOfUser")
    public List<Group> getGroupsOfUser(@RequestParam("id") int id) {
        return groupService.getGroupsOfUser(id);
    }

    @GetMapping("/getPrivateGroupsOfUser")
    public List<Group> getPrivateGroupsOfUser(@RequestParam("id") int id) {
        return groupService.getPrivateGroupsOfUser(id);
    }

    @GetMapping("/getPublicGroupsOfUser")
    public List<Group> getPublicGroupsOfUser(@RequestParam("id") int id) {
        return groupService.getPublicGroupsOfUser(id);
    }
}
