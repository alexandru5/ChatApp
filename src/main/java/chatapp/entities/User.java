package chatapp.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`User`")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="UserID", unique = true, nullable = false)
    private int userID;

    @Column(name="Name")
    private String userName;

    @Column(name="Email", unique = true, nullable = false)
    private String email;

    @Column(name="Password", nullable = false)
    private String password;

    @Column(name="ActivationToken", unique = true, nullable = false)
    private String activationToken;

    @Column(name="isActive", nullable = false)
    private boolean isActive;

    @Column(name="NotificationType", nullable = false)
    private String notificationType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CreatedAt", nullable = false)
    private Date createdAt;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "IsIn",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "GroupID")
    )
    private Set<Group> groups = new HashSet<>();

    public User() {}

    public User(String userName, String email, String password, String activationToken, String notificationType, Date createdAt) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.activationToken = activationToken;
        this.isActive = false;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User [id=" + userID + ", userName=" + userName + ", email=" + email + ", pass=" + password +
                ", active=" + isActive + ", activationTok=" + activationToken + "createdAt=" + createdAt + "]";
    }

    public void print() {
        for (Group g : groups)
            System.out.println(g.getGroupID());
    }

}
