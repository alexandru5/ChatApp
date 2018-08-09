package main.java.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`Group`")
public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="GroupID", nullable = false)
    private int groupID;

    @Column(name="GroupName")
    private String groupName;

    @Column(name="IsPrivate", nullable = false)
    private boolean isPrivate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CreatedAt", nullable = false)
    private Date createdAt;

    @OneToOne(cascade = CascadeType.ALL,
              orphanRemoval = true)
    @JoinColumn(name="CreatedBy", nullable = false)
    private User createdBy;

    /*@OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<IsIn> users = new HashSet<>();*/


    public Group() {}

    public Group(String groupName, boolean isPrivate, Date createdAt, User createdBy) {
        this.groupName = groupName;
        this.isPrivate = isPrivate;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
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
