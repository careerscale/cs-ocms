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
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `country_id` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_state_countries1_idx` (`country_id` ASC) ,
  CONSTRAINT `fk_state_countries1`
    FOREIGN KEY (`country_id` )
    REFERENCES `country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `city` ;

CREATE  TABLE IF NOT EXISTS `city` (
  `id` INT NOT NULL ,
  `city_name` VARCHAR(100) NOT NULL ,
  `state_id` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_cities_state1_idx` (`state_id` ASC) ,
  CONSTRAINT `fk_cities_state1`
    FOREIGN KEY (`state_id` )
    REFERENCES `state` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE  TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `address_line1` VARCHAR(150) NOT NULL ,
  `address_line2` VARCHAR(45) NULL ,
  `city_id` INT NOT NULL ,
  `zip_code` VARCHAR(20) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_address_cities1_idx` (`city_id` ASC) ,
  CONSTRAINT `fk_address_city_id`
    FOREIGN KEY (`city_id` )
    REFERENCES `city` (`id` )
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
  `organization_id` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_master_status_id_idx` (`case_status_id` ASC) ,
  INDEX `fk_case_master_help_category_id_idx` (`help_category_id` ASC) ,
  INDEX `fk_case_master_created_by_idx` (`created_by` ASC) ,
  INDEX `fk_case_master_updated_by_idx` (`updated_by` ASC) ,
  INDEX `fk_case_master_case_type_id_idx` (`case_type_id` ASC) ,
  INDEX `fk_case_master_address_id_idx` (`address_id` ASC) ,
  INDEX `fk_case_master_org_id_idx` (`organization_id` ASC) ,
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
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_org_id`
    FOREIGN KEY (`organization_id` )
    REFERENCES `organization` (`id` )
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
-- Table `document_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `document_type` ;

CREATE  TABLE IF NOT EXISTS `document_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `case_type_id` INT NULL ,
  `supported_formats` VARCHAR(150) NULL ,
  `is_mandatory` BIT NULL ,
  `max_size` INT NULL ,
  `name` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_document_type_case_type_id_idx` (`case_type_id` ASC) ,
  CONSTRAINT `fk_document_type_case_type_id`
    FOREIGN KEY (`case_type_id` )
    REFERENCES `case_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_artifact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_artifact` ;

CREATE  TABLE IF NOT EXISTS `case_artifact` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `artifact` MEDIUMBLOB NOT NULL ,
  `case_id` INT NOT NULL ,
  `added_by` INT NULL ,
  `file_extension` VARCHAR(45) NULL ,
  `document_type` INT NULL ,
  INDEX `fk_case_artifacts_case_id_idx` (`case_id` ASC) ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_artifact_user_id_idx` (`added_by` ASC) ,
  INDEX `fk_case_artifact_document_type_idx` (`document_type` ASC) ,
  CONSTRAINT `fk_case_artifacts_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_artifact_user_id`
    FOREIGN KEY (`added_by` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_artifact_document_type`
    FOREIGN KEY (`document_type` )
    REFERENCES `document_type` (`id` )
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
-- Table `fund_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fund_status` ;

CREATE  TABLE IF NOT EXISTS `fund_status` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fund`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fund` ;

CREATE  TABLE IF NOT EXISTS `fund` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `donor` VARCHAR(150) NOT NULL DEFAULT 'This will be used to issued receipt.' ,
  `purpose` VARCHAR(255) NOT NULL ,
  `credit_amount` INT NULL ,
  `promised_date` DATETIME NOT NULL ,
  `case_id` INT NOT NULL ,
  `confirmed_date` DATETIME NULL ,
  `status_id` INT NULL ,
  `receipt_issued_on` DATETIME NULL ,
  `login_id` INT NOT NULL ,
  `debited_amount` INT NULL ,
  `receipt` MEDIUMBLOB NULL ,
  `confirmed_by` INT NULL ,
  `receipt_description` VARCHAR(255) NULL ,
  `confirmation_comments` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_fund_management_case_id_idx` (`case_id` ASC) ,
  INDEX `fk_funds_status_id_idx` (`status_id` ASC) ,
  INDEX `fk_funds_login_id_idx` (`login_id` ASC) ,
  INDEX `fk_funds_confirmed_by_idx` (`confirmed_by` ASC) ,
  CONSTRAINT `fk_funds_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funds_status_id`
    FOREIGN KEY (`status_id` )
    REFERENCES `fund_status` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funds_login_id`
    FOREIGN KEY (`login_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funds_confirmed_by`
    FOREIGN KEY (`confirmed_by` )
    REFERENCES `login_master` (`id` )
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
  `id` INT NOT NULL ,
  `other_email_id` VARCHAR(100) NULL ,
  `blood_group` VARCHAR(10) NOT NULL ,
  `anniversary` DATETIME NULL ,
  `monthly_updates` TINYINT(1) NULL DEFAULT 1 ,
  `special_updates` TINYINT(1) NULL DEFAULT 1 ,
  `regular_updates` TINYINT(1) NULL DEFAULT 1 ,
  `mobile_number1` VARCHAR(20) NULL ,
  `mobile_number2` VARCHAR(20) NULL ,
  `landline_number` VARCHAR(20) NULL ,
  `address_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_user_profile_address_id_idx` (`address_id` ASC) ,
  CONSTRAINT `fk_user_profile_user_id`
    FOREIGN KEY (`id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_profile_address_id`
    FOREIGN KEY (`address_id` )
    REFERENCES `address` (`id` )
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
  `organization_id` INT NULL ,
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
  `user_access_token` VARCHAR(100) NULL ,
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
-- Table `notification_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_status` ;

CREATE  TABLE IF NOT EXISTS `notification_status` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification_recipient_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_recipient_type` ;

CREATE  TABLE IF NOT EXISTS `notification_recipient_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification_channel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_channel` ;

CREATE  TABLE IF NOT EXISTS `notification_channel` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification` ;

CREATE  TABLE IF NOT EXISTS `notification` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `template_name` VARCHAR(100) NULL ,
  `case_id` INT NULL ,
  `reciepient_type_id` INT NULL ,
  `recepient_additional_info` VARCHAR(255) NULL ,
  `status` INT NULL ,
  `created_on` DATETIME NOT NULL ,
  `created_by` INT NOT NULL ,
  `updated_on` DATETIME NOT NULL ,
  `updated_by` INT NOT NULL ,
  `channel_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_notification_case_master_id_idx` (`case_id` ASC) ,
  INDEX `fk_notification_notification_status_id_idx` (`status` ASC) ,
  INDEX `fk_notification_notification_recipient_id_idx` (`reciepient_type_id` ASC) ,
  INDEX `fk_notification_created_by_idx` (`created_by` ASC) ,
  INDEX `fk_notification_updated_by_idx` (`updated_by` ASC) ,
  INDEX `fk_notification_channel_id_idx` (`channel_id` ASC) ,
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
    FOREIGN KEY (`reciepient_type_id` )
    REFERENCES `notification_recipient_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_created_by`
    FOREIGN KEY (`created_by` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_updated_by`
    FOREIGN KEY (`updated_by` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_channel_id`
    FOREIGN KEY (`channel_id` )
    REFERENCES `notification_channel` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `document_options`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `document_options` ;

CREATE  TABLE IF NOT EXISTS `document_options` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `document_type_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `document_options_doc_type_id_idx` (`document_type_id` ASC) ,
  CONSTRAINT `document_options_doc_type_id`
    FOREIGN KEY (`document_type_id` )
    REFERENCES `document_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_discussion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_discussion` ;

CREATE  TABLE IF NOT EXISTS `case_discussion` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `case_id` INT NULL ,
  `type_id` INT NULL ,
  `parent_discussion_id` INT NULL ,
  `comments` MEDIUMTEXT NULL ,
  `created_on` DATETIME NULL ,
  `created_by` INT NULL ,
  `subject` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_discussion_case_id_idx` (`case_id` ASC) ,
  INDEX `fk_discusssion_parent_id_idx` (`parent_discussion_id` ASC) ,
  INDEX `fk_discussion_created_by_idx` (`created_by` ASC) ,
  INDEX `fk_discussion_type_id_idx` (`type_id` ASC) ,
  CONSTRAINT `fk_discussion_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_discusssion_parent_id`
    FOREIGN KEY (`parent_discussion_id` )
    REFERENCES `case_discussion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_discussion_created_by`
    FOREIGN KEY (`created_by` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_discussion_type_id`
    FOREIGN KEY (`type_id` )
    REFERENCES `document_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `discussion_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `discussion_type` ;

CREATE  TABLE IF NOT EXISTS `discussion_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_type_approver`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_type_approver` ;

CREATE  TABLE IF NOT EXISTS `case_type_approver` (
  `case_type` INT NOT NULL ,
  `login_id` INT NOT NULL ,
  `organization_id` INT NOT NULL DEFAULT 1 ,
  INDEX `fk_case_type_approver_case_type_id_idx` (`case_type` ASC) ,
  INDEX `fk_case_type_approver_login_id_idx` (`login_id` ASC) ,
  INDEX `fk_case_type_approver_org_id_idx` (`organization_id` ASC) ,
  PRIMARY KEY (`case_type`, `login_id`, `organization_id`) ,
  CONSTRAINT `fk_case_type_approver_case_type_id`
    FOREIGN KEY (`case_type` )
    REFERENCES `case_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_type_approver_login_id`
    FOREIGN KEY (`login_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_type_approver_org_id`
    FOREIGN KEY (`organization_id` )
    REFERENCES `organization` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_approval_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_approval_history` ;

CREATE  TABLE IF NOT EXISTS `case_approval_history` (
  `case_id` INT NOT NULL ,
  `login_id` INT NOT NULL ,
  `approval_status_id` INT NULL ,
  `reason` VARCHAR(500) NULL ,
  `approval_date` DATETIME NULL ,
  `id` INT NOT NULL AUTO_INCREMENT ,
  INDEX `fk_case_history_case_id_idx` (`case_id` ASC) ,
  INDEX `fk_case_history_login_id_idx` (`login_id` ASC) ,
  INDEX `fk_case_history_status_id_idx` (`approval_status_id` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_case_history_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_history_login_id`
    FOREIGN KEY (`login_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_history_status_id`
    FOREIGN KEY (`approval_status_id` )
    REFERENCES `case_status_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recurring_contribution`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recurring_contribution` ;

CREATE  TABLE IF NOT EXISTS `recurring_contribution` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `login_id` INT NULL ,
  `case_id` INT NULL ,
  `credit_amount` INT NULL ,
  `frequency` VARCHAR(100) NULL ,
  `day_of_month` INT NULL ,
  `is_active` TINYINT(1) NULL ,
  `last_contribution_date` DATE NULL ,
  `debit_amount` INT NULL ,
  `donor_name` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `recurring_contribution_login_id_idx` (`login_id` ASC) ,
  INDEX `fk_recurring_contribution_case_id_idx` (`case_id` ASC) ,
  CONSTRAINT `fk_recurring_contribution_login_id`
    FOREIGN KEY (`login_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recurring_contribution_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
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
INSERT INTO `login_master` (`id`, `email_id`, `password`, `first_name`, `last_name`, `date_of_birth`, `login_type`) VALUES (3, 'mahender@careerscale.in', 'test123', 'Mahender', 'Singh', '1979-01-01', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_status_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (1, 'New', 'New case, just entered the system');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (2, 'Pending', 'Case is pending, not yet active');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (3, 'OnHold', 'Case is on hold for some dependency or clarification');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (4, 'Approved', 'Case is active');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (5, 'Rejected', 'Case is rejected');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (6, 'Resolved', 'Case is addressed and met the expectations');
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (7, 'Closed', 'Closed in every aspect, including generating bills.');

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
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (1, 'Generic case', 'Generic case', NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (2, 'Medical case', 'Medical cases', 1);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (3, 'Education Case', 'Education Cases', 1);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (4, 'Amenities', 'Amenities/Facilities', 1);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (5, 'Heart Operation', 'Medical/Heart specific', 2);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (6, 'Kidney', 'Medical/kidney specific', 2);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (7, 'Education Loan', 'Education/loan', 3);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (8, 'Guidance', 'Education/guidance', 3);

COMMIT;

-- -----------------------------------------------------
-- Data for table `country`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `country` (`id`, `name`) VALUES (1, 'India');
INSERT INTO `country` (`id`, `name`) VALUES (2, 'United States Of America');

COMMIT;

-- -----------------------------------------------------
-- Data for table `state`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `state` (`id`, `name`, `country_id`) VALUES (1, 'Andhra Pradesh', 1);
INSERT INTO `state` (`id`, `name`, `country_id`) VALUES (2, 'Arunachal Pradesh', 1);
INSERT INTO `state` (`id`, `name`, `country_id`) VALUES (3, 'Bihar', 1);
INSERT INTO `state` (`id`, `name`, `country_id`) VALUES (4, 'Karnataka', 1);
INSERT INTO `state` (`id`, `name`, `country_id`) VALUES (5, 'Tamil Nadu', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `city`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (1, 'Hyderabad', 1);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (2, 'Vijayawada', 1);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (3, 'Tirupathi', 1);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (4, 'Vizag', 1);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (5, 'Warangal', 1);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (6, 'Chennai', 5);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (7, 'Madurai', 5);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (8, 'Trichy', 5);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (9, 'Bangalore', 4);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (10, 'Suratkal', 4);
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES (11, 'Mysore', 4);

COMMIT;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `address` (`id`, `address_line1`, `address_line2`, `city_id`, `zip_code`) VALUES (1, 'sv enclave, upparpally', 'attapur', 1, '500048');
INSERT INTO `address` (`id`, `address_line1`, `address_line2`, `city_id`, `zip_code`) VALUES (2, 'Vidyanagar, nellore', 'AP', 2, '545555');

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
-- Data for table `organization`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `organization` (`id`, `name`, `org_type_id`, `address`, `description`) VALUES (1, 'To Make A Difference', 1, '2-1-7/2/88,Venkateswara Enclave, upparpally', 'TMAD is established in 2006 and has been into various activities');

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_master` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_type_id`, `help_category_id`, `email_id`, `address_id`, `organization_id`) VALUES (1, 1, '2008-05-27', 1, '2009-02-23', 'Careerscale IT Consulitng LLP', '1980-01-01', 'Support to build and maintain OCMS', '91 9391920444', '+91 40 42100276', 'OCMS', 4, 1, 1, 'info@careerscale.in', 1, 1);
INSERT INTO `case_master` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_type_id`, `help_category_id`, `email_id`, `address_id`, `organization_id`) VALUES (2, 1, '2011-01-9', 1, '2009-09-12', 'To Make A Diffrence', '1979-05-05', 'Monthly contribution to pool up and attend to urgent cases', '', '', 'TMAD', 4, 1, 1, 'finance@tmad.org', 1, 1);
INSERT INTO `case_master` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_type_id`, `help_category_id`, `email_id`, `address_id`, `organization_id`) VALUES (3, 3, '2013-06-06', 1, '2013-07-07', 'Pavani', '195-01-01', 'Education case - need support for PG fee', '999999', '888888', 'chennai TMAD', 1, 2, 1, 'abc@tmad.org', 2, 1);

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
-- Data for table `document_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `document_type` (`id`, `case_type_id`, `supported_formats`, `is_mandatory`, `max_size`, `name`) VALUES (1, 1, 'pdf,jpg,gif', 1, 2, 'ID Proof');
INSERT INTO `document_type` (`id`, `case_type_id`, `supported_formats`, `is_mandatory`, `max_size`, `name`) VALUES (2, 1, 'pdf,jpg,gif', 1, 2, 'Address Proof');

COMMIT;

-- -----------------------------------------------------
-- Data for table `fund_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `fund_status` (`id`, `name`, `description`) VALUES (1, 'PROMISED', 'promised by donor or volunteer');
INSERT INTO `fund_status` (`id`, `name`, `description`) VALUES (2, 'CONFIRMED', 'Confirmed by the finance team or board');
INSERT INTO `fund_status` (`id`, `name`, `description`) VALUES (3, 'RELEASED', 'Funds released for the given purpose');
INSERT INTO `fund_status` (`id`, `name`, `description`) VALUES (4, 'RECEIPT_ISSUED', 'receipt issued to the donor');

COMMIT;

-- -----------------------------------------------------
-- Data for table `user_profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `user_profile` (`id`, `other_email_id`, `blood_group`, `anniversary`, `monthly_updates`, `special_updates`, `regular_updates`, `mobile_number1`, `mobile_number2`, `landline_number`, `address_id`) VALUES (2, 'harinath@careerscale.in', 'O Positive', '2006-03-10', 1, 1, 1, '2342343', '2423423', '24234234', 1);
INSERT INTO `user_profile` (`id`, `other_email_id`, `blood_group`, `anniversary`, `monthly_updates`, `special_updates`, `regular_updates`, `mobile_number1`, `mobile_number2`, `landline_number`, `address_id`) VALUES (1, 'harinath@tmad.org', 'O Positive', '2005-06-06', 1, 1, 1, '434', '344', '334', 1);
INSERT INTO `user_profile` (`id`, `other_email_id`, `blood_group`, `anniversary`, `monthly_updates`, `special_updates`, `regular_updates`, `mobile_number1`, `mobile_number2`, `landline_number`, `address_id`) VALUES (3, 'singh.mahender@gmail.com', 'B Positive', '2001-01-01', 1, 1, 1, '4343434', '2324234234', '2324324', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `role_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `role_master` (`id`, `role_name`, `description`) VALUES (1, 'REGISTERED', 'registered');
INSERT INTO `role_master` (`id`, `role_name`, `description`) VALUES (2, 'MODERATOR', 'site user');
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
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`, `organization_id`) VALUES (1, 1, 1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`, `organization_id`) VALUES (1, 2, 1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`, `organization_id`) VALUES (1, 3, 1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`, `organization_id`) VALUES (1, 4, 1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`, `organization_id`) VALUES (2, 1, 1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`, `module_id`, `organization_id`) VALUES (2, 2, 1, 1);

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
-- Data for table `notification_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `notification_status` (`id`, `name`) VALUES (1, 'Pending');
INSERT INTO `notification_status` (`id`, `name`) VALUES (2, 'Sent');
INSERT INTO `notification_status` (`id`, `name`) VALUES (3, 'Failed');
INSERT INTO `notification_status` (`id`, `name`) VALUES (4, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `notification_recipient_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `notification_recipient_type` (`id`, `type`) VALUES (1, 'Indiviual');
INSERT INTO `notification_recipient_type` (`id`, `type`) VALUES (2, 'CaseUsers');
INSERT INTO `notification_recipient_type` (`id`, `type`) VALUES (3, 'Admin');
INSERT INTO `notification_recipient_type` (`id`, `type`) VALUES (4, 'All');

COMMIT;

-- -----------------------------------------------------
-- Data for table `notification_channel`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `notification_channel` (`id`, `name`) VALUES (1, 'Email');
INSERT INTO `notification_channel` (`id`, `name`) VALUES (2, 'SMS');
INSERT INTO `notification_channel` (`id`, `name`) VALUES (3, 'EmailAndSMS');

COMMIT;

-- -----------------------------------------------------
-- Data for table `document_options`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `document_options` (`id`, `name`, `document_type_id`) VALUES (1, 'Passport', 1);
INSERT INTO `document_options` (`id`, `name`, `document_type_id`) VALUES (2, 'Voter ID', 1);
INSERT INTO `document_options` (`id`, `name`, `document_type_id`) VALUES (3, 'Driving License', 1);
INSERT INTO `document_options` (`id`, `name`, `document_type_id`) VALUES (4, 'Ration Card', 2);
INSERT INTO `document_options` (`id`, `name`, `document_type_id`) VALUES (5, 'Passport', 2);
INSERT INTO `document_options` (`id`, `name`, `document_type_id`) VALUES (6, 'Electricity Bill', 2);
INSERT INTO `document_options` (`id`, `name`, `document_type_id`) VALUES (7, 'Aadhar ID/UID', 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `discussion_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `discussion_type` (`id`, `name`, `description`) VALUES (1, 'COMMENT', NULL);
INSERT INTO `discussion_type` (`id`, `name`, `description`) VALUES (2, 'QUESTION', NULL);
INSERT INTO `discussion_type` (`id`, `name`, `description`) VALUES (3, 'FEEDBACK', NULL);
INSERT INTO `discussion_type` (`id`, `name`, `description`) VALUES (4, 'APPRECIATION', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_type_approver`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (1, 1, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (1, 2, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (1, 3, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (2, 1, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (2, 2, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (2, 3, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (3, 1, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (3, 2, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (3, 3, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (4, 1, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (4, 2, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (4, 3, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (5, 1, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (5, 2, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (6, 1, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (7, 1, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (7, 2, 1);
INSERT INTO `case_type_approver` (`case_type`, `login_id`, `organization_id`) VALUES (8, 1, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_approval_history`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_approval_history` (`case_id`, `login_id`, `approval_status_id`, `reason`, `approval_date`, `id`) VALUES (1, 2, 5, 'Couldnt figure out why we need to support', '2013-07-01', 1);
INSERT INTO `case_approval_history` (`case_id`, `login_id`, `approval_status_id`, `reason`, `approval_date`, `id`) VALUES (1, 1, 4, 'let us help', '2013-07-01', 2);
INSERT INTO `case_approval_history` (`case_id`, `login_id`, `approval_status_id`, `reason`, `approval_date`, `id`) VALUES (1, 3, 4, 'I am ok', '2013-07-03', 3);
INSERT INTO `case_approval_history` (`case_id`, `login_id`, `approval_status_id`, `reason`, `approval_date`, `id`) VALUES (2, 2, 4, 'Ok, let us help', '2013-06-01', 4);
INSERT INTO `case_approval_history` (`case_id`, `login_id`, `approval_status_id`, `reason`, `approval_date`, `id`) VALUES (2, 3, 3, 'provide income proof', '2013-02-01', 5);
INSERT INTO `case_approval_history` (`case_id`, `login_id`, `approval_status_id`, `reason`, `approval_date`, `id`) VALUES (2, 1, 4, 'Let us go ahead', '2013-05-01', 6);

COMMIT;
