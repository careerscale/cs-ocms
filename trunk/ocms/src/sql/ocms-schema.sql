SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `ocms` ;
CREATE SCHEMA IF NOT EXISTS `ocms` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `ocms` ;

-- -----------------------------------------------------
-- Table `login_master`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `login_master` ;

CREATE  TABLE IF NOT EXISTS `login_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `email_id` VARCHAR(100) NULL ,
  `password` VARCHAR(80) NULL ,
  `first_name` VARCHAR(75) NOT NULL ,
  `last_name` VARCHAR(75) NULL ,
  `date_of_birth` DATETIME NULL ,
  `login_type` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_id_UNIQUE` (`email_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_status_master`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_status_master` ;

CREATE  TABLE IF NOT EXISTS `case_status_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `case_status_name` VARCHAR(50) NOT NULL ,
  `case_status_description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `case_status_name_UNIQUE` (`case_status_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_category_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `help_category_type` ;

CREATE  TABLE IF NOT EXISTS `help_category_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `category_name` VARCHAR(50) NOT NULL ,
  `description` VARCHAR(100) NULL ,
  `parent_type_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `category_name_UNIQUE` (`category_name` ASC) ,
  INDEX `fk_help_category_parent_id_idx` (`parent_type_id` ASC) ,
  CONSTRAINT `fk_help_category_parent_id`
    FOREIGN KEY (`parent_type_id` )
    REFERENCES `help_category_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_type` ;

CREATE  TABLE IF NOT EXISTS `case_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `parent_type_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `fk_case_parent_type_id_idx` (`parent_type_id` ASC) ,
  CONSTRAINT `fk_case_parent_type_id`
    FOREIGN KEY (`parent_type_id` )
    REFERENCES `case_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `country` ;

CREATE  TABLE IF NOT EXISTS `country` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `state` ;

CREATE  TABLE IF NOT EXISTS `state` (
  `id` INT(15) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `fk_country_id` INT(15) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_state_countries1_idx` (`fk_country_id` ASC) ,
  CONSTRAINT `fk_state_countries1`
    FOREIGN KEY (`fk_country_id` )
    REFERENCES `country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `city` ;

CREATE  TABLE IF NOT EXISTS `city` (
  `id` INT(15) NOT NULL ,
  `city_name` VARCHAR(45) NOT NULL ,
  `fk_state_id` INT(15) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_cities_state1_idx` (`fk_state_id` ASC) ,
  CONSTRAINT `fk_cities_state1`
    FOREIGN KEY (`fk_state_id` )
    REFERENCES `state` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE  TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL ,
  `address_line1` VARCHAR(150) NOT NULL ,
  `address_line2` VARCHAR(45) NULL ,
  `city_id` INT NOT NULL DEFAULT 0 ,
  `zip code` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_address_cities1_idx` (`city_id` ASC) ,
  CONSTRAINT `fk_address_city_id`
    FOREIGN KEY (`city_id` )
    REFERENCES `city` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_master`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_master` ;

CREATE  TABLE IF NOT EXISTS `case_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `created_by` INT NOT NULL ,
  `created_on` DATETIME NOT NULL ,
  `updated_by` INT NOT NULL ,
  `updated_on` DATETIME NOT NULL ,
  `person_name` VARCHAR(100) NOT NULL ,
  `date_of_birth` DATETIME NULL ,
  `case_description` VARCHAR(500) NOT NULL ,
  `contact_number1` VARCHAR(25) NOT NULL ,
  `contact_number2` VARCHAR(45) NULL ,
  `source` VARCHAR(100) NOT NULL ,
  `case_status_id` INT NOT NULL ,
  `case_type_id` INT NOT NULL ,
  `help_category_id` INT NOT NULL ,
  `email_id` VARCHAR(45) NULL ,
  `address_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_master_status_id_idx` (`case_status_id` ASC) ,
  INDEX `fk_case_master_help_category_id_idx` (`help_category_id` ASC) ,
  INDEX `fk_case_master_created_by_idx` (`created_by` ASC) ,
  INDEX `fk_case_master_updated_by_idx` (`updated_by` ASC) ,
  INDEX `fk_case_master_case_type_id_idx` (`case_type_id` ASC) ,
  INDEX `fk_case_master_address_id_idx` (`address_id` ASC) ,
  CONSTRAINT `fk_case_master_status_id`
    FOREIGN KEY (`case_status_id` )
    REFERENCES `case_status_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_help_category_id`
    FOREIGN KEY (`help_category_id` )
    REFERENCES `help_category_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_created_by`
    FOREIGN KEY (`created_by` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_updated_by`
    FOREIGN KEY (`updated_by` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_case_type_id`
    FOREIGN KEY (`case_type_id` )
    REFERENCES `case_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_address_id`
    FOREIGN KEY (`address_id` )
    REFERENCES `address` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_user` ;

CREATE  TABLE IF NOT EXISTS `case_user` (
  `case_id` INT NULL ,
  `user_id` INT NULL ,
  `is_org` TINYINT(1) NULL DEFAULT false ,
  INDEX `fk_case_users_user_id_idx` (`user_id` ASC) ,
  INDEX `fk_case_users_case_id_idx` (`case_id` ASC) ,
  PRIMARY KEY (`case_id`, `user_id`) ,
  CONSTRAINT `fk_case_users_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_users_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_artifact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_artifact` ;

CREATE  TABLE IF NOT EXISTS `case_artifact` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `artifact_type` VARCHAR(25) NOT NULL ,
  `artifact` BLOB NOT NULL ,
  `case_id` INT NOT NULL ,
  `added_by` INT NULL ,
  INDEX `fk_case_artifacts_case_id_idx` (`case_id` ASC) ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_artifact_user_id_idx` (`added_by` ASC) ,
  CONSTRAINT `fk_case_artifacts_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_artifact_user_id`
    FOREIGN KEY (`added_by` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_activity` ;

CREATE  TABLE IF NOT EXISTS `case_activity` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `action` VARCHAR(255) NOT NULL ,
  `action_by` INT NOT NULL ,
  `action_date` DATETIME NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `case_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_activities_case_id_idx` (`case_id` ASC) ,
  INDEX `fk_case_activity_user_id_idx` (`action_by` ASC) ,
  CONSTRAINT `fk_case_activities_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_activity_user_id`
    FOREIGN KEY (`action_by` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fund_management`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fund_management` ;

CREATE  TABLE IF NOT EXISTS `fund_management` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `donor` VARCHAR(100) NOT NULL ,
  `purpose` VARCHAR(255) NOT NULL ,
  `amount` INT NOT NULL ,
  `credit_debit` INT NOT NULL ,
  `date` DATETIME NOT NULL ,
  `case_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_fund_management_case_id_idx` (`case_id` ASC) ,
  CONSTRAINT `fk_fund_management_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_category_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `help_category_user` ;

CREATE  TABLE IF NOT EXISTS `help_category_user` (
  `user_id` INT NOT NULL ,
  `help_category_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `help_category_id`) ,
  INDEX `fk_help_category_users_category_id_idx` (`help_category_id` ASC) ,
  INDEX `fk_help_category_users_user_id_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_help_category_users_category_id`
    FOREIGN KEY (`help_category_id` )
    REFERENCES `help_category_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_help_category_users_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_type_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_type_user` ;

CREATE  TABLE IF NOT EXISTS `case_type_user` (
  `user_id` INT NOT NULL ,
  `case_type_id` INT NOT NULL ,
  PRIMARY KEY (`case_type_id`, `user_id`) ,
  INDEX `fk_case_type_user_id_idx` (`user_id` ASC) ,
  INDEX `fk_case_type_case_type_id_idx` (`case_type_id` ASC) ,
  CONSTRAINT `fk_case_type_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_type_case_type_id`
    FOREIGN KEY (`case_type_id` )
    REFERENCES `case_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_profile` ;

CREATE  TABLE IF NOT EXISTS `user_profile` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `other_email_id` VARCHAR(100) NOT NULL ,
  `blood_group` VARCHAR(10) NOT NULL ,
  `anniversary` DATETIME NOT NULL ,
  `user_id` INT NOT NULL ,
  `monthly_updates` TINYINT(1) NOT NULL DEFAULT 1 ,
  `special_updates` TINYINT(1) NOT NULL DEFAULT 1 ,
  `regular_updates` TINYINT(1) NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_user_profile_user_id_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_user_profile_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `org_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `org_type` ;

CREATE  TABLE IF NOT EXISTS `org_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `parent_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `fk_org_type_parent_id_idx` (`parent_id` ASC) ,
  CONSTRAINT `fk_org_type_parent_id`
    FOREIGN KEY (`parent_id` )
    REFERENCES `org_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `organization` ;

CREATE  TABLE IF NOT EXISTS `organization` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `org_type_id` INT NOT NULL ,
  `address` VARCHAR(255) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_organization_info_org_type_id_idx` (`org_type_id` ASC) ,
  CONSTRAINT `fk_organization_info_org_type_id`
    FOREIGN KEY (`org_type_id` )
    REFERENCES `org_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_organization` ;

CREATE  TABLE IF NOT EXISTS `user_organization` (
  `user_id` INT NOT NULL ,
  `org_id` INT NOT NULL ,
  INDEX `fk_user_organization_user_id_idx` (`user_id` ASC) ,
  INDEX `fk_user_organization_org_id_idx` (`org_id` ASC) ,
  PRIMARY KEY (`user_id`, `org_id`) ,
  CONSTRAINT `fk_user_organization_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_organization_org_id`
    FOREIGN KEY (`org_id` )
    REFERENCES `organization` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role_master`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role_master` ;

CREATE  TABLE IF NOT EXISTS `role_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `module_master`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module_master` ;

CREATE  TABLE IF NOT EXISTS `module_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_role` ;

CREATE  TABLE IF NOT EXISTS `user_role` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `role_id` INT NOT NULL ,
  `module_id` INT NULL ,
  INDEX `fk_user_role_user_id_idx` (`user_id` ASC) ,
  INDEX `fk_user_role_role_id_idx` (`role_id` ASC) ,
  INDEX `fk_user_role_module_id_idx` (`module_id` ASC) ,
  CONSTRAINT `fk_user_role_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_role_id`
    FOREIGN KEY (`role_id` )
    REFERENCES `role_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_module_id`
    FOREIGN KEY (`module_id` )
    REFERENCES `module_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role_permission` ;

CREATE  TABLE IF NOT EXISTS `role_permission` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `read_perm` VARCHAR(1) NOT NULL ,
  `edit_perm` VARCHAR(1) NOT NULL ,
  `add_perm` VARCHAR(1) NOT NULL ,
  `approve_perm` VARCHAR(1) NOT NULL ,
  `delete_perm` VARCHAR(1) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `role_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_role_permissions_role_id`
    FOREIGN KEY (`role_id` )
    REFERENCES `role_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `email_template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `email_template` ;

CREATE  TABLE IF NOT EXISTS `email_template` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_network`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_network` ;

CREATE  TABLE IF NOT EXISTS `social_network` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `description` VARCHAR(250) NULL ,
  `api_key` VARCHAR(45) NULL ,
  `api_secret` VARCHAR(45) NULL ,
  `callback_url` VARCHAR(100) NULL ,
  `scope` VARCHAR(250) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_network`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_network` ;

CREATE  TABLE IF NOT EXISTS `user_network` (
  `user_id` INT NOT NULL ,
  `network_id` INT NOT NULL ,
  `user_network_id` VARCHAR(100) NOT NULL ,
  `last_access_date` DATE NULL ,
  PRIMARY KEY (`user_network_id`) ,
  INDEX `user_network_user_id_idx` (`user_id` ASC) ,
  INDEX `user_network_social_id_idx` (`network_id` ASC) ,
  CONSTRAINT `user_network_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_network_social_id`
    FOREIGN KEY (`network_id` )
    REFERENCES `social_network` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification_template_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_template_status` ;

CREATE  TABLE IF NOT EXISTS `notification_template_status` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification_template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_template` ;

CREATE  TABLE IF NOT EXISTS `notification_template` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `status_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_notification_template_notification_temp_status1_idx` (`status_id` ASC) ,
  CONSTRAINT `fk_notification_template_notification_temp_status1`
    FOREIGN KEY (`status_id` )
    REFERENCES `notification_template_status` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_status` ;

CREATE  TABLE IF NOT EXISTS `notification_status` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_details` ;

CREATE  TABLE IF NOT EXISTS `notification_details` (
  `id` INT NOT NULL ,
  `template_id` INT NULL ,
  `notification_type` VARCHAR(45) NULL ,
  `template_text` TEXT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_notification_details_notification_template1_idx` (`template_id` ASC) ,
  CONSTRAINT `fk_notification_details_notification_template1`
    FOREIGN KEY (`template_id` )
    REFERENCES `notification_template` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification_recipient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_recipient` ;

CREATE  TABLE IF NOT EXISTS `notification_recipient` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification` ;

CREATE  TABLE IF NOT EXISTS `notification` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `template_id` INT NULL ,
  `case_id` INT NULL ,
  `reciepient_type` INT NULL ,
  `recepient_additional_info` VARCHAR(95) NULL ,
  `status` INT NULL ,
  `created_on` DATETIME NOT NULL ,
  `created_by` INT NOT NULL ,
  `updated_on` DATETIME NOT NULL ,
  `updated_by` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_notification_notification_template_id_idx` (`template_id` ASC) ,
  INDEX `fk_notification_case_master_id_idx` (`case_id` ASC) ,
  INDEX `fk_notification_notification_status_id_idx` (`status` ASC) ,
  INDEX `fk_notification_notification_recipient_id_idx` (`reciepient_type` ASC) ,
  CONSTRAINT `fk_notification_notification_template_id`
    FOREIGN KEY (`template_id` )
    REFERENCES `notification_template` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_case_master_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_notification_status_id`
    FOREIGN KEY (`status` )
    REFERENCES `notification_status` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_notification_recipient_id`
    FOREIGN KEY (`reciepient_type` )
    REFERENCES `notification_recipient` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `ocms` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `login_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `login_master` (`id`, `email_id`, `password`, `first_name`, `last_name`, `date_of_birth`, `login_type`) VALUES (1, 'harinath@tmad.org', 'test123', 'Harinath', 'Mallepally', '1979-06-06', 1);
INSERT INTO `login_master` (`id`, `email_id`, `password`, `first_name`, `last_name`, `date_of_birth`, `login_type`) VALUES (2, 'hari@harinath.in', 'test123', 'Harinath', '', '1979-06-01', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_status_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (1, 'New', 'New case, just entered the system');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (2, 'Pending', 'Case is pending, not yet active');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (3, 'In Progress', 'Case is active');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (4, 'On Hold', 'Case is on hold for some dependency or clarification');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (5, 'Resolved', 'Case is successfully addressed. ');

COMMIT;

-- -----------------------------------------------------
-- Data for table `help_category_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `help_category_type` (`id`, `category_name`, `description`, `parent_type_id`) VALUES (1, 'Monetary', 'Monetary/Financial help', NULL);
INSERT INTO `help_category_type` (`id`, `category_name`, `description`, `parent_type_id`) VALUES (2, 'Reference', 'Need/Provide reference person/information', NULL);
INSERT INTO `help_category_type` (`id`, `category_name`, `description`, `parent_type_id`) VALUES (3, 'Guidance', 'Need/provide Guidance (SME)', NULL);
INSERT INTO `help_category_type` (`id`, `category_name`, `description`, `parent_type_id`) VALUES (4, 'Non monetary', 'Any other non financial support needed/provided', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (1, 'Medical', 'Medical cases', NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (2, 'Education', 'Education cases', NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (3, 'Amenities', 'Amenities/facilities needed', NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (4, 'Calamities', 'Natural calamitiies like floods or fire etc', NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (5, 'Heart Operation', 'Medical/Heart specific', 1);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (6, 'Kidney', 'Medical/kidney specific', 1);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (7, 'Education Loan', 'Education/loan', 2);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (8, 'Guidance', 'Education/guidance', 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_master` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_type_id`, `help_category_id`, `email_id`, `address_id`) VALUES (1, 1, '2008-05-27', 1, '2009-02-23', 'Prasanthi', '1980-01-01', 'need urgent blood group O-', '898989898', '898989', 'friend', 1, 1, 1, NULL, NULL);
INSERT INTO `case_master` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_type_id`, `help_category_id`, `email_id`, `address_id`) VALUES (2, 1, '2011-01-9', 1, '2009-09-12', 'Kashyap', '1979-05-05', 'Fresher needs Job', '2342343', '4324324', 'news paper', 1, 2, 1, NULL, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_user` (`case_id`, `user_id`, `is_org`) VALUES (1, 1, 0);
INSERT INTO `case_user` (`case_id`, `user_id`, `is_org`) VALUES (2, 1, 0);

COMMIT;

-- -----------------------------------------------------
-- Data for table `org_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (1, 'NGO', 'NGO Organization', NULL);
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (2, 'State Government', 'State Govt. Organizations', NULL);
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (3, 'Union/Central Government', 'Central Govt. organizations', NULL);
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (4, 'Private Limited/Corporate', 'Private/Corporate company', NULL);
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (5, 'International Charity', 'International organization', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `role_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `role_master` (`id`, `role_name`, `description`) VALUES (1, 'REGISTERED', 'registered');
INSERT INTO `role_master` (`id`, `role_name`, `description`) VALUES (2, 'USER', 'site user');
INSERT INTO `role_master` (`id`, `role_name`, `description`) VALUES (3, 'MANAGER', 'site manager');
INSERT INTO `role_master` (`id`, `role_name`, `description`) VALUES (4, 'ADMIN', 'admin');

COMMIT;

-- -----------------------------------------------------
-- Data for table `module_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `module_master` (`id`, `name`, `description`) VALUES (1, 'Case Registration', 'Case registration');
INSERT INTO `module_master` (`id`, `name`, `description`) VALUES (2, 'User Registration', 'User Registration');
INSERT INTO `module_master` (`id`, `name`, `description`) VALUES (3, 'Administration', 'Administration');
INSERT INTO `module_master` (`id`, `name`, `description`) VALUES (4, 'Case Life Cycle', 'Case Life cycle');

COMMIT;

-- -----------------------------------------------------
-- Data for table `user_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`) VALUES (1, 1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`) VALUES (1, 2, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`) VALUES (1, 3, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`) VALUES (1, 4, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`) VALUES (2, 1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`) VALUES (2, 2, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `social_network`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `social_network` (`id`, `name`, `description`, `api_key`, `api_secret`, `callback_url`, `scope`) VALUES (1, 'Linkedn', 'Linkedn', 'vpfc1bjfjokm', 'jbN6kSiRSaYp2dDe', 'http://localhost:9090/linkedin-callback', NULL);
INSERT INTO `social_network` (`id`, `name`, `description`, `api_key`, `api_secret`, `callback_url`, `scope`) VALUES (2, 'Facebook', 'Facebook', '547688988597448', '9a07fdf996236b9c4e7a010549d638d3', 'http://careerscale.in:9090/facebook-callback', NULL);
INSERT INTO `social_network` (`id`, `name`, `description`, `api_key`, `api_secret`, `callback_url`, `scope`) VALUES (3, 'Google', 'Google', '179271485873.apps.googleusercontent.com', 'ghGOdAEKfCWlz_ClgbYLTLEp', 'http://localhost:9090/google-callback', 'https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile');
INSERT INTO `social_network` (`id`, `name`, `description`, `api_key`, `api_secret`, `callback_url`, `scope`) VALUES (4, 'Twitter', 'Twitter', 'FDyGFjNABJKZlPo80TmcA', '0JS0T1PcgFVDQlKhaM6LNwhInzhjmrimgq0k88QgUE', 'http://localhost:9090/twitter-callback', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `notification_template_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `notification_template_status` (`id`, `name`) VALUES (1, 'Active');
INSERT INTO `notification_template_status` (`id`, `name`) VALUES (2, 'Pending');

COMMIT;

-- -----------------------------------------------------
-- Data for table `notification_template`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `notification_template` (`id`, `name`, `status_id`) VALUES (1, 'NewCase', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `notification_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `notification_status` (`id`, `name`) VALUES (1, 'Pending');
INSERT INTO `notification_status` (`id`, `name`) VALUES (2, 'Processing');
INSERT INTO `notification_status` (`id`, `name`) VALUES (3, 'Processed');
INSERT INTO `notification_status` (`id`, `name`) VALUES (4, 'Failed');

COMMIT;
