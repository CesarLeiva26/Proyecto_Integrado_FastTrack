/* LOGIN USUARIO */

CREATE TABLE IF NOT EXISTS Rol (
    idrol INT AUTO_INCREMENT PRIMARY KEY,
    nomrol VARCHAR(100) NOT NULL
);

insert into rol (idrol, nomrol) values
  (1,'ROLE_ADMIN'),
  (2,'ROLE_USER');

CREATE TABLE IF NOT EXISTS Usuario (
    idusuario INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    email VARCHAR(200) unique,
    nomusuario VARCHAR(100) NOT NULL,
    password VARCHAR(300) NOT NULL,
    activo BOOLEAN
);

CREATE TABLE IF NOT EXISTS Usuario_Rol (
    idusuario INT,
    idrol INT,
    PRIMARY KEY (idusuario, idrol),
    FOREIGN KEY (idusuario) REFERENCES Usuario (idusuario),
    FOREIGN KEY (idrol) REFERENCES Rol (idrol)
);


/* MANTENIMIENTO */

CREATE TABLE IF NOT EXISTS locales (
    idlocal INT AUTO_INCREMENT PRIMARY KEY,
    nombrelocal VARCHAR(255) NOT NULL,
    direccionlocal VARCHAR(255) NOT NULL,
    ciudadlocal VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Emisor (
    idemisor INT AUTO_INCREMENT PRIMARY KEY,
    dniemisor int unique,
    nombreemisor VARCHAR(30) NOT NULL,
    apellidoemisor VARCHAR(30) NOT NULL,
    celularemisor VARCHAR(30) unique,
    distritoemisor VARCHAR(30) default 'Sin distrito'
);

CREATE TABLE IF NOT EXISTS Receptor (
    idreceptor INT AUTO_INCREMENT PRIMARY KEY,
    dnireceptor int unique,
    nombrereceptor VARCHAR(30),
    apellidoreceptor VARCHAR(30),
    celularreceptor VARCHAR(30) unique,
    distritoreceptor VARCHAR(30) default 'Sin distrito'
);


/* OTRAS TABLAS*/

CREATE TABLE IF NOT EXISTS tipoestadosorden (
    idestadoorden INT AUTO_INCREMENT PRIMARY KEY,
    nombreestado VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS vehiculos (
    idvehiculo INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) unique,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL
);


/* TRANSACCIÓN */


CREATE TABLE IF NOT EXISTS ordenes (
    idorden INT AUTO_INCREMENT PRIMARY KEY,
    idlocal INT,
    idemisor INT,
    fechaorden TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    kilos double,
    precioporkilo double,
    idestadoorden INT,
    idreceptor INT,
    idvehiculo INT,
    claveorden varchar(50),
    FOREIGN KEY (idlocal) REFERENCES locales(idlocal),
    FOREIGN KEY (idemisor) REFERENCES Emisor(idemisor),
    FOREIGN KEY (idreceptor) REFERENCES Receptor(idreceptor),
    FOREIGN KEY (idestadoorden) REFERENCES tipoestadosorden(idestadoorden),
    FOREIGN KEY (idvehiculo) REFERENCES vehiculos(idvehiculo)
);

/*REGISTROS*/

INSERT INTO locales (idlocal ,nombrelocal, direccionlocal, ciudadlocal)
VALUES
    (1, 'Fast-Track Lima', 'Av. Principal 123', 'Lima'),
    (2, 'Fast-Track Arequipa', 'Jr. Comercial 456', 'Arequipa'),
    (3, 'Fast-Track Trujillo', 'Calle Central 789', 'Trujillo'),
    (4, 'Fast-Track Cusco', 'Av. Norte 567', 'Cusco'),
    (5, 'Fast-Track Ica', 'Calle Sur 890', 'Ica'),
    (6, 'Fast-Track Tacna', 'Av. Este 456', 'Tacna');

INSERT INTO Emisor (dniemisor, nombreemisor, apellidoemisor, celularemisor, distritoemisor)
VALUES
(97876864, 'Renato Alejandro', 'Díaz Pérez', '+51 987654321', 'Miraflores'),
(59978787, 'María Fernanda', 'Alvarado López', '+51 987654322', 'San Isidro'),
(97876564, 'Xavier Antonio', 'González Martínez', '+51 987654323', 'Surco'),
(98765432, 'Valentina Ana', 'Morales Guerrero', '+51 987654320', 'Miraflores'),
(87654321, 'Joaquín Sebastián', 'González López', '+51 987654319', 'Barranco'),
(76543210, 'Gabriela Alejandra', 'Fernández Martínez', '+51 987654318', 'Arequipa'),
(65432109, 'Matías Ignacio', 'Vargas Rodríguez', '+51 987654317', 'Lince'),
(54321098, 'Victoria Nahomi', 'Sánchez Díaz', '+51 987654316', 'Surco');

INSERT INTO Receptor (dnireceptor, nombrereceptor, apellidoreceptor, celularreceptor, distritoreceptor)
VALUES
(98765432, 'Sofía Carolina', 'Suárez Velásquez', '+51 987654320', 'Trujillo'),
(87654321, 'Lucas Sebastián', 'Vargas Fernández', '+51 987654319', 'Arequipa'),
(76543210, 'Olivia Valentina', 'Rivera Gutiérrez', '+51 987654318', 'Arequipa'),
(12345678, 'Fernando José', 'Gutiérrez Mendoza', '+51 987654321', 'Cusco'),
(23456789, 'Camila Sandra', 'Herrera Rodríguez', '+51 987654322', 'Trujillo'),
(34567890, 'Felipe Andrés', 'Gómez Sánchez', '+51 987654323', 'Lima'),
(45678901, 'Laura Sofia', 'Flores Pérez', '+51 987654324', 'Ica'),
(56789012, 'Daniel Alejandro', 'Castañeda García', '+51 987654325', 'Tacna');

INSERT INTO tipoestadosorden (nombreestado)
VALUES
    ('En Proceso'),
    ('Cancelada'),
    ('En Almacén'),
	('Completada');

INSERT INTO vehiculos (placa, marca, modelo)
VALUES
    ('ABC123', 'Volvo', 'FH16'),
    ('DEF456', 'Scania', 'R730'),
    ('GHI789', 'Kenworth', 'T880'),
    ('JKL012', 'Mercedes-Benz', 'Actros'),
    ('MNO345', 'MAN', 'TGX');


INSERT INTO ordenes (idlocal, idemisor, fechaorden, kilos, precioporkilo, idestadoorden, idreceptor, idvehiculo, claveorden)
VALUES
    (1, 1, NOW(), 20, 5, 1, 1, 1, '123'),
    (2, 2, NOW(), 50, 8.5, 2, 2, 2, '456'),
    (3, 3, NOW(), 30, 2.5, 1, 2, 3, '789');



SELECT idorden,
nombrelocal, direccionlocal, ciudadlocal,
dniemisor, nombreemisor, apellidoemisor,
fechaorden, kilos, precioporkilo,
nombreestado,
dnireceptor, nombrereceptor, apellidoreceptor,
placa, marca, modelo,
claveorden
FROM ordenes o join locales l on o.idlocal = l.idlocal
    join emisor e on o.idemisor = e.idemisor
	join tipoestadosorden t on o.idestadoorden = t.idestadoorden
    join receptor r on o.idreceptor = r.idreceptor
    join vehiculos v on o.idvehiculo = v.idvehiculo;