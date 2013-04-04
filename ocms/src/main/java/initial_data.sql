
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
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (1, 'New', NULL);
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (2, 'Pending', NULL);
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (3, 'In Progress', NULL);
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (4, 'On Hold', NULL);
INSERT INTO `case_status_master` (`id`, `case_status_name`, `case_status_description`) VALUES (5, 'Resolved', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `help_category_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `help_category_type` (`id`, `category_name`, `description`, `parent_type_id`) VALUES (1, 'Monetary', NULL, NULL);
INSERT INTO `help_category_type` (`id`, `category_name`, `description`, `parent_type_id`) VALUES (2, 'Reference', NULL, NULL);
INSERT INTO `help_category_type` (`id`, `category_name`, `description`, `parent_type_id`) VALUES (3, 'Guidance', NULL, NULL);
INSERT INTO `help_category_type` (`id`, `category_name`, `description`, `parent_type_id`) VALUES (4, 'Non monetary', NULL, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (1, 'Medical', NULL, NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (2, 'Education', NULL, NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (3, 'Amenities', NULL, NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (4, 'Calamities', NULL, NULL);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (5, 'Heart Operation', NULL, 1);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (6, 'Kidney', NULL, 1);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (7, 'Education Loan', NULL, 2);
INSERT INTO `case_type` (`id`, `name`, `description`, `parent_type_id`) VALUES (8, 'Guidance', NULL, 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `case_master`
-- -----------------------------------------------------
START TRANSACTION;
USE `ocms`;
INSERT INTO `case_master` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_type_id`, `help_category_id`) VALUES (1, 1, '2008-05-27', 1, '2009-02-23', 'Prasanthi', '1980-01-01', 'need urgent blood group O-', '898989898', '898989', 'friend', 1, 1, 1);
INSERT INTO `case_master` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `person_name`, `date_of_birth`, `case_description`, `contact_number1`, `contact_number2`, `source`, `case_status_id`, `case_type_id`, `help_category_id`) VALUES (2, 1, '2011-01-9', 1, '2009-09-12', 'Kashyap', '1979-05-05', 'Fresher needs Job', '2342343', '4324324', 'news paper', 1, 2, 1);

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
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (1, 'NGO', NULL, NULL);
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (2, 'State Government', NULL, NULL);
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (3, 'Union/Central Government', NULL, NULL);
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (4, 'Private Limited/Corporate', NULL, NULL);
INSERT INTO `org_type` (`id`, `name`, `description`, `parent_id`) VALUES (5, 'International Charity', NULL, NULL);

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
INSERT INTO `module_master` (`id`, `name`, `description`) VALUES (1, 'Case Registration', NULL);
INSERT INTO `module_master` (`id`, `name`, `description`) VALUES (2, 'User Registration', NULL);
INSERT INTO `module_master` (`id`, `name`, `description`) VALUES (3, 'Administration', NULL);
INSERT INTO `module_master` (`id`, `name`, `description`) VALUES (4, 'Case Life Cycle', NULL);

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
