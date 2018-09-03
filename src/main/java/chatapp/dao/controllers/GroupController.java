package chatapp.dao.controllers;


import chatapp.dao.services.GroupService;
import chatapp.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService repo;

    @RequestMapping(value = "/getByID", method = RequestMethod.GET)
    public Optional<Group> getGroupByID(@RequestParam("id") int id) {
        return repo.getGroupByID(id);
    }

    @RequestMapping(value = "/getCreatedBy", method = RequestMethod.GET)
    public List<Group> getGroupsCreatedBy(@RequestParam("id") int id) {
        return repo.getGroupsCreatedBy(id);
    }

    }
