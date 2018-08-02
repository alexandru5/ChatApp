USE chat_app;
CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(40) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `ActivationToken` varchar(40) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `NotificationType` varchar(30) NOT NULL,
  `CreatedAt` datetime NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Email` (`Email`)
);

CREATE TABLE `UserType` (
  `TypeID` int(11) NOT NULL,
  `TypeName` varchar(50) NOT NULL,
  PRIMARY KEY (`TypeID`)
);

CREATE TABLE `Group` (
  `GroupID` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(100) DEFAULT NULL,
  `IsPrivate` tinyint(1) NOT NULL,
  `CreatedAt` datetime NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  PRIMARY KEY (`GroupID`),
  UNIQUE KEY `GroupName` (`GroupName`),
  KEY `CreatedBy` (`CreatedBy`),
  CONSTRAINT `Group_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`userid`)
);

CREATE TABLE `IsIn` (
  `UserID` int(11) NOT NULL,
  `GroupID` int(11) NOT NULL,
  `TypeID` int(11) NOT NULL,
  `IsBlocked` bit(1) NOT NULL,
  PRIMARY KEY (`UserID`,`GroupID`),
  KEY `GroupID` (`GroupID`),
  KEY `TypeID` (`TypeID`),
  CONSTRAINT `IsIn_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`userid`),
  CONSTRAINT `IsIn_ibfk_2` FOREIGN KEY (`GroupID`) REFERENCES `Group` (`groupid`),
  CONSTRAINT `IsIn_ibfk_3` FOREIGN KEY (`TypeID`) REFERENCES `UserType` (`typeid`)
);

CREATE TABLE `Notification` (
  `NotificationID` int(11) NOT NULL,
  `NotificationMessage` varchar(255) NOT NULL,
  `Frequency` int(11) NOT NULL,
  `CreatedAt` datetime NOT NULL,
  `UpdatedAt` datetime NOT NULL,
  PRIMARY KEY (`NotificationID`)
);

CREATE TABLE `Privilege` (
  `PrivilegeID` int(11) NOT NULL,
  `PrivilegeName` varchar(50) NOT NULL,
  PRIMARY KEY (`PrivilegeID`)
);

CREATE TABLE `HasPrivilege` (
  `TypeID` int(11) NOT NULL,
  `PrivilegeID` int(11) NOT NULL,
  PRIMARY KEY (`TypeID`,`PrivilegeID`),
  KEY `PrivilegeID` (`PrivilegeID`),
  CONSTRAINT `HasPrivilege_ibfk_1` FOREIGN KEY (`TypeID`) REFERENCES `UserType` (`typeid`),
  CONSTRAINT `HasPrivilege_ibfk_2` FOREIGN KEY (`PrivilegeID`) REFERENCES `Privilege` (`privilegeid`)
);

CREATE TABLE `Message` (
  `MessageID` int(11) NOT NULL AUTO_INCREMENT,
  `GroupID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Message` varchar(255) NOT NULL,
  `CreatedAt` datetime NOT NULL,
  `NotificationID` int(11) NOT NULL,
  PRIMARY KEY (`MessageID`),
  KEY `GroupID` (`GroupID`),
  KEY `UserID` (`UserID`),
  KEY `NotificationID` (`NotificationID`),
  CONSTRAINT `Message_ibfk_1` FOREIGN KEY (`GroupID`) REFERENCES `Group` (`groupid`),
  CONSTRAINT `Message_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `User` (`userid`),
  CONSTRAINT `Message_ibfk_3` FOREIGN KEY (`NotificationID`) REFERENCES `Notification` (`notificationid`)
);

--insert users
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `ActivationToken`, `isActive`, `NotificationType`, `CreatedAt`)
VALUES ("Ion", "Dodo@gmail.com",  "qweqwrasd",  "sfdsf4rsdf43fds4sdfv", 0, "phone",  NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `ActivationToken`, `isActive`, `NotificationType`, `CreatedAt`)
VALUES ("Mircea", "Popovic@yahoo.com", "asdwasresas", "sfd345saf4btyu43532dfv", 0, "email", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `ActivationToken`, `isActive`, `NotificationType`, `CreatedAt`)
VALUES ("Bubu", "Nebuna@gmail.com", "sqwug=psulda", "sfqw34sadf4565yq1xcfv", 0, "phone", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `ActivationToken`, `isActive`, `NotificationType`, `CreatedAt`)
VALUES ("Mirel", "Radoi@gmail.com", "amnammingeatraglapoarta", "sfaaaaxvrr54dfr466667fv", 0, "phone", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `ActivationToken`, `isActive`, `NotificationType`, `CreatedAt`)
VALUES ("Viorel", "Liss@yahoo.com", "fosilaepava", "sfrtq43535fdscbrytxczfv", 0, "email", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `ActivationToken`, `isActive`, `NotificationType`, `CreatedAt`)
VALUES ("Dorian", "Pupa@gmail.com", "qweqsdsd", "s435sdsr43fdbncdt45dowq35v", 0, "phone", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `ActivationToken`, `isActive`, `NotificationType`, `CreatedAt`)
VALUES ("Mssd", "Popc@yahoo.com", "axcas", "hfdgjetowrsdcxzcwer34", 0, "email", NOW());
INSERT INTO `chat_app`.`User`(`Name`, `Email`, `Password`, `ActivationToken`, `isActive`, `NotificationType`, `CreatedAt`)
VALUES ("Buabd", "Neba@yahoo.com", "43dfezcxvr3", "pokji435hsf3k5o3qfds4556", 0, "email", NOW());

--insert groups
INSERT INTO `chat_app`.`Group`(`GroupName`, `IsPrivate`, `CreatedAt`, `CreatedBy`) VALUES("Marocanii", 0, NOW(), 4);
INSERT INTO `chat_app`.`Group`(`GroupName`, `IsPrivate`, `CreatedAt`, `CreatedBy`) VALUES("Homofobii", 1, NOW(), 3);

--insert user types
INSERT INTO `chat_app`.`UserType`(`TypeID`,`TypeName`)VALUES(1, "admin");
INSERT INTO `chat_app`.`UserType`(`TypeID`,`TypeName`)VALUES(2, "moderator");
INSERT INTO `chat_app`.`UserType`(`TypeID`,`TypeName`)VALUES(3, "member");

--insert privileges
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`,`PrivilegeName`) VALUES(1, "canAdd");
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`,`PrivilegeName`) VALUES(2, "canBlock");
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`,`PrivilegeName`) VALUES(3, "canKick");
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`,`PrivilegeName`) VALUES(4, "canPromote");
INSERT INTO `chat_app`.`Privilege`(`PrivilegeID`,`PrivilegeName`) VALUES(5, "canCallBot");

--insert isIn relation for users and groups
INSERT INTO `chat_app`.`IsIn`(`UserID`,`GroupID`,`TypeID`,`IsBlocked`) VALUES(1,1,3,0);
INSERT INTO `chat_app`.`IsIn`(`UserID`,`GroupID`,`TypeID`,`IsBlocked`) VALUES(2,2,3,0);
INSERT INTO `chat_app`.`IsIn`(`UserID`,`GroupID`,`TypeID`,`IsBlocked`) VALUES(5,2,1,0);
INSERT INTO `chat_app`.`IsIn`(`UserID`,`GroupID`,`TypeID`,`IsBlocked`) VALUES(6,1,2,0);
INSERT INTO `chat_app`.`IsIn`(`UserID`,`GroupID`,`TypeID`,`IsBlocked`) VALUES(7,1,1,0);
INSERT INTO `chat_app`.`IsIn`(`UserID`,`GroupID`,`TypeID`,`IsBlocked`) VALUES(8,1,3,0);
INSERT INTO `chat_app`.`IsIn`(`UserID`,`GroupID`,`TypeID`,`IsBlocked`) VALUES(8,2,2,0);

--insert notification types
INSERT INTO `chat_app`.`Notification`(`NotificationMessage`,`Frequency`,`CreatedAt`,`UpdatedAt`) VALUES("You received new messages on your group", 3600, NOW(), NOW());
INSERT INTO `chat_app`.`Notification`(`NotificationMessage`,`Frequency`,`CreatedAt`,`UpdatedAt`) VALUES("You have been blocked", 3600, NOW(), NOW());
INSERT INTO `chat_app`.`Notification`(`NotificationMessage`,`Frequency`,`CreatedAt`,`UpdatedAt`) VALUES("You have been kicked", 0, NOW(), NOW());
INSERT INTO `chat_app`.`Notification`(`NotificationMessage`,`Frequency`,`CreatedAt`,`UpdatedAt`) VALUES("Someone has been added to the group", 3600, NOW(), NOW());
INSERT INTO `chat_app`.`Notification`(`NotificationMessage`,`Frequency`,`CreatedAt`,`UpdatedAt`) VALUES("You have been promoted", 3600, NOW(), NOW());

--insert hasPrivileges for user types
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(1,1);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(2,1);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(3,1);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(1,2);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(2,2);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(1,3);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(2,3);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(1,4);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(1,5);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(2,5);
INSERT INTO `chat_app`.`HasPrivilege`(`TypeID`,`PrivilegeID`) VALUES(3,5);