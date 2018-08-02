package entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Group")
public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="GroupID")
    private int groupID;

    @Column(name="GroupName")
    private String groupName;

    @Column(name="IsPrivate")
    private boolean isPrivate;

    @Column(name="CreatedAt")
    private String createdAt;

    @OneToOne
    @JoinColumn(name="CreatedBy")
    private User createdBy;

    public Group() {}

    public Group(int groupID, String groupName, boolean isPrivate, String createdAt, User createdBy) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.isPrivate = isPrivate;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Group [id=" + groupID + ", groupName=" + groupName + ", private=" + isPrivate + ", CreatedAt=" + createdAt +
                ", createdBy=" + createdBy + "]";
    }
}
