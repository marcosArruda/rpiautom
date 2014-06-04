--TIPOS DE DADOS:
--	NULL: The value is a NULL value.
--	INTEGER: Signed stored in 1, 2, 3, 4, 6, or 8 bytes depending on the magnitude of the value.
--	REAL: 8-byte IEEE floating point number.
--	TEXT: (UTF-8, UTF-16BE or UTF-16LE).
--	BLOB: appenda o arquivo na base

--GUARDANDO DATAS(TEMPO):
--	(DATE is TEXT): "YYYY-MM-DD HH:MM"
--	Compute the current date.
--	SELECT date('now');
--	Compute the last day of the current month.
--	SELECT date('now','start of month','+1 month','-1 day');
--	Compute the date and time given a unix timestamp 1092941466.
--	SELECT datetime(1092941466, 'unixepoch');
--	Compute the date and time given a unix timestamp 1092941466, and compensate for your local timezone.
--	SELECT datetime(1092941466, 'unixepoch', 'localtime');
--	Compute the current unix timestamp.
--	SELECT strftime('%s','now');
--	Compute the number of days since the signing of the US Declaration of Independence.
--	SELECT julianday('now') - julianday('1776-07-04');
--	Compute the number of seconds since a particular moment in 2004:
--	SELECT strftime('%s','now') - strftime('%s','2004-01-01 02:34:56');
--	Compute the date of the first Tuesday in October for the current year.
--	SELECT date('now','start of year','+9 months','weekday 2');
--	Compute the time since the unix epoch in seconds (like strftime('%s','now') except includes fractional part):
--	SELECT (julianday('now') - 2440587.5)*86400.0;

DROP TABLE IF EXISTS DATA_HIST;
DROP TABLE IF EXISTS SENSOR;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS PLACE;
DROP TABLE IF EXISTS SENSOR_TYPE;

CREATE TABLE IF NOT EXISTS USER(CD_USER INTEGER PRIMARY KEY,
	NM_USER_FULL TEXT,
	NM_ALIAS TEXT,
	TX_PASSWORD TEXT,
	DT_NASCIM TEXT,
	TX_EMAIL TEXT
);

CREATE TABLE IF NOT EXISTS PLACE(CD_PLACE INTEGER PRIMARY KEY,
	NM_PLACE INTEGER,
	DT_REGISTERED TEXT
);

CREATE TABLE IF NOT EXISTS DATA_HIST(CD_HIST INTEGER PRIMARY KEY,
	CD_PLACE INTEGER,
	VL_TEMPERATURE_C INTEGER,
	VL_HUMIDITY INTEGER,
	DT_REGISTERED TEXT,
	FOREIGN KEY(CD_PLACE) REFERENCES PLACE(CD_PLACE)
);

CREATE TABLE IF NOT EXISTS SENSOR_TYPE(CD_SENSOR_TYPE INTEGER PRIMARY KEY,
	NM_SENSOR_TYPE TEXT,
	DS_DESCRIPTION TEXT
);

CREATE TABLE IF NOT EXISTS SENSOR(CD_SENSOR INTEGER PRIMARY KEY,
	CD_PLACE INTEGER,
	CD_SENSOR_TYPE INTEGER,
	DS_DESCRIPTION TEXT,
	VL_PRICE REAL,
	TX_URL TEXT,
	FOREIGN KEY(CD_PLACE) REFERENCES PLACE(CD_PLACE),
	FOREIGN KEY(CD_SENSOR_TYPE) REFERENCES SENSOR_TYPE(CD_SENSOR_TYPE)
);

insert into USER VALUES (1, 'Marcos Faria Arruda', 'marcosfa', 'marcosfa', '1985-06-10', 'marcos.f.arruda@gmail.com'),
	(2, 'Newton Faria Arruda', 'newtonfa', 'newtonfa', '1983-03-07', 'newton.arruda@gmail.com'),
	(3, 'Waldemar Bandeira Arruda', 'waldemarba', 'waldemarba', '1957-08-12', 'waldemar@infodigital.com.br'),
	(4, 'Maria Cristina Fontes Faria Arruda', 'cristinaffa', 'cristinaffa', '1959-08-25', 'cristina@infodigital.com.br');

insert into PLACE VALUES (1, 'Sala', '2014-05-26'),
	(2, 'Quarto 1', '2014-05-26'),
	(3, 'Cozinha', '2014-05-26'),
	(4, 'Banheiro 1', '2014-05-26');

insert into SENSOR_TYPE VALUES (1, 'LM35', 'Sensor de Temperatura Analógico. O mais eficiente, poré dificil de achar.'),
	(2, 'LDR', 'Mini Fotocélula.'),
	(3, 'MQ-3', 'Sensor de Álcool.'),
	(4, 'RHT-22', 'Sensor de Temperatura e Humidade. É muito bom e completo porém mais caro. R$ 42,50');

insert into DATA_HIST VALUES (1, 1, 26, 56, '2014-04-11 02:34:56'),
	(2, 1, 21, 51, '2014-04-11 12:22:16'),
	(3, 1, 25, 51, '2014-04-11 13:24:12'),
	(4, 2, 18, 52, '2014-04-11 13:28:15'),
	(5, 2, 16, 48, '2014-04-12 16:30:12'),
	(6, 2, 19, 43, '2014-04-12 18:57:02'),
	(7, 3, 21, 47, '2014-04-12 22:14:16'),
	(8, 3, 24, 43, '2014-04-12 23:02:34'),
	(9, 3, 31, 45, '2014-04-12 23:03:34'),
	(10, 4, 29, 56, '2014-04-12 01:00:04'),
	(11, 4, 28, 58, '2014-04-13 02:24:25'),
	(12, 4, 28, 59, '2014-04-13 03:54:55'),
	(13, 2, 21, 54, '2014-04-13 04:34:52'),
	(14, 2, 22, 53, '2014-04-13 05:15:55'),
	(15, 2, 20, 52, '2014-04-14 06:54:41'),
	(16, 1, 15, 51, '2014-04-14 07:32:38'),
	(17, 1, 12, 55, '2014-04-14 08:25:59'),
	(18, 1, 18, 33, '2014-04-14 09:48:38'),
	(19, 4, 27, 34, '2014-04-15 10:23:22'),
	(20, 3, 28, 46, '2014-04-15 11:36:37');

select P.NM_PLACE AS NOME, DH.DT_REGISTERED AS DATA from DATA_HIST AS DH
	INNER JOIN PLACE AS P ON DH.CD_PLACE = P.CD_PLACE
		WHERE date(DH.DT_REGISTERED)
    		BETWEEN date('2014-04-12') AND date('2014-04-13');