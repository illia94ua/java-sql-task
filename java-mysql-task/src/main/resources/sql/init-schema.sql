CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
	`user_id` INT NOT NULL,
	`username` VARCHAR(16) NOT NULL,
	`email` VARCHAR(255) NULL,
	`password` VARCHAR(32) NOT NULL,
	`create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS `mydb`.`category` (
	`category_id` INT NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`category_id`)
);
 
CREATE TABLE IF NOT EXISTS `mydb`.`user_to_category` (
	`user_id` INT NOT NULL,
	`category_id` INT NOT NULL,
	CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `mydb`.`user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `mydb`.`category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;