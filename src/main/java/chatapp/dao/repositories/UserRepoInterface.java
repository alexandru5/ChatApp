package chatapp.dao.repositories;

import chatapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepoInterface extends JpaRepository<User, Integer> {

    Optional<User> findByUserID(int id);
    Optional<User> findByUserName(String username);
    Optional<User> findByPhoneNo(String phoneNo);
    Optional<User> findByEmail(String email);
    Optional<User> findByActivationToken(String activationToken);
    Optional<User> findByUserIDAndActivationToken(int id, String activationToken);
    List<User> findAll();

    boolean existsById(Integer integer);
    boolean existsByEmail(String email);
    boolean existsByUserName(String username);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.userName = :name WHERE u.userID = :id")
    void updateUserName(@Param("id") int id, @Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.email = :email WHERE u.userID = :id")
    void updateEmail(@Param("id") int id, @Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.phoneNo = :phoneNo WHERE u.userID = :id")
    void updatePhoneNo(@Param("id") int id, @Param("phoneNo") String phoneNo);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.notificationType = :notificationType WHERE u.userID = :id")
    void updateNotificationType(@Param("id") int id, @Param("notificationType") String notificationType);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.isActive = :isActive WHERE u.userID = :id")
    void updateActivity(@Param("id") int id, @Param("isActive") boolean isActive);

    @Query("SELECT u FROM User u JOIN IsIn i ON u.userID = i.id.userID WHERE i.id.groupID = :id")
    List<User> findByGroupID(@Param("id") int id);

    @Query("SELECT u FROM User u JOIN IsIn i ON u.userID = i.id.userID WHERE i.id.groupID = :id AND u.isActive = true")
    List<User> findActiveUsersInGroup(@Param("id") int id);

}
