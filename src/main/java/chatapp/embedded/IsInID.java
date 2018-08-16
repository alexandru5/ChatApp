package chatapp.embedded;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IsInID implements Serializable {
    @Column(name = "UserID")
    private int userID;

    @Column(name = "GroupID")
    private int groupID;

    public IsInID() {}

    public IsInID(int userID, int groupID) {
        this.userID = userID;
        this.groupID = groupID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IsInID that = (IsInID) o;
        return Objects.equals(userID, that.userID) &&
                Objects.equals(groupID, that.groupID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, groupID);
    }
}
