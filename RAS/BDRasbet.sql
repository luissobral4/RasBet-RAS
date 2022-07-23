-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema RasBet
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema RasBet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RasBet` DEFAULT CHARACTER SET utf8 ;
USE `RasBet` ;

-- -----------------------------------------------------
-- Table `RasBet`.`Bet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RasBet`.`Bet` (
  `idBet` int NOT NULL,
  `Odd` double NOT NULL,
  `Status` int NOT NULL,
  `Value` double NOT NULL,
  PRIMARY KEY (`idBet`)
) 
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RasBet`.`Event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RasBet`.`Event` (
  `idEvent` int NOT NULL,
  `description` varchar(45) NOT NULL,
  `status` int NOT NULL,
  `betCount` int NOT NULL,
  `result` varchar(45) NOT NULL,
  `vencedor` int NOT NULL, 
  `hora` varchar(45) NOT NULL,
  `dia`varchar(45) NOT NULL,
  PRIMARY KEY (`idEvent`)
) 
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RasBet`.`Market`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RasBet`.`Market` (
  `idSport` int NOT NULL,
  `sport` varchar(45) NOT NULL,
  PRIMARY KEY (`idSport`)
) 
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RasBet`.`Odd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RasBet`.`Odd` (
  `idOdd` int NOT NULL,
  `count` int NOT NULL,
  `betType` varchar(45) NOT NULL,
  `odd` double NOT NULL,
  `state` int NOT NULL,
  PRIMARY KEY (`idOdd`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RasBet`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RasBet`.`User` (
  `idUser` int NOT NULL,
  `password` varchar(45) NOT NULL,
  `status` int NOT NULL,
  `balance` double NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `historico` varchar(1000) NOT NULL,
  `coin` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RasBet`.`UserBetRelation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RasBet`.`UserBetRelation`(
	idUser int NOT NULL,
    idBet int NOT NULL,
    FOREIGN KEY (idUser) REFERENCES User(idUser), 
    FOREIGN KEY (idBet) REFERENCES Bet(idBet),
    UNIQUE (idUser, idBet)
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `RasBet`.`BetOddRelation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RasBet`.`BetOddRelation`(
	idBet int NOT NULL,
    idOdd int NOT NULL,
    FOREIGN KEY (idBet) REFERENCES Bet(idBet), 
    FOREIGN KEY (idOdd) REFERENCES Odd(idOdd),
    UNIQUE (idBet, idOdd)
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `RasBet`.`OddEventRelation`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `RasBet`.`OddEventRelation`(
	idOdd int NOT NULL,
    idEvent int NOT NULL,
    FOREIGN KEY (idOdd) REFERENCES Odd(idOdd), 
    FOREIGN KEY (idEvent) REFERENCES Event(idEvent),
    UNIQUE (idOdd, idEvent)
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `RasBet`.`EventMarketRelation`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `RasBet`.`EventMarketRelation`(
	idEvent int NOT NULL,
    idsport int NOT NULL,
    FOREIGN KEY (idEvent) REFERENCES Event(idEvent), 
    FOREIGN KEY (idSport) REFERENCES Market(idSport),
    UNIQUE (idEvent, idSport)
)ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


