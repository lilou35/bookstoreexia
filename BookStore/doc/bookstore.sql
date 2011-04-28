-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- G�n�r� le : Jeu 28 Avril 2011 � 13:04
-- Version du serveur: 5.1.36
-- Version de PHP: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de donn�es: `libbdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `auteur`
--

CREATE TABLE IF NOT EXISTS `auteur` (
  `auteurid` int(11) NOT NULL AUTO_INCREMENT,
  `auteurnom` varchar(20) DEFAULT NULL,
  `auteurprenom` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`auteurid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `auteur`
--

INSERT INTO `auteur` (`auteurid`, `auteurnom`, `auteurprenom`) VALUES
(1, 'wiko', 'nicolas'),
(2, 'boe', 'boe'),
(3, 'flo', 'flo'),
(4, 'Oups', 'Florian'),
(5, 'test', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `categorieid` int(11) NOT NULL AUTO_INCREMENT,
  `categorietype` text,
  `categoriedescription` text,
  PRIMARY KEY (`categorieid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`categorieid`, `categorietype`, `categoriedescription`) VALUES
(1, 'fiction', 'science fiction '),
(2, 'info', 'libre d''information, de culture, de formation'),
(3, 'Mangas', 'Bande dessin�e Japonaise (tout type confondu)'),
(4, 'Policier', 'Livre policier/thriller, \r\nlivre � suspense ...'),
(5, 'Historique', 'Documents historiques ou roman s''inspirant de fait historique'),
(6, 'Roman', 'Histoire aux contours flous caract�ris� pour l''essentiel par une narration fictionnelle');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `clientid` int(11) NOT NULL AUTO_INCREMENT,
  `clientlogin` varchar(20) DEFAULT NULL,
  `clientmdp` varchar(20) DEFAULT NULL,
  `clientnom` varchar(20) DEFAULT NULL,
  `clientprenom` varchar(20) DEFAULT NULL,
  `clientmail` varchar(20) DEFAULT NULL,
  `clientrue` varchar(40) DEFAULT NULL,
  `clientcodepostal` int(7) DEFAULT NULL,
  `clientville` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`clientid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`clientid`, `clientlogin`, `clientmdp`, `clientnom`, `clientprenom`, `clientmail`, `clientrue`, `clientcodepostal`, `clientville`) VALUES
(1, 'lo', 'lo', 'Bo�', 'Nicolas', 'wikola@hotmail.fr', '1 place du four', 81500, 'fiac'),
(2, 'flo', 'flo', 'Fayeulle', 'Florian', 'rock303@hotmail.fr', '60 rue des tours', 31670, 'lab�ge');

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
(1, 1),
(2, 1),
(1, 2),
(1, 3),
(1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `journal`
--

CREATE TABLE IF NOT EXISTS `journal` (
  `journalId` int(11) NOT NULL AUTO_INCREMENT,
  `journaldescription` text,
  `journaldate` datetime DEFAULT NULL,
  `libraireid` int(11) NOT NULL,
  PRIMARY KEY (`journalId`),
  KEY `FK_Journal_libraireid` (`libraireid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `journal`
--


-- --------------------------------------------------------

--
-- Structure de la table `libraire`
--

CREATE TABLE IF NOT EXISTS `libraire` (
  `libraireid` int(11) NOT NULL AUTO_INCREMENT,
  `librairenom` text,
  `librairemdp` text,
  PRIMARY KEY (`libraireid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `libraire`
--

INSERT INTO `libraire` (`libraireid`, `librairenom`, `librairemdp`) VALUES
(1, 'lo', 'lo'),
(2, 'li', 'li'),
(3, 'flo', 'flo'),
(4, 'wiko', 'wiko');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE IF NOT EXISTS `livre` (
  `livreid` int(11) NOT NULL AUTO_INCREMENT,
  `livretitre` varchar(20) DEFAULT NULL,
  `livreresume` text,
  `livrenbvente` int(11) DEFAULT NULL,
  `livreparution` datetime DEFAULT NULL,
  `livresommaire` text,
  `livrecouverture` varchar(20) DEFAULT NULL,
  `livrestock` int(6) DEFAULT NULL,
  `livreprix` float DEFAULT NULL,
  `livrestockalerte` int(11) DEFAULT NULL,
  `livreediteur` varchar(20) DEFAULT NULL,
  `livreetat` varchar(50) DEFAULT 'nouveaut�',
  `categorieid` int(11) NOT NULL,
  PRIMARY KEY (`livreid`),
  KEY `FK_Livre_categorieid` (`categorieid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Contenu de la table `livre`
--

INSERT INTO `livre` (`livreid`, `livretitre`, `livreresume`, `livrenbvente`, `livreparution`, `livresommaire`, `livrecouverture`, `livrestock`, `livreprix`, `livrestockalerte`, `livreediteur`, `livreetat`, `categorieid`) VALUES
(1, 'livre1', 'resume du livre 1 ^fvonef�blon^sfn\r\nqfbv q�pdfi nq�ldfbn�qdlvn ', 1, '2011-04-14 13:52:04', '1 un\r\n2 deux\r\n3 trois', 'mag100.jpg', 0, 10, 10, '10', 'en stock', 2),
(2, 'livre2', 'fdbdogibghih', 2, '2011-04-04 14:27:50', 'rogiuehpgih', 'mag101.jpg', 0, 10, 10, 'mi', '� Venir', 2),
(3, 'livre3', 'efbodsibldkbh', 2, '2011-04-05 14:28:24', 'dfyvgzlbvhj', 'mag102.jpg', 5, 10, 10, 'toi', 'Nouveaut�', 2),
(4, 'livre4', 'dfbvodfibg', 2, '2011-04-05 14:28:58', 'fbdbkjqdbhvlkdhbfvldkhbv', 'mag97.jpg', 10, 10, 10, 'lui', 'Nouveaut�', 2),
(12, 'One piece v57', 'apr�s le v56', 4, '2013-02-04 00:00:00', '', 'one piece 57.jpg', 20, 6.2, 5, 'glenat', 'en Stock', 3),
(13, 'Bleach v 42', 'apr�s le v 41', 6, '2013-02-03 00:00:00', '', 'bleach 42.jpg', 20, 6.1, 5, 'greant', 'en Stock', 3),
(14, 'Bersrk v34', 'apr�s le v33', 7, '2011-03-03 00:00:00', '', 'berserk 34.jpg', 20, 5.8, 5, 'grenat', 'en Stock', 3),
(15, 'Claymore v17', 'apr�s le v16', 3, '2013-03-04 00:00:00', '', 'claymore 17.jpg', 20, 6.1, 5, 'grenat', 'Nouveaut�', 3),
(16, 'Bikini', 'Lors d''une s�ance photo � Hawaii, une top-mod�le � la beaut� renversante dispara�t. Alarm�s par un �trange coup de fil nocturne, ses parents prennent le premier avion, sans imaginer une seule seconde le spectacle effroyable qui les attend. Ben Hawkins, reporter au Los Angeles Times, charg� de couvrir l''affaire, leur propose de mener l''enqu�te ensemble. Tr�s vite, le d�cor paradisiaque se transforme en enfer, et quand ils comprennent qu''ils affrontent un pervers psychopathe � l''app�tit insatiable, il est trop tard pour faire marche arri�re.', 20, '2011-04-05 00:00:00', '', 'bikini.jpg', 20, 6.95, 5, 'jc latt�s', 'Nouveaut�', 4),
(17, 'Le voisin', 'Un mari souvent absent. Un m�tier qui ne l''�panouit gu�re. Un quotidien banal. Colombe Barou est une femme sans histoires. Comment imaginer ce qui l''attend dans le charmant appartement o� elle vient d''emm�nager ? � l''�tage sup�rieur, un inconnu lui a d�clar� la guerre. Seule l''�paisseur d''un plancher la s�pare d�sormais de son pire ennemi... Quel prix est-elle pr�te � payer pour retrouver sommeil et s�r�nit� ? Gr�ce � un sc�nario implacable, Tatiana de Rosnay installe une tension psychologique extr�me. Situant le danger � notre porte, elle r�veille nos terreurs intimes.\r\n\r\nRien ne se passe comme le lecteur habitu� aux films d&#8217;horreur ou aux com�dies romantiques pourrait s&#8217;y attendre : entre thriller domestique, conflit intime et roman initiatique, l&#8217;auteure brouille les cartes et conduit son histoire vers une issue aussi subtile qu&#8217;inattendue. Karine Papillaud, Le Point.', 15, '2011-02-03 00:00:00', '', 'le voisin.jpg', 20, 6.5, 5, 'h�loise', 'en R�approvisionnement', 4),
(18, 'HHhH', 'Prague, 1942, op�ration � Anthropo�de � : deux parachutistes tch�ques sont charg�s par Londres d''assassiner Reinhard Heydrich, le chef de la Gestapo et des services secrets nazis, le planificateur de la Solution finale, le � bourreau de Prague �. Heydrich, le bras droit d''Himmler. Chez les SS, on dit de lui : � HHhH �. Himmlers Hirn hei&#946;t Heydrich &#8211; le cerveau d''Himmler s''appelle Heydrich. Dans ce livre, les faits relat�s comme les personnages sont authentiques. Pourtant, une autre guerre se fait jour, celle que livre la fiction romanesque � la v�rit� historique. L''auteur doit r�sister � la tentation de romancer. Il faut bien, cependant, mener l''histoire � son terme&#8230;\r\n\r\nUn �crivain qui accomplit la prouesse d&#8217;� la fois narrer l&#8217;Histoire authentique, tout en ins�rant sa vision du monde [&#8230;].On trouve de tout dans HHhH : on y cite Tarantino, Chaplin, Bogart, les films de Fritz Lang et de Douglas Sirk. Philippe Labro, Le Figaro.', 0, '2011-04-05 00:00:00', '', 'HHhH.jpg', 20, 7.5, 5, 'grasset', '� Venir', 5),
(20, 'La prophetie', 'a', 20, '2011-04-05 00:00:00', '', '2012.jpg', 20, 7, 5, '', 'en Stock', 1),
(21, 'Boomerang', 'Sa soeur �tait sur le point de lui r�v�ler un secret... et c&#8217;est l&#8217;accident. Elle est gri�vement bless�e. Seul, l&#8217;angoisse au ventre, alors qu&#8217;il attend qu&#8217;elle sorte du bloc op�ratoire, Antoine fait le bilan de son existence : sa femme l&#8217;a quitt�, ses ados lui �chappent, son m�tier l&#8217;ennuie et son vieux p�re le tyrannise. Comment en est-il arriv� l� ? Et surtout, quelle terrible confidence sa cadette s&#8217;appr�tait-elle � lui faire ? Entre suspense, com�die et �motion, Boomerang brosse le portrait d&#8217;un homme bouleversant, qui nous fait rire et nous serre le coeur. D�j� traduit en plusieurs langues, ce roman conna�t le m�me succ�s international que Elle s&#8217;appelait Sarah.\r\n\r\nL&#8217;auteur s&#8217;inscrit dans la lign�e des romanci�res � succ�s, les Anna Gavalda, Katherine Pancol et Muriel Barbery dont les histoires vous tiennent en haleine. Fran�oise Dargent, Le Figaro litt�raire.\r\n\r\nUn roman qui se lit d&#8217;une traite tant Tatiana de Rosnay a le sens de la narration et du suspense, m�me si le thriller s&#8217;accompagne toujours chez l&#8217;auteur de profondeur psychologique. �milie Grangeray, Le Monde des livres.', 0, '2010-07-04 00:00:00', '', 'boomerang.jpg', 20, 6.95, 5, 'H�lo�se d''Ormesson', 'Nouveaut�', 6),
(22, 'World of Warcraft', '', 0, '2011-01-02 00:00:00', '', 'WoW.jpg', 20, 6.5, 5, '', 'en Stock', 1),
(23, 'Etoiles', 'Enoch Wallace est un garde-barri�re d''un genre bien particulier. Sa maison, �trange b�tisse plant�e au sein du Wisconsin, est un lieu de transit pour voyageurs interstellaires.\r\nLe temps n''ayant pas de prise sur la station et sur son gardien, il lui devient difficile de passer inaper�u...\r\nSon amiti� pour Lucy, une jeune sourde muette en butte � la violence rustique de sa famille, ne va pas simplifier les choses...\r\nUn r�cit profond�ment humaniste et tol�rant, o� la po�sie des �toiles rejoint celle, plus prosa�que, de la nature.', 0, '1999-05-01 00:00:00', '', 'carrefour.jpg', 20, 4.95, 5, 'a', 'en Stock', 1),
(24, 'Toi l''immortel', 'La catastrophe atomique des Trois jours n''a pas seulement d�truit � peu pr�s toute trace des civilisations continentales ; elle a �galement provoqu� l''exode de la plupart des Terriens survivants sur les plan�tes de la Conf�d�ration v�gane, et consid�rablement augment� l''esp�rance de vie de quelques hommes. Conrad Nomikos est l''un d''entre eux. Nul ne sait son �ge, pas m�me son amie Cassandre avec qui il vit sur une �le grecque miraculeusement pr�serv�e du cataclysme. Nomikos est aussi le conservateur des ruines de la Terre ; � ce titre, il va servir de guide � Cort Myshtigo, un V�gan venu visiter les d�combres de la plan�te sous le pr�texte d''une �tude historique. Or quelqu''un tente d''assassiner Nomikos. Puis le V�gan, � son tour, est pris pour cible. Que cache r�ellement le voyage de Myshtigo ? Qui donc peut ourdir un complot pour une Terre encore en grande partie radioactive ? ', 0, '2004-06-12 00:00:00', '', 'immortel.gif', 20, 5.89, 5, '', 'en Stock', 1);

-- --------------------------------------------------------

--
-- Structure de la table `parametres`
--

CREATE TABLE IF NOT EXISTS `parametres` (
  `paraId` int(11) NOT NULL AUTO_INCREMENT,
  `paranblivrepage` int(11) NOT NULL,
  `paranblivreaccueil` int(11) DEFAULT NULL,
  `paraimage` tinyint(1) DEFAULT NULL,
  `parastockdefault` int(11) DEFAULT NULL,
  `paraalertedefault` int(11) DEFAULT NULL,
  PRIMARY KEY (`paraId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `parametres`
--

INSERT INTO `parametres` (`paraId`, `paranblivrepage`, `paranblivreaccueil`, `paraimage`, `parastockdefault`, `paraalertedefault`) VALUES
(1, 5, 10, 0, 20, 5);

--
-- Contraintes pour les tables export�es
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
