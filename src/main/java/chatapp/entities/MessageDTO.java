package chatapp.entities;

public class MessageDTO {
    private int groupID;
    private int userID;
    private String message;
    private int notificationID;

    public MessageDTO() {}

    public MessageDTO(int groupID, int userID, String message, int notificationID) {
        this.groupID = groupID;
        this.userID = userID;
        this.message = message;
        this.notificationID = notificationID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    @Override
    public String toString() {
        return "Message [group=" + groupID + ", user=" + userID + ", message=" + message +
                ", notifID=" + notificationID + "]";
    }
}
