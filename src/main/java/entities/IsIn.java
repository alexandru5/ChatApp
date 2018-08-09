package main.java.entities;



import main.java.embedded.IsInID;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "IsIn")
public class IsIn {

    @EmbeddedId
    IsInID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("groupID")
    private Group group;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TypeID", nullable = false)
    private UserType type;

    @Column(name = "IsBlocked", nullable = false)
    boolean isBlocked;

    public IsIn() {}

    public IsIn(User user, Group group) {
        id = new IsInID(user.getUserID(), group.getGroupID());
        this.user = user;
        this.group = group;
        this.isBlocked = false;
    }

    public IsInID getId() {
        return id;
    }

    public void setId(IsInID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        return Objects.equals(user, that.user) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, group);
    }
}
