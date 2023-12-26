user_rolesusuarios_rolesusuarios_rolesuse producto2db;
INSERT INTO `user` VALUES
(1,'admin@gmail.com','admin','1234','admin'),
(2,'test@gmail.com','test','1234','test');

INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
INSERT INTO `user_roles` VALUES (1,1),(2,2);