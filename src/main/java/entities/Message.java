package entities;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="GroupID")
    private int messageID;

    @Column(name = "GroupID")
    private int groupID;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "Message")
    private String message;

    @Column(name = "CreatedAt")
    private String createdAt;


    @JoinColumn(name = "NotificationID")
    private Notification notification;
}
