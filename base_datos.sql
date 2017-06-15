CREATE SCHEMA IF NOT XEISTS `evaluador` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
CREATE TABLE IF NOT EXISTS `evaluador`.`Sesion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NULL DEFAULT NULL,
  `evaluador` VARCHAR(120) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `evaluador`.`Aspirante` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(120) NULL DEFAULT NULL,
  `edad` INT(11) NULL DEFAULT NULL,
  `escolaridad` VARCHAR(120) NULL DEFAULT NULL,
  `puesto` VARCHAR(120) NULL DEFAULT NULL,
  `puntuacion` FLOAT(11) NULL DEFAULT NULL,
  `Sesion_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Aspirante_Sesion1_idx` (`Sesion_id` ASC),
  CONSTRAINT `fk_Aspirante_Sesion1`
    FOREIGN KEY (`Sesion_id`)
    REFERENCES `evaluador`.`Sesion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `evaluador`.`Pregunta` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `pregunta` VARCHAR(1000) NULL DEFAULT NULL,
  `respuesta` VARCHAR(1000) NULL DEFAULT NULL,
  `correcta` BIT(1) NULL DEFAULT NULL,
  `fecha` DATETIME NULL DEFAULT NULL,
  `Aspirante_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Pregunta_Aspirante1_idx` (`Aspirante_id` ASC),
  CONSTRAINT `fk_Pregunta_Aspirante1`
    FOREIGN KEY (`Aspirante_id`)
    REFERENCES `evaluador`.`Aspirante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;