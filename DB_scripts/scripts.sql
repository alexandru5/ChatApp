 #select groups and type of user in witch is an user
SELECT 
    GroupID, TypeID
FROM
    chat_app.IsIn
WHERE
    UserID = 8;

#select users from a group
SELECT
    UserID
FROM
    chat_app.IsIn
WHERE
    GroupID = 2;

#select messages of an user
SELECT 
    chat_app.Message.MessageID, chat_app.Message.Message
FROM
    chat_app.Message
WHERE
    chat_app.Message.UserID = 5;

#select privileges for an user from a group
SELECT 
    chat_app.HasPrivilege.PrivilegeID
FROM
    chat_app.HasPrivilege
        NATURAL JOIN
    chat_app.IsIn
WHERE
    chat_app.IsIn.UserID = 8
        AND chat_app.IsIn.GroupID = 2;

#select users and messages to be notified for in a group
SELECT 
    chat_app.IsIn.UserID,
    chat_app.Message.MessageID,
    chat_app.Message.CreatedAt,
    chat_app.Message.NotificationID,
    chat_app.Notification.NotificationMessage,
    chat_app.Notification.Frequency
FROM
    chat_app.IsIn
        JOIN
    chat_app.Message ON chat_app.Message.GroupID = chat_app.IsIn.GroupID
		JOIN
	chat_app.Notification ON chat_app.Notification.NotificationID = chat_app.Message.NotificationID
WHERE
    chat_app.IsIn.UserID != chat_app.Message.UserID
        AND chat_app.IsIn.GroupID = 2;

#select Notification details for an user from group for a specific Message
SELECT
    chat_app.IsIn.UserID,
    chat_app.Message.MessageID,
    chat_app.Message.CreatedAt,
    chat_app.Message.NotificationID,
    chat_app.Notification.NotificationMessage,
    chat_app.Notification.Frequency
FROM
    chat_app.IsIn
        JOIN
    chat_app.Message ON chat_app.Message.GroupID = chat_app.IsIn.GroupID
		JOIN
	chat_app.Notification ON chat_app.Notification.NotificationID = chat_app.Message.NotificationID
WHERE
    chat_app.IsIn.UserID != chat_app.Message.UserID
        AND chat_app.Message.MessageID = 1;


