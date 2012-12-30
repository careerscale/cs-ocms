SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `login_master`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `login_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `email_id` VARCHAR(100) NOT NULL ,
  `password` VARCHAR(80) NOT NULL ,
  `first_name` VARCHAR(75) NOT NULL ,
  `last_name` VARCHAR(75) NOT NULL ,
  `date_of_birth` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_id_UNIQUE` (`email_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_status_master`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_status_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `case_status_name` VARCHAR(50) NOT NULL ,
  `case_status_description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `case_status_name_UNIQUE` (`case_status_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `help_category` (
  `category_id` INT NOT NULL AUTO_INCREMENT ,
  `category_name` VARCHAR(50) NOT NULL ,
  `description` VARCHAR(100) NULL ,
  PRIMARY KEY (`category_id`) ,
  UNIQUE INDEX `category_name_UNIQUE` (`category_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_sub_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_sub_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `case_type_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_sub_type_type_id` (`case_type_id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  CONSTRAINT `fk_case_sub_type_type_id`
    FOREIGN KEY (`case_type_id` )
    REFERENCES `case_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_master`
-- -----------------------------------------------------
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
  `case_sub_type_id` INT NOT NULL ,
  `help_category_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_master_status_id` (`case_status_id` ASC) ,
  INDEX `fk_case_master_help_category_id` (`help_category_id` ASC) ,
  INDEX `fk_case_master_sub_type_id` (`case_sub_type_id` ASC) ,
  INDEX `fk_case_master_created_by` (`created_by` ASC) ,
  INDEX `fk_case_master_updated_by` (`updated_by` ASC) ,
  CONSTRAINT `fk_case_master_status_id`
    FOREIGN KEY (`case_status_id` )
    REFERENCES `case_status_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_help_category_id`
    FOREIGN KEY (`help_category_id` )
    REFERENCES `help_category` (`category_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_sub_type_id`
    FOREIGN KEY (`case_sub_type_id` )
    REFERENCES `case_sub_type` (`id` )
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
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_user` (
  `case_id` INT NULL ,
  `user_id` INT NULL ,
  `is_org` TINYINT(1)  NULL DEFAULT false ,
  INDEX `fk_case_users_user_id` (`user_id` ASC) ,
  INDEX `fk_case_users_case_id` (`case_id` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `case_artifact` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `artifact_type` VARCHAR(25) NOT NULL ,
  `artifact` BLOB NOT NULL ,
  `case_id` INT NOT NULL ,
  `added_by` INT NULL ,
  INDEX `fk_case_artifacts_case_id` (`case_id` ASC) ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_artifact_user_id` (`added_by` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `case_activity` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `action` VARCHAR(255) NOT NULL ,
  `action_by` INT NOT NULL ,
  `action_date` DATETIME NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `case_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_activities_case_id` (`case_id` ASC) ,
  INDEX `fk_case_activity_user_id` (`action_by` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `fund_management` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `donor` VARCHAR(100) NOT NULL ,
  `purpose` VARCHAR(255) NOT NULL ,
  `amount` INT NOT NULL ,
  `credit_debit` INT NOT NULL ,
  `date` DATETIME NOT NULL ,
  `case_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_fund_management_case_id` (`case_id` ASC) ,
  CONSTRAINT `fk_fund_management_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_category_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `help_category_user` (
  `user_id` INT NOT NULL ,
  `help_category_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `help_category_id`) ,
  INDEX `fk_help_category_users_category_id` (`help_category_id` ASC) ,
  INDEX `fk_help_category_users_user_id` (`user_id` ASC) ,
  CONSTRAINT `fk_help_category_users_category_id`
    FOREIGN KEY (`help_category_id` )
    REFERENCES `help_category` (`category_id` )
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
CREATE  TABLE IF NOT EXISTS `case_type_user` (
  `user_id` INT NOT NULL ,
  `case_type_id` INT NOT NULL ,
  PRIMARY KEY (`case_type_id`, `user_id`) ,
  INDEX `fk_case_type_user_id` (`user_id` ASC) ,
  CONSTRAINT `fk_case_type_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_profile`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user_profile` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `other_email_id` VARCHAR(100) NOT NULL ,
  `blood_group` VARCHAR(10) NOT NULL ,
  `anniversary` DATETIME NOT NULL ,
  `user_id` INT NOT NULL ,
  `monthly_updates` TINYINT(1)  NOT NULL DEFAULT 1 ,
  `special_updates` TINYINT(1)  NOT NULL DEFAULT 1 ,
  `regular_updates` TINYINT(1)  NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_user_profile_user_id` (`user_id` ASC) ,
  CONSTRAINT `fk_user_profile_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `org_type_master`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `org_type_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organization`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `organization` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `org_type_id` INT NOT NULL ,
  `address` VARCHAR(255) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_organization_info_org_type_id` (`org_type_id` ASC) ,
  CONSTRAINT `fk_organization_info_org_type_id`
    FOREIGN KEY (`org_type_id` )
    REFERENCES `org_type_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_organization`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user_organization` (
  `user_id` INT NOT NULL ,
  `org_id` INT NOT NULL ,
  INDEX `fk_user_organization_user_id` (`user_id` ASC) ,
  INDEX `fk_user_organization_org_id` (`org_id` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `module_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user_role` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `role_id` INT NOT NULL ,
  `module_id` INT NULL ,
  INDEX `fk_user_role_user_id` (`user_id` ASC) ,
  INDEX `fk_user_role_role_id` (`role_id` ASC) ,
  INDEX `fk_user_role_module_id` (`module_id` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `email_template` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `login_master`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `login_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `email_id` VARCHAR(100) NOT NULL ,
  `password` VARCHAR(80) NOT NULL ,
  `first_name` VARCHAR(75) NOT NULL ,
  `last_name` VARCHAR(75) NOT NULL ,
  `date_of_birth` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_id_UNIQUE` (`email_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_status_master`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_status_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `case_status_name` VARCHAR(50) NOT NULL ,
  `case_status_description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `case_status_name_UNIQUE` (`case_status_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `help_category` (
  `category_id` INT NOT NULL AUTO_INCREMENT ,
  `category_name` VARCHAR(50) NOT NULL ,
  `description` VARCHAR(100) NULL ,
  PRIMARY KEY (`category_id`) ,
  UNIQUE INDEX `category_name_UNIQUE` (`category_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_sub_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_sub_type` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `case_type_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_sub_type_type_id` (`case_type_id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  CONSTRAINT `fk_case_sub_type_type_id`
    FOREIGN KEY (`case_type_id` )
    REFERENCES `case_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_master`
-- -----------------------------------------------------
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
  `case_sub_type_id` INT NOT NULL ,
  `help_category_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_master_status_id` (`case_status_id` ASC) ,
  INDEX `fk_case_master_help_category_id` (`help_category_id` ASC) ,
  INDEX `fk_case_master_sub_type_id` (`case_sub_type_id` ASC) ,
  INDEX `fk_case_master_created_by` (`created_by` ASC) ,
  INDEX `fk_case_master_updated_by` (`updated_by` ASC) ,
  CONSTRAINT `fk_case_master_status_id`
    FOREIGN KEY (`case_status_id` )
    REFERENCES `case_status_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_help_category_id`
    FOREIGN KEY (`help_category_id` )
    REFERENCES `help_category` (`category_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_master_sub_type_id`
    FOREIGN KEY (`case_sub_type_id` )
    REFERENCES `case_sub_type` (`id` )
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
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_user` (
  `case_id` INT NULL ,
  `user_id` INT NULL ,
  `is_org` TINYINT(1)  NULL DEFAULT false ,
  INDEX `fk_case_users_user_id` (`user_id` ASC) ,
  INDEX `fk_case_users_case_id` (`case_id` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `case_artifact` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `artifact_type` VARCHAR(25) NOT NULL ,
  `artifact` BLOB NOT NULL ,
  `case_id` INT NOT NULL ,
  `added_by` INT NULL ,
  INDEX `fk_case_artifacts_case_id` (`case_id` ASC) ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_artifact_user_id` (`added_by` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `case_activity` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `action` VARCHAR(255) NOT NULL ,
  `action_by` INT NOT NULL ,
  `action_date` DATETIME NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `case_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_case_activities_case_id` (`case_id` ASC) ,
  INDEX `fk_case_activity_user_id` (`action_by` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `fund_management` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `donor` VARCHAR(100) NOT NULL ,
  `purpose` VARCHAR(255) NOT NULL ,
  `amount` INT NOT NULL ,
  `credit_debit` INT NOT NULL ,
  `date` DATETIME NOT NULL ,
  `case_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_fund_management_case_id` (`case_id` ASC) ,
  CONSTRAINT `fk_fund_management_case_id`
    FOREIGN KEY (`case_id` )
    REFERENCES `case_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_category_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `help_category_user` (
  `user_id` INT NOT NULL ,
  `help_category_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `help_category_id`) ,
  INDEX `fk_help_category_users_category_id` (`help_category_id` ASC) ,
  INDEX `fk_help_category_users_user_id` (`user_id` ASC) ,
  CONSTRAINT `fk_help_category_users_category_id`
    FOREIGN KEY (`help_category_id` )
    REFERENCES `help_category` (`category_id` )
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
CREATE  TABLE IF NOT EXISTS `case_type_user` (
  `user_id` INT NOT NULL ,
  `case_type_id` INT NOT NULL ,
  PRIMARY KEY (`case_type_id`, `user_id`) ,
  INDEX `fk_case_type_user_id` (`user_id` ASC) ,
  CONSTRAINT `fk_case_type_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_profile`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user_profile` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `other_email_id` VARCHAR(100) NOT NULL ,
  `blood_group` VARCHAR(10) NOT NULL ,
  `anniversary` DATETIME NOT NULL ,
  `user_id` INT NOT NULL ,
  `monthly_updates` TINYINT(1)  NOT NULL DEFAULT 1 ,
  `special_updates` TINYINT(1)  NOT NULL DEFAULT 1 ,
  `regular_updates` TINYINT(1)  NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_user_profile_user_id` (`user_id` ASC) ,
  CONSTRAINT `fk_user_profile_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `login_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `org_type_master`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `org_type_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organization`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `organization` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `org_type_id` INT NOT NULL ,
  `address` VARCHAR(255) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_organization_info_org_type_id` (`org_type_id` ASC) ,
  CONSTRAINT `fk_organization_info_org_type_id`
    FOREIGN KEY (`org_type_id` )
    REFERENCES `org_type_master` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_organization`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user_organization` (
  `user_id` INT NOT NULL ,
  `org_id` INT NOT NULL ,
  INDEX `fk_user_organization_user_id` (`user_id` ASC) ,
  INDEX `fk_user_organization_org_id` (`org_id` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `module_master` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user_role` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `role_id` INT NOT NULL ,
  `module_id` INT NULL ,
  INDEX `fk_user_role_user_id` (`user_id` ASC) ,
  INDEX `fk_user_role_role_id` (`role_id` ASC) ,
  INDEX `fk_user_role_module_id` (`module_id` ASC) ,
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
CREATE  TABLE IF NOT EXISTS `email_template` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
