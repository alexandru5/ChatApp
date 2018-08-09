package main.java.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="NotificationID")
    private int notificationID;

    @Column(name="NotificationMessage")
    private String notifiationMsg;

    @Column(name="Frequency")
    private int frequency;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CreatedAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UpdatedAt")
    private Date updatedAt;

    public Notification() {}

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getNotifiationMsg() {
        return notifiationMsg;
    }

    public void setNotifiationMsg(String notifiationMsg) {
        this.notifiationMsg = notifiationMsg;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Notification [id=" + notificationID + ", msg=" + notifiationMsg + ", freq=" + frequency + ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt + "]";
    }
}
