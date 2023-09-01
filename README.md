# Tercer desafío del programa Oracle Next Education

### Hotel Alura

Desarrollar un sistema de reservas para llevar el control de las reservaciones hechas por sus clientes

#### Requisitos:

El sistema debe contiener :
- Autenticación de usuario para que solo usuarios pertenecientes al hotel consigan acceder al sistema
- Permitir crear, editar y eliminar una reserva para los clientes
- Buscar en la base de datos todas las informaciones tanto de los clientes como de las reservas
- Registrar, editar y eliminar datos de los huéspedes
- Calcular el valor de la reserva en base a la cantidades de días de la reserva y a una tasa diaria, todo esto deberá ser hecho automaticamente y mostrado al usuario antes de guardar la reserva
- Base de datos para almacenar todos los datos pedidos anteriormente

<br/><br/>
----
<br/><br/>   

> Tabla Users
```SQL
CREATE TABLE `users` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user` VARCHAR(50) NULL DEFAULT NULL,
	`password` VARCHAR(250) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);
```

<br/>

> Tabla Reservas
```SQL
CREATE TABLE `reservas` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`fecha_entrada` DATE NOT NULL,
	`fecha_salida` DATE NOT NULL,
	`valor` VARCHAR(50) NULL DEFAULT NULL,
	`forma_pago` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
);
```

<br/>

> Tabla Huespedes
```SQL
CREATE TABLE `huespedes` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NOT NULL,
	`apellido` VARCHAR(50) NOT NULL,
	`fecha_nacimiento` DATE NOT NULL,
	`nacionalidad` VARCHAR(50) NOT NULL,
	`telefono` VARCHAR(15) NOT NULL,
	`id_reserva` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_huespedes_reservas` (`id_reserva`),
	CONSTRAINT `FK_huespedes_reservas` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id`) ON DELETE CASCADE
);
```
