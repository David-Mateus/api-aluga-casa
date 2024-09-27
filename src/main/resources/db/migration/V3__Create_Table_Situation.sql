CREATE TABLE `tb_situation` (
  `situation_id` bigint NOT NULL AUTO_INCREMENT,
  `month` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`situation_id`),
  KEY `K_tenant_id` (`tenant_id`),
  CONSTRAINT `FK_tenant_id` FOREIGN KEY (`tenant_id`) REFERENCES `tb_tenant` (`tenant_id`)
)
