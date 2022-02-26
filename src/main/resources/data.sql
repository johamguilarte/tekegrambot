DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS comandos_bot;
DROP TABLE IF EXISTS descripcion_bot;
DROP TABLE IF EXISTS usuarios_sunacrip;
DROP TABLE IF EXISTS billeteras_creadas;
DROP TABLE IF EXISTS tasa_sunacrip;

--------------------------------------------------------------------------
---------------------------- EXPLICACION DE ROLES ------------------------
--1000 -> USUARIOS CON ACCESO A TODO (ROOT)
--1100 -> ADMINISTRADORES
--1200 -> OPERADORES
--------------------------------------------------------------------------
---------------------------- LISTA DE OPERACIONES ------------------------
------------------------- OPERACIONES DEL BOT-----------------------------
-- CREAR USUARIOS BOT (1000)
-- ELIMINAR USUARIOS BOT (1000)
-- CERRAR SESIONES GENERAL BOT (1000)
-- CONSULTA DE TODOS LOS USUARIOS POR ROL (1000)
------------------------- OPERACIONES DE ESTADISTICAS --------------------
-- CONSULTA DE APERTURAS CREADAS (1000, 1100)
-- CONSULTA DE BILLETERAS CREADAS BY MODENA O TODOS (1000, 1100)
-- CONSULTA DE BILLETE OPTENIDO BY MONEDA (1000, 1100)
-- CONSULTA DE USUARIOS POR STATUS (1000, 1100)
------------------------- OPERACIONES DE USUARIOS --------------------------------
-- CONSULTA DE TODAS LAS MONEDAS DE SUNACRIP BY MONEDA (PETRO, USD, EUR) (1000, 1100, 1200)
-- COMPARTIR LINK DEL BOT (1000, 1100, 1200)
---------------------------------------------------------------------------------
CREATE TABLE usuarios (
  user VARCHAR(250) PRIMARY KEY,
  password VARCHAR(250) NOT NULL,
  status VARCHAR(250) NOT NULL,
  rol VARCHAR(250) DEFAULT '1200',
  ult_session DATE 
);

CREATE TABLE comandos_bot (
  comando VARCHAR(250) PRIMARY KEY,
  rol VARCHAR(250) NOT NULL DEFAULT '1200' -- ["1000", "1100", "1200"]
);

CREATE TABLE descripcion_bot (
  comando VARCHAR(250) PRIMARY KEY,
  rol VARCHAR(250) NOT NULL DEFAULT '1200', -- ["1000", "1100", "1200"]
  descripcion_bot VARCHAR(250) NOT NULL
);

CREATE TABLE usuarios_sunacrip (
  email VARCHAR(250) PRIMARY KEY,
  cedula VARCHAR(250) NOT NULL,
  status VARCHAR(250) NOT NULL DEFAULT 'A0', -- ["1000", "1100", "1200"]
  nombre VARCHAR(250) NOT NULL,
  telefono VARCHAR(250) NOT NULL,
  ult_session DATE
);

CREATE TABLE billeteras_creadas (
  billetera VARCHAR(250) PRIMARY KEY,
  moneda VARCHAR(250) NOT NULL,
  cedula VARCHAR(250) NOT NULL
);


CREATE TABLE tasa_sunacrip (
  moneda VARCHAR(250) PRIMARY KEY,
  precio_euro VARCHAR(250) NOT NULL,
  precio_dolar VARCHAR(250) NOT NULL
);

CREATE TABLE user_id (
  id_telegram VARCHAR(250) PRIMARY KEY,
  name_lastname VARCHAR(250),
  status VARCHAR(250) NOT NULL,
  rol VARCHAR(250) DEFAULT '1200',
  ult_session DATE
);


INSERT INTO usuarios (user, password,status,rol,ult_session) 
VALUES ('SUPERUSER','123456', 'ACTIVO', '1000' ,TO_DATE('17/12/2015', 'DD/MM/YYYY'));

INSERT INTO comandos_bot (comando, rol) VALUES ('/crear','1000,1100,1200');
INSERT INTO comandos_bot (comando, rol) VALUES ('/usuariosbot','1000');
INSERT INTO comandos_bot (comando, rol) VALUES ('/root','1000');
INSERT INTO comandos_bot (comando, rol) VALUES ('/aperturas','1000');
INSERT INTO comandos_bot (comando, rol) VALUES ('/billetera','1000');
INSERT INTO comandos_bot (comando, rol) VALUES ('/tasa','1000,1100,1200');
INSERT INTO comandos_bot (comando, rol) VALUES ('/help','1000,1100,1200');
INSERT INTO descripcion_bot (comando, rol, descripcion_bot) VALUES ('/crear','1000,1100,1200', 'Registrarte en el bot');
INSERT INTO descripcion_bot (comando, rol, descripcion_bot) VALUES ('/usuariosbot','1000', 'Detalle de cuantos usuarios estan usando el bot');
INSERT INTO descripcion_bot (comando, rol, descripcion_bot) VALUES ('/root','1000', 'Subir de nivel (rol) a cualquier usuario');
INSERT INTO descripcion_bot (comando, rol, descripcion_bot) VALUES ('/aperturas','1000', 'Cuenta total de usuarios registrados en sunacrip');
INSERT INTO descripcion_bot (comando, rol, descripcion_bot) VALUES ('/billetera','1000', 'Cuenta total por moneda de billeteras generadas');
INSERT INTO descripcion_bot (comando, rol, descripcion_bot) VALUES ('/tasa','1000,1100,1200', 'Tasa de las criptomonedas actualizadas');
INSERT INTO descripcion_bot (comando, rol, descripcion_bot) VALUES ('/help','1000,1100,1200', 'Lista de comandos de ayuda para el usuario');

INSERT INTO usuarios_sunacrip (email,cedula,status,nombre,telefono,ult_session) 
VALUES ('test1@gmail.com','26484572', 'A0', 'PRUEBA1', '04240000001',TO_DATE('17/12/2015', 'DD/MM/YYYY'));
INSERT INTO usuarios_sunacrip (email,cedula,status,nombre,telefono,ult_session) 
VALUES ('test2@gmail.com','26484573', 'A0', 'PRUEBA2', '04240000002',TO_DATE('17/12/2015', 'DD/MM/YYYY'));
INSERT INTO usuarios_sunacrip (email,cedula,status,nombre,telefono,ult_session) 
VALUES ('test3@gmail.com','26484574', 'A0', 'PRUEBA3', '04240000003',TO_DATE('17/12/2015', 'DD/MM/YYYY'));
INSERT INTO usuarios_sunacrip (email,cedula,status,nombre,telefono,ult_session) 
VALUES ('test4@gmail.com','26484575', 'A0', 'PRUEBA4', '04240000004',TO_DATE('17/12/2015', 'DD/MM/YYYY'));

INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa', 'BTC','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNO', 'LTC','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNU', 'PTR','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNY', 'PTR','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfN3', 'BTC','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfN4', 'PTR','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv744vfN4', 'BTC','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv733vfN4', 'BTC','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefi2DMPTfTL5SLmv766vfN4', 'PTR','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGefiDMPTfTL5SLmv7234vfN4', 'PTR','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGei2DMPTfTLLmv7D25432fN4', 'PTR','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGei2DMPTfTL586FDv7DivfN4', 'PTR','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGfi2DMPTfT5HGDRTD7DivfN4', 'PTR','V26484572');
INSERT INTO billeteras_creadas (billetera,moneda,cedula) VALUES ('1A1zP1eP5QGDMPTfTL5SLmv7DJFGFivfN4', 'PTR','V26484572');

INSERT INTO tasa_sunacrip (moneda,precio_euro,precio_dolar) VALUES ('PTR', '51,89 €','57,81 $');
INSERT INTO tasa_sunacrip (moneda,precio_euro,precio_dolar) VALUES ('BTC', '34.252,79 €','38.443,60 $');
INSERT INTO tasa_sunacrip (moneda,precio_euro,precio_dolar) VALUES ('LTC', '101,89 € ','104,36 $');
INSERT INTO tasa_sunacrip (moneda,precio_euro,precio_dolar) VALUES ('DASH', '77,37 €','86,81 $');


