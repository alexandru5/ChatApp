USE chat_app;

CREATE TABLE `Privilege` (
 PrivilegeID   INT NOT NULL ,
 PrivilegeName VARCHAR(50) NOT NULL ,

 PRIMARY KEY (PrivilegeID)
);

CREATE TABLE `Notification` (
 NotificationID      INT NOT NULL ,
 NotificationMessage VARCHAR(255) NOT NULL ,
 Frequency           INT NOT NULL ,
 CreatedAt           DATETIME NOT NULL ,
 UpdatedAt           DATETIME NOT NULL ,

 PRIMARY KEY (NotificationID)
);

CREATE TABLE `UserType` (
 TypeID   INT NOT NULL ,
 TypeName VARCHAR(50) NOT NULL ,

 PRIMARY KEY (TypeID)
);

CREATE TABLE `User`
(
 UserID           INT NOT NULL auto_increment,
 Name     		  VARCHAR(40) NOT NULL ,
 Email            VARCHAR(40) NOT NULL unique,
 Password         VARCHAR(30) NOT NULL ,
 isActive         BOOLEAN NOT NULL ,
 NotificationType VARCHAR(30) NOT NULL ,
 CreatedAt        DATETIME NOT NULL ,

 PRIMARY KEY (UserID)
);

CREATE TABLE `HasPrivilege`
(
 TypeID      INT NOT NULL ,
 PrivilegeID INT NOT NULL ,

 PRIMARY KEY (TypeID, PrivilegeID),
 FOREIGN KEY (TypeID) REFERENCES `UserType`(TypeID),
 FOREIGN KEY (PrivilegeID) REFERENCES `Privilege`(PrivilegeID)
);

CREATE TABLE `Group`
(
 GroupID   INT NOT NULL auto_increment,
 GroupName VARCHAR(100) NULL unique,
 IsPrivate BOOLEAN NOT NULL ,
 CreatedAt DATETIME NOT NULL ,
 CreatedBy INT NOT NULL ,

 PRIMARY KEY (GroupID),
 FOREIGN KEY (CreatedBy) REFERENCES `User`(UserID)
);

CREATE TABLE `Message`
(
 MessageID      INT NOT NULL auto_increment,
 GroupID        INT NOT NULL ,
 UserID         INT NOT NULL ,
 Message        VARCHAR(255) NOT NULL ,
 CreatedAt      DATETIME NOT NULL ,
 NotificationID INT NOT NULL ,

 PRIMARY KEY (MessageID),
 FOREIGN KEY (GroupID) REFERENCES `Group`(GroupID),
 FOREIGN KEY (UserID) REFERENCES `User`(UserID),
 FOREIGN KEY (NotificationID) REFERENCES `Notification`(NotificationID)
);

CREATE TABLE `IsIn`
(
 UserID    INT NOT NULL ,
 GroupID   INT NOT NULL ,
 TypeID    INT NOT NULL ,
 IsBlocked BIT NOT NULL ,

 PRIMARY KEY (UserID, GroupID),
 FOREIGN KEY (UserID) REFERENCES `User`(UserID),
 FOREIGN KEY (GroupID) REFERENCES `Group`(GroupID),
 FOREIGN KEY (TypeID) REFERENCES `UserType`(TypeID)
);

#insert users
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Ion", "Dodo@gnmail.com", "qweqwrasd", false, "phone", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Mircea", "Popovic@yahoo.com", "asdwasresas", false, "email", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Bubu", "Nebuna", "sqwug=psulda", false, "phone", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Mirel", "Radoi@gnmail.com", "amnammingeatraglapoarta", false, "phone", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Viorel", "Liss@yahoo.com", "fosilaepava", false, "email", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Dorian", "Pupa@yahoo.com", "qweqsdsd", false, "phone", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Mssd", "Popc@gnmail.com", "axcas", false, "email", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Buabd", "Neba@yahoo.com", "43dfezcxvr3", false, "email", NOW());

#insert groups
INSERT INTO `chat_app`.`Group`(`GroupName`, `IsPrivate`, `CreatedAt`, `CreatedBy`) VALUES("Marocanii", false, NOW(), 4);
INSERT INTO `chat_app`.`Group`(`GroupName`, `IsPrivate`, `CreatedAt`, `CreatedBy`) VALUES("Homofobii", true, NOW(), 3);

#insert user types
INSERT INTO `chat_app`.`UserType`(`TypeID`,`TypeName`) VALUES(1, "admin");
INSERT INTO `chat_app`.`UserType`(`TypeID`,`TypeName`) VALUES(2, "moderator");
INSERT INTO `chat_app`.`UserType`(`TypeID`,`TypeName`) VALUES(3, "member");

#insert users in groups
INSERT INTO `chat_app`.`IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(7, 1, 1, false);
INSERT INTO `chat_app`.`IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(5, 2, 1, false);

INSERT INTO `chat_app`.`IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(1, 1, 3, false);
INSERT INTO `chat_app`.`IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(6, 1, 2, false);
INSERT INTO `chat_app`.`IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(8, 1, 3, false);

INSERT INTO `chat_app`.`IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(2, 2, 3, false);
INSERT INTO `chat_app`.`IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(8, 2, 2, false);

#insert privileges
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(1, "canAdd");
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(2, "canBlock");
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(3, "canKick");
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(4, "canPromote");
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(5, "canCallBot");

#insert relationships between user types and privileges
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 1);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 2);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 3);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 4);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 5);

INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(2, 1);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(2, 2);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(2, 3);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(2, 5);

INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(3, 1);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(3, 5);

#insert notification
INSERT INTO `chat_app`.`Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(1, "You received new messages on your group", 3600, NOW(), NOW());
INSERT INTO `chat_app`.`Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(2, "You have been blocked", 3600, NOW(), NOW());
INSERT INTO `chat_app`.`Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(3, "You have been kicked", 0, NOW(), NOW());
INSERT INTO `chat_app`.`Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(4, "Someone has been added to the group", 3600, NOW(), NOW());
INSERT INTO `chat_app`.`Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(5, "You have been promoted", 3600, NOW(), NOW());

#insert messages
INSERT INTO `chat_app`.`Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(1, 7, "Hello to Marocanii mei!", NOW(), 1);
INSERT INTO `chat_app`.`Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(1, 6, "Saluuuut", NOW(), 1);
INSERT INTO `chat_app`.`Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(1, 1, "Nice to meet you ba", NOW(), 1);

INSERT INTO `chat_app`.`Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(2, 5, "Hello to new group bros", NOW(), 1);
INSERT INTO `chat_app`.`Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(2, 8, "Pa ba", NOW(), 1);