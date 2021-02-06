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
DROP TABLE IF EXISTS `analysen`;
CREATE TABLE IF NOT EXISTS `analysen` (
  `id` int(11) NOT NULL,
  `experiment` int(11) NOT NULL,
  `typ` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `experimente_analysen_experiment_id_fk` (`experiment`),
  KEY `analysen_analysetyp_id_fk` (`typ`),
  CONSTRAINT `analysen_analysetyp_id_fk` FOREIGN KEY (`typ`) REFERENCES `analysetyp` (`id`),
  CONSTRAINT `experimente_analysen_experiment_id_fk` FOREIGN KEY (`experiment`) REFERENCES `experiment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.analysen: ~0 rows (ungefähr)
DELETE FROM `analysen`;
/*!40000 ALTER TABLE `analysen` DISABLE KEYS */;
/*!40000 ALTER TABLE `analysen` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.analysetyp
DROP TABLE IF EXISTS `analysetyp`;
CREATE TABLE IF NOT EXISTS `analysetyp` (
  `id` int(11) NOT NULL,
  `typ` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.analysetyp: ~4 rows (ungefähr)
DELETE FROM `analysetyp`;
/*!40000 ALTER TABLE `analysetyp` DISABLE KEYS */;
INSERT INTO `analysetyp` (`id`, `typ`) VALUES
	(0, 'pxrd'),
	(1, 'dsc'),
	(2, 'tga'),
	(3, 'ir');
/*!40000 ALTER TABLE `analysetyp` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.datenmaske_dsc
DROP TABLE IF EXISTS `datenmaske_dsc`;
CREATE TABLE IF NOT EXISTS `datenmaske_dsc` (
  `id` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `einwaage` double DEFAULT NULL,
  `auswaage` double DEFAULT NULL,
  `temperaturprogramm` varchar(30) DEFAULT NULL,
  `tiegel` varchar(30) DEFAULT NULL,
  `tiegelpräparation` varchar(30) DEFAULT NULL,
  `bemerkung` varchar(30) DEFAULT NULL,
  `operator` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datenmaske_dsc_temperaturprogramme_table_fk` (`temperaturprogramm`),
  CONSTRAINT `datenmaske_dsc_analysen_id_fk` FOREIGN KEY (`id`) REFERENCES `analysen` (`id`),
  CONSTRAINT `datenmaske_dsc_temperaturprogramme_table_fk` FOREIGN KEY (`temperaturprogramm`) REFERENCES `temperaturprogramme` (`table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.datenmaske_dsc: ~0 rows (ungefähr)
DELETE FROM `datenmaske_dsc`;
/*!40000 ALTER TABLE `datenmaske_dsc` DISABLE KEYS */;
/*!40000 ALTER TABLE `datenmaske_dsc` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.datenmaske_ir
DROP TABLE IF EXISTS `datenmaske_ir`;
CREATE TABLE IF NOT EXISTS `datenmaske_ir` (
  `id` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `scans` int(11) DEFAULT NULL,
  `aufloesung` int(11) DEFAULT NULL,
  `geometrie` varchar(30) DEFAULT NULL,
  `praeparation` varchar(30) DEFAULT NULL,
  `background` date DEFAULT NULL,
  `bemerkung` varchar(30) DEFAULT NULL,
  `operator` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `datenmaske_ir_analysen_id_fk` FOREIGN KEY (`id`) REFERENCES `analysen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.datenmaske_ir: ~0 rows (ungefähr)
DELETE FROM `datenmaske_ir`;
/*!40000 ALTER TABLE `datenmaske_ir` DISABLE KEYS */;
/*!40000 ALTER TABLE `datenmaske_ir` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.datenmaske_pxrd
DROP TABLE IF EXISTS `datenmaske_pxrd`;
CREATE TABLE IF NOT EXISTS `datenmaske_pxrd` (
  `id` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `geraet` varchar(2) DEFAULT NULL,
  `probenpaeparation` varchar(30) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `programm` varchar(30) DEFAULT NULL,
  `messzeit` int(11) DEFAULT NULL,
  `bemerkung` varchar(30) DEFAULT NULL,
  `operator` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `datenmaske_pxrd_analysen_id_fk` FOREIGN KEY (`id`) REFERENCES `analysen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.datenmaske_pxrd: ~0 rows (ungefähr)
DELETE FROM `datenmaske_pxrd`;
/*!40000 ALTER TABLE `datenmaske_pxrd` DISABLE KEYS */;
/*!40000 ALTER TABLE `datenmaske_pxrd` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.datenmaske_tga
DROP TABLE IF EXISTS `datenmaske_tga`;
CREATE TABLE IF NOT EXISTS `datenmaske_tga` (
  `id` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `einwaage` double DEFAULT NULL,
  `rampe` int(11) DEFAULT NULL,
  `temperaturprogramm` varchar(30) DEFAULT NULL,
  `bemerkung` varchar(30) DEFAULT NULL,
  `operator` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `datenmaske_tga_temperaturprogramme_table_fk` (`temperaturprogramm`),
  CONSTRAINT `datenmaske_tga_analysen_id_fk` FOREIGN KEY (`id`) REFERENCES `analysen` (`id`),
  CONSTRAINT `datenmaske_tga_temperaturprogramme_table_fk` FOREIGN KEY (`temperaturprogramm`) REFERENCES `temperaturprogramme` (`table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.datenmaske_tga: ~0 rows (ungefähr)
DELETE FROM `datenmaske_tga`;
/*!40000 ALTER TABLE `datenmaske_tga` DISABLE KEYS */;
/*!40000 ALTER TABLE `datenmaske_tga` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.eigenschaften
DROP TABLE IF EXISTS `eigenschaften`;
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

-- Exportiere Struktur von Tabelle demo.eingangsanalystik
DROP TABLE IF EXISTS `eingangsanalystik`;
CREATE TABLE IF NOT EXISTS `eingangsanalystik` (
  `id` int(11) NOT NULL,
  `planung` int(11) NOT NULL,
  `notiz` varchar(30) DEFAULT NULL,
  `verweis` blob DEFAULT NULL,
  `startfreigabe` date DEFAULT NULL,
  `erledigt_bis` date DEFAULT NULL,
  `hinweis` varchar(30) DEFAULT NULL,
  `planung_abgeschlossen` tinyint(1) DEFAULT NULL,
  `sicherheitshinweis` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `eingangsanalystik_id_uindex` (`id`),
  CONSTRAINT `eingangsanalystik_experiment_id_fk` FOREIGN KEY (`id`) REFERENCES `experiment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.eingangsanalystik: ~0 rows (ungefähr)
DELETE FROM `eingangsanalystik`;
/*!40000 ALTER TABLE `eingangsanalystik` DISABLE KEYS */;
/*!40000 ALTER TABLE `eingangsanalystik` ENABLE KEYS */;

-- Exportiere Struktur von Ereignis demo.example
DROP EVENT IF EXISTS `example`;
DELIMITER //
CREATE EVENT `example` ON SCHEDULE EVERY 1 DAY STARTS '2021-01-23 03:32:09' ON COMPLETION NOT PRESERVE ENABLE DO delete from sessions where date < current_date - 30//
DELIMITER ;

-- Exportiere Struktur von Tabelle demo.experiment
DROP TABLE IF EXISTS `experiment`;
CREATE TABLE IF NOT EXISTS `experiment` (
  `id` int(11) NOT NULL,
  `probennummer` varchar(30) NOT NULL,
  `typ` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experiment_id_uindex` (`id`),
  KEY `experiment_experimenttyp_id_fk` (`typ`),
  KEY `experiment_probe_probennummer_fk` (`probennummer`),
  CONSTRAINT `experiment_experimenttyp_id_fk` FOREIGN KEY (`typ`) REFERENCES `experimenttyp` (`id`),
  CONSTRAINT `experiment_probe_probennummer_fk` FOREIGN KEY (`probennummer`) REFERENCES `probe` (`probennummer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.experiment: ~0 rows (ungefähr)
DELETE FROM `experiment`;
/*!40000 ALTER TABLE `experiment` DISABLE KEYS */;
/*!40000 ALTER TABLE `experiment` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experimenttyp
DROP TABLE IF EXISTS `experimenttyp`;
CREATE TABLE IF NOT EXISTS `experimenttyp` (
  `id` int(11) NOT NULL,
  `typ` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experimenttyp_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experimenttyp: ~3 rows (ungefähr)
DELETE FROM `experimenttyp`;
/*!40000 ALTER TABLE `experimenttyp` DISABLE KEYS */;
INSERT INTO `experimenttyp` (`id`, `typ`) VALUES
	(0, 'Verdampfung 1Lömi'),
	(1, 'Slurry 1Lömi'),
	(2, 'Eingangsanalytik');
/*!40000 ALTER TABLE `experimenttyp` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experimenttyp_grinding
DROP TABLE IF EXISTS `experimenttyp_grinding`;
CREATE TABLE IF NOT EXISTS `experimenttyp_grinding` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experimenttyp_grinding_id_uindex` (`id`),
  CONSTRAINT `experimenttyp_grinding_experiment_id_fk` FOREIGN KEY (`id`) REFERENCES `experiment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experimenttyp_grinding: ~0 rows (ungefähr)
DELETE FROM `experimenttyp_grinding`;
/*!40000 ALTER TABLE `experimenttyp_grinding` DISABLE KEYS */;
/*!40000 ALTER TABLE `experimenttyp_grinding` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experimenttyp_slurry
DROP TABLE IF EXISTS `experimenttyp_slurry`;
CREATE TABLE IF NOT EXISTS `experimenttyp_slurry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planung` int(11) DEFAULT NULL,
  `serie` varchar(30) DEFAULT NULL,
  `durchfuehrung` varchar(30) DEFAULT NULL,
  `notiz_intention` varchar(30) DEFAULT NULL,
  `verweis` varchar(30) DEFAULT NULL,
  `startfreigabe` date DEFAULT NULL,
  `erledigt_bis` date DEFAULT NULL,
  `hinweis_laborleiter` varchar(30) DEFAULT NULL,
  `planung_abgeschlossen` tinyint(1) DEFAULT NULL,
  `prioritaet` varchar(11) DEFAULT NULL,
  `sicherheitshinweis` varchar(30) DEFAULT NULL,
  `operator` int(11) DEFAULT NULL,
  `experiment_start` date DEFAULT NULL,
  `soll_einwaage` double DEFAULT NULL,
  `einwaage` double DEFAULT NULL,
  `cof_bezeichnung` varchar(30) DEFAULT NULL,
  `cof_ref_code` varchar(11) DEFAULT NULL,
  `cof_soll_einwaaage` double DEFAULT NULL,
  `cof_einwaage` double DEFAULT NULL,
  `soll_temperatur` double DEFAULT NULL,
  `lösungsmittel` varchar(30) DEFAULT NULL,
  `vorgabe_volumen` varchar(30) DEFAULT NULL,
  `ist_volumen` varchar(30) DEFAULT NULL,
  `beobachtungen_aenderungen` varchar(30) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `beobachtungen_verlauf` varchar(30) DEFAULT NULL,
  `ende` date DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `aufarbeitung_präsentation_pxrd` date DEFAULT NULL,
  `beobachtung_aufarbeitung` varchar(30) DEFAULT NULL,
  `lagerorte` varchar(30) DEFAULT NULL,
  `prioritaet_analytik` varchar(30) DEFAULT NULL,
  `erstanalytik_pxrd` varchar(2) DEFAULT NULL,
  `pxrd_moeglich` tinyint(1) DEFAULT NULL,
  `weitere_analytik_moeglich` varchar(30) DEFAULT NULL,
  `ergebnis_pxrd` tinyint(1) DEFAULT NULL,
  `folgeanalytik_dsc` tinyint(1) DEFAULT NULL,
  `folgeanalytik_tg` tinyint(1) DEFAULT NULL,
  `folgeanalytik_pxrd_ii` tinyint(1) DEFAULT NULL,
  `folgeanalytik_h-nmr` tinyint(1) DEFAULT NULL,
  `folgeanalytik_ir` tinyint(1) DEFAULT NULL,
  `folgeanalytik_raman` tinyint(1) DEFAULT NULL,
  `folgeanalytik_omi` tinyint(1) DEFAULT NULL,
  `informationen_folgeanalytik` varchar(30) DEFAULT NULL,
  `ergebnis_dsc` varchar(30) DEFAULT NULL,
  `ergebnis_tg` varchar(30) DEFAULT NULL,
  `ergebnis_pxrd_ii` varchar(30) DEFAULT NULL,
  `ergebnis_h-nmr` varchar(30) DEFAULT NULL,
  `ergebnis_ir` varchar(30) DEFAULT NULL,
  `ergebnis_raman` varchar(30) DEFAULT NULL,
  `ergebnis_omi` varchar(30) DEFAULT NULL,
  `status_analytik` varchar(30) DEFAULT NULL,
  `gesamt_ergebnis` varchar(30) DEFAULT NULL,
  `einstufung_ergebnis` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experimenttyp_slurry_id_uindex` (`id`),
  KEY `experimenttyp_slurry_experiment_durchführungstext_id_fk` (`durchfuehrung`),
  KEY `experimenttyp_slurry_experiment_serie_serie_fk` (`serie`),
  KEY `experimenttyp_slurry_mitarbeiter_mitarbeiterID_fk` (`planung`),
  KEY `experimenttyp_slurry_mitarbeiter_mitarbeiterID_fk_2` (`operator`),
  CONSTRAINT `experimenttyp_slurry_experiment_durchführungstext_id_fk` FOREIGN KEY (`durchfuehrung`) REFERENCES `experiment_durchführungstext` (`id`),
  CONSTRAINT `experimenttyp_slurry_experiment_id_fk` FOREIGN KEY (`id`) REFERENCES `experiment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `experimenttyp_slurry_experiment_serie_serie_fk` FOREIGN KEY (`serie`) REFERENCES `experiment_serie` (`serie`),
  CONSTRAINT `experimenttyp_slurry_mitarbeiter_mitarbeiterID_fk` FOREIGN KEY (`planung`) REFERENCES `mitarbeiter` (`mitarbeiterID`),
  CONSTRAINT `experimenttyp_slurry_mitarbeiter_mitarbeiterID_fk_2` FOREIGN KEY (`operator`) REFERENCES `mitarbeiter` (`mitarbeiterID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experimenttyp_slurry: ~0 rows (ungefähr)
DELETE FROM `experimenttyp_slurry`;
/*!40000 ALTER TABLE `experimenttyp_slurry` DISABLE KEYS */;
/*!40000 ALTER TABLE `experimenttyp_slurry` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experimenttyp_verdampfung
DROP TABLE IF EXISTS `experimenttyp_verdampfung`;
CREATE TABLE IF NOT EXISTS `experimenttyp_verdampfung` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planung` int(11) DEFAULT NULL,
  `serie` varchar(30) DEFAULT NULL,
  `durchfuehrung` varchar(30) DEFAULT NULL,
  `notiz_intention` varchar(30) DEFAULT NULL,
  `verweis` varchar(30) DEFAULT NULL,
  `startfreigabe` date DEFAULT NULL,
  `erledigt_bis` date DEFAULT NULL,
  `hinweis_laborleiter` varchar(30) DEFAULT NULL,
  `planung_abgeschlossen` tinyint(1) DEFAULT NULL,
  `prioritaet` varchar(11) DEFAULT NULL,
  `sicherheitshinweis` varchar(30) DEFAULT NULL,
  `operator` int(11) DEFAULT NULL,
  `experiment_start` date DEFAULT NULL,
  `vial_tara` double DEFAULT NULL,
  `soll_einwaage` double DEFAULT NULL,
  `einwaage` double DEFAULT NULL,
  `cof_bezeichnung` varchar(30) DEFAULT NULL,
  `cof_ref_code` varchar(11) DEFAULT NULL,
  `cof_soll_einwaaage` double DEFAULT NULL,
  `cof_einwaage` double DEFAULT NULL,
  `soll_temperatur` double DEFAULT NULL,
  `loesungsmittel` varchar(30) DEFAULT NULL,
  `vorgabe_volumen` varchar(30) DEFAULT NULL,
  `ist_volumen` varchar(30) DEFAULT NULL,
  `beobachtungen_aenderungen` varchar(30) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `beobachtungen_verlauf` varchar(30) DEFAULT NULL,
  `ende` date DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `aufarbeitung_präsentation_pxrd` date DEFAULT NULL,
  `beobachtung_aufarbeitung` varchar(30) DEFAULT NULL,
  `ausbeute_vial_kristallisat` double DEFAULT NULL,
  `ausbeute_praep_analystik` double DEFAULT NULL,
  `lagerorte` varchar(30) DEFAULT NULL,
  `prioritaet_analytik` varchar(30) DEFAULT NULL,
  `erstanalytik_pxrd` varchar(2) DEFAULT NULL,
  `pxrd_moeglich` tinyint(1) DEFAULT NULL,
  `weitere_analytik_moeglich` varchar(30) DEFAULT NULL,
  `ergebnis_pxrd` tinyint(1) DEFAULT NULL,
  `folgeanalytik_dsc` tinyint(1) DEFAULT NULL,
  `folgeanalytik_tg` tinyint(1) DEFAULT NULL,
  `folgeanalytik_pxrd_ii` tinyint(1) DEFAULT NULL,
  `folgeanalytik_h-nmr` tinyint(1) DEFAULT NULL,
  `folgeanalytik_ir` tinyint(1) DEFAULT NULL,
  `folgeanalytik_raman` tinyint(1) DEFAULT NULL,
  `folgeanalytik_omi` tinyint(1) DEFAULT NULL,
  `informationen_folgeanalytik` varchar(30) DEFAULT NULL,
  `ergebnis_dsc` varchar(30) DEFAULT NULL,
  `ergebnis_tg` varchar(30) DEFAULT NULL,
  `ergebnis_pxrd_ii` varchar(30) DEFAULT NULL,
  `ergebnis_h-nmr` varchar(30) DEFAULT NULL,
  `ergebnis_ir` varchar(30) DEFAULT NULL,
  `ergebnis_raman` varchar(30) DEFAULT NULL,
  `ergebnis_omi` varchar(30) DEFAULT NULL,
  `status_analytik` varchar(30) DEFAULT NULL,
  `gesamt_ergebnis` varchar(30) DEFAULT NULL,
  `einstufung_ergebnis` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `experimenttyp_loemi_id_uindex` (`id`),
  KEY `experimenttyp_verdampfung_experiment_durchführungstext_id_fk` (`durchfuehrung`),
  KEY `experimenttyp_verdampfung_experiment_serie_serie_fk` (`serie`),
  KEY `experimenttyp_verdampfung_mitarbeiter_mitarbeiterID_fk` (`planung`),
  KEY `experimenttyp_verdampfung_mitarbeiter_mitarbeiterID_fk_2` (`operator`),
  CONSTRAINT `experimenttyp_loemi_experiment_id_fk` FOREIGN KEY (`id`) REFERENCES `experiment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `experimenttyp_verdampfung_experiment_durchführungstext_id_fk` FOREIGN KEY (`durchfuehrung`) REFERENCES `experiment_durchführungstext` (`id`),
  CONSTRAINT `experimenttyp_verdampfung_experiment_serie_serie_fk` FOREIGN KEY (`serie`) REFERENCES `experiment_serie` (`serie`),
  CONSTRAINT `experimenttyp_verdampfung_mitarbeiter_mitarbeiterID_fk` FOREIGN KEY (`planung`) REFERENCES `mitarbeiter` (`mitarbeiterID`),
  CONSTRAINT `experimenttyp_verdampfung_mitarbeiter_mitarbeiterID_fk_2` FOREIGN KEY (`operator`) REFERENCES `mitarbeiter` (`mitarbeiterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experimenttyp_verdampfung: ~0 rows (ungefähr)
DELETE FROM `experimenttyp_verdampfung`;
/*!40000 ALTER TABLE `experimenttyp_verdampfung` DISABLE KEYS */;
/*!40000 ALTER TABLE `experimenttyp_verdampfung` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experiment_durchführungstext
DROP TABLE IF EXISTS `experiment_durchführungstext`;
CREATE TABLE IF NOT EXISTS `experiment_durchführungstext` (
  `id` varchar(30) NOT NULL,
  `text` longtext DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experiment_durchführungstext: ~0 rows (ungefähr)
DELETE FROM `experiment_durchführungstext`;
/*!40000 ALTER TABLE `experiment_durchführungstext` DISABLE KEYS */;
/*!40000 ALTER TABLE `experiment_durchführungstext` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.experiment_serie
DROP TABLE IF EXISTS `experiment_serie`;
CREATE TABLE IF NOT EXISTS `experiment_serie` (
  `serie` varchar(30) NOT NULL,
  PRIMARY KEY (`serie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.experiment_serie: ~0 rows (ungefähr)
DELETE FROM `experiment_serie`;
/*!40000 ALTER TABLE `experiment_serie` DISABLE KEYS */;
/*!40000 ALTER TABLE `experiment_serie` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.mitarbeiter
DROP TABLE IF EXISTS `mitarbeiter`;
CREATE TABLE IF NOT EXISTS `mitarbeiter` (
  `mitarbeiterID` int(11) NOT NULL AUTO_INCREMENT,
  `vorname` varchar(30) DEFAULT NULL,
  `nachname` varchar(30) DEFAULT NULL,
  `passwort` varchar(30) DEFAULT NULL,
  `rolle` int(11) NOT NULL,
  PRIMARY KEY (`mitarbeiterID`),
  UNIQUE KEY `mitarbeiter_mitarbeiterID_uindex` (`mitarbeiterID`),
  KEY `mitarbeiter_rollen_id_fk` (`rolle`),
  CONSTRAINT `mitarbeiter_rollen_id_fk` FOREIGN KEY (`rolle`) REFERENCES `rollen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.mitarbeiter: ~2 rows (ungefähr)
DELETE FROM `mitarbeiter`;
/*!40000 ALTER TABLE `mitarbeiter` DISABLE KEYS */;
INSERT INTO `mitarbeiter` (`mitarbeiterID`, `vorname`, `nachname`, `passwort`, `rolle`) VALUES
	(123, 'Hans', 'Wurst', 'abc', 0),
	(456, 'Harry', 'Potter', 'abc', 0);
/*!40000 ALTER TABLE `mitarbeiter` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.partner
DROP TABLE IF EXISTS `partner`;
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
DROP TABLE IF EXISTS `probe`;
CREATE TABLE IF NOT EXISTS `probe` (
  `probennummer` varchar(30) NOT NULL,
  `substanz_id` varchar(30) NOT NULL,
  PRIMARY KEY (`probennummer`),
  KEY `probe_substanz_substanz_id_fk` (`substanz_id`),
  CONSTRAINT `probe_substanz_substanz_id_fk` FOREIGN KEY (`substanz_id`) REFERENCES `substanz` (`substanz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.probe: ~0 rows (ungefähr)
DELETE FROM `probe`;
/*!40000 ALTER TABLE `probe` DISABLE KEYS */;
/*!40000 ALTER TABLE `probe` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.projekte
DROP TABLE IF EXISTS `projekte`;
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

-- Exportiere Struktur von Tabelle demo.rollen
DROP TABLE IF EXISTS `rollen`;
CREATE TABLE IF NOT EXISTS `rollen` (
  `id` int(11) NOT NULL,
  `typ` varchar(30) DEFAULT NULL,
  `zugehörigkeit` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rollen_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.rollen: ~3 rows (ungefähr)
DELETE FROM `rollen`;
/*!40000 ALTER TABLE `rollen` DISABLE KEYS */;
INSERT INTO `rollen` (`id`, `typ`, `zugehörigkeit`) VALUES
	(0, 'Projektmanager', 'Projektplanung'),
	(1, 'Laborleiter', 'Laborbetreuung'),
	(2, 'Laborteam', 'Durchführung');
/*!40000 ALTER TABLE `rollen` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.sessions
DROP TABLE IF EXISTS `sessions`;
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
DROP TABLE IF EXISTS `substanz`;
CREATE TABLE IF NOT EXISTS `substanz` (
  `substanz_id` varchar(30) NOT NULL,
  `projekt_id` varchar(30) NOT NULL,
  `substanz_name` varchar(30) DEFAULT NULL,
  `Summenformel` varchar(30) DEFAULT NULL,
  `probeneingang` date DEFAULT NULL,
  `probenmasse` float DEFAULT NULL,
  `batch/charge` varchar(30) DEFAULT NULL,
  `info` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`substanz_id`),
  UNIQUE KEY `substanz_substanz_id_uindex` (`substanz_id`),
  KEY `substanz_projekte_projekt_id_fk` (`projekt_id`),
  CONSTRAINT `substanz_projekte_projekt_id_fk` FOREIGN KEY (`projekt_id`) REFERENCES `projekte` (`projekt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportiere Daten aus Tabelle demo.substanz: ~6 rows (ungefähr)
DELETE FROM `substanz`;
/*!40000 ALTER TABLE `substanz` DISABLE KEYS */;
INSERT INTO `substanz` (`substanz_id`, `projekt_id`, `substanz_name`, `Summenformel`, `probeneingang`, `probenmasse`, `batch/charge`, `info`) VALUES
	('SubstanzA', 'A', NULL, NULL, NULL, NULL, NULL, NULL),
	('SubstanzA2', 'A', NULL, NULL, NULL, NULL, NULL, NULL),
	('SubstanzA3', 'B', NULL, NULL, NULL, NULL, NULL, NULL),
	('SubstanzB', 'B', NULL, NULL, NULL, NULL, NULL, NULL),
	('SubstanzC', 'C', NULL, NULL, NULL, NULL, NULL, NULL),
	('SubstanzG', 'A', NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `substanz` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle demo.substanz_eigenschaften
DROP TABLE IF EXISTS `substanz_eigenschaften`;
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

-- Exportiere Struktur von Tabelle demo.temperaturprogramme
DROP TABLE IF EXISTS `temperaturprogramme`;
CREATE TABLE IF NOT EXISTS `temperaturprogramme` (
  `table` varchar(30) NOT NULL,
  `schritt` int(11) NOT NULL,
  `temperatur` int(11) DEFAULT NULL,
  `rampe` int(11) DEFAULT NULL,
  `zeit` int(11) DEFAULT NULL,
  `segmenttyp` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`table`,`schritt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle demo.temperaturprogramme: ~0 rows (ungefähr)
DELETE FROM `temperaturprogramme`;
/*!40000 ALTER TABLE `temperaturprogramme` DISABLE KEYS */;
/*!40000 ALTER TABLE `temperaturprogramme` ENABLE KEYS */;

-- Exportiere Struktur von View demo.test
DROP VIEW IF EXISTS `test`;
-- Erstelle temporäre Tabelle um View Abhängigkeiten zuvorzukommen
CREATE TABLE `test` (
	`vertragsnummer` INT(11) NOT NULL,
	`name` VARCHAR(30) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Exportiere Struktur von View demo.test
DROP VIEW IF EXISTS `test`;
-- Entferne temporäre Tabelle und erstelle die eigentliche View
DROP TABLE IF EXISTS `test`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `test` AS select vertragsnummer, name from partner ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
