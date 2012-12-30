SET SESSION sql_mode='NO_AUTO_VALUE_ON_ZERO';

-- -----------------------------------------------------
-- Data for table `login_master`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO login_master (`id`, `email_id`, `password`, `first_name`, `last_name`, `date_of_birth`) VALUES (1, 'harinath@tmad.org', '5a105e8b9d40e1329780d62ea2265d8a', 'Harinath', 'Mallepally', '1979-06-06');
INSERT INTO login_master (`id`, `email_id`, `password`, `first_name`, `last_name`, `date_of_birth`) VALUES (2, 'hari@harinath.in', '5a105e8b9d40e1329780d62ea2265d8a', 'Harinath', '', '1979-06-01');

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_status_master`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO case_status_master (`id`, `case_status_name`, `case_status_description`) VALUES (0, 'New', NULL);
INSERT INTO case_status_master (`id`, `case_status_name`, `case_status_description`) VALUES (1, 'Pending', NULL);
INSERT INTO case_status_master (`id`, `case_status_name`, `case_status_description`) VALUES (2, 'In Progress', NULL);
INSERT INTO case_status_master (`id`, `case_status_name`, `case_status_description`) VALUES (3, 'On Hold', NULL);
INSERT INTO case_status_master (`id`, `case_status_name`, `case_status_description`) VALUES (4, 'Resolved', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `help_category`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO help_category (`category_id`, `category_name`, `description`) VALUES (0, 'Monetary', NULL);
INSERT INTO help_category (`category_id`, `category_name`, `description`) VALUES (1, 'Reference', NULL);
INSERT INTO help_category (`category_id`, `category_name`, `description`) VALUES (2, 'Guidance', NULL);
INSERT INTO help_category (`category_id`, `category_name`, `description`) VALUES (3, 'Non monetary', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_type`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO case_type (`id`, `name`, `description`) VALUES (0, 'Medical', NULL);
INSERT INTO case_type (`id`, `name`, `description`) VALUES (1, 'Education', NULL);
INSERT INTO case_type (`id`, `name`, `description`) VALUES (3, 'Amenities', NULL);
INSERT INTO case_type (`id`, `name`, `description`) VALUES (4, 'Calamities', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_sub_type`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO case_sub_type (`id`, `name`, `description`, `case_type_id`) VALUES (0, 'Heart', NULL, 0);
INSERT INTO case_sub_type (`id`, `name`, `description`, `case_type_id`) VALUES (1, 'Kidney', NULL, 0);
INSERT INTO case_sub_type (`id`, `name`, `description`, `case_type_id`) VALUES (2, 'Tuition Fee', NULL, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_master`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO case_master (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_sub_type_id`, `help_category_id`) VALUES (1, 1, '2008-05-27', 1, '2009-02-23', 'Prasanthi', '1980-01-01', 'need urgent blood group O-', '898989898', '898989', 'friend', 1, 1, 1);
INSERT INTO case_master (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_sub_type_id`, `help_category_id`) VALUES (2, 1, '2011-01-9', 1, '2009-09-12', 'Kashyap', '1979-05-05', 'Fresher needs Job', '2342343', '4324324', 'news paper', 1, 2, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_user`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO case_user (`case_id`, `user_id`, `is_org`) VALUES (1, 1, 0);
INSERT INTO case_user (`case_id`, `user_id`, `is_org`) VALUES (2, 1, 0);

COMMIT;

-- -----------------------------------------------------
-- Data for table `role_master`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO role_master (`id`, `role_name`, `description`) VALUES (1, 'REGISTERED', 'registered');
INSERT INTO role_master (`id`, `role_name`, `description`) VALUES (2, 'USER', 'site user');
INSERT INTO role_master (`id`, `role_name`, `description`) VALUES (3, 'MANAGER', 'site manager');
INSERT INTO role_master (`id`, `role_name`, `description`) VALUES (4, 'ADMIN', 'admin');

COMMIT;

-- -----------------------------------------------------
-- Data for table `user_role`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO user_role (`user_id`, `role_id`, `module_id`) VALUES (1, 1, NULL);
INSERT INTO user_role (`user_id`, `role_id`, `module_id`) VALUES (1, 2, NULL);
INSERT INTO user_role (`user_id`, `role_id`, `module_id`) VALUES (1, 3, NULL);
INSERT INTO user_role (`user_id`, `role_id`, `module_id`) VALUES (1, 4, NULL);
INSERT INTO user_role (`user_id`, `role_id`, `module_id`) VALUES (2, 1, NULL);
INSERT INTO user_role (`user_id`, `role_id`, `module_id`) VALUES (2, 2, NULL);

COMMIT;
