-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 10, 2019 alle 13:00
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
(2009, -17, 'temperatura'),
(2010, -11, 'temperatura'),
(2011, 46, 'umidità'),
(2012, 29, 'temperatura'),
(2013, -9, 'temperatura'),
(2014, 47, 'umidità'),
(2015, -1, 'temperatura'),
(2016, 28, 'temperatura'),
(2017, 21, 'umidità'),
(2018, -18, 'temperatura'),
(2019, -11, 'temperatura'),
(2020, 58, 'umidità'),
(2021, -14, 'temperatura'),
(2022, 2, 'temperatura'),
(2023, 73, 'umidità');

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
(1, 'Univeristà Coppito', 'Via vetoio,48', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `gestisce`
--

CREATE TABLE `gestisce` (
  `ID_gestore` int(10) UNSIGNED NOT NULL,
  `ID_zona` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(3, 'AQEL20', 'test', 'pass', 1);

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
(1, 2009, '2019-01-09'),
(1, 2012, '2019-01-09'),
(1, 2015, '2019-01-09'),
(1, 2018, '2019-01-09'),
(1, 2021, '2019-01-09'),
(2, 2, '2018-12-27'),
(2, 2011, '2019-01-09'),
(2, 2014, '2019-01-09'),
(2, 2017, '2019-01-09'),
(2, 2020, '2019-01-09'),
(2, 2023, '2019-01-09'),
(3, 6, '2018-12-25'),
(3, 2010, '2019-01-09'),
(3, 2013, '2019-01-09'),
(3, 2016, '2019-01-09'),
(3, 2019, '2019-01-09'),
(3, 2022, '2019-01-09');

-- --------------------------------------------------------

--
-- Struttura della tabella `luogo aperto`
--

CREATE TABLE `luogo aperto` (
  `ID` int(11) UNSIGNED NOT NULL,
  `nome` varchar(35) NOT NULL,
  `indirizzo` varchar(50) NOT NULL,
  `ID_zona` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `luogo aperto`
--

INSERT INTO `luogo aperto` (`ID`, `nome`, `indirizzo`, `ID_zona`) VALUES
(1, 'Lago Vetoio', 'Str. Lago di Vetoio', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `monitora`
--

CREATE TABLE `monitora` (
  `ID_luogoaperto` int(10) UNSIGNED NOT NULL,
  `ID_gestore` int(10) UNSIGNED NOT NULL,
  `ID_edificio` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(3, 'luogo', 3);

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
  `ID_edificio` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `sensore`
--

INSERT INTO `sensore` (`ID`, `cod`, `stato`, `massimale`, `freq`, `ID_luogoaperto`, `ID_edificio`) VALUES
(1, 'TEMP12', 0, 22, 0, NULL, NULL),
(2, 'UM56', 0, 40, 2, NULL, NULL),
(3, 'TEMP13', 0, 18, 0, NULL, NULL);

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
-- Indici per le tabelle `luogo aperto`
--
ALTER TABLE `luogo aperto`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `luogoaperto_zona` (`ID_zona`);

--
-- Indici per le tabelle `monitora`
--
ALTER TABLE `monitora`
  ADD PRIMARY KEY (`ID_luogoaperto`,`ID_gestore`),
  ADD KEY `monitora_edificio` (`ID_edificio`),
  ADD KEY `monitora_gestore` (`ID_gestore`);

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
  ADD UNIQUE KEY `cod` (`cod`);

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
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2024;

--
-- AUTO_INCREMENT per la tabella `edificio`
--
ALTER TABLE `edificio`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `gestore`
--
ALTER TABLE `gestore`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `luogo aperto`
--
ALTER TABLE `luogo aperto`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `sensore`
--
ALTER TABLE `sensore`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `stanza`
--
ALTER TABLE `stanza`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

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
-- Limiti per la tabella `luogo aperto`
--
ALTER TABLE `luogo aperto`
  ADD CONSTRAINT `luogoaperto_zona` FOREIGN KEY (`ID_zona`) REFERENCES `zona` (`ID`);

--
-- Limiti per la tabella `monitora`
--
ALTER TABLE `monitora`
  ADD CONSTRAINT `monitora_edificio` FOREIGN KEY (`ID_edificio`) REFERENCES `edificio` (`ID`),
  ADD CONSTRAINT `monitora_gestore` FOREIGN KEY (`ID_gestore`) REFERENCES `gestore` (`ID`),
  ADD CONSTRAINT `monitora_luogoaperto` FOREIGN KEY (`ID_luogoaperto`) REFERENCES `luogo aperto` (`ID`);

--
-- Limiti per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  ADD CONSTRAINT `ruolo_getsore` FOREIGN KEY (`ID_gestore`) REFERENCES `gestore` (`ID`);

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
