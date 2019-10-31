insert INTO team (id, name) VALUES (1, 'DEV');
insert INTO team (id, name) VALUES (2, 'TESTE');

INSERT INTO employee (id, adjunct, city, neighborhood, number, state, street, birhth_date, hiring_date, name, photo, registration, team_id) VALUES
(1, 'Casa', 'São Lourenço da Mata', 'Rosina Labanca', 73, 'PE', 'Rua Amazonita', '1988-04-19', '2007-02-12', 'Eudes Jose', 12, 'd8aa6357d', 1);

INSERT INTO employee (id, adjunct, city, neighborhood, number, state, street, birhth_date, hiring_date, name, photo, registration, team_id) VALUES
(2, 'Casa', 'Olinda', 'Ouro preto', 179, 'PE', 'Guiomar Figueredo', '1989-02-23', '2012-04-05', 'Michele Marques', 12, '6fde72fc9', 1);

INSERT INTO employee (id, adjunct, city, neighborhood, number, state, street, birhth_date, hiring_date, name, photo, registration, team_id) VALUES
(3, 'Apartamento', 'Sertaemba', 'Ulisses', 273, 'MT', 'Rua Pereira', '1988-04-19', '2007-02-12', 'João Alberto', 12, 'db40744c1', 2);


INSERT INTO vacation (id,employee_id,start_date,end_date) VALUES(1,1,'2019-05-01','2019-04-01');
INSERT INTO vacation (id,employee_id,start_date,end_date) VALUES(2,1,'2019-03-12','2019-04-15');
INSERT INTO vacation (id,employee_id,start_date,end_date) VALUES(3,2,'2019-01-01','2019-02-26');