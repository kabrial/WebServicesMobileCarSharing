-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Ven 19 Janvier 2018 à 21:11
-- Version du serveur :  5.7.20-0ubuntu0.16.04.1
-- Version de PHP :  7.0.22-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `carsharing`
--

-- --------------------------------------------------------

--
-- Structure de la table `child`
--

CREATE TABLE `child` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `child`
--

INSERT INTO `child` (`id`, `name`, `group_id`, `parent_id`) VALUES
(1, 'Enfant 1', 1, 1),
(2, 'Enfant 2', 2, 1),
(3, 'Enfant 3', 3, 1),
(4, 'Enfant 4', 4, 1),
(5, 'Enfant 5', 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `excursion`
--

CREATE TABLE `excursion` (
  `id` bigint(20) NOT NULL,
  `days` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `excursion`
--

INSERT INTO `excursion` (`id`, `days`, `name`) VALUES
(1, 3, 'Voyage a Barcelone');

-- --------------------------------------------------------

--
-- Structure de la table `excursiongroup`
--

CREATE TABLE `excursiongroup` (
  `id_excursion` bigint(20) NOT NULL,
  `id_group` bigint(20) NOT NULL,
  `excursion_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `excursiongroup`
--

INSERT INTO `excursiongroup` (`id_excursion`, `id_group`, `excursion_id`, `group_id`) VALUES
(1, 1, 1, 1),
(1, 2, 1, 2),
(1, 3, 1, 3),
(1, 4, 1, 4),
(1, 5, 1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `groups`
--

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `groups`
--

INSERT INTO `groups` (`id`, `name`) VALUES
(1, 'beavers'),
(2, 'cubs'),
(3, 'scouts'),
(4, 'explorer scouts'),
(5, 'scout network');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(0, 'ADMIN'),
(1, 'USER');

-- --------------------------------------------------------

--
-- Structure de la table `trip`
--

CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL,
  `date_depart` varchar(255) DEFAULT NULL,
  `number_places` bigint(20) NOT NULL,
  `places_reserved` bigint(20) NOT NULL,
  `excursion_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `trip`
--

INSERT INTO `trip` (`id`, `date_depart`, `number_places`, `places_reserved`, `excursion_id`, `parent_id`) VALUES
(1, '2018-01-19', 5, 3, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `tripchild`
--

CREATE TABLE `tripchild` (
  `id_child` bigint(20) NOT NULL,
  `id_trip` bigint(20) NOT NULL,
  `child_id` bigint(20) DEFAULT NULL,
  `trip_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tripchild`
--

INSERT INTO `tripchild` (`id_child`, `id_trip`, `child_id`, `trip_id`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `tripparent`
--

CREATE TABLE `tripparent` (
  `id_parent` bigint(20) NOT NULL,
  `id_trip` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `trip_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tripparent`
--

INSERT INTO `tripparent` (`id_parent`, `id_trip`, `parent_id`, `trip_id`) VALUES
(1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `userrole` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `password`, `username`, `userrole`) VALUES
(1, 'Kevin', 'ABRIAL', '$2a$10$34pXuGy/IARbUjDFA2lXI.phbiFY0TJ9aiDfLzEsrcz.prPH0FW5.', 'useradmin', 'ADMIN'),
(2, 'Amine', 'IDIR', '$2a$10$y/X/8kD/FI7URVMbD39G7e3xMXkj8sCXeBOuOvH6sCg2eKuikdEsW', 'useramine', 'USER'),
(3, 'Alexis', 'BARTHELEMY', '$2a$10$0j5FTl.8gAHkT5paVuh.xuk9QlFNJ5t2G74O29.uvC2NhWCcLUl5W', 'useralexis', 'USER');

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 0),
(2, 1),
(3, 1);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `child`
--
ALTER TABLE `child`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9dmg29jc4ny5pi1clrja3l3cg` (`group_id`),
  ADD KEY `FK2hhx1fdmwt995qgrr4p33pgsq` (`parent_id`);

--
-- Index pour la table `excursion`
--
ALTER TABLE `excursion`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `excursiongroup`
--
ALTER TABLE `excursiongroup`
  ADD PRIMARY KEY (`id_excursion`,`id_group`),
  ADD KEY `FKo7u8iic2qs89was44h86dvx7o` (`excursion_id`),
  ADD KEY `FKexg3sdeppxnjacsqgrbek6bxn` (`group_id`);

--
-- Index pour la table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdxnmx0jtnmpom1ghmnctbs1fy` (`excursion_id`),
  ADD KEY `FKp8xux57fl4yw15v66rd3nj69t` (`parent_id`);

--
-- Index pour la table `tripchild`
--
ALTER TABLE `tripchild`
  ADD PRIMARY KEY (`id_child`,`id_trip`),
  ADD KEY `FK6fq3emv4copiuhsb9mnhi0ta5` (`child_id`),
  ADD KEY `FKeljehgfsabrm0o3wa9rcnd8ew` (`trip_id`);

--
-- Index pour la table `tripparent`
--
ALTER TABLE `tripparent`
  ADD PRIMARY KEY (`id_parent`,`id_trip`),
  ADD KEY `FK96dbras56bvgtlj6n9jytii4a` (`parent_id`),
  ADD KEY `FK4sb5ixs6jvt1qjgst3dxksk9o` (`trip_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `child`
--
ALTER TABLE `child`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `excursion`
--
ALTER TABLE `excursion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `groups`
--
ALTER TABLE `groups`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `trip`
--
ALTER TABLE `trip`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `child`
--
ALTER TABLE `child`
  ADD CONSTRAINT `FK2hhx1fdmwt995qgrr4p33pgsq` FOREIGN KEY (`parent_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK9dmg29jc4ny5pi1clrja3l3cg` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`);

--
-- Contraintes pour la table `excursiongroup`
--
ALTER TABLE `excursiongroup`
  ADD CONSTRAINT `FKexg3sdeppxnjacsqgrbek6bxn` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `FKo7u8iic2qs89was44h86dvx7o` FOREIGN KEY (`excursion_id`) REFERENCES `excursion` (`id`);

--
-- Contraintes pour la table `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `FKdxnmx0jtnmpom1ghmnctbs1fy` FOREIGN KEY (`excursion_id`) REFERENCES `excursion` (`id`),
  ADD CONSTRAINT `FKp8xux57fl4yw15v66rd3nj69t` FOREIGN KEY (`parent_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `tripchild`
--
ALTER TABLE `tripchild`
  ADD CONSTRAINT `FK6fq3emv4copiuhsb9mnhi0ta5` FOREIGN KEY (`child_id`) REFERENCES `child` (`id`),
  ADD CONSTRAINT `FKeljehgfsabrm0o3wa9rcnd8ew` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`);

--
-- Contraintes pour la table `tripparent`
--
ALTER TABLE `tripparent`
  ADD CONSTRAINT `FK4sb5ixs6jvt1qjgst3dxksk9o` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`),
  ADD CONSTRAINT `FK96dbras56bvgtlj6n9jytii4a` FOREIGN KEY (`parent_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
