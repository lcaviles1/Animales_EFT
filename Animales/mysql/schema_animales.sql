CREATE DATABASE IF NOT EXISTS animales_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE animales_db;

CREATE TABLE IF NOT EXISTS mascotas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raza VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    foto_url VARCHAR(255),
    estado_adopcion VARCHAR(50) NOT NULL
);