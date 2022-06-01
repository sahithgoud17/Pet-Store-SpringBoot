create database pet_store;

use pet_store;

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `password` char(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `category` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT NULL,
 PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `category` VALUES (1, 'Dog');
INSERT INTO `category` VALUES (2, 'Cat');
INSERT INTO `category` VALUES (3, 'Rabbit');
INSERT INTO `category` VALUES (4, 'Parrot');


CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category_id` int NOT NULL,
  `breed` varchar(45) NOT NULL,
  `height` int NOT NULL,
  `weight` int NOT NULL,
  `colour` varchar(5) NOT NULL,
  `age` int NOT NULL,
  `cost` int NOT NULL,
  `imageurl` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `category_mapping` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `item` VALUES(1,'lucy', 1, 'pug', 2, 10, 'brown', 2 , 1200, 'https://thumbs.dreamstime.com/b/take-photo-portrait-pug-dog-pug-dog-smliley-128752320.jpg');
INSERT INTO `item` VALUES(2,'jolly', 1, 'lab', 4, 15, 'black', 1 , 1700, 'https://www.thelabradorsite.com/wp-content/uploads/2017/04/english-black-lab-puppy.jpg');
INSERT INTO `item` VALUES(3,'thomas', 2, 'persian', 1, 8, 'white', 1 , 600, 'https://easyscienceforkids.com/wp-content/uploads/2019/05/Persian-Cat-23-4-1-758x635.jpg');
INSERT INTO `item` VALUES(4,'cattie', 2, 'scottish fold', 2, 9, 'grey', 1 , 900, 'https://static9.depositphotos.com/1594920/1090/i/950/depositphotos_10901840-stock-photo-scottish-fold-cat-9-and.jpg');
INSERT INTO `item` VALUES(5,'danger', 3, 'rexerabbit', 1, 2, 'brown', 1 , 350, 'https://64.media.tumblr.com/57ccbf2f78d7354ee74d66afd24e5738/tumblr_o331gmDcf51uvq9elo5_1280.jpg');
INSERT INTO `item` VALUES(6,'rouge', 3, 'lionrabbit', 1, 4, 'white', 1 , 1100, 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Lionhead_rabbit_Dobby.jpg/1200px-Lionhead_rabbit_Dobby.jpg');
INSERT INTO `item` VALUES(7,'candy', 4, 'parrotlet', 1, 1, 'green', 1 , 1800, 'https://cdn.download.ams.birds.cornell.edu/api/v1/asset/159806261/1800');


CREATE TABLE `customer_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `is_placed` boolean NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `customer_mapping` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `order_item` (
  `item_id` int NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT 1,
  PRIMARY KEY (`item_id`,`order_id`),
  CONSTRAINT `item_mapping` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `order_mapping` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
