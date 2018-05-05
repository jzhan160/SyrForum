-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: forum
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.26-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `CatID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`CatID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'car'),(2,'book'),(3,'furniture');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `CommentID` int(11) NOT NULL AUTO_INCREMENT,
  `ItemID` int(11) NOT NULL,
  `Content` varchar(500) NOT NULL,
  `Users_UserID` int(11) NOT NULL,
  `CreateTime` datetime NOT NULL,
  PRIMARY KEY (`CommentID`),
  KEY `Users_UserID_idx` (`Users_UserID`),
  KEY `UserID_idx` (`Users_UserID`),
  KEY `TpID_idx` (`ItemID`),
  CONSTRAINT `ItemID` FOREIGN KEY (`ItemID`) REFERENCES `items` (`ItemID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `UserID` FOREIGN KEY (`Users_UserID`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (6,46,'Nice!',6,'2018-04-25 19:21:52'),(7,46,'Nice!',6,'2018-04-25 19:22:46'),(8,46,'Nice!',6,'2018-04-25 19:25:56'),(9,46,'Good',6,'2018-04-25 19:26:01'),(10,50,'good',1,'2018-04-26 13:24:18'),(11,49,'good',1,'2018-04-26 13:24:29'),(12,38,'nice',1,'2018-05-01 14:10:35'),(13,38,'good',1,'2018-05-01 14:17:59'),(14,38,'good',1,'2018-05-01 14:18:04'),(15,49,'nice',1,'2018-05-01 14:20:40'),(16,46,'nice',1,'2018-05-01 14:20:55');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorites` (
  `FavoriteID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `ItemID` int(11) NOT NULL,
  PRIMARY KEY (`FavoriteID`),
  KEY `UserID_idx` (`UserID`),
  KEY `ItemID_idx` (`ItemID`),
  CONSTRAINT `ItemID_` FOREIGN KEY (`ItemID`) REFERENCES `items` (`ItemID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `UserID_` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (1,1,50);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `ItemID` int(11) NOT NULL AUTO_INCREMENT,
  `ItemName` varchar(500) NOT NULL,
  `Description` varchar(500) NOT NULL,
  `CatID` int(11) NOT NULL,
  `ImagePath` varchar(500) DEFAULT NULL,
  `TopicID` int(11) NOT NULL,
  `Price` double DEFAULT NULL,
  `ReadingTimes` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ItemID`),
  KEY `_CatID_idx` (`CatID`),
  KEY `_TopicID_idx` (`TopicID`),
  CONSTRAINT `CtID` FOREIGN KEY (`CatID`) REFERENCES `categories` (`CatID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TopID` FOREIGN KEY (`TopicID`) REFERENCES `topics` (`TopicID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (38,'C++ prime 5th','good condition, very good for C++ beginner. ',2,'\\upload\\1\\13\\9e4db87f-c685-4ae1-b92f-fd5a5b2730a0_C++Primer.jpg',62,20,7),(39,'TOYOTA VENZA 2009','2009 Toyota Venza , Very Clean , non smoker \r\nOnly 71k miles \r\nGreat Gas mileage \r\nRemote start \r\nBackup Camera\r\nKeyless trunk open (automatic) push button close \r\nNew 20 \" inch Alloy Rims \r\nNew tires \r\nLeather interior\r\nHeated seats \r\nBack seats Reclines, Very roomy!! \r\nHuge Trunk Space (with cover) \r\nABSOLUTELY NO LIGHTS ON * (Very well maintained)',1,'\\upload\\0\\6\\7c150b38-b4ac-4cac-944b-9413de54b399_TOYOTA VENZA 2009.jpg',63,12000,0),(40,'PATIO rocker','patio 2-person rocker. excellent condition',3,'\\upload\\12\\6\\bc1cd043-13f0-44b0-961e-e0a7f3dd6604_PATIO rocker .jpg',64,55,0),(41,'BEAUTIFUL chalk paint bedroom furniture','38\" high, 34\" width, 18\"deep\r\nDresser w/ mirror\r\n29\" high (additional 49\" w/ mirror)\r\n52\" long\r\n18\" deep\r\nNight stand\r\n24\" high\r\n24\" long\r\n14\" deep\r\nDelivery to Syracuse area negotiable\r\n',3,'\\upload\\13\\2\\cecd5361-827d-4f88-8f94-6d4d691e834f_chalk paint.jpg',64,54,0),(42,'Double-Sided Display Dresser - 24 Drawers','H 34\" x W 42\" x L 55\"\r\nThis item is extremely heavy and store staff may not be able to assist with carry out. Store staff is never allowed to load any items into customer vehicles. Bring sufficient help to load item.\r\n',3,'\\upload\\5\\3\\5883d38b-9649-4981-a565-fd93ef4be2ff_Double-Sided Display Dresser.jpg',64,99,0),(43,'Data Analysis Book','1 - Doing Data Analysis w/SPSS Version 14\r\nISBN: 0-495-10793-X\r\n',2,'\\upload\\1\\7\\d0988825-abe1-4589-b5b5-6b4e70e334b8_Data Analysis Book.jpg',65,20,1),(44,'\"Rediscover Jesus\" by Matthew Kelly','Book: \"Rediscover Jesus\" by Matthew Kelly , excellent condition, soft cover, 187 pgs. $2.00 from a smoke free & pet free home.\r\nSerious inquiries only please. Cash only. Emails ONLY, sorry no calls or texts. \r\nWill not ship or deliver. Book can be picked up at my home off of Henry Clay Blvd in Liverpool or at Taft Rd Liverpool Wegmans Cafe. Available for pick up Mon - Fri after 5:30pm or anytime Saturday or Sundays.\r\n',2,'\\upload\\7\\15\\956ab996-5ee7-4865-9b57-00ba08bbb15c_Rediscover Jesus .jpg',65,15,1),(45,'BMW 328XI','Excellent condition , Very well maintained',1,'\\upload\\11\\10\\c07ffa39-1562-4dd8-a67d-db4808c747de_BMW 328XI.jpg',66,9300,0),(46,'Computer desk','Computer desk in Very good condition. Measures: 43\" wide, 23\" deep, and 50\" high.',3,'\\upload\\15\\3\\029e5a5b-3efa-409e-85bd-3530dab1e67e_Computer desk.jpg',67,25,2),(47,'2005 Mitsubishi eclipes gts','2005 Mitsubishi eclipse gts 5 speed manual. 29000 original miles one owner. Near new condition inside and out. Rare condition. No winters must see. Will only sell to local buyer in Syracuse/upstate New york area.',1,'\\upload\\15\\9\\38c8c484-9087-4c80-8219-c5d9656159b9_2005 Mitsubishi eclipes gts.jpg',68,6000,0),(48,'Funk & Wagnalls New Encyclopedia Item','Funk & Wagnalls New Encyclopedia Copyright 1979...Excellent condition...Hardcover',2,'\\upload\\15\\1\\484e89df-b5be-4ad0-978c-5febdfe7705b_Funk & Wagnalls.jpg',69,125,3),(49,'2013 Audi A5 premium plus','Second owner bought from Burdick awesome car needs nothing hundred thousand mile rust warranty low miles good price empire credit union will lend up to 22k on this car runs perfect',1,'\\upload\\7\\8\\0c9ae3a1-aacd-46ea-8382-b71eb210e106_2013 Audi A5 premium plus.jpg',70,18500,2),(50,'Current New York Times Best Sellers, Thrillers - 3 Books','I purchased these new and read them each once. Mint Condition and TERRIFIC stories. All are currently or have been on the New York Times Best Seller List. \r\nBook Trio includes the following three thriller books:\r\nThe Woman in Cabin 10 by Ruth Ware.\r\nThe Couple Next Door by Shari Lapena.\r\nBehind Closed Doors by B.A. Paris.\r\n10 dollars takes all. Pick up in Cazenovia\r\n',2,'\\upload\\2\\4\\ba464583-c041-4948-8755-b3beb5d3e15b_Current New York Times.jpg',71,55,6);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `UserID` int(11) NOT NULL,
  `CommentID` int(11) NOT NULL,
  `Viewed` bit(1) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `NoteID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`NoteID`),
  KEY `User_ID_idx` (`UserID`),
  KEY `Comment_ID_idx` (`CommentID`),
  CONSTRAINT `Comment_ID` FOREIGN KEY (`CommentID`) REFERENCES `comments` (`CommentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `User_ID` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topics` (
  `TopicID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(60) NOT NULL,
  `CreateTime` datetime NOT NULL,
  `Users_UserID` int(11) NOT NULL,
  `Contact` varchar(50) DEFAULT NULL,
  `Address` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`TopicID`),
  KEY `Users_UserID` (`Users_UserID`),
  CONSTRAINT `Users_UserID` FOREIGN KEY (`Users_UserID`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (62,'Very good book','2018-04-25 18:50:39',1,'315 -000-1234','SU'),(63,'Selling a car','2018-04-25 18:52:01',1,'315 -000-1234','SU'),(64,'Second hand furniture','2018-04-25 18:55:49',3,'315-345-0078','121 Lafayette Rd.'),(65,'Selling Books','2018-04-25 18:58:18',2,'315 -010-1234','NewBury'),(66,'BMW 328XI','2018-04-25 18:59:38',2,'315-123-0078','SU'),(67,'Second-hand furniture','2018-04-25 19:01:24',2,'315-333-0078','123 Lafayette Rd.'),(68,'used car ','2018-04-25 19:05:13',4,'315-566-0078','121 Lafayette Rd'),(69,'Book Series','2018-04-25 19:06:43',4,'315 -010-1234','SU'),(70,'Car','2018-04-25 19:08:53',5,'315-123-0078','SU'),(71,'3 books for sale','2018-04-25 19:11:03',6,'315 -010-1234','CH');
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `UserPassword` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Gender` varchar(45) DEFAULT NULL,
  `UserBirthday` date DEFAULT NULL,
  `CreateTime` datetime NOT NULL,
  `IsAdmin` tinyint(4) NOT NULL,
  `ImageID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UserName` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Biao','123','ba100@syr.edu','Male','1994-01-01','2018-04-03 22:18:18',0,0),(2,'Zhang','123','jzhan160@syr.edu','Male','1995-01-01','2018-04-04 08:29:04',0,0),(3,'He','123','ckhe@syr.edu','Male','1992-01-01','2018-04-04 08:29:04',0,0),(4,'YangDu','123',NULL,NULL,NULL,'2018-04-25 19:03:24',0,0),(5,'Zoe','123',NULL,NULL,NULL,'2018-04-25 19:07:52',0,0),(6,'tanming','123',NULL,NULL,NULL,'2018-04-25 19:10:03',0,0),(7,'kkk','kkk',NULL,NULL,NULL,'2018-04-27 00:37:46',0,0),(8,'Admin','123','admin@gmail.com',NULL,NULL,'2018-04-29 19:24:24',1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-04 20:54:45
