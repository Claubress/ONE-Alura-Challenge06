
DROP DATABASE hotel;

# CREAR BASE DE DATOS
CREATE DATABASE hotel;

# ACCEDER BASE DE DATOS
USE hotel;

# CREAR TABLA reserva
CREATE TABLE reserva(
id INT AUTO_INCREMENT,
fecha_entrada DATE,
fecha_salida DATE,
valor FLOAT,
forma_pago VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
)ENGINE=InnoDB;


# CREAR TABLA huesped
CREATE TABLE huesped(
id INT AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
apellido VARCHAR(50) NOT NULL,
fecha_nacimiento DATE,
nacionalidad VARCHAR(50),
telefono VARCHAR(50),
reserva_id INT,
PRIMARY KEY(id),
FOREIGN KEY (reserva_id) REFERENCES reserva(id) ON DELETE CASCADE
)ENGINE=InnoDB;



