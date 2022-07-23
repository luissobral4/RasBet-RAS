INSERT INTO User VALUES
	(1, 'user1', 0, 0.0, 'António', 'antonio@email.com', '','EUR'),
    (2, 'user2', 0, 10.0, 'Francisco', 'francisco@email.com', '','EUR'),
    (3, 'user3', 0, 5.0, 'LuísM', 'luism@email.com', '','EUR'),
    (4, 'user4', 0, 3.0, 'LuísS', 'luiss@email.com', '','EUR'),
    (5, 'user5', 0, 0.0, 'Pedro', 'pedro@email.com', '','EUR'),
    (6, 'user6', 0, 0.0, 'Tomás', 'tomas@email.com', '','EUR'),
    (7, 'user7', 0, 100.0, 'Miguel', 'miguel@email.com', '','EUR'),
    (8, 'user8', 0, 30.0, 'Santiago', 'santiago@email.com', '','EUR'),
    (9, 'user9', 0, 40.0, 'Guilherme', 'guilherme@email.com', '','EUR'),
    (10, 'user10', 0, 50.0, 'José', 'jose@email.com', '','EUR');

INSERT INTO Market VALUES
	(1, 'Futebol');

INSERT INTO Event VALUES
	(1, 'Sporting x Besiktas', 0, 0, '10 x 0', 1, '20:00', '17/01/2022'),
    (2, 'scb x scp', 1, 0, '0 x 10', 5, '20:00', '18/01/2022'),
    (3, 'scp x vsc', 0, 0, '10 x 0', 7, '20:00', '19/01/2022'),
    (4, 'slb x scp', 1, 0, '0 x 10', 11, '20:00', '20/01/2022'),
    (5, 'scp x bs', 0, 0, '10 x 0', 13, '20:00', '21/01/2022');

INSERT INTO Odd VALUES
    (1, 0.0, 'Sporting', 3.0,0),
    (2, 0.0, 'Besiktas', 3.0,0),
    (3, 0.0, 'Empate', 3.0,0),
    (4, 0.0, 'Braga', 3.0,0),
    (5, 0.0, 'Sporting', 3.0,0),
    (6, 0.0, 'Empate', 3.0,0),
    (7, 0.0, 'Sporting', 3.0,0),
    (8, 0.0, 'Vitória', 3.0,0),
    (9, 0.0, 'Empate', 3.0,0),
    (10, 0.0, 'Benfica', 3.0,0),
    (11, 0.0, 'Sporting', 3.0,0),
    (12, 0.0, 'Empate', 3.0,0),
    (13, 0.0, 'Sporting', 3.0,0),
    (14, 0.0, 'Belenenses', 3.0,0),
    (15, 0.0, 'Empate', 3.0,0);

INSERT INTO Bet VALUES
  	(1, 1.5, 0, 50.0);


INSERT INTO UserBetRelation VALUES
    (7, 1);

INSERT INTO BetOddRelation VALUES
    (1, 1);

INSERT INTO OddEventRelation VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 3),
    (8, 3),
    (9, 3),
    (10, 4),
    (11, 4),
    (12, 4),
    (13, 5),
    (14, 5),
    (15, 5);

INSERT INTO EventMarketRelation VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1);