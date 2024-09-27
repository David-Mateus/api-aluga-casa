CREATE TABLE `tb_tenant` (
  `tenant_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `duration` int NOT NULL,
  `email` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`tenant_id`),
  KEY `K_user_id` (`user_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`user_id`)
)

