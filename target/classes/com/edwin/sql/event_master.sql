# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 8.0.20)
# Database: event_master
# Generation Time: 2020-08-04 22:36:52 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table event
# ------------------------------------------------------------

CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `ticket_price` decimal(10,2) DEFAULT NULL,
  `available_tickets` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `cover_image` text,
  `start_date` timestamp NULL DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_event` (`user_id`),
  CONSTRAINT `fk_user_event` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;

INSERT INTO `event` (`id`, `title`, `type`, `description`, `address`, `ticket_price`, `available_tickets`, `status`, `cover_image`, `start_date`, `start_time`, `end_time`, `updated_at`, `created_at`, `user_id`)
VALUES
	(2,'This is magic','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',1.00,2,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/6834a6ffb3ab4db8951280bbbaf8150f.png','2020-08-13 00:00:00','2020-07-26 22:25:08','2020-07-26 22:24:14','2020-07-26 22:25:19','2020-07-26 22:25:19',3),
	(3,'Cloud','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',1.00,1,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/6834a6ffb3ab4db8951280bbbaf8150f.png','2020-06-14 00:00:00','2020-07-26 23:04:08','2020-07-26 23:03:10','2020-07-27 22:52:23','2020-07-26 23:04:15',3),
	(4,'27 years','Drama','description','Olympic Blvd, Sydney Olympic Park NSW 2127',1.00,0,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/6834a6ffb3ab4db8951280bbbaf8150f.png','2020-08-29 00:00:00','2020-07-27 20:17:12','2020-07-27 04:16:15','2020-07-29 16:32:32','2020-07-27 20:16:19',3),
	(5,'8 miles','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',1.00,2,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/6834a6ffb3ab4db8951280bbbaf8150f.png','2020-07-06 00:00:00','2020-07-27 20:17:09','2020-07-27 04:16:15','2020-07-27 22:11:52','2020-07-27 20:17:12',4),
	(6,'Gorgeous','Live Concerts','description','Olympic Blvd, Sydney Olympic Park NSW 2127',2.00,0,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/6834a6ffb3ab4db8951280bbbaf8150f.png','2020-08-27 00:00:00','2020-07-28 22:03:39','2020-07-28 22:05:40','2020-07-28 22:10:43','2020-07-28 22:04:49',3),
	(7,'Listen','Live Concerts','description','Olympic Blvd, Sydney Olympic Park NSW 2127',10.00,8,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/b58ffc36d07b4b1483fc4dbed7ec865e.png','2020-08-07 00:00:00','2020-07-29 09:28:24','2020-07-29 09:28:23','2020-07-29 09:28:29','2020-07-29 09:28:29',3),
	(9,'Rain','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',2.00,5,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/6bd744c532954c99898030c6ce1a19b1.png','2020-07-31 00:00:00','2020-07-29 18:57:16','2020-07-29 18:57:15','2020-07-29 18:57:27','2020-07-29 18:57:27',3),
	(10,'The real','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',3.00,3,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/45f93ac5af85471592d160fa6dbfc4f6.png','2020-07-30 00:00:00','2020-07-30 22:19:44','2020-07-30 22:19:45','2020-07-30 22:19:50','2020-07-30 22:19:50',8),
	(11,'Justice','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',3.00,2,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/ee727958437f4a1794beb20adf1af911.png','2020-08-01 00:00:00','2020-01-01 22:22:50','2020-07-30 22:22:50','2020-07-30 22:22:57','2020-07-30 22:22:57',8),
	(12,'The live','Live Concerts','description','Olympic Blvd, Sydney Olympic Park NSW 2127',5.00,3,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/90bc4368abbe4da69b3724c4eee4c81d.png','2020-08-01 00:00:00','2020-07-31 09:04:42','2020-07-31 09:04:41','2020-07-31 09:04:49','2020-07-31 09:04:49',8),
	(13,'Live party','Live Concerts','description','Olympic Blvd, Sydney Olympic Park NSW 2127',4.00,5,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/b7863b3a7d8b45248cc796dafc63ef62.jpg','2020-08-12 00:00:00','2020-08-01 22:38:51','2020-08-01 22:38:52','2020-08-01 22:39:00','2020-08-01 22:39:00',3),
	(14,'The sport','Sport','description','Olympic Blvd, Sydney Olympic Park NSW 2127',45.00,7,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/4973402892644c378d5d026abf337b2d.png','2020-08-14 00:00:00','2020-08-02 11:49:08','2020-08-02 11:49:08','2020-08-02 11:49:18','2020-08-02 11:49:18',3),
	(15,'J cole','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',334.00,6,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/a1a710177fc14e718df82d09b516d253.png','2020-08-28 00:00:00','2020-08-02 11:50:43','2020-08-02 11:50:44','2020-08-02 11:50:53','2020-08-02 11:50:53',3),
	(16,'The park','Drama','description','Olympic Blvd, Sydney Olympic Park NSW 2127',66.00,6,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/6fbd2e84d8454e5aaa18ebdda56cb4ae.png','2020-08-22 00:00:00','2020-08-02 11:51:14','2020-08-02 11:51:14','2020-08-02 11:51:28','2020-08-02 11:51:28',3),
	(17,'江河湖海','Live Concerts','description','Olympic Blvd, Sydney Olympic Park NSW 2127',55.00,55,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/6bc72d9eb54443beb07d6fdc32d4e5c3.png','2020-08-27 00:00:00','2020-08-02 19:45:34','2020-08-02 19:45:35','2020-08-03 22:56:57','2020-08-02 19:45:42',3),
	(18,'BrunoMars','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',15.00,60,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/5e2a11a69aba4790805cdb1a999adac4.png','2020-08-20 00:00:00','2020-08-02 19:46:16','2020-08-02 21:46:16','2020-08-02 19:46:27','2020-08-02 19:46:27',3),
	(19,'coldplay ','Live Concerts','description','Olympic Blvd, Sydney Olympic Park NSW 2127',60.00,100,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/5582b066754d4b36b78271ceb0e78cce.png','2020-08-16 00:00:00','2020-08-02 19:46:58','2020-08-02 21:46:59','2020-08-02 19:47:08','2020-08-02 19:47:08',3),
	(20,'find somebody','Sport','description','Olympic Blvd, Sydney Olympic Park NSW 2127',300.00,130,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/3379c9e496ea4991a61a3c8c0a40f04c.png','2020-08-15 00:00:00','2020-08-02 19:47:40','2020-08-02 21:47:40','2020-08-02 19:47:50','2020-08-02 19:47:50',3),
	(21,'jannabi','Drama','description','Olympic Blvd, Sydney Olympic Park NSW 2127',150.00,50,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/46c74248335f4a75b4feb913e5ceecd8.png','2020-08-19 00:00:00','2020-08-02 13:48:17','2020-08-02 18:48:19','2020-08-02 19:48:30','2020-08-02 19:48:30',3),
	(22,'joker','Movies','description','Olympic Blvd, Sydney Olympic Park NSW 2127',50.00,80,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/93d00501bd5f4124912f5936d27b3692.png','2020-08-18 00:00:00','2020-08-02 19:49:46','2020-08-02 21:49:47','2020-08-02 19:49:55','2020-08-02 19:49:55',3),
	(23,'judge','Sport','description','Olympic Blvd, Sydney Olympic Park NSW 2127',600.00,50,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/1dbaa8dc1f944c159a142fc77f6b8873.png','2020-08-21 00:00:00','2020-08-02 19:50:25','2020-08-02 19:50:25','2020-08-02 19:50:32','2020-08-02 19:50:32',3),
	(24,'shape of you','Live Concerts','description','Olympic Blvd, Sydney Olympic Park NSW 2127',65.00,150,0,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/30f273b50070426b9dd20bc0e3e055c5.png','2020-08-12 00:00:00','2020-08-02 17:51:07','2020-08-02 20:51:09','2020-08-02 19:51:26','2020-08-02 19:51:26',3),
	(26,'Radiohead Live','Live Concerts','Radiohead are an English rock band formed in Abingdon, Oxfordshire, in 1985. The band consists of Thom Yorke (vocals, guitar, piano, keyboards), brothers Jonny Greenwood (lead guitar, keyboards, other','Olympic Blvd, Sydney Olympic Park NSW 2127',120.00,400,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/concert中图1.png','2020-08-23 00:00:00','2020-08-23 18:00:00','2020-08-23 21:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(28,'DJ Montana Electric Syllable','Live Concerts','MONTANA (Damian Gelle) brings some of the best electronic music to Australia. Montana has been playing at underground electronic parties for over a decade.','Elizabeth St, Sydney NSW 2000',20.00,400,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/concert中图2.png','2020-08-28 00:00:00','2020-08-28 10:00:00','2020-08-29 21:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(29,'Harry Styles Live','Live Concerts','Harry Edward Styles (born 1 February 1994) is an English singer, songwriter and actor. Born in Redditch, Worcestershire, his musical career began in 2010, when he auditioned as a solo contestant on th','Driver Ave, Moore Park NSW 2021',80.00,1000,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/concert大图.png','2020-08-23 00:00:00','2020-08-23 18:00:00','2020-08-23 21:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(31,'Set It Off Live','Live Concerts','Set It Off is an American rock band that started in Tampa, Florida. The band initially gained a large following through vocalist Cody Carson\'s YouTube channel.','Elizabeth St, Sydney NSW 2000',90.00,200,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/concert小图1.png','2020-08-15 00:00:00','2020-08-15 18:00:00','2020-08-15 20:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(32,'Celina Kotz Violin Live','Live Concerts','Honorary Patronage of the President of the Republic of Poland Andrzej Duda','Driver Ave, Moore Park NSW 2021',150.00,40,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/concert小图2.png','2020-08-28 00:00:00','2020-08-28 17:00:00','2020-08-28 19:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(36,'Fleetwood Mac Bond Show','Live Concerts','Fleetwood Mac are a British-American rock band, formed in London in 1967. They have sold more than 120 million records worldwide, making them one of the world\'s best-selling bands. ','Olympic Blvd, Sydney Olympic Park NSW 2127',120.00,600,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/concert小图3.png','2020-08-15 00:00:00','2020-08-15 18:00:00','2020-08-15 20:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(37,'Billie Eilish Live','Live Concerts','Billie Eilish Pirate Baird O\'Connell (/ˈaɪlɪʃ/ EYE-lish; born December 18, 2001) is an American singer and songwriter. ','Olympic Blvd, Sydney Olympic Park NSW 2127',100.00,200,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/concert小图4.png','2020-09-09 00:00:00','2021-07-09 18:00:00','2021-07-09 20:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(41,'Little Miss Sunshine','Movies','A family determined to get their young daughter into the finals of a beauty pageant take a cross-country trip in their VW bus.','Cnr Greek &, Bay St, Broadway NSW 2007',20.00,80,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/movie中图1.png','2020-08-03 00:00:00','2020-08-23 10:00:00','2020-08-23 12:00:00','2020-08-04 22:25:07','2020-07-31 18:00:00',9),
	(42,'Jurassic Park','Movies','A pragmatic paleontologist visiting an almost complete theme park is tasked with protecting a couple of kids after a power failure causes the park\'s cloned dinosaurs to run loose.','Cnr Greek &, Bay St, Broadway NSW 2007',20.00,80,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/movie中图2.png','2020-08-23 00:00:00','2020-08-23 12:00:00','2020-08-23 14:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(43,'Peatriassair','Movies','The life of a family turned upside down','Cnr Greek &, Bay St, Broadway NSW 2007',20.00,80,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/movie大图.png','2020-08-23 00:00:00','2020-08-23 14:00:00','2020-08-23 16:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(44,'Eye in the Sky','Movies','Col. Katherine Powell, a military officer in command of an operation to capture terrorists in Kenya, sees her mission escalate when a girl enters the kill zone triggering an international dispute over','Cnr Greek &, Bay St, Broadway NSW 2007',20.00,80,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/movie小图1.png','2020-08-24 00:00:00','2020-08-24 10:00:00','2020-08-24 12:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(45,'The Breakfast Club','Movies','Five high school students meet in Saturday detention and discover how they have a lot more in common than they thought.','Cnr Greek &, Bay St, Broadway NSW 2007',20.00,80,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/movie小图2.png','2020-08-24 00:00:00','2020-08-24 12:00:00','2020-08-24 14:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(46,'1917','Movies','April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a','Cnr Greek &, Bay St, Broadway NSW 2007',20.00,80,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/movie小图3.png','2020-08-24 00:00:00','2020-08-24 14:00:00','2020-08-24 16:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(47,'Midsommar','Movies','A couple travels to Sweden to visit a rural hometown\'s fabled mid-summer festival. What begins as an idyllic retreat quickly devolves into an increasingly violent and bizarre competition at the hands ','Cnr Greek &, Bay St, Broadway NSW 2007',20.00,80,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/movie小图4.png','2020-08-24 00:00:00','2020-08-24 16:00:00','2020-08-24 18:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(48,'Hedda Gabler','Drama','Hedda Gabler is a captivating and well-known play from the 1890s that dramatizes the experiences of the title character, Hedda, the daughter of a general, who is trapped in a marriage and a house that','The National Theatre',180.00,70,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/drama中图1.png','2020-09-12 00:00:00','2020-09-12 18:00:00','2020-09-12 21:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(49,'Willy Wonka & the Chocolate Factory','Drama','A young boy wins a tour through the most magnificent chocolate factory in the world, led by the world\'s most unusual candy maker.','Bennelong Point, Sydney NSW 2000',160.00,100,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/drama中图2.png','2020-09-20 00:00:00','2020-09-20 19:00:00','2020-09-20 22:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(50,'Macbeth','Drama','Macbeth, the Thane of Glamis, receives a prophecy from a trio of witches that one day he will become King of Scotland. Consumed by ambition and spurred to action by his wife, Macbeth murders his king ','Bennelong Point, Sydney NSW 2000',180.00,101,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/drama大图.png','2020-09-10 00:00:00','2020-10-10 18:00:00','2020-10-10 20:00:00','2020-08-05 08:27:48','2020-07-31 18:00:00',9),
	(51,'Łatwa droga','Drama','Łatwa droga (1967) Der Sanfte Lauf - informacje o filmie w bazie Filmweb.pl. Oceny, recenzje, obsada, dyskusje wiadomości, zwiastuny, ciekawostki oraz ...','Bennelong Point, Sydney NSW 2000',200.00,100,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/drama小图1.png','2020-09-30 00:00:00','2020-10-30 18:00:00','2020-10-30 21:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(52,'Visuel du Festival Arsène ','Drama','Visuel du festival Arsène 2016, organisé par l\'Université d\'Artois (Nantes). Imprimé en Pantone 801 + Pantone Warm red. Poster design','Bennelong Point, Sydney NSW 2000',80.00,100,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/drama小图2.png','2020-09-02 00:00:00','2020-11-02 18:00:00','2020-11-02 20:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(53,'Dermond the tutuy rumours','Drama','...','Bennelong Point, Sydney NSW 2000',150.00,100,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/drama小图3.png','2020-09-02 00:00:00','2020-11-02 18:00:00','2020-11-02 21:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(54,'Romeo and Juliet','Drama','Romeo and Juliet is a tragedy written by William Shakespeare early in his career about two young star-crossed lovers whose deaths ultimately reconcile their feuding families.','Bennelong Point, Sydney NSW 2000',190.00,100,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/drama小图4.png','2020-09-22 00:00:00','2020-11-22 19:00:00','2020-11-22 22:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(55,'Sports Fest 2020','Sport','Annual sports event','Edwin Flack Ave, Sydney Olympic Park NSW 2127',50.00,2000,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/sports中图1.png','2020-09-30 00:00:00','2020-11-30 21:30:00','2020-12-10 21:30:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(56,'Nike Brasileiragem','Sport','For the 2022 World Cup, we celebrated a new generation while throwing back to an iconic spot.','https://www.wk.com/work/nike-brasileiragem/',0.00,8000,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/sports中图2.png','2020-08-23 00:00:00','2020-08-21 18:00:00','2020-08-21 19:30:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(57,'PAOK FC','Sport','Super League Gameweek 3','Stadio Toumbas Thessaloniki',20.00,800,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/sports大图.png','2020-08-21 00:00:00','2020-08-21 18:00:00','2020-08-21 19:30:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(58,'AIK Fotboll VS. Manchester United','Sport','A wonderful game you can\'t miss','Edwin Flack Ave, Sydney Olympic Park NSW 2127',50.00,1000,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/sports小图1.png','2020-08-06 00:00:00','2020-08-06 18:30:00','2020-08-06 20:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(59,'UNITED VS. FULHAM','Sport','...','Edwin Flack Ave, Sydney Olympic Park NSW 2127',50.00,1000,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/sports小图2.png','2020-09-09 00:00:00','2021-02-09 18:30:00','2021-02-09 20:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(60,'Kasimpasa VS. Basaksehir','Sport','...','Edwin Flack Ave, Sydney Olympic Park NSW 2127',50.00,1000,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/sports小图3.png','2020-08-19 00:00:00','2020-08-19 18:30:00','2020-08-19 20:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9),
	(61,'Vanderbilt Commodores football','Sport','Vanderbilt Commodores football. 2020 Vanderbilt Commodores football team. ','Stadio Toumbas Thessaloniki',70.00,800,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/sports小图4.png','2020-09-17 00:00:00','2020-11-17 18:00:00','2020-11-17 20:00:00','2020-07-31 18:00:00','2020-07-31 18:00:00',9);

/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table event_review
# ------------------------------------------------------------

CREATE TABLE `event_review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `receiver_id` int DEFAULT NULL,
  `content` text,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `event_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_event_review` (`sender_id`),
  KEY `fk_event_event_review` (`event_id`),
  CONSTRAINT `fk_event_event_review` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `fk_user_event_review` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `event_review` WRITE;
/*!40000 ALTER TABLE `event_review` DISABLE KEYS */;

INSERT INTO `event_review` (`id`, `sender_id`, `receiver_id`, `content`, `updated_at`, `created_at`, `event_id`)
VALUES
	(1,3,0,'I like it very much and will continue to participate next time','2020-07-29 10:06:36','2020-07-29 10:06:36',58),
	(2,4,3,'Cheers','2020-07-29 10:12:17','2020-07-29 10:12:17',58),
	(4,4,0,'An unforgettable experience','2020-08-02 12:00:08','2020-08-02 12:00:08',50),
	(6,3,0,'My son likes it very much, worth the fare','2020-08-02 23:15:59','2020-08-02 23:15:59',50),
	(7,4,0,'Generally, won\'t come again','2020-08-03 23:23:00','2020-08-03 23:23:00',41),
	(8,9,4,'Sorry, we will pay attention','2020-08-03 23:23:23','2020-08-03 23:23:23',41),
	(9,9,3,'thanks','2020-08-03 23:23:31','2020-08-03 23:23:31',50),
	(11,9,4,'Thanks, hope you come again next time','2020-08-03 23:24:26','2020-08-03 23:24:26',50),
	(13,3,0,'Good event, I like it!','2020-08-04 22:27:32','2020-08-04 22:27:32',41);

/*!40000 ALTER TABLE `event_review` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table order
# ------------------------------------------------------------

CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_number` int DEFAULT NULL,
  `ticket_amount` int DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone` varchar(200) DEFAULT NULL,
  `address_1` varchar(200) DEFAULT NULL,
  `address_2` varchar(200) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `postcode` varchar(20) DEFAULT NULL,
  `donate` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `user_id` int NOT NULL,
  `event_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_event_order` (`event_id`),
  KEY `fk_users_order` (`user_id`),
  CONSTRAINT `fk_event_order` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `fk_users_order` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;

INSERT INTO `order` (`id`, `order_number`, `ticket_amount`, `total_price`, `first_name`, `last_name`, `email`, `phone`, `address_1`, `address_2`, `city`, `state`, `postcode`, `donate`, `status`, `updated_at`, `created_at`, `user_id`, `event_id`)
VALUES
	(2,156226,1,1.00,'Jay','Z','Z.Jay@gmail.com','0435178506','10/28 barber avenue','Maroubra','Sydney','NSW','2018',1,2,'2020-07-27 21:14:46','2020-07-26 23:55:52',3,54),
	(3,456332,1,1.00,'John','Snow','John_s@gmail.com','0415684205','Edwin Flack Ave','Olympic Park','Sydney','NSW','2127',1,2,'2020-07-27 22:52:23','2020-07-27 00:02:22',3,53),
	(4,409235,1,1.00,'Tok','Tik','zjtd2020@gmail.com','0435124879','U2/3Tik road','Maroubra','rosebery','NSW','2018',0,1,'2020-07-27 21:25:11','2020-07-27 00:03:20',4,52),
	(5,148343,1,1.00,'Tom','Zhang','Tom_z@gmail.com','0431547805','Bourke St','Richmond','Richmond','NSW','2735',1,1,'2020-07-27 22:09:46','2020-07-27 22:09:46',4,51),
	(6,912630,1,1.00,'Xi','Zhao','sissi_2013@gmail.com','0431556512','2-4 Rica St','','Moorabbin','VIC','3189',1,1,'2020-08-05 08:27:48','2020-07-27 22:11:52',4,50),
	(7,994995,1,1.00,'Tom','Horspool','tom.horspool@gmail.com','0412557478','57-243 Anzac Parade','Kingsford','Sydney','NSW','2017',0,2,'2020-08-01 22:35:27','2020-07-27 22:14:17',4,36),
	(8,413888,1,2.00,'chongshi','wang','719204145@qq.com','0435175168','42/4 Grandstand Parade','Zetland','Sydney','NSW','2017',0,1,'2020-07-28 22:06:16','2020-07-28 22:06:16',4,32),
	(9,204256,1,2.00,'chongshi','wang','719204145@qq.com','0435175168','42/4 Grandstand Parade','Zetland','Sydney','NSW','2017',0,1,'2020-07-28 22:10:43','2020-07-28 22:10:43',4,31),
	(10,947933,1,1.00,'chongshi','wang','719204145@qq.com','0435175168','42/4 Grandstand Parade','Zetland','Sydney','NSW','2017',2,2,'2020-08-01 22:35:27','2020-07-29 16:32:32',4,29),
	(11,539288,2,40.00,'chongshi','wang','wcs2019inau@gmail.com','041023456','unit 501','8 rosebery road','rosebery','NSW','2018',2,1,'2020-08-04 22:45:38','2020-08-04 22:20:38',4,41);

/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table payment
# ------------------------------------------------------------

CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(100) DEFAULT NULL,
  `expiry_date` varchar(100) DEFAULT NULL,
  `cvv` int DEFAULT NULL,
  `card_holder` varchar(50) DEFAULT NULL,
  `account_balance` decimal(10,2) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_payment` (`order_id`),
  CONSTRAINT `fk_order_payment` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;

INSERT INTO `payment` (`id`, `card_number`, `expiry_date`, `cvv`, `card_holder`, `account_balance`, `status`, `updated_at`, `created_at`, `order_id`)
VALUES
	(9,'4064259512354895','01/22',123,'chongshi wang',0.00,1,'2020-07-29 16:32:32','2020-07-29 16:32:32',NULL),
	(10,'4064256915234865','04/20',267,'Edwin wang',0.00,1,'2020-08-03 22:56:57','2020-08-03 22:56:57',NULL),
	(11,'123456789','01/22',123,'chongshi wang',0.00,1,'2020-08-04 22:20:38','2020-08-04 22:20:38',NULL);

/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `address_1` varchar(200) DEFAULT NULL,
  `address_2` varchar(200) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `postcode` varchar(200) DEFAULT NULL,
  `user_balance` decimal(10,2) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `avatar` text,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `email`, `user_name`, `password`, `first_name`, `last_name`, `phone`, `address_1`, `address_2`, `city`, `state`, `postcode`, `user_balance`, `status`, `avatar`, `updated_at`, `created_at`)
VALUES
	(3,'719204145@qq.com','host','123','chongshi','wang','0435175168','601/717 Anzac Pde','Maroubra','Sydney','NSW','2035',65.00,2,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/3bc2c9ec330c401ca6e13130ea6333d6.png','2020-08-04 22:53:59','2020-07-26 22:24:53'),
	(4,'wcs2019inau@gmail.com','edwin','123','Edwin','wang','0435176024','10/28 barber avenue','eastlakes','Sydney','NSW','2018',178.00,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/2f102e9150f14c698df20a78efa71faf.png','2020-08-05 08:27:48','2020-07-26 22:26:12'),
	(8,'lilihuoshan@gmail.com','toly','123456789','ling','tong','0435179635','42/4 Grandstand Parade','Zetland','Sydney','NSW','2017',0.00,1,'/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/1f9a1368d7284888ae72cf6897d24c24.png','2020-07-31 09:04:26','2020-07-30 20:02:11'),
	(9,'tom.horspool@gmail.com','TommyH','123456789','Tom','Horspool','0435178506','57-243 Anzac Parade','Kingsford','Sydney','NSW','2032',99978.00,2,'/Users/edwin/capstone-project-coding-yes-sleeping-no/src/main/resources/static/photos/bd2612fe0dc240499505f8afd6557a86.png','2020-08-04 22:37:25','2020-07-26 22:26:12');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
