-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.5.8-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Exportiere Struktur von Tabelle demo.analysen
CREATE TABLE IF NOT EXISTS `analysen` (
  `id` int(11) NOT NULL,
  `api` varchar(30) NOT NULL,
  `bemerkung` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `analysen_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.analysen: ~2 rows (ungefähr)
DELETE FROM `analysen`;
/*!40000 ALTER TABLE `analysen` DISABLE KEYS */;
INSERT INTO `analysen` (`id`, `api`, `bemerkung`) VALUES
	(1, 'karl', 'Donuts sind lecker'),
	(2, 'analyse', 'Donuts sind sehr lecker');
/*!40000 ALTER TABLE `analysen` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.eigenschaften
CREATE TABLE IF NOT EXISTS `eigenschaften` (
  `eigenschft_key` varchar(30) NOT NULL,
  `value` varchar(30) NOT NULL,
  PRIMARY KEY (`eigenschft_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.eigenschaften: ~10 rows (ungefähr)
DELETE FROM `eigenschaften`;
/*!40000 ALTER TABLE `eigenschaften` DISABLE KEYS */;
INSERT INTO `eigenschaften` (`eigenschft_key`, `value`) VALUES
	('C', 'Ätzend'),
	('E', 'Explusionsgefährlich'),
	('F', 'Leichtentzündlich'),
	('F+', 'Hochentzündlich'),
	('N', 'Umweltgefährlich'),
	('O', 'Brandfördernd'),
	('T', 'Giftig'),
	('T+', 'Sehr Giftig'),
	('Xi', 'Reizend'),
	('Xn', 'Gesundheidsschädlich');
/*!40000 ALTER TABLE `eigenschaften` ENABLE KEYS */;

-- Exportiere Struktur von Ereignis demo.example
DELIMITER //
CREATE EVENT `example` ON SCHEDULE EVERY 1 DAY STARTS '2021-01-23 03:32:09' ON COMPLETION NOT PRESERVE ENABLE DO delete from sessions where date < current_date - 30//
DELIMITER ;

-- Exportiere Struktur von Tabelle demo.experiment
CREATE TABLE IF NOT EXISTS `experiment` (
  `id` int(11) NOT NULL,
  `proben_nr` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experiment_id_uindex` (`id`),
  KEY `experiment_probe_proben_nr_fk` (`proben_nr`),
  CONSTRAINT `experiment_probe_proben_nr_fk` FOREIGN KEY (`proben_nr`) REFERENCES `probe` (`proben_nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.experiment: ~3 rows (ungefähr)
DELETE FROM `experiment`;
/*!40000 ALTER TABLE `experiment` DISABLE KEYS */;
INSERT INTO `experiment` (`id`, `proben_nr`) VALUES
	(0, 'Probe A1'),
	(3, 'Probe A1'),
	(1, 'Probe A2');
/*!40000 ALTER TABLE `experiment` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experimente_analysen
CREATE TABLE IF NOT EXISTS `experimente_analysen` (
  `experiment` int(11) NOT NULL,
  `analyse` int(11) NOT NULL,
  PRIMARY KEY (`experiment`,`analyse`),
  KEY `experimente_analysen_analysen_id_fk` (`analyse`),
  CONSTRAINT `experimente_analysen_analysen_id_fk` FOREIGN KEY (`analyse`) REFERENCES `analysen` (`id`),
  CONSTRAINT `experimente_analysen_experiment_id_fk` FOREIGN KEY (`experiment`) REFERENCES `experiment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experimente_analysen: ~2 rows (ungefähr)
DELETE FROM `experimente_analysen`;
/*!40000 ALTER TABLE `experimente_analysen` DISABLE KEYS */;
INSERT INTO `experimente_analysen` (`experiment`, `analyse`) VALUES
	(0, 1),
	(3, 2);
/*!40000 ALTER TABLE `experimente_analysen` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experimenttyp
CREATE TABLE IF NOT EXISTS `experimenttyp` (
  `id` int(11) NOT NULL,
  `typ` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experimenttyp_id_uindex` (`id`),
  CONSTRAINT `experimenttyp_experiment_id_fk` FOREIGN KEY (`id`) REFERENCES `experiment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experimenttyp: ~2 rows (ungefähr)
DELETE FROM `experimenttyp`;
/*!40000 ALTER TABLE `experimenttyp` DISABLE KEYS */;
INSERT INTO `experimenttyp` (`id`, `typ`) VALUES
	(1, 'slurry'),
	(3, 'grinding');
/*!40000 ALTER TABLE `experimenttyp` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experimenttyp_grinding
CREATE TABLE IF NOT EXISTS `experimenttyp_grinding` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experimenttyp_grinding_id_uindex` (`id`),
  CONSTRAINT `experimenttyp_grinding_experimenttyp_id_fk` FOREIGN KEY (`id`) REFERENCES `experimenttyp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experimenttyp_grinding: ~1 rows (ungefähr)
DELETE FROM `experimenttyp_grinding`;
/*!40000 ALTER TABLE `experimenttyp_grinding` DISABLE KEYS */;
INSERT INTO `experimenttyp_grinding` (`id`) VALUES
	(3);
/*!40000 ALTER TABLE `experimenttyp_grinding` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experimenttyp_slurry
CREATE TABLE IF NOT EXISTS `experimenttyp_slurry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experimenttyp_slurry_id_uindex` (`id`),
  CONSTRAINT `experimenttyp_slurry_experimenttyp_id_fk` FOREIGN KEY (`id`) REFERENCES `experimenttyp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experimenttyp_slurry: ~0 rows (ungefähr)
DELETE FROM `experimenttyp_slurry`;
/*!40000 ALTER TABLE `experimenttyp_slurry` DISABLE KEYS */;
/*!40000 ALTER TABLE `experimenttyp_slurry` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.mitarbeiter
CREATE TABLE IF NOT EXISTS `mitarbeiter` (
  `mitarbeiterID` int(11) NOT NULL AUTO_INCREMENT,
  `vorname` varchar(30) DEFAULT NULL,
  `nachname` varchar(30) DEFAULT NULL,
  `passwort` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`mitarbeiterID`),
  UNIQUE KEY `mitarbeiter_mitarbeiterID_uindex` (`mitarbeiterID`)
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.mitarbeiter: ~2 rows (ungefähr)
DELETE FROM `mitarbeiter`;
/*!40000 ALTER TABLE `mitarbeiter` DISABLE KEYS */;
INSERT INTO `mitarbeiter` (`mitarbeiterID`, `vorname`, `nachname`, `passwort`) VALUES
	(123, 'Hans', 'Wurst', 'abc'),
	(456, 'Harry', 'Potter', 'abc');
/*!40000 ALTER TABLE `mitarbeiter` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.partner
CREATE TABLE IF NOT EXISTS `partner` (
  `vertragsnummer` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`vertragsnummer`),
  UNIQUE KEY `partner_vertragsnummer_uindex` (`vertragsnummer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.partner: ~3 rows (ungefähr)
DELETE FROM `partner`;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
INSERT INTO `partner` (`vertragsnummer`, `name`, `email`) VALUES
	(0, 'hans', 'hans@kranz.lanz'),
	(1, 'perter', 'peter@meter.keter'),
	(2, 'jack', 'jack@hack.lack');
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.probe
CREATE TABLE IF NOT EXISTS `probe` (
  `proben_nr` varchar(30) NOT NULL,
  `substanz_id` varchar(30) NOT NULL,
  PRIMARY KEY (`proben_nr`),
  UNIQUE KEY `probe_proben_nr_uindex` (`proben_nr`),
  KEY `probe_substanz_substanz_id_fk` (`substanz_id`),
  CONSTRAINT `probe_substanz_substanz_id_fk` FOREIGN KEY (`substanz_id`) REFERENCES `substanz` (`substanz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.probe: ~5 rows (ungefähr)
DELETE FROM `probe`;
/*!40000 ALTER TABLE `probe` DISABLE KEYS */;
INSERT INTO `probe` (`proben_nr`, `substanz_id`) VALUES
	('Probe A1', 'SubstanzA'),
	('Probe A2', 'SubstanzA'),
	('Probe A21', 'SubstanzA2'),
	('Probe B1', 'SubstanzB'),
	('Probe C1', 'SubstanzC');
/*!40000 ALTER TABLE `probe` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.projekte
CREATE TABLE IF NOT EXISTS `projekte` (
  `projekt_id` varchar(30) NOT NULL,
  `vertragsnummer` int(11) NOT NULL,
  PRIMARY KEY (`projekt_id`),
  UNIQUE KEY `projekte_projekt_id_uindex` (`projekt_id`),
  KEY `projekte_partner_vertragsnummer_fk` (`vertragsnummer`),
  CONSTRAINT `projekte_partner_vertragsnummer_fk` FOREIGN KEY (`vertragsnummer`) REFERENCES `partner` (`vertragsnummer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.projekte: ~3 rows (ungefähr)
DELETE FROM `projekte`;
/*!40000 ALTER TABLE `projekte` DISABLE KEYS */;
INSERT INTO `projekte` (`projekt_id`, `vertragsnummer`) VALUES
	('A', 0),
	('B', 1),
	('C', 2);
/*!40000 ALTER TABLE `projekte` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.sessions
CREATE TABLE IF NOT EXISTS `sessions` (
  `key` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `mitarbeiterID` int(11) NOT NULL,
  PRIMARY KEY (`key`),
  UNIQUE KEY `sessions_key_uindex` (`key`),
  KEY `sessions_mitarbeiter_mitarbeiterID_fk` (`mitarbeiterID`),
  CONSTRAINT `sessions_mitarbeiter_mitarbeiterID_fk` FOREIGN KEY (`mitarbeiterID`) REFERENCES `mitarbeiter` (`mitarbeiterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.sessions: ~0 rows (ungefähr)
DELETE FROM `sessions`;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.substanz
CREATE TABLE IF NOT EXISTS `substanz` (
  `substanz_id` varchar(30) NOT NULL,
  `projekt_id` varchar(30) NOT NULL,
  PRIMARY KEY (`substanz_id`),
  UNIQUE KEY `substanz_substanz_id_uindex` (`substanz_id`),
  KEY `substanz_projekte_projekt_id_fk` (`projekt_id`),
  CONSTRAINT `substanz_projekte_projekt_id_fk` FOREIGN KEY (`projekt_id`) REFERENCES `projekte` (`projekt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.substanz: ~4 rows (ungefähr)
DELETE FROM `substanz`;
/*!40000 ALTER TABLE `substanz` DISABLE KEYS */;
INSERT INTO `substanz` (`substanz_id`, `projekt_id`) VALUES
	('SubstanzA', 'A'),
	('SubstanzA2', 'A'),
	('SubstanzB', 'B'),
	('SubstanzC', 'C');
/*!40000 ALTER TABLE `substanz` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.substanz_eigenschaften
CREATE TABLE IF NOT EXISTS `substanz_eigenschaften` (
  `substanz` varchar(30) NOT NULL,
  `eigenschaft` varchar(30) NOT NULL,
  PRIMARY KEY (`substanz`,`eigenschaft`),
  KEY `substanz_eigenschaften_eigenschaften_eigenschft_key_fk` (`eigenschaft`),
  CONSTRAINT `substanz_eigenschaften_eigenschaften_eigenschft_key_fk` FOREIGN KEY (`eigenschaft`) REFERENCES `eigenschaften` (`eigenschft_key`),
  CONSTRAINT `substanz_eigenschaften_substanz_substanz_id_fk` FOREIGN KEY (`substanz`) REFERENCES `substanz` (`substanz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.substanz_eigenschaften: ~0 rows (ungefähr)
DELETE FROM `substanz_eigenschaften`;
/*!40000 ALTER TABLE `substanz_eigenschaften` DISABLE KEYS */;
/*!40000 ALTER TABLE `substanz_eigenschaften` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
