

CREATE TABLE IF NOT EXISTS `customer` (
                                          `customer_id` int AUTO_INCREMENT  PRIMARY KEY,
                                          `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `created_at` date DEFAULT NULL,
    `created_by` varchar(20) DEFAULT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `accounts` (
                                          `customer_id` int NOT NULL,
                                          `account_number` int AUTO_INCREMENT  PRIMARY KEY,
                                          `account_type` varchar(100) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `communication_sw` BOOLEAN,
    `created_at` date DEFAULT NULL,
    `created_by` varchar(20) DEFAULT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
    );

--INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`,
--                        `communication_sw`, `created_at`, `created_by`, `updated_at`, `updated_by`)
--VALUES (10, 186576453, 'Savings', '123 Main Street, New York', true, CURDATE(), 'Amine', CURDATE(), 'Amine');



--INSERT INTO `customer` (`customer_id`, `name`, `email`, `mobile_number`,
--                       `created_at`, `created_by`, `updated_at`, `updated_by`)
--VALUES (20, 'Amine Saidi', 'me@me.com', '1234567890', CURDATE(), 'Amine', CURDATE(), 'Amine');