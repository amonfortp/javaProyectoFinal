-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bbddProyecto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bbddProyecto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bbddProyecto` DEFAULT CHARACTER SET utf8 ;
USE `bbddProyecto` ;

-- -----------------------------------------------------
-- Table `bbddProyecto`.`Alumno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bbddProyecto`.`Alumno` (
  `email` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bbddProyecto`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bbddProyecto`.`Curso` (
  `curso` YEAR NOT NULL,
  PRIMARY KEY (`curso`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bbddProyecto`.`Mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bbddProyecto`.`Mensaje` (
  `tipo` VARCHAR(45) NOT NULL,
  `mensaje` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`tipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bbddProyecto`.`Periodo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bbddProyecto`.`Periodo` (
  `idPeriodo` INT(11) NOT NULL AUTO_INCREMENT,
  `diaInicio` DATE NOT NULL,
  `diaFinal` DATE NOT NULL,
  `horaInicio` TIME NOT NULL,
  `horaFinal` TIME NOT NULL,
  `tiempo` TIME NOT NULL,
  `curso` YEAR NOT NULL,
  PRIMARY KEY (`idPeriodo`),
  INDEX `fk_Periodo_1_idx` (`curso` ASC),
  CONSTRAINT `fk_Periodo_1`
    FOREIGN KEY (`curso`)
    REFERENCES `bbddProyecto`.`Curso` (`curso`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bbddProyecto`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bbddProyecto`.`Reserva` (
  `idReserva` INT(11) NOT NULL AUTO_INCREMENT,
  `dia` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `idPeriodo` INT(11) NOT NULL,
  PRIMARY KEY (`idReserva`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_Reserva_1_idx` (`idPeriodo` ASC),
  INDEX `fk_Reserva_2_idx` (`email` ASC),
  CONSTRAINT `fk_Reserva_1`
    FOREIGN KEY (`idPeriodo`)
    REFERENCES `bbddProyecto`.`Periodo` (`idPeriodo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Reserva_2`
    FOREIGN KEY (`email`)
    REFERENCES `bbddProyecto`.`Alumno` (`email`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Procedimientos
-- -----------------------------------------------------
DELIMITER @@
DROP PROCEDURE IF EXISTS crearReservas @@
CREATE PROCEDURE crearReservas(in id INT)
BEGIN

    DECLARE existePeriodo INT DEFAULT 0;
    DECLARE dia DATE;
    DECLARE HORA TIME;

    SELECT COUNT(*) INTO existePeriodo FROM Periodo WHERE idPeriodo=id;
	/*
    IF existePeriodo = 1
    THEN
        
    END IF;
    */
END @@
DELIMITER ;




