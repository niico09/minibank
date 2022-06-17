INSERT INTO Person(Dni,Name,LastName,Email,CellPhone) VALUES (39878422,'Juan', 'Pereza', 'asdfa@as.dfasdf', '1126456472');
INSERT INTO Person(Dni,Name,LastName,Email,CellPhone) VALUES (39878423,'Juana', 'Perez', 'eda@asd.fasdf', '1126456473');

INSERT INTO Account(Number,DniOwner,PrimaryOwner,CreateDate,InitialBalance, ActualBalance,agreed, EndDate,TypeMoney) 
VALUES 
(02165489,39878422, 1, NOW(), 1000.0 , 1500.0, 'SI', NOW(), 'EUR' );

INSERT INTO Account(Number,DniOwner,PrimaryOwner,CreateDate,InitialBalance, ActualBalance,agreed, EndDate,TypeMoney) 
VALUES 
(02165421,39878422, 1, NOW(), 1000.0 , 1500.0, 'SI', NOW(), 'USA' );

INSERT INTO Account(Number,DniOwner,PrimaryOwner,CreateDate,InitialBalance, ActualBalance,agreed, EndDate,TypeMoney) 
VALUES 
(02165482,39878422, 0, NOW(), 1000.0 , 1500.0, 'SI', NOW(), 'EUR' );

INSERT INTO Account(Number,DniOwner,PrimaryOwner,CreateDate,InitialBalance, ActualBalance,agreed, EndDate,TypeMoney) 
VALUES 
(02165483,39878422, 0, NOW(), 1000.0 , 1500.0, 'SI', NOW(), 'USA' );

INSERT INTO Account(Number,DniOwner,PrimaryOwner,CreateDate,InitialBalance, ActualBalance,agreed, EndDate,TypeMoney) 
VALUES 
(02165484,39878423, 1, NOW(), 1000.0 , 1500.0, 'SI', NOW(), 'EUR' );

INSERT INTO Account(Number,DniOwner,PrimaryOwner,CreateDate,InitialBalance, ActualBalance,agreed, EndDate,TypeMoney) 
VALUES 
(02165485,39878423, 1, NOW(), 1000.0 , 1500.0, 'SI', NOW(), 'USA' );