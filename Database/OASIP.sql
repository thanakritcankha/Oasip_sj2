-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OASIP
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `OASIP` ;

-- -----------------------------------------------------
-- Schema OASIP
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OASIP` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema oasip
-- -----------------------------------------------------
USE `OASIP` ;

-- -----------------------------------------------------
-- Table `OASIP`.`EventCategories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OASIP`.`EventCategories` ;

CREATE TABLE IF NOT EXISTS `OASIP`.`EventCategories` (
  `eventCategoryId` INT NOT NULL,
  `eventCategoryName` VARCHAR(100) NOT NULL,
  `eventCategoryDescription` TEXT(500) NULL,
  `eventCategoryDuration` INT NOT NULL,
  UNIQUE INDEX `eventCategoryName_UNIQUE` (`eventCategoryName` ASC) VISIBLE,
  PRIMARY KEY (`eventCategoryId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OASIP`.`Events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OASIP`.`Events` ;

CREATE TABLE IF NOT EXISTS `OASIP`.`Events` (
  `eventId` INT NOT NULL,
  `bookingName` VARCHAR(100) NOT NULL,
  `bookingEmail` VARCHAR(255) NOT NULL,
  `eventStartTime` DATETIME NOT NULL,
  `eventDuration` INT NOT NULL,
  `eventNotes` TEXT(500) NULL,
  `eventCategoryId` INT NOT NULL,
  PRIMARY KEY (`eventId`),
  INDEX `fk_Event_EventCategory_idx` (`eventCategoryId` ASC) VISIBLE,
  CONSTRAINT `fk_Event_EventCategory`
    FOREIGN KEY (`eventCategoryId`)
    REFERENCES `OASIP`.`EventCategories` (`eventCategoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
