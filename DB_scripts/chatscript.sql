USE chat_app;
CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(40) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `PhoneNo` varchar(17) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `ActivationToken` varchar(64) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `NotificationType` varchar(30) NOT NULL,
  `CreatedAt` datetime NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `ActivationToken` (`ActivationToken`)
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
  `CreatedBy` int(11) NULL,
  PRIMARY KEY (`GroupID`),
  UNIQUE KEY `GroupName` (`GroupName`),
  KEY `CreatedBy` (`CreatedBy`),
  CONSTRAINT `Group_ibfk_1` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`userid`) ON DELETE SET NULL
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
  CONSTRAINT `Message_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `User` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `Message_ibfk_3` FOREIGN KEY (`NotificationID`) REFERENCES `Notification` (`notificationid`)
);

#insert users
INSERT INTO `User`(`Name`, `Email`, `PhoneNo`, `Password`, `ActivationToken`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Ion", "Dodo@gnmail.com", "+40763242343", "qweqwrasd", "asfd43453fsw45435f3rsdf", false, "phone", NOW());
INSERT INTO `User`(`Name`, `Email`, `PhoneNo`, `Password`, `ActivationToken`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Mircea", "Popovic@yahoo.com", "+40781200239", "asdwasresas", "asfd43dh6hbv545f3rsdf", false, "email", NOW());
INSERT INTO `User`(`Name`, `Email`, `PhoneNo`, `Password`, `ActivationToken`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Bubu", "Nebuna@nebunii.org", "+40760900923", "sqwug=psulda", "4sdf45xvhttq5f3rsdf", false, "phone", NOW());
INSERT INTO `User`(`Name`, `Email`, `PhoneNo`, `Password`, `ActivationToken`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Mirel", "Radoi@gnmail.com", "+40780901239", "amnammingeatraglapoarta", "a2cvrhytr56gf435f56544f", false, "phone", NOW());
INSERT INTO `User`(`Name`, `Email`, `PhoneNo`, `Password`, `ActivationToken`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Viorel", "Liss@yahoo.com", "+40721029358", "fosilaepava", "zxcnyy3523xzc2421fwedf", false, "email", NOW());
INSERT INTO `User`(`Name`, `Email`, `PhoneNo`, `Password`, `ActivationToken`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Dorian", "Pupa@yahoo.com", "+40749126049", "qweqsdsd", "a123asdxzcvtyilkoppf", false, "phone", NOW());
INSERT INTO `User`(`Name`, `Email`, `PhoneNo`, `Password`, `ActivationToken`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Mssd", "Popc@gnmail.com", "+40745242644", "axcas", "cbghy6574353fdsgtr454dvz", false, "email", NOW());
INSERT INTO `User`(`Name`, `Email`, `PhoneNo`, `Password`, `ActivationToken`, `IsActive`, `NotificationType`, `CreatedAt`) VALUES("Buabd", "Neba@yahoo.com", "+40725432343", "43dfezcxvr3", "ascvbtu46fs1w2d2e32vfdtf", false, "email", NOW());

#insert groups
INSERT INTO `Group`(`GroupName`, `IsPrivate`, `CreatedAt`, `CreatedBy`) VALUES("Marocanii", false, NOW(), 4);
INSERT INTO `Group`(`GroupName`, `IsPrivate`, `CreatedAt`, `CreatedBy`) VALUES("Homofobii", true, NOW(), 3);

#insert user types
INSERT INTO `UserType`(`TypeID`,`TypeName`) VALUES(1, "admin");
INSERT INTO `UserType`(`TypeID`,`TypeName`) VALUES(2, "moderator");
INSERT INTO `UserType`(`TypeID`,`TypeName`) VALUES(3, "member");

#insert users in groups
INSERT INTO `IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(7, 1, 1, false);
INSERT INTO `IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(5, 2, 1, false);

INSERT INTO `IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(1, 1, 3, false);
INSERT INTO `IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(6, 1, 2, false);
INSERT INTO `IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(8, 1, 3, false);

INSERT INTO `IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(2, 2, 3, false);
INSERT INTO `IsIn`(`UserID`, `GroupID`, `TypeID`, `IsBlocked`) VALUES(8, 2, 2, false);

#insert privileges
INSERT INTO `Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(1, "canAdd");
INSERT INTO `Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(2, "canBlock");
INSERT INTO `Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(3, "canKick");
INSERT INTO `Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(4, "canPromote");
INSERT INTO `Privilege`(`PrivilegeID`, `PrivilegeName`) VALUES(5, "canCallBot");

#insert relationships between user types and privileges
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 1);
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 2);
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 3);
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 4);
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(1, 5);

INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(2, 1);
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(2, 2);
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(2, 3);
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(2, 5);

INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(3, 1);
INSERT INTO `HasPrivilege`(`TypeID`, `PrivilegeID`) VALUES(3, 5);

#insert notification
INSERT INTO `Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(1, "You received new messages on your group", 3600, NOW(), NOW());
INSERT INTO `Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(2, "You have been blocked", 3600, NOW(), NOW());
INSERT INTO `Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(3, "You have been kicked", 0, NOW(), NOW());
INSERT INTO `Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(4, "Someone has been added to the group", 3600, NOW(), NOW());
INSERT INTO `Notification`(`NotificationID`, `NotificationMessage`, `Frequency`, `CreatedAt`, `UpdatedAt`) VALUES(5, "You have been promoted", 3600, NOW(), NOW());

#insert messages
INSERT INTO `Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(1, 7, "Hello to Marocanii mei!", NOW(), 1);
INSERT INTO `Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(1, 6, "Saluuuut", NOW(), 1);
INSERT INTO `Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(1, 1, "Nice to meet you ba", NOW(), 1);

INSERT INTO `Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(2, 5, "Hello to new group bros", NOW(), 1);
INSERT INTO `Message`(`GroupID`, `UserID`, `Message`, `CreatedAt`, `NotificationID`) VALUES(2, 8, "Pa ba", NOW(), 1);