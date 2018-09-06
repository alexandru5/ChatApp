package chatapp.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Privilege")
public class Privilege {
    @Id
    @Column(name="PrivilegeID", unique = true)
    private int privilegeID;

    @Column(name="PrivilegeName")
    private String privilegeName;

    @ManyToMany(mappedBy = "privileges")
    private Set<UserType> userTypes = new HashSet<>();

    public Privilege() {}

    public Privilege(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public int getPrivilegeID() {
        return privilegeID;
    }

    public void setPrivilegeID(int privilegeID) {
        this.privilegeID = privilegeID;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public void print() {
        for (UserType g : userTypes)
            System.out.println(g.getTypeID());
    }

    @Override
    public String toString() {
        return "Privilege [id=" + privilegeID + ", name=" + privilegeName + "]";
    }
}
