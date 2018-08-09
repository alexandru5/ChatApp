package main.java.entities;



import main.java.embedded.IsInID;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "IsIn")
public class IsIn {

    @EmbeddedId
    IsInID id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TypeID", nullable = false)
    private UserType type;

    @Column(name = "IsBlocked", nullable = false)
    boolean isBlocked;

    public IsIn() {}

    public IsIn(User user, Group group) {
        id = new IsInID(user.getUserID(), group.getGroupID());
        this.isBlocked = false;
    }

    public IsInID getId() {
        return id;
    }

    public void setId(IsInID id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IsIn that = (IsIn) o;
        return Objects.equals(id.getUserID(), that.getId().getUserID()) &&
                Objects.equals(id.getGroupID(), that.getId().getGroupID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getUserID(), id.getGroupID());
    }
}
