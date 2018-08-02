package entities;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="UserID")
    private int userID;

    @Column(name="Name")
    private String userName;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

    @Column(name="ActivationToken")
    private String activationToken;

    @Column(name="isActive")
    private boolean isActive;

    @Column(name="NotificationType")
    private String notificationType;

    @Column(name="CreatedAt")
    private String createdAt;

    public User() {}

    public User(String userName, String email, String password, String activationToken, String notificationType, String createdAt) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.activationToken = activationToken;
        this.isActive = false;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public int getId() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User [id=" + userID + ", userName=" + userName + ", email=" + email + ", pass=" + password +
                ", active=" + isActive + ", activationTok=" + activationToken + "]";
    }

}
