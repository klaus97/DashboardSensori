-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 17, 2019 alle 14:24
-- Versione del server: 10.1.32-MariaDB
-- Versione PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dashboardambientale`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `area`
--

CREATE TABLE `area` (
  `ID` int(10) UNSIGNED NOT NULL,
  `nome` varchar(35) NOT NULL,
  `regione` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `area`
--

INSERT INTO `area` (`ID`, `nome`, `regione`) VALUES
(1, 'L\'Aquila', 'Abruzzo');

-- --------------------------------------------------------

--
-- Struttura della tabella `dato`
--

CREATE TABLE `dato` (
  `ID` int(10) UNSIGNED NOT NULL,
  `valore` int(11) NOT NULL,
  `tipo` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `dato`
--

INSERT INTO `dato` (`ID`, `valore`, `tipo`) VALUES
(1, 12, 'temperatura'),
(2, 45, 'umidità'),
(6, 10, 'temperatura'),
(2036, 12, 'temperatura'),
(2037, 31, 'temperatura'),
(2038, -7, 'temperatura'),
(2039, 37, 'temperatura'),
(2040, -1, 'temperatura'),
(2041, -6, 'temperatura'),
(2042, 5, 'temperatura'),
(2043, 90, 'umidità'),
(2044, -1, 'temperatura'),
(2045, 25, 'temperatura'),
(2046, 15, 'temperatura'),
(2047, 19, 'temperatura'),
(2048, 5, 'temperatura'),
(2049, 19, 'temperatura'),
(2050, 39, 'temperatura'),
(2051, 29, 'umidità'),
(2052, 4, 'temperatura'),
(2053, -7, 'temperatura'),
(2054, 61, 'umidità');

-- --------------------------------------------------------

--
-- Struttura della tabella `edificio`
--

CREATE TABLE `edificio` (
  `ID` int(10) UNSIGNED NOT NULL,
  `nome` varchar(35) NOT NULL,
  `indirizzo` varchar(50) NOT NULL,
  `ID_zona` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `edificio`
--

INSERT INTO `edificio` (`ID`, `nome`, `indirizzo`, `ID_zona`) VALUES
(1, 'Univeristà Coppito', 'Via vetoio,48', 1),
(2, 'Università Coppito 0', 'Via vetoio ', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `gestisce`
--

CREATE TABLE `gestisce` (
  `ID_gestore` int(10) UNSIGNED NOT NULL,
  `ID_zona` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `gestisce`
--

INSERT INTO `gestisce` (`ID_gestore`, `ID_zona`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `gestore`
--

CREATE TABLE `gestore` (
  `ID` int(10) UNSIGNED NOT NULL,
  `codiceacc` varchar(15) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `ID_area` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `gestore`
--

INSERT INTO `gestore` (`ID`, `codiceacc`, `nome`, `cognome`, `ID_area`) VALUES
(1, 'AQ12', 'carlo', 'conti', 1),
(2, 'AQZ19', 'Paul', 'prova', 1),
(3, 'AQEL20', 'test', 'pass', 1),
(4, 'AQEL21', 'pippo', 'peppo', 1),
(5, 'AQZ20', 'prova', 'bebe', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `invia`
--

CREATE TABLE `invia` (
  `ID_sensore` int(10) UNSIGNED NOT NULL,
  `ID_dato` int(10) UNSIGNED NOT NULL,
  `datainvio` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `invia`
--

INSERT INTO `invia` (`ID_sensore`, `ID_dato`, `datainvio`) VALUES
(1, 1, '2018-12-22'),
(1, 2042, '2019-01-16'),
(2, 2, '2018-12-27'),
(2, 2043, '2019-01-16'),
(2, 2051, '2019-01-16'),
(2, 2054, '2019-01-16'),
(3, 6, '2018-12-25'),
(3, 2037, '2019-01-16'),
(3, 2038, '2019-01-16'),
(3, 2040, '2019-01-16'),
(3, 2041, '2019-01-16'),
(3, 2050, '2019-01-16'),
(3, 2053, '2019-01-16'),
(4, 2036, '2019-01-16'),
(4, 2039, '2019-01-16'),
(4, 2044, '2019-01-16'),
(4, 2045, '2019-01-16'),
(4, 2046, '2019-01-16'),
(4, 2047, '2019-01-16'),
(4, 2048, '2019-01-16'),
(4, 2049, '2019-01-16'),
(4, 2052, '2019-01-16');

-- --------------------------------------------------------

--
-- Struttura della tabella `luogoaperto`
--

CREATE TABLE `luogoaperto` (
  `ID` int(11) UNSIGNED NOT NULL,
  `nome` varchar(35) NOT NULL,
  `indirizzo` varchar(50) NOT NULL,
  `ID_zona` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `luogoaperto`
--

INSERT INTO `luogoaperto` (`ID`, `nome`, `indirizzo`, `ID_zona`) VALUES
(1, 'Lago Vetoio', 'Str. Lago di Vetoio', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `monitoraed`
--

CREATE TABLE `monitoraed` (
  `ID_gestore` int(10) UNSIGNED NOT NULL,
  `ID_edificio` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `monitoraed`
--

INSERT INTO `monitoraed` (`ID_gestore`, `ID_edificio`) VALUES
(4, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `monitoraluogo`
--

CREATE TABLE `monitoraluogo` (
  `ID_gestore` int(10) UNSIGNED NOT NULL,
  `ID_luogoaperto` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `monitoraluogo`
--

INSERT INTO `monitoraluogo` (`ID_gestore`, `ID_luogoaperto`) VALUES
(3, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `ruolo`
--

CREATE TABLE `ruolo` (
  `ID` int(10) UNSIGNED NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `ID_gestore` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `ruolo`
--

INSERT INTO `ruolo` (`ID`, `tipo`, `ID_gestore`) VALUES
(1, 'area', 1),
(2, 'zona', 2),
(3, 'luogo', 3),
(4, 'luogo', 4),
(5, 'zona', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `sensore`
--

CREATE TABLE `sensore` (
  `ID` int(10) UNSIGNED NOT NULL,
  `cod` varchar(13) NOT NULL,
  `stato` int(11) NOT NULL,
  `massimale` int(11) NOT NULL,
  `freq` int(11) NOT NULL,
  `ID_luogoaperto` int(10) UNSIGNED DEFAULT NULL,
  `ID_stanza` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `sensore`
--

INSERT INTO `sensore` (`ID`, `cod`, `stato`, `massimale`, `freq`, `ID_luogoaperto`, `ID_stanza`) VALUES
(1, 'TEMP12', 0, 22, 0, 1, NULL),
(2, 'UM56', 1, 33, 1, NULL, 1),
(3, 'TEMP13', 0, 22, 0, NULL, 1),
(4, 'TEMP14', 1, 20, 0, NULL, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `stanza`
--

CREATE TABLE `stanza` (
  `ID` int(10) UNSIGNED NOT NULL,
  `nome` varchar(20) NOT NULL,
  `piano` smallint(6) DEFAULT NULL,
  `ID_edificio` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `stanza`
--

INSERT INTO `stanza` (`ID`, `nome`, `piano`, `ID_edificio`) VALUES
(1, 'C1.10', 1, 1),
(2, 'A1.6', 2, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `zona`
--

CREATE TABLE `zona` (
  `ID` int(10) UNSIGNED NOT NULL,
  `nome` varchar(40) NOT NULL,
  `ID_area` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `zona`
--

INSERT INTO `zona` (`ID`, `nome`, `ID_area`) VALUES
(1, 'Coppito', 1),
(2, 'L\'Aquila', 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `dato`
--
ALTER TABLE `dato`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `edificio`
--
ALTER TABLE `edificio`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `edificio_zona` (`ID_zona`);

--
-- Indici per le tabelle `gestisce`
--
ALTER TABLE `gestisce`
  ADD PRIMARY KEY (`ID_gestore`,`ID_zona`),
  ADD KEY `gestisce_zona` (`ID_zona`);

--
-- Indici per le tabelle `gestore`
--
ALTER TABLE `gestore`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `codiceacc` (`codiceacc`),
  ADD KEY `gestore_area` (`ID_area`);

--
-- Indici per le tabelle `invia`
--
ALTER TABLE `invia`
  ADD PRIMARY KEY (`ID_sensore`,`ID_dato`,`datainvio`),
  ADD KEY `dato_invia` (`ID_dato`);

--
-- Indici per le tabelle `luogoaperto`
--
ALTER TABLE `luogoaperto`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `luogoaperto_zona` (`ID_zona`);

--
-- Indici per le tabelle `monitoraed`
--
ALTER TABLE `monitoraed`
  ADD PRIMARY KEY (`ID_gestore`,`ID_edificio`),
  ADD KEY `monitoraed_edificio` (`ID_edificio`);

--
-- Indici per le tabelle `monitoraluogo`
--
ALTER TABLE `monitoraluogo`
  ADD PRIMARY KEY (`ID_gestore`,`ID_luogoaperto`),
  ADD KEY `monitoraluogo_luogoaperto` (`ID_luogoaperto`);

--
-- Indici per le tabelle `ruolo`
--
ALTER TABLE `ruolo`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ruolo_getsore` (`ID_gestore`);

--
-- Indici per le tabelle `sensore`
--
ALTER TABLE `sensore`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `cod` (`cod`),
  ADD KEY `sensore_luogoaperto` (`ID_luogoaperto`),
  ADD KEY `sensore_stanza` (`ID_stanza`);

--
-- Indici per le tabelle `stanza`
--
ALTER TABLE `stanza`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `stanza_edificio` (`ID_edificio`);

--
-- Indici per le tabelle `zona`
--
ALTER TABLE `zona`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `zona_area` (`ID_area`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `area`
--
ALTER TABLE `area`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `dato`
--
ALTER TABLE `dato`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2055;

--
-- AUTO_INCREMENT per la tabella `edificio`
--
ALTER TABLE `edificio`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `gestore`
--
ALTER TABLE `gestore`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `luogoaperto`
--
ALTER TABLE `luogoaperto`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `sensore`
--
ALTER TABLE `sensore`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `stanza`
--
ALTER TABLE `stanza`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `zona`
--
ALTER TABLE `zona`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `edificio`
--
ALTER TABLE `edificio`
  ADD CONSTRAINT `edificio_zona` FOREIGN KEY (`ID_zona`) REFERENCES `zona` (`ID`);

--
-- Limiti per la tabella `gestisce`
--
ALTER TABLE `gestisce`
  ADD CONSTRAINT `gestisce_gestore` FOREIGN KEY (`ID_gestore`) REFERENCES `gestore` (`ID`),
  ADD CONSTRAINT `gestisce_zona` FOREIGN KEY (`ID_zona`) REFERENCES `zona` (`ID`);

--
-- Limiti per la tabella `gestore`
--
ALTER TABLE `gestore`
  ADD CONSTRAINT `gestore_area` FOREIGN KEY (`ID_area`) REFERENCES `area` (`ID`);

--
-- Limiti per la tabella `invia`
--
ALTER TABLE `invia`
  ADD CONSTRAINT `dato_invia` FOREIGN KEY (`ID_dato`) REFERENCES `dato` (`ID`),
  ADD CONSTRAINT `sensore_invia` FOREIGN KEY (`ID_sensore`) REFERENCES `sensore` (`ID`);

--
-- Limiti per la tabella `luogoaperto`
--
ALTER TABLE `luogoaperto`
  ADD CONSTRAINT `luogoaperto_zona` FOREIGN KEY (`ID_zona`) REFERENCES `zona` (`ID`);

--
-- Limiti per la tabella `monitoraed`
--
ALTER TABLE `monitoraed`
  ADD CONSTRAINT `monitoraed_edificio` FOREIGN KEY (`ID_edificio`) REFERENCES `edificio` (`ID`),
  ADD CONSTRAINT `monitoraed_gestore` FOREIGN KEY (`ID_gestore`) REFERENCES `gestore` (`ID`);

--
-- Limiti per la tabella `monitoraluogo`
--
ALTER TABLE `monitoraluogo`
  ADD CONSTRAINT `monitoraluogo_gestore` FOREIGN KEY (`ID_gestore`) REFERENCES `gestore` (`ID`),
  ADD CONSTRAINT `monitoraluogo_luogoaperto` FOREIGN KEY (`ID_luogoaperto`) REFERENCES `luogoaperto` (`ID`);

--
-- Limiti per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  ADD CONSTRAINT `ruolo_getsore` FOREIGN KEY (`ID_gestore`) REFERENCES `gestore` (`ID`);

--
-- Limiti per la tabella `sensore`
--
ALTER TABLE `sensore`
  ADD CONSTRAINT `sensore_luogoaperto` FOREIGN KEY (`ID_luogoaperto`) REFERENCES `luogoaperto` (`ID`),
  ADD CONSTRAINT `sensore_stanza` FOREIGN KEY (`ID_stanza`) REFERENCES `stanza` (`ID`);

--
-- Limiti per la tabella `stanza`
--
ALTER TABLE `stanza`
  ADD CONSTRAINT `stanza_edificio` FOREIGN KEY (`ID_edificio`) REFERENCES `edificio` (`ID`);

--
-- Limiti per la tabella `zona`
--
ALTER TABLE `zona`
  ADD CONSTRAINT `zona_area` FOREIGN KEY (`ID_area`) REFERENCES `area` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
