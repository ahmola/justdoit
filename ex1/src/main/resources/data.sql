INSERT IGNORE INTO `spring`.`user` (`id`, `username`, `password`, `algorithm`)
VALUES ('1', 'john', '12345', 'BCRYPT');

INSERT IGNORE INTO `spring`.`authorities`(`id`, `name`, `user`)
VALUES('1', 'READ', '1');

INSERT IGNORE INTO `spring`.`product` (`id`, `name`, `price`, `currency`)
VALUES('1', 'chocolate', '10', 'USD');