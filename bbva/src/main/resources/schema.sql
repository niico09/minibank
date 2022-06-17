DROP TABLE Person IF EXISTS;
CREATE TABLE IF NOT EXISTS Person (Id BIGINT auto_increment, Dni BIGINT, Name VARCHAR(25),LastName VARCHAR(25),Email VARCHAR(50),CellPhone VARCHAR(12),PRIMARY KEY (Id));


DROP TABLE Account IF EXISTS;
CREATE TABLE IF NOT EXISTS Account (Id BIGINT auto_increment, Number BIGINT, DniOwner BIGINT, PrimaryOwner BOOLEAN, CreateDate DATE, InitialBalance FLOAT, ActualBalance FLOAT, agreed VARCHAR(2), EndDate DATE, TypeMoney VARCHAR(3),PRIMARY KEY (Id)); 

DROP TABLE Movements IF EXISTS;
CREATE TABLE IF NOT EXISTS Movements(Id BIGINT auto_increment, DayTransfer DATE, Amount FLOAT,Description VARCHAR(25),TypeMoney VARCHAR(3),Origin VARCHAR(50),Destination VARCHAR(50), Site VARCHAR(12), PRIMARY KEY (Id));
