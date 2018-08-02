package entities;

import javax.persistence.*;

@Entity
@Table(name = "UserType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private int typeID;

    @Column(name = "TypeName")
    private String typeName;

    public UserType() {}

    public UserType(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeID() {

        return typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "UserType [TypeID=" + typeID + ", TypeName=" + typeName + "]";
    }
}