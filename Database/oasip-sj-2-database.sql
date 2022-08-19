-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema oasip
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `oasip` ;

-- -----------------------------------------------------
-- Schema oasip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oasip` DEFAULT CHARACTER SET utf8mb3 ;
USE `oasip` ;

-- -----------------------------------------------------
-- Table `oasip`.`eventcategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oasip`.`eventcategories` (
  `eventCategoryId` INT NOT NULL AUTO_INCREMENT,
  `eventCategoryName` VARCHAR(100) NOT NULL,
  `eventCategoryDescription` VARCHAR(500) NULL DEFAULT NULL,
  `eventCategoryDuration` INT NOT NULL,
  PRIMARY KEY (`eventCategoryId`),
  UNIQUE INDEX `eventCategoryName_UNIQUE` (`eventCategoryName` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `oasip`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oasip`.`users` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `role` ENUM('admin', 'lecturer', 'student') NOT NULL,
  `createOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `oasip`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oasip`.`events` (
  `eventId` INT NOT NULL AUTO_INCREMENT,
  `bookingName` VARCHAR(100) NOT NULL,
  `bookingEmail` VARCHAR(255) NOT NULL,
  `eventStartTime` DATETIME NOT NULL,
  `eventDuration` INT NOT NULL,
  `eventNotes` TEXT NULL DEFAULT NULL,
  `EventCategory_id` INT NOT NULL,
  `user_Id` INT NOT NULL,
  PRIMARY KEY (`eventId`),
  INDEX `fk_Event_EventCategory_idx` (`EventCategory_id` ASC) VISIBLE,
  INDEX `fk_events_users1_idx` (`user_Id` ASC) VISIBLE,
  CONSTRAINT `fk_Events_EventCategories`
    FOREIGN KEY (`EventCategory_id`)
    REFERENCES `oasip`.`eventcategories` (`eventCategoryId`),
  CONSTRAINT `fk_events_users1`
    FOREIGN KEY (`user_Id`)
    REFERENCES `oasip`.`users` (`userId`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
