-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS tarea2 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE tarea2;

-- Crear la tabla roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Crear la tabla usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `imagen` longblob DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Crear la tabla usuario_roles
CREATE TABLE IF NOT EXISTS `usuario_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `rol_id` (`rol_id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Insertar datos en roles si no existen
INSERT INTO `roles` (`id`, `nombre`) VALUES
(1, 'ROLE_ADMIN') ON DUPLICATE KEY UPDATE id=id;
INSERT INTO `roles` (`id`, `nombre`) VALUES
(2, 'ROLE_USER') ON DUPLICATE KEY UPDATE id=id;

-- Insertar datos en usuarios si no existen
INSERT INTO `usuarios` (`id`, `nombre`, `email`, `password`, `imagen`) VALUES
(1, 'moises', 'sesiom202@gmail.com', '$2a$10$RAgqjTBYblP9AXzMdwjhVOLvucUioalYimPf.q9pr2MtKoccgsvkO', NULL) 
ON DUPLICATE KEY UPDATE id=id;
INSERT INTO `usuarios` (`id`, `nombre`, `email`, `password`, `imagen`) VALUES
(2, 'andres', 'kjashdd@aflnaefd.com', '$2a$10$3TfstEFJym5xVryynOqeh.AFh.oePlkaQwNagBX7pEjJqRqqrcufa', NULL)
ON DUPLICATE KEY UPDATE id=id;

-- Insertar datos en usuario_roles si no existen
INSERT INTO `usuario_roles` (`usuario_id`, `rol_id`) VALUES
(1, 1) ON DUPLICATE KEY UPDATE usuario_id=usuario_id;
