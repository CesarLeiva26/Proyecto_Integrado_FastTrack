CREATE DATABASE fasttrack;
USE fasttrack;

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
    email VARCHAR(200),
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

CREATE TABLE IF NOT EXISTS Emisor (
    idemisor INT AUTO_INCREMENT PRIMARY KEY,
    dniemisor int,
    nombreemisor VARCHAR(100) NOT NULL,
    apellidoemisor VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Receptor (
    idreceptor INT AUTO_INCREMENT PRIMARY KEY,
    dnireceptor int,
    nombrereceptor VARCHAR(100),
    apellidoreceptor VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS tipoestadosorden (
    idestadoorden INT AUTO_INCREMENT PRIMARY KEY,
    nombreestado VARCHAR(50) NOT NULL
);

INSERT INTO tipoestadosorden (nombreestado)
VALUES
    ('En Proceso'),
    ('Cancelada'),
    ('En Almacén'),
	('Completada');

CREATE TABLE IF NOT EXISTS vehiculos (
    idvehiculo INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL
);

INSERT INTO vehiculos (placa, marca, modelo)
VALUES
    ('GHI789', 'Kenworth', 'W990'),
    ('JKL012', 'Peterbilt', '389'),
    ('MNO345', 'Freightliner', 'Cascadia');

CREATE TABLE IF NOT EXISTS locales (
    idlocal INT AUTO_INCREMENT PRIMARY KEY,
    nombrelocal VARCHAR(255),
    direccionlocal VARCHAR(255),
    ciudadlocal VARCHAR(100)
);

INSERT INTO locales (idlocal ,nombrelocal, direccionlocal, ciudadlocal)
VALUES
    (1, 'Local LA', 'Av. Principal 123', 'Lima'),
    (2, 'Local AA', 'Jr. Comercial 456', 'Arequipa'),
    (3, 'Local TO', 'Calle Central 789', 'Trujillo'),
    (4, 'Local CO', 'Av. Norte 567', 'Cusco'),
    (5, 'Local IA', 'Calle Sur 890', 'Ica'),
    (6, 'Local TA', 'Av. Este 456', 'Tacna');

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