package chatapp.dao.repositories;

import chatapp.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepoInterface extends JpaRepository<Group, Integer> {

    Optional<Group> findByGroupID(int id);

    @Query("UPDATE Group g SET g.isPrivate = :isPrivate WHERE g.groupID = :id")
    void updatePrivacyOfGroup(@Param("id") int id, @Param("isPrivate") boolean isPrivate);

    @Query("UPDATE Group g SET g.groupName = :name WHERE g.groupID = :id")
    void updateGroupName(@Param("id") int id, @Param("name") String name);

    @Query("SELECT g FROM Group g WHERE g.createdBy.userID = :id")
    List<Group> findAllByCreatedBy(@Param("id") int id);

    @Query("SELECT g FROM Group g JOIN IsIn i ON i.id.userID = :id")
    List<Group> findGroupsOfUser(@Param("id") int id);

    @Query("SELECT g FROM Group g JOIN IsIn i ON i.id.userID = :id WHERE g.isPrivate = false")
    List<Group> findPublicGroupsOfUser(@Param("id") int id);

    @Query("SELECT g FROM Group g JOIN IsIn i ON i.id.userID = :id WHERE g.isPrivate = true")
    List<Group> findPrivateGroupsOfUser(@Param("id") int id);

}
