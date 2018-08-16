package chatapp.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PrivilegeID")
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

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeID(int privilegeID) {
        this.privilegeID = privilegeID;
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
