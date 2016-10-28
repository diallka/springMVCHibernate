CREATE TABLE `Person` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `age` int(2) NOT NULL,
  `country` varchar(20) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
--	Moteur de stockage innoDB permet des transactions 
--	ACID (atomiques, cohérentes, isolées et durables), 
--	ainsi que la gestion des clés étrangères 
--	(avec vérification de la cohérence).

