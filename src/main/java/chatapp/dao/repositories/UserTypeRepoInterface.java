package chatapp.dao.repositories;

import chatapp.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTypeRepoInterface extends JpaRepository<UserType, Integer> {

    Optional<UserType> findByTypeID(int id);

    @Query("SELECT ut FROM UserType ut JOIN IsIn i ON ut.typeID = i.type.typeID " +
                "WHERE i.id.userID = :idUser AND i.id.groupID = :idGroup")
    Optional<UserType> findUserTypeOfUserInGroup(@Param("idUser") int idUser, @Param("idGroup") int idGroup);

    @Query("SELECT ut FROM UserType ut JOIN IsIn i ON ut.typeID = i.type.typeID WHERE i.id.userID = :idUser")
    List<UserType> findUserTypesOfUser(@Param("idUser") int idUser);
}
