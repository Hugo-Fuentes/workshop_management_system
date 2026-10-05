DROP DATABASE IF EXISTS workshop;

CREATE DATABASE IF NOT EXISTS workshop;

USE  workshop;

CREATE TABLE workshop.mechanic(
id int AUTO_INCREMENT,
name_ varchar(80) NOT NULL,
last_name varchar(100),
phone_number char(9),
PRIMARY KEY(id)
);

CREATE TABLE workshop.car(
id int AUTO_INCREMENT,
brand varchar(50) NOT NULL,
model varchar(70) NOT NULL,
idClient int,
PRIMARY KEY(id,idClient)
);

CREATE TABLE workshop.repair(
mechanicId int,
carId int,
date_ date NOT NULL,
description_ text,
cost decimal(10,2) NOT NULL,
PRIMARY KEY(mechanicId,carId)
);

CREATE TABLE workshop.client(
id int AUTO_INCREMENT,
name_ varchar(80) NOT NULL,
last_name varchar(100),
phone_number char(9),
PRIMARY KEY(id)
);

ALTER TABLE workshop.car 
ADD CONSTRAINT fk_client_car
FOREIGN KEY (idClient) REFERENCES client(id);

ALTER TABLE workshop.repair 
ADD CONSTRAINT fk_mechanic_repair
FOREIGN KEY(mechanicId) REFERENCES mechanic(id);

ALTER TABLE workshop.repair 
ADD CONSTRAINT fk_car_repair
FOREIGN KEY(carId) REFERENCES car(id);

INSERT INTO workshop.mechanic(name_,last_name,phone_number)
VALUES
    ("James","Smith","600445812"),
    ("Oliver","Brown","670567432"),
    ("George","Davis","605580462"),
    ("Max","Lopez","623087465"); 

INSERT INTO workshop.client(name_,last_name,phone_number)
VALUES
    ("Mathew","Jonshon","612545812"),
    ("Jack","Jameson","689567433"),
    ("Zack","Brooks","619900462"),
    ("Kevin","Wood","680327464");   

INSERT INTO workshop.car(brand,model,idClient)
VALUES
    ("Ford","Mustang-gt",1),
    ("Audi","A-1(2026)",1),
    ("Mercedes","A class AMG",2),
    ("Tesla","Model-S",3); 



INSERT INTO workshop.repair(date_,cost,mechanicId,carId)
VALUES
    ("2026-12-12",2100,1,2),
    ("2026-12-13",150.50,2,3),
    ("2026-12-14",200,4,1),
    ("2026-12-15",400,3,4); 