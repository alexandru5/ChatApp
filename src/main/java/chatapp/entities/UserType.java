package chatapp.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "UserType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private int typeID;

    @Column(name = "TypeName")
    private String typeName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "HasPrivilege",
            joinColumns = @JoinColumn(name = "TypeID"),
            inverseJoinColumns = @JoinColumn(name = "PrivilegeID")
    )
    private Set<Privilege> privileges = new HashSet<>();

    public UserType() {}

    public UserType(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void print() {
        for (Privilege g : privileges)
            System.out.println(g.getPrivilegeID());
    }

    @Override
    public String toString() {
        return "UserType [TypeID=" + typeID + ", TypeName=" + typeName + "]";
    }
}