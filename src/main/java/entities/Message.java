package main.java.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MessageID")
    private int messageID;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "GroupID")
    private Group group;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "Message")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CreatedAt")
    private Date createdAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "NotificationID")
    private Notification notification;

    public Message() {}

    public Message(Group group, User user, String message, Date createdAt, Notification notification) {
        this.group = group;
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
        this.notification = notification;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public Group getGroupID() {
        return group;
    }

    public void setGroupID(Group group) {
        this.group = group;
    }

    public User getUserID() {
        return user;
    }

    public void setUserID(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "Message [id=" + messageID + ", group=" + group.getGroupID() + ", user=" + user.getUserID() + ", message=" + message +
                ", createdAt=" + createdAt + ", notifID=" + notification.getNotificationID() + "]";
    }
}
