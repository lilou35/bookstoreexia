-- phpMyAdmin SQL Dump
-- version 3.1.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Lun 18 Avril 2011 à 13:19
-- Version du serveur: 5.1.30
-- Version de PHP: 5.2.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `bookstore`
--

-- --------------------------------------------------------

--
-- Structure de la table `auteur`
--

CREATE TABLE IF NOT EXISTS `auteur` (
  `auteurid` int(11) NOT NULL,
  `auteurnom` varchar(20) DEFAULT NULL,
  `auteurprenom` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`auteurid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `auteur`
--

INSERT INTO `auteur` (`auteurid`, `auteurnom`, `auteurprenom`) VALUES
(1, 'wiko', 'nicolas');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `categorieid` int(11) NOT NULL,
  `categorietype` text,
  `categoriedescription` text,
  PRIMARY KEY (`categorieid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`categorieid`, `categorietype`, `categoriedescription`) VALUES
(0, 'fiction', 'sicence fiction ');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `clientid` int(11) NOT NULL,
  `clientlogin` varchar(20) DEFAULT NULL,
  `clientmdp` varchar(20) DEFAULT NULL,
  `clientnom` varchar(20) DEFAULT NULL,
  `clientprenom` varchar(20) DEFAULT NULL,
  `clientmail` varchar(20) DEFAULT NULL,
  `clientrue` varchar(40) DEFAULT NULL,
  `clientcodepostal` int(7) DEFAULT NULL,
  `clientville` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`clientid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--


-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `clientid` int(11) NOT NULL,
  `livreid` int(11) NOT NULL,
  `journalId` int(11) NOT NULL,
  `commandeid` int(11) DEFAULT NULL,
  `commandedate` datetime DEFAULT NULL,
  `commandedatelivraison` datetime DEFAULT NULL,
  `commandequantite` int(11) DEFAULT NULL,
  `commandeetat` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`clientid`,`livreid`,`journalId`),
  KEY `FK_Commande_livreid` (`livreid`),
  KEY `FK_Commande_journalId` (`journalId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commande`
--


-- --------------------------------------------------------

--
-- Structure de la table `ecrivain`
--

CREATE TABLE IF NOT EXISTS `ecrivain` (
  `auteurid` int(11) NOT NULL,
  `livreid` int(11) NOT NULL,
  PRIMARY KEY (`auteurid`,`livreid`),
  KEY `FK_Ecrivain_livreid` (`livreid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ecrivain`
--

INSERT INTO `ecrivain` (`auteurid`, `livreid`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `journal`
--

CREATE TABLE IF NOT EXISTS `journal` (
  `journalId` int(11) NOT NULL,
  `journaldescription` text,
  `journaldate` datetime DEFAULT NULL,
  `libraireid` int(11) NOT NULL,
  PRIMARY KEY (`journalId`),
  KEY `FK_Journal_libraireid` (`libraireid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `journal`
--


-- --------------------------------------------------------

--
-- Structure de la table `libraire`
--

CREATE TABLE IF NOT EXISTS `libraire` (
  `libraireid` int(11) NOT NULL,
  `librairenom` text,
  `librairemdp` text,
  PRIMARY KEY (`libraireid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `libraire`
--


-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE IF NOT EXISTS `livre` (
  `livreid` int(11) NOT NULL,
  `livretitre` varchar(20) DEFAULT NULL,
  `livreresume` text,
  `livrenbvente` int(11) DEFAULT NULL,
  `livreparution` datetime DEFAULT NULL,
  `livresommaire` text,
  `livrecouverture` varchar(20) DEFAULT NULL,
  `livrestock` int(6) DEFAULT NULL,
  `livreprix` float DEFAULT NULL,
  `livrestockalerte` tinyint(1) DEFAULT NULL,
  `livreediteur` varchar(20) DEFAULT NULL,
  `livreetat` int(11) DEFAULT NULL,
  `categorieid` int(11) NOT NULL,
  PRIMARY KEY (`livreid`),
  KEY `FK_Livre_categorieid` (`categorieid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `livre`
--

INSERT INTO `livre` (`livreid`, `livretitre`, `livreresume`, `livrenbvente`, `livreparution`, `livresommaire`, `livrecouverture`, `livrestock`, `livreprix`, `livrestockalerte`, `livreediteur`, `livreetat`, `categorieid`) VALUES
(1, 'livre1', 'resume du livre 1 ^fvonefùblon^sfn\r\nqfbv qùpdfi nqùldfbnùqdlvn ', 1, '2011-04-14 13:52:04', '1 un\r\n2 deux\r\n3 trois', 'image.jpg', 10, 10, 10, '10', 10, 0);

-- --------------------------------------------------------

--
-- Structure de la table `parametres`
--

CREATE TABLE IF NOT EXISTS `parametres` (
  `paranblivrepage` int(11) NOT NULL,
  `paranblivreaccueil` int(11) DEFAULT NULL,
  `paraimage` tinyint(1) DEFAULT NULL,
  `parastockdefault` int(11) DEFAULT NULL,
  `paraalertedefault` int(11) DEFAULT NULL,
  PRIMARY KEY (`paranblivrepage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `parametres`
--


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_Commande_clientid` FOREIGN KEY (`clientid`) REFERENCES `client` (`clientid`),
  ADD CONSTRAINT `FK_Commande_journalId` FOREIGN KEY (`journalId`) REFERENCES `journal` (`journalId`),
  ADD CONSTRAINT `FK_Commande_livreid` FOREIGN KEY (`livreid`) REFERENCES `livre` (`livreid`);

--
-- Contraintes pour la table `ecrivain`
--
ALTER TABLE `ecrivain`
  ADD CONSTRAINT `FK_Ecrivain_auteurid` FOREIGN KEY (`auteurid`) REFERENCES `auteur` (`auteurid`),
  ADD CONSTRAINT `FK_Ecrivain_livreid` FOREIGN KEY (`livreid`) REFERENCES `livre` (`livreid`);

--
-- Contraintes pour la table `journal`
--
ALTER TABLE `journal`
  ADD CONSTRAINT `FK_Journal_libraireid` FOREIGN KEY (`libraireid`) REFERENCES `libraire` (`libraireid`);

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `FK_Livre_categorieid` FOREIGN KEY (`categorieid`) REFERENCES `categorie` (`categorieid`);
