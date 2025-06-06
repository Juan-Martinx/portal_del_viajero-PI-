SET NAMES utf8mb4;
CREATE DATABASE IF NOT EXISTS `bdpdv` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE  `bdpdv`;

START TRANSACTION;

--
-- Base de datos: `bdpdv`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento`
--

CREATE TABLE `alojamiento` (
  `id_alojamiento` bigint(20) NOT NULL,
  `num_plaza_max` int(11) NOT NULL,
  `num_plaza_min` int(11) NOT NULL,
  `num_precio_noche` double NOT NULL,
  `txt_descripcion` varchar(255) NOT NULL,
  `txt_nombre` varchar(50) NOT NULL,
  `id_usuario` bigint(20) NOT NULL
);

--
-- Volcado de datos para la tabla `alojamiento`
--

INSERT INTO `alojamiento` (`id_alojamiento`, `num_plaza_max`, `num_plaza_min`, `num_precio_noche`, `txt_descripcion`, `txt_nombre`, `id_usuario`) VALUES
(12, 5, 1, 52, 'Casa Rural situada en las montañas de la región Norte de España, con grandes vistas y muy buen sitio para disfrutar de la tranquilidad.', 'Disfruta de la Montaña Norteña', 25),
(13, 6, 1, 48, 'Casa Rural aislada de ruidos exteriores situada en la mitad de la montaña, perfecta para disfrutar de la tranquilidad y paz del campo.', 'Refugio de Montaña con Relajación', 25),
(14, 4, 1, 45, 'Casa rural situada en al montaña cerca de una senda para ciclistas, la cuál permite que los huéspedes puedan disfrutar de una alegre visita con sus bicicletas. Además, cuenta con cocina equipada.', 'Disfruta de una nueva experiencia', 25),
(15, 7, 1, 49, 'Casa Rural diseñada cerca de la Costa del Sur, la cual permite disfrutar de unas alegres vacaciones y del sol tan alegre del Sur de España.', 'Escápate al paraíso del mar', 26),
(16, 9, 4, 59, 'La mejor casa rural para escaparte de vacaciones a la costa, la cual consta con un gran clima que acompaña, relajación y brisa marina.', 'Descubre la magia de la costa', 26),
(17, 10, 5, 67, 'Casas Rural situada en la montaña, en la cuál puedes disfrutar de la tranquilidad y el buen tiempo al mismo tiempo que disfrutas de la brisa marina que proviene de la costa.', 'Refugio Costero', 26),
(18, 8, 3, 45, 'Casa rural ideal para relajarte, con vistas al mar y con el sonido del oleaje marino', 'Escapada a orillas del mar', 27),
(19, 6, 2, 52, 'Casa rural de gran dimensiones, apta para disfrutar en familia de unas vacaciones espectaculares.', 'Escapada al lujoso mar', 27),
(20, 10, 3, 42, 'Casa Rural situada en la sierra de Cádiz, pero también está situada cerca de la playa', 'Aventura y relax junto al mar', 27),
(21, 15, 3, 46, 'Casa rural apropiada para el disfrute de toda la familia en la montaña, habilitada para mascotas.', 'Paraíso de montaña', 28),
(22, 6, 2, 39, 'Cuenta con grandes dimensiones, aislada en la montaña de todo el ruido y aislada de los ruidos de la ciudad.', 'Conectate con la naturaleza', 28),
(23, 8, 2, 35, 'Escapada rural en la montaña con todas las comodidades disponibles para disfrutar de la naturaleza de la mejor forma posible.', 'Escapada rural', 28),
(24, 5, 2, 45, 'Vistas al mar, cerca de la ciudad y cuenta con todos los factores posibles par a disfrutar de unas vacaciones maravillosas.', 'Despierta con vistas al mar', 29),
(25, 8, 1, 45, 'Disfruta de las mejores vacaciones posibles, con vistas al mar y un alojamiento impecable.', 'Vacaciones con vistas al mar', 29),
(26, 8, 2, 54, 'Disfruta de una encantadora experiencia en las costas de Almería.', 'Escapada con encanto costero', 29),
(27, 6, 1, 50, 'Disfruta de la mejor experiencia junto a tu familia en la montaña del norte.', 'La mejor experiencia de tu vida', 30),
(28, 10, 6, 24, 'Disfruta de la tranquilidad que aporta la naturaleza.', 'Vive en un paraíso en vacaciones', 30),
(29, 15, 6, 36, 'Casa rural que comparte todos los lujos para que puedas disfrutar junto a tu familia de las mejores vacaciones.', 'Paraíso escondido', 30),
(30, 8, 1, 54, 'Viaje con grandes comodidades e instalaciones para personas de todas edades.', 'Vaciones inolvidables', 31),
(31, 15, 5, 45, 'Ocasión inolvidable para poder disfrutar en familia de más tiempo.', 'Refugio familiar', 31),
(32, 6, 2, 45, 'Casa Rural ecológica, rodeado completamente de naturaleza.', 'Casa Rural Ecológica', 31),
(33, 10, 3, 35, 'Casa rural situada en la cima del monte a unos 5 kilómetros de la ciudad.', 'Descubre la magia de la naturaleza', 32),
(34, 5, 2, 46, 'Casa rural situada en medio de la naturaleza.', 'Escapada a lo natural', 32),
(35, 10, 2, 45, 'Situada en la montaña.', 'Vacaciones pasional', 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento_comodidad_alojamiento`
--

CREATE TABLE `alojamiento_comodidad_alojamiento` (
  `id_alojamiento_comodidad_alojamiento` bigint(20) NOT NULL,
  `id_alojamiento` bigint(20) DEFAULT NULL,
  `id_comodidad_alojamiento` bigint(20) NOT NULL
);

--
-- Volcado de datos para la tabla `alojamiento_comodidad_alojamiento`
--

INSERT INTO `alojamiento_comodidad_alojamiento` (`id_alojamiento_comodidad_alojamiento`, `id_alojamiento`, `id_comodidad_alojamiento`) VALUES
(52, 12, 27),
(53, 12, 28),
(54, 12, 25),
(55, 12, 33),
(56, 12, 30),
(57, 12, 64),
(58, 12, 52),
(59, 12, 66),
(60, 12, 59),
(61, 12, 29),
(62, 12, 31),
(63, 13, 27),
(64, 13, 44),
(65, 13, 51),
(66, 13, 40),
(67, 13, 28),
(68, 13, 43),
(69, 13, 25),
(70, 13, 35),
(71, 13, 38),
(72, 13, 54),
(73, 13, 52),
(74, 13, 29),
(75, 13, 55),
(76, 14, 27),
(77, 14, 34),
(78, 14, 64),
(79, 14, 28),
(80, 14, 25),
(81, 14, 35),
(82, 14, 30),
(83, 14, 54),
(84, 14, 66),
(85, 14, 52),
(86, 14, 29),
(87, 14, 31),
(88, 14, 39),
(89, 15, 32),
(90, 15, 44),
(91, 15, 41),
(92, 15, 34),
(93, 15, 40),
(94, 15, 26),
(95, 15, 45),
(96, 15, 59),
(97, 15, 58),
(98, 15, 28),
(99, 15, 43),
(100, 15, 25),
(101, 15, 33),
(102, 15, 35),
(103, 15, 38),
(104, 15, 30),
(105, 15, 54),
(106, 15, 52),
(107, 15, 42),
(108, 15, 57),
(109, 15, 29),
(110, 15, 55),
(111, 16, 32),
(112, 16, 44),
(113, 16, 41),
(114, 16, 34),
(115, 16, 40),
(116, 16, 26),
(117, 16, 45),
(118, 16, 59),
(119, 16, 46),
(120, 16, 58),
(121, 16, 63),
(122, 16, 65),
(123, 16, 28),
(124, 16, 43),
(125, 16, 25),
(126, 16, 33),
(127, 16, 35),
(128, 16, 38),
(129, 16, 30),
(130, 16, 42),
(131, 16, 57),
(132, 16, 29),
(133, 17, 44),
(134, 17, 41),
(135, 17, 34),
(136, 17, 59),
(137, 17, 46),
(138, 17, 28),
(139, 17, 25),
(140, 17, 33),
(141, 17, 30),
(142, 17, 42),
(143, 17, 50),
(144, 17, 57),
(145, 17, 55),
(146, 17, 27),
(147, 17, 32),
(148, 17, 40),
(149, 17, 26),
(150, 17, 45),
(151, 17, 58),
(152, 17, 63),
(153, 17, 43),
(154, 17, 35),
(155, 17, 54),
(156, 17, 52),
(157, 17, 66),
(158, 17, 29),
(159, 17, 53),
(160, 18, 44),
(161, 18, 41),
(162, 18, 34),
(163, 18, 56),
(164, 18, 40),
(165, 18, 26),
(166, 18, 59),
(167, 18, 58),
(168, 18, 28),
(169, 18, 43),
(170, 18, 30),
(171, 18, 52),
(172, 18, 29),
(173, 19, 28),
(174, 19, 44),
(175, 19, 41),
(176, 19, 43),
(177, 19, 25),
(178, 19, 35),
(179, 19, 34),
(180, 19, 26),
(181, 19, 45),
(182, 20, 41),
(183, 20, 60),
(184, 20, 34),
(185, 20, 56),
(186, 20, 26),
(187, 20, 36),
(188, 20, 62),
(189, 20, 61),
(190, 20, 28),
(191, 20, 25),
(192, 20, 33),
(193, 20, 38),
(194, 20, 54),
(195, 20, 66),
(196, 20, 37),
(197, 20, 39),
(198, 21, 27),
(199, 21, 47),
(200, 21, 43),
(201, 21, 25),
(202, 21, 30),
(203, 21, 56),
(204, 21, 40),
(205, 21, 26),
(206, 21, 62),
(207, 21, 66),
(208, 21, 42),
(209, 21, 53),
(210, 22, 27),
(211, 22, 44),
(212, 22, 35),
(213, 22, 54),
(214, 22, 56),
(215, 22, 64),
(216, 22, 66),
(217, 22, 42),
(218, 22, 29),
(219, 22, 39),
(220, 22, 37),
(221, 22, 55),
(222, 23, 27),
(223, 23, 32),
(224, 23, 34),
(225, 23, 56),
(226, 23, 36),
(227, 23, 58),
(228, 23, 28),
(229, 23, 25),
(230, 23, 33),
(231, 23, 54),
(232, 23, 57),
(233, 23, 29),
(234, 23, 31),
(235, 23, 37),
(236, 23, 39),
(237, 23, 55),
(238, 24, 32),
(239, 24, 44),
(240, 24, 41),
(241, 24, 60),
(242, 24, 34),
(243, 24, 40),
(244, 24, 26),
(245, 24, 45),
(246, 24, 59),
(247, 24, 61),
(248, 24, 58),
(249, 24, 28),
(250, 24, 43),
(251, 24, 25),
(252, 24, 33),
(253, 24, 35),
(254, 24, 30),
(255, 24, 42),
(256, 24, 57),
(257, 24, 29),
(258, 25, 27),
(259, 25, 25),
(260, 25, 51),
(261, 25, 54),
(262, 25, 56),
(263, 25, 26),
(264, 25, 45),
(265, 25, 52),
(266, 25, 50),
(267, 25, 46),
(268, 26, 44),
(269, 26, 41),
(270, 26, 43),
(271, 26, 45),
(272, 26, 46),
(273, 26, 58),
(274, 27, 28),
(275, 27, 25),
(276, 27, 35),
(277, 27, 34),
(278, 27, 54),
(279, 27, 26),
(280, 27, 64),
(281, 27, 66),
(282, 27, 52),
(283, 27, 29),
(284, 27, 31),
(285, 27, 53),
(286, 28, 27),
(287, 28, 28),
(288, 28, 25),
(289, 28, 33),
(290, 28, 40),
(291, 28, 52),
(292, 28, 50),
(293, 28, 31),
(294, 28, 55),
(295, 29, 27),
(296, 29, 25),
(297, 29, 35),
(298, 29, 54),
(299, 29, 45),
(300, 29, 59),
(301, 29, 31),
(302, 29, 37),
(303, 29, 55),
(304, 30, 27),
(305, 30, 60),
(306, 30, 34),
(307, 30, 56),
(308, 30, 26),
(309, 30, 62),
(310, 30, 64),
(311, 30, 45),
(312, 30, 65),
(313, 30, 63),
(314, 30, 28),
(315, 30, 25),
(316, 30, 54),
(317, 30, 57),
(318, 30, 29),
(319, 30, 31),
(320, 30, 55),
(321, 31, 60),
(322, 31, 34),
(323, 31, 26),
(324, 31, 45),
(325, 31, 59),
(326, 31, 61),
(327, 31, 46),
(328, 31, 28),
(329, 31, 25),
(330, 31, 33),
(331, 31, 35),
(332, 31, 30),
(333, 31, 54),
(334, 31, 57),
(335, 31, 29),
(336, 31, 39),
(337, 32, 27),
(338, 32, 44),
(339, 32, 41),
(340, 32, 51),
(341, 32, 26),
(342, 32, 47),
(343, 32, 33),
(344, 32, 35),
(345, 32, 30),
(346, 32, 52),
(347, 32, 50),
(348, 32, 29),
(349, 32, 31),
(350, 32, 53),
(351, 32, 55),
(352, 33, 27),
(353, 33, 41),
(354, 33, 64),
(355, 33, 45),
(356, 33, 46),
(357, 33, 58),
(358, 33, 28),
(359, 33, 25),
(360, 33, 35),
(361, 33, 54),
(362, 33, 52),
(363, 33, 31),
(364, 33, 53),
(365, 34, 47),
(366, 34, 28),
(367, 34, 51),
(368, 34, 56),
(369, 34, 26),
(370, 34, 52),
(371, 34, 29),
(372, 34, 53),
(373, 35, 63),
(374, 35, 65),
(375, 35, 28),
(376, 35, 44),
(377, 35, 25),
(378, 35, 35),
(379, 35, 40),
(380, 35, 62),
(381, 35, 66),
(382, 35, 39);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento_dias_ocupados`
--

CREATE TABLE `alojamiento_dias_ocupados` (
  `id_alojamiento_dias_ocupados` bigint(20) NOT NULL,
  `anyo` int(11) NOT NULL,
  `dia` int(11) NOT NULL,
  `mes` int(11) NOT NULL,
  `id_alojamiento` bigint(20) DEFAULT NULL
);

--
-- Volcado de datos para la tabla `alojamiento_dias_ocupados`
--

INSERT INTO `alojamiento_dias_ocupados` (`id_alojamiento_dias_ocupados`, `anyo`, `dia`, `mes`, `id_alojamiento`) VALUES
(21, 2024, 1, 7, 13),
(22, 2024, 2, 7, 13),
(23, 2024, 3, 7, 13),
(24, 2024, 4, 7, 13),
(25, 2024, 5, 7, 13),
(26, 2024, 6, 7, 13),
(27, 2024, 7, 7, 29),
(28, 2024, 8, 7, 29),
(29, 2024, 9, 7, 29),
(30, 2024, 10, 7, 29),
(31, 2024, 11, 7, 29),
(32, 2024, 12, 7, 29),
(33, 2024, 13, 7, 29),
(34, 2024, 14, 7, 29),
(35, 2024, 15, 7, 29),
(36, 2024, 16, 7, 29),
(37, 2024, 17, 7, 29),
(38, 2024, 18, 7, 29),
(39, 2024, 19, 7, 29),
(40, 2024, 15, 7, 13),
(41, 2024, 16, 7, 13),
(42, 2024, 17, 7, 13),
(43, 2024, 18, 7, 13),
(44, 2024, 19, 7, 13),
(45, 2024, 22, 7, 15),
(46, 2024, 23, 7, 15),
(47, 2024, 24, 7, 15),
(48, 2024, 25, 7, 15),
(49, 2024, 26, 7, 15),
(50, 2024, 27, 7, 15),
(51, 2024, 28, 7, 15),
(52, 2024, 29, 7, 15),
(53, 2024, 1, 7, 16),
(54, 2024, 2, 7, 16),
(55, 2024, 3, 7, 16),
(56, 2024, 4, 7, 16),
(57, 2024, 5, 7, 16),
(58, 2024, 6, 7, 16),
(59, 2024, 7, 7, 16),
(60, 2024, 8, 7, 16),
(61, 2024, 9, 7, 16),
(62, 2024, 10, 7, 16),
(63, 2024, 11, 7, 16),
(64, 2024, 12, 7, 16),
(65, 2024, 13, 7, 16),
(66, 2024, 14, 7, 16),
(67, 2024, 15, 7, 16),
(68, 2024, 16, 7, 16),
(69, 2024, 17, 7, 16),
(70, 2024, 18, 7, 16),
(71, 2024, 19, 7, 16),
(72, 2024, 20, 7, 16),
(73, 2024, 21, 7, 16),
(74, 2024, 22, 7, 16),
(75, 2024, 23, 7, 16),
(76, 2024, 24, 7, 16),
(77, 2024, 25, 7, 16),
(78, 2024, 26, 7, 16),
(79, 2024, 27, 7, 16),
(80, 2024, 28, 7, 16),
(81, 2024, 29, 7, 16),
(82, 2024, 1, 8, 17),
(83, 2024, 2, 8, 17),
(84, 2024, 3, 8, 17),
(85, 2024, 1, 7, 18),
(86, 2024, 2, 7, 18),
(87, 2024, 3, 7, 18),
(88, 2024, 4, 7, 18),
(89, 2024, 5, 7, 18),
(90, 2024, 6, 7, 18),
(91, 2024, 9, 7, 19),
(92, 2024, 10, 7, 19),
(93, 2024, 11, 7, 19),
(94, 2024, 12, 7, 19),
(95, 2024, 13, 7, 19),
(96, 2024, 9, 7, 18),
(97, 2024, 10, 7, 18),
(98, 2024, 11, 7, 18),
(99, 2024, 12, 7, 18),
(100, 2024, 13, 7, 18),
(101, 2024, 1, 7, 19),
(102, 2024, 2, 7, 19),
(103, 2024, 3, 7, 19),
(104, 2024, 4, 7, 19),
(105, 2024, 5, 7, 19),
(106, 2024, 6, 7, 19),
(107, 2024, 1, 7, 20),
(108, 2024, 2, 7, 20),
(109, 2024, 3, 7, 20),
(110, 2024, 4, 7, 20),
(111, 2024, 5, 7, 20),
(112, 2024, 6, 7, 20),
(113, 2024, 1, 7, 21),
(114, 2024, 2, 7, 21),
(115, 2024, 3, 7, 21),
(116, 2024, 4, 7, 21),
(117, 2024, 5, 7, 21),
(118, 2024, 6, 7, 21),
(119, 2024, 1, 7, 22),
(120, 2024, 2, 7, 22),
(121, 2024, 3, 7, 22),
(122, 2024, 4, 7, 22),
(123, 2024, 5, 7, 22),
(124, 2024, 6, 7, 22),
(125, 2024, 7, 7, 22),
(126, 2024, 8, 7, 22),
(127, 2024, 9, 7, 22),
(128, 2024, 10, 7, 22),
(129, 2024, 11, 7, 22),
(130, 2024, 12, 7, 22),
(131, 2024, 13, 7, 22),
(132, 2024, 16, 7, 23),
(133, 2024, 17, 7, 23),
(134, 2024, 18, 7, 23),
(135, 2024, 19, 7, 23),
(136, 2024, 20, 7, 23),
(137, 2024, 21, 7, 23),
(138, 2024, 22, 7, 23),
(139, 2024, 23, 7, 23),
(140, 2024, 24, 7, 23),
(141, 2024, 25, 7, 23),
(142, 2024, 26, 7, 23),
(143, 2024, 27, 7, 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler_alojamiento`
--

CREATE TABLE `alquiler_alojamiento` (
  `id_alquiler_alojamiento` bigint(20) NOT NULL,
  `fecha_fin_alquiler` date NOT NULL,
  `fecha_inicio_alquiler` date NOT NULL,
  `num_plazas_reservadas` int(11) NOT NULL,
  `precio_total_alquiler` double NOT NULL,
  `id_alojamiento` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL
);

--
-- Volcado de datos para la tabla `alquiler_alojamiento`
--

INSERT INTO `alquiler_alojamiento` (`id_alquiler_alojamiento`, `fecha_fin_alquiler`, `fecha_inicio_alquiler`, `num_plazas_reservadas`, `precio_total_alquiler`, `id_alojamiento`, `id_usuario`) VALUES
(4, '2024-07-07', '2024-07-01', 6, 288, 13, 33),
(5, '2024-07-20', '2024-07-07', 8, 468, 29, 33),
(6, '2024-07-20', '2024-07-15', 5, 240, 13, 34),
(7, '2024-07-30', '2024-07-22', 6, 392, 15, 34),
(8, '2024-07-30', '2024-07-01', 5, 1711, 16, 35),
(9, '2024-08-04', '2024-08-01', 6, 201, 17, 35),
(10, '2024-07-07', '2024-07-01', 5, 270, 18, 36),
(11, '2024-07-14', '2024-07-09', 6, 260, 19, 36),
(12, '2024-07-14', '2024-07-09', 5, 225, 18, 37),
(13, '2024-07-07', '2024-07-01', 3, 312, 19, 37),
(14, '2024-07-07', '2024-07-01', 4, 252, 20, 38),
(15, '2024-07-07', '2024-07-01', 4, 276, 21, 38),
(16, '2024-07-14', '2024-07-01', 6, 507, 22, 39),
(17, '2024-07-28', '2024-07-16', 4, 420, 23, 39);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorization_consent`
--

CREATE TABLE `authorization_consent` (
  `principal_name` varchar(255) NOT NULL,
  `registered_client_id` varchar(255) NOT NULL,
  `authorities` varchar(1000) DEFAULT NULL
);

--
-- Volcado de datos para la tabla `authorization_consent`
--

INSERT INTO `authorization_consent` (`principal_name`, `registered_client_id`, `authorities`) VALUES
('jmarcanofficial@gmail.com', 'client', 'SCOPE_openid_profile');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `require_proof_key` bit(1) NOT NULL
);

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`id`, `client_id`, `client_secret`, `require_proof_key`) VALUES
(1, 'client', '$2a$10$yjuryPs6dJxsDTTsfbLDgeKNAmeJzJVT2fZ/wKZI.gdsoNtdMsN4a', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_authentication_methods`
--

CREATE TABLE `client_authentication_methods` (
  `client_id` int(11) NOT NULL,
  `authentication_methods` varbinary(255) DEFAULT NULL
);

--
-- Volcado de datos para la tabla `client_authentication_methods`
--

INSERT INTO `client_authentication_methods` (`client_id`, `authentication_methods`) VALUES
(1, 0xaced0005737200436f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f72652e436c69656e7441757468656e7469636174696f6e4d6574686f6400000000000002620200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b7870740013636c69656e745f7365637265745f6261736963);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_authorization_grant_types`
--

CREATE TABLE `client_authorization_grant_types` (
  `client_id` int(11) NOT NULL,
  `authorization_grant_types` varbinary(255) DEFAULT NULL
);

--
-- Volcado de datos para la tabla `client_authorization_grant_types`
--

INSERT INTO `client_authorization_grant_types` (`client_id`, `authorization_grant_types`) VALUES
(1, 0xaced00057372003f6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f72652e417574686f72697a6174696f6e4772616e745479706500000000000002620200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b787074000d726566726573685f746f6b656e),
(1, 0xaced00057372003f6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f72652e417574686f72697a6174696f6e4772616e745479706500000000000002620200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b7870740012636c69656e745f63726564656e7469616c73),
(1, 0xaced00057372003f6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f72652e417574686f72697a6174696f6e4772616e745479706500000000000002620200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b7870740012617574686f72697a6174696f6e5f636f6465);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_redirect_uris`
--

CREATE TABLE `client_redirect_uris` (
  `client_id` int(11) NOT NULL,
  `redirect_uris` varchar(255) DEFAULT NULL
);

--
-- Volcado de datos para la tabla `client_redirect_uris`
--

INSERT INTO `client_redirect_uris` (`client_id`, `redirect_uris`) VALUES
(1, 'http://portaldelviajero.com/authorized');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_scopes`
--

CREATE TABLE `client_scopes` (
  `client_id` int(11) NOT NULL,
  `scopes` varchar(255) DEFAULT NULL
);

--
-- Volcado de datos para la tabla `client_scopes`
--

INSERT INTO `client_scopes` (`client_id`, `scopes`) VALUES
(1, 'openid');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comodidad_alojamiento`
--

CREATE TABLE `comodidad_alojamiento` (
  `id_comodidad_alojamiento` bigint(20) NOT NULL,
  `codigo_comodidad` varchar(50) NOT NULL,
  `icono_comodidad` varchar(255) DEFAULT NULL,
  `txt_descripcion` varchar(255) NOT NULL,
  `txt_nombre` varchar(50) NOT NULL,
  `id_tipo_comodidad` bigint(20) NOT NULL
);

--
-- Volcado de datos para la tabla `comodidad_alojamiento`
--

INSERT INTO `comodidad_alojamiento` (`id_comodidad_alojamiento`, `codigo_comodidad`, `icono_comodidad`, `txt_descripcion`, `txt_nombre`, `id_tipo_comodidad`) VALUES
(25, 'COM001', 'wifi', 'Las personas que alquilan esta casa rural contará con disponibilidad de Wi-Fi gratuito', 'Wi-Fi gratuito', 2),
(26, 'COM002', 'ac_unit', 'Casa Rural que cuenta con aire  acondicionado', 'Aire acondicionado', 2),
(27, 'COM003', 'wb_incandescent', 'Casa Rural que  cuenta con calefacción', 'Calefacción', 2),
(28, 'COM004', 'tv', 'Casa Rural que cuenta con televisión', 'Televisión', 2),
(29, 'COM005', 'local_laundry_service', 'Casa Rural que cuenta con lavadora', 'Lavadora', 2),
(30, 'COM006', 'local_cafe', 'Casa Rural que cuenta con cafetera incluida', 'Cafetera', 2),
(31, 'COM007', 'whatshot', 'Casa Rural con chimenea incluida', 'Chimenea', 2),
(32, 'COM008', 'delete', 'Casa Rural que cuenta con servicio de basura', 'Servicio de Busara', 2),
(33, 'COM009', 'smoke_free', 'Casa Rural en la que está prohibido fumar en su interior', 'Prohibido Fumar', 2),
(34, 'COM010', 'kitchen', 'Casa Rural que cuenta con frigorífico', 'Frigorífico', 2),
(35, 'COM011', 'restaurant', 'Casa Rural que cuenta con cocina equipada', 'Cocina equipada', 2),
(36, 'COM012', 'album', 'Casa Rural que cuenta con DVD y películas', 'DVD y Películas', 2),
(37, 'COM013', 'my_library_books', 'Casa Rural con libros', 'Libros', 2),
(38, 'COM014', 'music_video', 'Casa Rural con altavoz incluido', 'Altavoz', 2),
(39, 'COM015', 'accessible', 'Casa Rural con servicios habilitados para personas con movilidad reducida', 'Servicios Accesibles', 2),
(40, 'COM016', 'pets', 'Casa Rural en la que se permite la entrada de mascotas', 'Mascotas', 2),
(41, 'COM017', 'brightness_7', 'Casa Rural situada en una ubicación donde siempre hay sol', 'Días Soleados', 2),
(42, 'COM018', 'room_service', 'Casa Rural que cuenta con comida a domicilio', 'Comida a Domicilio', 2),
(43, 'COM019', 'airline_seat_individual_suite ', 'Casa Rural que cuenta con camas individuales', 'Cama Individual', 2),
(44, 'COM020', 'weekend', 'Casa Rural que cuenta con sofá', 'Sofá', 2),
(45, 'INS001', 'pool', 'Casa Rural que incluye piscina', 'Piscina', 1),
(46, 'INS002', 'hot_tub', 'Casa Rural que incluye jacuzzi', 'Jacuzzi', 1),
(47, 'INS003', 'spa', 'Casa Rural que incluye Spa', 'Spa', 1),
(50, 'INS004', 'fitness_center', 'Casa Rural que incluye gimnasio', 'Gimnasio', 1),
(51, 'INS005', 'golf_course', 'Casa Rural con campo de golf incluido', 'Campo de Golf', 1),
(52, 'INS006', 'local_parking', 'Casa Rural que cuenta con parking privado para sus clientes', 'Parking', 1),
(53, 'INS007', 'local_hospital', 'Casa Rural situada cerca de hospital', 'Hospital Cerca', 1),
(54, 'INS008', 'nature_people', 'Casa Rural que cuenta con jardín.', 'Jardín', 1),
(55, 'INS009', 'gamepad', 'Casa Rural que cuenta con una zona de juegos', 'Zona de Juegos', 1),
(56, 'INS010', 'local_restaurant', 'Casa Rural con restaurante cerca', 'Restaurante', 1),
(57, 'INS011', 'local_pharmacy', 'Casa Rural situada cerca de farmacias', 'Farmacia', 1),
(58, 'INS012', 'beach_access', 'Casa Rural situada cerca de la playa', 'Playa', 1),
(59, 'INS013', 'location_city', 'Casa Rural situada cerca de la ciudad', 'Cerca de Ciudad', 1),
(60, 'INS014', 'school', 'Casa Rural situada cerca de colegio', 'Colegio', 1),
(61, 'INS015', 'casino', 'Casino cerca de la Casa Rural', 'Casino', 1),
(62, 'INS016', 'train', 'Casa Rural cerca de la estación de tren', 'Tren Cerca', 1),
(63, 'INS017', 'local_gas_station', 'Gasolinera situada cerca de la Casa Rural', 'Gasolinera Cerca', 1),
(64, 'INS018', 'directions_bike', 'Cuanta ruta sendera para ciclistas bastante cerca', 'Ruta Ciclista', 1),
(65, 'INS019', 'local_grocery_store', 'Supermercado cerca de la Casa Rural', 'Supermercado', 1),
(66, 'INS020', 'terrain', 'Casa Rural situada en la montaña', 'Montaña', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `google_user`
--

CREATE TABLE `google_user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `family_name` varchar(255) DEFAULT NULL,
  `given_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagen_alojamiento`
--

CREATE TABLE `imagen_alojamiento` (
  `id_imagen_alojamiento` bigint(20) NOT NULL,
  `num_orden` int(11) NOT NULL,
  `url_datos_imagen` varchar(255) NOT NULL,
  `id_alojamiento` bigint(20) NOT NULL
);

--
-- Volcado de datos para la tabla `imagen_alojamiento`
--

INSERT INTO `imagen_alojamiento` (`id_imagen_alojamiento`, `num_orden`, `url_datos_imagen`, `id_alojamiento`) VALUES
(26, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 12),
(27, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 13),
(28, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 14),
(29, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 15),
(30, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 16),
(31, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 17),
(32, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 18),
(33, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 19),
(34, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 20),
(35, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 21),
(36, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 22),
(37, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 23),
(38, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 24),
(39, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 25),
(40, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 26),
(41, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 27),
(42, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 28),
(43, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 29),
(44, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 30),
(45, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 31),
(46, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 32),
(47, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 33),
(48, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 34),
(49, 0, 'http://portaldelviajero.com/pdv-backend/resource/media/public/default.jpg', 35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `id_perfil` bigint(20) NOT NULL,
  `cod_perfil` enum('OIDC_USER','PERFIL_ADMIN','PERFIL_CLIENTE','PERFIL_GESTOR') NOT NULL,
  `txt_perfil` varchar(255) NOT NULL
);

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`id_perfil`, `cod_perfil`, `txt_perfil`) VALUES
(1, 'PERFIL_ADMIN', 'Administrador'),
(2, 'PERFIL_GESTOR', 'Gestor'),
(3, 'PERFIL_CLIENTE', 'Cliente'),
(4, 'OIDC_USER', 'Usuario Google');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_comodidad`
--

CREATE TABLE `tipo_comodidad` (
  `id_tipo_comodidad` bigint(20) NOT NULL,
  `codigo_tipo_comodidad` varchar(50) NOT NULL,
  `txt_nombre` varchar(50) NOT NULL
);

--
-- Volcado de datos para la tabla `tipo_comodidad`
--

INSERT INTO `tipo_comodidad` (`id_tipo_comodidad`, `codigo_tipo_comodidad`, `txt_nombre`) VALUES
(1, 'INST', 'Instalaciones'),
(2, 'COM', 'Comodidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicacion_alojamiento`
--

CREATE TABLE `ubicacion_alojamiento` (
  `id_ubicacion_alojamiento` bigint(20) NOT NULL,
  `ciudad` varchar(60) DEFAULT NULL,
  `codigo_postal` int(11) NOT NULL,
  `latitud` varchar(255) DEFAULT NULL,
  `linea_direccion` varchar(255) NOT NULL,
  `longitud` varchar(255) DEFAULT NULL,
  `provincia` varchar(60) NOT NULL,
  `id_alojamiento` bigint(20) NOT NULL
);

--
-- Volcado de datos para la tabla `ubicacion_alojamiento`
--

INSERT INTO `ubicacion_alojamiento` (`id_ubicacion_alojamiento`, `ciudad`, `codigo_postal`, `latitud`, `linea_direccion`, `longitud`, `provincia`, `id_alojamiento`) VALUES
(12, NULL, 33070, NULL, 'Asturias, España', NULL, 'Asturias', 12),
(13, NULL, 33070, NULL, 'Calle Brisa, 48B', NULL, 'Asturias', 13),
(14, NULL, 33070, NULL, 'Aranjuez, 12', NULL, 'Asturias', 14),
(15, NULL, 11001, NULL, 'Calle Palma, 23', NULL, 'Cádiz', 15),
(16, NULL, 11001, NULL, 'Calle Rosa, 7', NULL, 'Cádiz', 16),
(17, NULL, 11001, NULL, 'Calle Flor, 12', NULL, 'Cádiz', 17),
(18, NULL, 11001, NULL, 'Calle Luna, 40', NULL, 'Cádiz', 18),
(19, NULL, 11001, NULL, 'Estepa, 21', NULL, 'Cádiz', 19),
(20, NULL, 11001, NULL, 'Calle sierra, 29', NULL, 'Cádiz', 20),
(21, NULL, 48795, NULL, 'Rosa, 76', NULL, 'Galicia', 21),
(22, NULL, 48571, NULL, 'Calle Vigüel, 12', NULL, 'Galicia', 22),
(23, NULL, 48571, NULL, 'Calla Volian, 27', NULL, 'Galicia', 23),
(24, NULL, 11001, NULL, 'Calle amapola, 12', NULL, 'Cádiz', 24),
(25, NULL, 11002, NULL, 'Calle Gálvez, 3', NULL, 'Málaga', 25),
(26, NULL, 11002, NULL, 'Calle Amapole, 6', NULL, 'Málaga', 26),
(27, NULL, 48571, NULL, 'Calla Colón, 4', NULL, 'Lugo', 27),
(28, NULL, 48571, NULL, 'Calle Colón, 34', NULL, 'Lugo', 28),
(29, NULL, 11002, NULL, 'Calle Amapola, 15', NULL, 'Lugo', 29),
(30, NULL, 11004, NULL, 'Calle Sol, 45', NULL, 'Sevilla', 30),
(31, NULL, 11004, NULL, 'Calle Sol, 34', NULL, 'Sevilla', 31),
(32, NULL, 11004, NULL, 'Calle Sol, 32', NULL, 'Sevilla', 32),
(33, NULL, 11003, NULL, 'Calle Rosal, 2', NULL, 'Córdoba', 33),
(34, NULL, 11003, NULL, 'Calle Rosal, 5', NULL, 'Córdoba', 34),
(35, NULL, 11003, NULL, 'Calle Rosal, 7', NULL, 'Córdoba', 35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `disabled` bit(1) NOT NULL,
  `expired` bit(1) NOT NULL,
  `locked` bit(1) NOT NULL,
  `num_telefono` int(11) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `txt_descripcion` varchar(255) DEFAULT NULL,
  `txt_dni` varchar(9) DEFAULT NULL,
  `txt_email` varchar(255) DEFAULT NULL,
  `url_imagen_usuario` varchar(255) DEFAULT NULL,
  `username` varchar(15) NOT NULL
);

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `credentials_expired`, `disabled`, `expired`, `locked`, `num_telefono`, `password`, `txt_descripcion`, `txt_dni`, `txt_email`, `url_imagen_usuario`, `username`) VALUES
(1, b'0', b'0', b'0', b'0', 621887352, '$2a$10$MUfLv9o8pOmezu1mkRGk8udtpqab2nvvACdQOyII4Dd0Qx7JITgd.', 'Este es el usuario administrador del sistema 2', '37123322W', NULL, 'http://localhost:8080/pdv-backend/resource/media/public/Berserk.png', 'admin'),
(25, b'0', b'0', b'0', b'0', 987456123, '$2a$10$.wx3uUvdWsM7V/0dVuCGnu5MLhybypV.9W/zsnxu6.c5LYV1pVg6e', NULL, '49578621L', 'jose@gmail.com', NULL, 'Jose'),
(26, b'0', b'0', b'0', b'0', 649785213, '$2a$10$VwXRxdcAnX.g6cMg.HE5/OUUz3IX59jXSLmUqJghD3dvYAEouo.gm', NULL, '45978623J', 'manolillo@gmail.com', NULL, 'Manuel'),
(27, b'0', b'0', b'0', b'0', 461578923, '$2a$10$T0fh7Q.UUlfalSk8EgDUbOzU9yeUpX/IbGUi4A9NoVwCM9OTrFwoC', NULL, '42173246K', 'juan_pala@gmail.com', NULL, 'Juan'),
(28, b'0', b'0', b'0', b'0', 541236789, '$2a$10$wB3TOig4tyJ8E2Bzhlyzyu4w01kD7UhniRS4AshDVvWTCAaoHjh82', NULL, '40316278O', 'david_pala@gmail.com', NULL, 'David'),
(29, b'0', b'0', b'0', b'0', 487219835, '$2a$10$nPcqdDIVO7AG/KVt3druY.WpWQyannF2IIBDoCcwwwpp79ROH3V4O', NULL, '26558941D', 'maria@gmail.com', NULL, 'Maria'),
(30, b'0', b'0', b'0', b'0', 978456123, '$2a$10$Y7kg2yjoRfxsjI4ZgcQtxuXv314D3RphXaxhhK2niE/mtdpGF8oqW', NULL, '47985621L', 'luis@gmail.com', NULL, 'Luis'),
(31, b'0', b'0', b'0', b'0', 467215389, '$2a$10$F2/kxOd/OEKLB04SPpiQduTt6S/sFUfRRrT/UiRvU1IK4OpQW8Xeu', NULL, '57982164M', 'laurita@gmail.com', NULL, 'Laura'),
(32, b'0', b'0', b'0', b'0', 879456231, '$2a$10$tmEuiSExh82.Kgi8b5fJOubfUOFo3vLNC6Q8GWB4fAPiUdzZeU3mK', NULL, '34678125A', 'mario@gmail.com', NULL, 'Mario'),
(33, b'0', b'0', b'0', b'0', 487591236, '$2a$10$tRff7W2wvheoKuAn3Rosi.AucqjPQPfqdaa.5QwABYAbH3bc6KcDK', NULL, '48761529J', 'pedrito@gmail.com', NULL, 'Pedro'),
(34, b'0', b'0', b'0', b'0', 557412668, '$2a$10$FqneLzCR.1x2UJ2Qmg3QDeYJ78yf6.XkkV3dn5oKnBHY4CbZuTG.m', NULL, '47895167F', 'daniel@gmail.com', NULL, 'Daniel'),
(35, b'0', b'0', b'0', b'0', 874599619, '$2a$10$DF9uTMfsLFNNmlfk4nnkUuUuyPzyFM/jj1x1LtwjJZFFQvhBU2EgK', NULL, '41278956U', 'lucia@gmail.com', NULL, 'Lucia'),
(36, b'0', b'0', b'0', b'0', 879456123, '$2a$10$jkD0FKGkjGOBPzMRB6cbVO0v64fSg1gl3uzuMjIwU0dlRDqnQNy6G', NULL, '23748951G', 'angela@gmail.com', NULL, 'Angela'),
(37, b'0', b'0', b'0', b'0', 345612789, '$2a$10$R274/mBj3ICMUZSuUOIHnuuZMbGFjGIlVvAf5t.O1Z0xfJBj2ooIG', NULL, '87549612P', 'antonio@gmail.com', NULL, 'Antonio'),
(38, b'0', b'0', b'0', b'0', 794856123, '$2a$10$s9pTQLXPsKBuoVumy42gie9X3FzL8OOFIAtaTfkGUXjr6kWlJ.nSK', NULL, '87945612K', 'gonzalo@gmail.com', NULL, 'Gonzalo'),
(39, b'0', b'0', b'0', b'0', 875462193, '$2a$10$ETrhtUHFstnlG6Y/FX3Vn.f9z80A87cUarQaSIWq4aNR44d9LKQY.', NULL, '45789126J', 'miguelin@gmail.com', NULL, 'Miguel');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_perfiles`
--

CREATE TABLE `usuario_perfiles` (
  `id_usuario` bigint(20) NOT NULL,
  `id_perfil` bigint(20) NOT NULL
);

--
-- Volcado de datos para la tabla `usuario_perfiles`
--

INSERT INTO `usuario_perfiles` (`id_usuario`, `id_perfil`) VALUES
(1, 1),
(1, 2),
(1, 3),
(25, 2),
(25, 3),
(26, 2),
(26, 3),
(27, 2),
(27, 3),
(28, 2),
(28, 3),
(29, 2),
(29, 3),
(30, 2),
(30, 3),
(31, 2),
(31, 3),
(32, 2),
(32, 3),
(33, 3),
(34, 3),
(35, 3),
(36, 3),
(37, 3),
(38, 3),
(39, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoracion_alojamiento`
--

CREATE TABLE `valoracion_alojamiento` (
  `id_valoracion_alojamiento` bigint(20) NOT NULL,
  `puntuacion` int(11) NOT NULL,
  `txt_asunto` varchar(50) DEFAULT NULL,
  `txt_mensaje` varchar(255) NOT NULL,
  `id_alojamiento` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL
);

--
-- Volcado de datos para la tabla `valoracion_alojamiento`
--

INSERT INTO `valoracion_alojamiento` (`id_valoracion_alojamiento`, `puntuacion`, `txt_asunto`, `txt_mensaje`, `id_alojamiento`, `id_usuario`) VALUES
(3, 5, NULL, 'Me ha resultado fantástico!', 13, 33),
(4, 5, NULL, 'Muy bien!', 29, 33),
(5, 5, NULL, 'Perfecto', 13, 34),
(6, 5, NULL, 'Perfecto', 15, 34),
(7, 3, NULL, 'Regular', 16, 35),
(8, 5, NULL, 'Perfecto', 17, 35),
(9, 5, NULL, 'Perfecto', 18, 36),
(10, 5, NULL, 'Perfecto', 19, 36),
(11, 5, NULL, 'Perfecto', 18, 37),
(12, 4, NULL, 'Bien', 19, 37),
(13, 5, NULL, 'Perfecto', 20, 38),
(14, 4, NULL, 'Bien', 21, 38),
(15, 5, NULL, 'Perfecto', 22, 39),
(16, 4, NULL, 'Regular', 23, 39);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD PRIMARY KEY (`id_alojamiento`),
  ADD KEY `FKef90opai1g6l77ux5rdbavnlg` (`id_usuario`);

--
-- Indices de la tabla `alojamiento_comodidad_alojamiento`
--
ALTER TABLE `alojamiento_comodidad_alojamiento`
  ADD PRIMARY KEY (`id_alojamiento_comodidad_alojamiento`),
  ADD KEY `FKqjrtrscd4lijlocqpta00jd3v` (`id_alojamiento`),
  ADD KEY `FKdudsxqu9rrahrp207iw39s9q5` (`id_comodidad_alojamiento`);

--
-- Indices de la tabla `alojamiento_dias_ocupados`
--
ALTER TABLE `alojamiento_dias_ocupados`
  ADD PRIMARY KEY (`id_alojamiento_dias_ocupados`),
  ADD KEY `FK2gj84i6q212uls6lgrhl5ukm1` (`id_alojamiento`);

--
-- Indices de la tabla `alquiler_alojamiento`
--
ALTER TABLE `alquiler_alojamiento`
  ADD PRIMARY KEY (`id_alquiler_alojamiento`),
  ADD KEY `FKgldwcw6ry356bulemaulcnsvw` (`id_alojamiento`),
  ADD KEY `FKbjvmjdjfa8x3bn4gnw7rlsm92` (`id_usuario`);

--
-- Indices de la tabla `authorization_consent`
--
ALTER TABLE `authorization_consent`
  ADD PRIMARY KEY (`principal_name`,`registered_client_id`);

--
-- Indices de la tabla `comodidad_alojamiento`
--
ALTER TABLE `comodidad_alojamiento`
  ADD PRIMARY KEY (`id_comodidad_alojamiento`),
  ADD UNIQUE KEY `UK_b4by5t13qflun4k7fftuxwhvw` (`codigo_comodidad`),
  ADD KEY `FKd5b8ohuisr8bar2a48cvbblc` (`id_tipo_comodidad`);

--
-- Indices de la tabla `google_user`
--
ALTER TABLE `google_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_mcql1nohviiqgwsi1mwdtpsb5` (`id_usuario`);

--
-- Indices de la tabla `imagen_alojamiento`
--
ALTER TABLE `imagen_alojamiento`
  ADD PRIMARY KEY (`id_imagen_alojamiento`),
  ADD KEY `FKpkymmqbg12el7d8sk47bck1xl` (`id_alojamiento`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id_perfil`),
  ADD UNIQUE KEY `UK_p9ma4vh5lvuv693b8qd9nhv5p` (`cod_perfil`);

--
-- Indices de la tabla `tipo_comodidad`
--
ALTER TABLE `tipo_comodidad`
  ADD PRIMARY KEY (`id_tipo_comodidad`),
  ADD UNIQUE KEY `UK_6bdlb5q4711n3k1njp8vjo8ql` (`codigo_tipo_comodidad`);

--
-- Indices de la tabla `ubicacion_alojamiento`
--
ALTER TABLE `ubicacion_alojamiento`
  ADD PRIMARY KEY (`id_ubicacion_alojamiento`),
  ADD UNIQUE KEY `UK_bbyr9xt6qjrd446u2c5xgn58i` (`id_alojamiento`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `UK_863n1y3x0jalatoir4325ehal` (`username`),
  ADD UNIQUE KEY `UK_fvuef28cvvfukepbxnu9g8g9m` (`num_telefono`),
  ADD UNIQUE KEY `UK_qyu6yce0jt2w95uy4gsfsxo44` (`txt_dni`),
  ADD UNIQUE KEY `UK_2quswsdm0pqy89cpo78nqn96r` (`txt_email`);

--
-- Indices de la tabla `usuario_perfiles`
--
ALTER TABLE `usuario_perfiles`
  ADD PRIMARY KEY (`id_usuario`,`id_perfil`),
  ADD KEY `FKspuf18hg75qo0vgnlfpgiwnas` (`id_perfil`);

--
-- Indices de la tabla `valoracion_alojamiento`
--
ALTER TABLE `valoracion_alojamiento`
  ADD PRIMARY KEY (`id_valoracion_alojamiento`),
  ADD KEY `FK8o25odsdr5u6wk5ktbvb6om0c` (`id_alojamiento`),
  ADD KEY `FKiiv8odw1npxkwhftkxriavp23` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  MODIFY `id_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `alojamiento_comodidad_alojamiento`
--
ALTER TABLE `alojamiento_comodidad_alojamiento`
  MODIFY `id_alojamiento_comodidad_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=383;

--
-- AUTO_INCREMENT de la tabla `alojamiento_dias_ocupados`
--
ALTER TABLE `alojamiento_dias_ocupados`
  MODIFY `id_alojamiento_dias_ocupados` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- AUTO_INCREMENT de la tabla `alquiler_alojamiento`
--
ALTER TABLE `alquiler_alojamiento`
  MODIFY `id_alquiler_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `comodidad_alojamiento`
--
ALTER TABLE `comodidad_alojamiento`
  MODIFY `id_comodidad_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT de la tabla `google_user`
--
ALTER TABLE `google_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `imagen_alojamiento`
--
ALTER TABLE `imagen_alojamiento`
  MODIFY `id_imagen_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id_perfil` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_comodidad`
--
ALTER TABLE `tipo_comodidad`
  MODIFY `id_tipo_comodidad` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ubicacion_alojamiento`
--
ALTER TABLE `ubicacion_alojamiento`
  MODIFY `id_ubicacion_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `valoracion_alojamiento`
--
ALTER TABLE `valoracion_alojamiento`
  MODIFY `id_valoracion_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD CONSTRAINT `FKef90opai1g6l77ux5rdbavnlg` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `alojamiento_comodidad_alojamiento`
--
ALTER TABLE `alojamiento_comodidad_alojamiento`
  ADD CONSTRAINT `FKdudsxqu9rrahrp207iw39s9q5` FOREIGN KEY (`id_comodidad_alojamiento`) REFERENCES `comodidad_alojamiento` (`id_comodidad_alojamiento`),
  ADD CONSTRAINT `FKqjrtrscd4lijlocqpta00jd3v` FOREIGN KEY (`id_alojamiento`) REFERENCES `alojamiento` (`id_alojamiento`);

--
-- Filtros para la tabla `alojamiento_dias_ocupados`
--
ALTER TABLE `alojamiento_dias_ocupados`
  ADD CONSTRAINT `FK2gj84i6q212uls6lgrhl5ukm1` FOREIGN KEY (`id_alojamiento`) REFERENCES `alojamiento` (`id_alojamiento`);

--
-- Filtros para la tabla `alquiler_alojamiento`
--
ALTER TABLE `alquiler_alojamiento`
  ADD CONSTRAINT `FKbjvmjdjfa8x3bn4gnw7rlsm92` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FKgldwcw6ry356bulemaulcnsvw` FOREIGN KEY (`id_alojamiento`) REFERENCES `alojamiento` (`id_alojamiento`);

--
-- Filtros para la tabla `comodidad_alojamiento`
--
ALTER TABLE `comodidad_alojamiento`
  ADD CONSTRAINT `FKd5b8ohuisr8bar2a48cvbblc` FOREIGN KEY (`id_tipo_comodidad`) REFERENCES `tipo_comodidad` (`id_tipo_comodidad`);

--
-- Filtros para la tabla `google_user`
--
ALTER TABLE `google_user`
  ADD CONSTRAINT `FKpeq4mxa0neme1e2mlv3tryscq` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `imagen_alojamiento`
--
ALTER TABLE `imagen_alojamiento`
  ADD CONSTRAINT `FKpkymmqbg12el7d8sk47bck1xl` FOREIGN KEY (`id_alojamiento`) REFERENCES `alojamiento` (`id_alojamiento`);

--
-- Filtros para la tabla `ubicacion_alojamiento`
--
ALTER TABLE `ubicacion_alojamiento`
  ADD CONSTRAINT `FKhlbqbs6l4ryxw3nfba0oj0ge6` FOREIGN KEY (`id_alojamiento`) REFERENCES `alojamiento` (`id_alojamiento`);

--
-- Filtros para la tabla `usuario_perfiles`
--
ALTER TABLE `usuario_perfiles`
  ADD CONSTRAINT `FKnkmediyx2ja8vofv0aap3pwm` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FKspuf18hg75qo0vgnlfpgiwnas` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`);

--
-- Filtros para la tabla `valoracion_alojamiento`
--
ALTER TABLE `valoracion_alojamiento`
  ADD CONSTRAINT `FK8o25odsdr5u6wk5ktbvb6om0c` FOREIGN KEY (`id_alojamiento`) REFERENCES `alojamiento` (`id_alojamiento`),
  ADD CONSTRAINT `FKiiv8odw1npxkwhftkxriavp23` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);
COMMIT;
