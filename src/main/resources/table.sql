--音乐表
create table `SPIDER_MUSIC`(
	`ID` int(21) NOT NULL AUTO_INCREMENT,
	`MUSIC_NAME` VARCHAR(20) NOT NULL,
	`AUTHOR` VARCHAR(20),
	`URL` VARCHAR(255),
	`EXTRACTION_CODE` VARCHAR(20) not null
	`CREATE_TIME` datetime NOT NULL,
	PRIMARY KEY(`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
--小说表
create table `SPIDER_FICTION`(
	`ID` int(21) NOT NULL AUTO_INCREMENT,
	`FICTION_NAME` VARCHAR(20) NOT NULL,
	`AUTHOR` VARCHAR(20),
	`URL` VARCHAR(255),
	`CREATE_TIME` datetime NOT NULL,
	PRIMARY KEY(`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
--用户表
create table `spider_user`(
	`ID` int(21) NOT NULL AUTO_INCREMENT,
	`ACCOUT_NUMBER` int(13) NOT NULL,
	`MAIL` VARCHAR(50) NOT NULL,
	`USER_NAME` VARCHAR(20) NOT NULL,
	`PASS_WORD` VARCHAR(20) NOT NULL,
	`CREATE_TIME` datetime NOT NULL,
	PRIMARY KEY(`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8