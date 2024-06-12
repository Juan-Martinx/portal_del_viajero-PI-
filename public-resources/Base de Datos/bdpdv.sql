--
-- Base de datos: `bdpdv`
--
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- -------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `id_perfil` bigint(20) NOT NULL,
  `cod_perfil` enum('OIDC_USER','PERFIL_ADMIN','PERFIL_CLIENTE','PERFIL_GESTOR') NOT NULL,
  `txt_perfil` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_perfiles`
--

CREATE TABLE `usuario_perfiles` (
  `id_usuario` bigint(20) NOT NULL,
  `id_perfil` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento_comodidad_alojamiento`
--

CREATE TABLE `alojamiento_comodidad_alojamiento` (
  `id_alojamiento_comodidad_alojamiento` bigint(20) NOT NULL,
  `id_alojamiento` bigint(20) DEFAULT NULL,
  `id_comodidad_alojamiento` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_comodidad`
--

CREATE TABLE `tipo_comodidad` (
  `id_tipo_comodidad` bigint(20) NOT NULL,
  `codigo_tipo_comodidad` varchar(50) NOT NULL,
  `txt_nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorization_consent`
--

CREATE TABLE `authorization_consent` (
  `principal_name` varchar(255) NOT NULL,
  `registered_client_id` varchar(255) NOT NULL,
  `authorities` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `require_proof_key` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_authentication_methods`
--

CREATE TABLE `client_authentication_methods` (
  `client_id` int(11) NOT NULL,
  `authentication_methods` varbinary(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_authorization_grant_types`
--

CREATE TABLE `client_authorization_grant_types` (
  `client_id` int(11) NOT NULL,
  `authorization_grant_types` varbinary(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_redirect_uris`
--

CREATE TABLE `client_redirect_uris` (
  `client_id` int(11) NOT NULL,
  `redirect_uris` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_scopes`
--

CREATE TABLE `client_scopes` (
  `client_id` int(11) NOT NULL,
  `scopes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `client_scopes`
--

INSERT INTO `client_scopes` (`client_id`, `scopes`) VALUES
(1, 'openid');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagen_alojamiento`
--

CREATE TABLE `imagen_alojamiento` (
  `id_imagen_alojamiento` bigint(20) NOT NULL,
  `num_orden` int(11) NOT NULL,
  `url_datos_imagen` varchar(255) NOT NULL,
  `id_alojamiento` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  MODIFY `id_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `alojamiento_comodidad_alojamiento`
--
ALTER TABLE `alojamiento_comodidad_alojamiento`
  MODIFY `id_alojamiento_comodidad_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `alojamiento_dias_ocupados`
--
ALTER TABLE `alojamiento_dias_ocupados`
  MODIFY `id_alojamiento_dias_ocupados` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `alquiler_alojamiento`
--
ALTER TABLE `alquiler_alojamiento`
  MODIFY `id_alquiler_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `comodidad_alojamiento`
--
ALTER TABLE `comodidad_alojamiento`
  MODIFY `id_comodidad_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `google_user`
--
ALTER TABLE `google_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `imagen_alojamiento`
--
ALTER TABLE `imagen_alojamiento`
  MODIFY `id_imagen_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

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
  MODIFY `id_ubicacion_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `valoracion_alojamiento`
--
ALTER TABLE `valoracion_alojamiento`
  MODIFY `id_valoracion_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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

--
-- INSERTS
--

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `credentials_expired`, `disabled`, `expired`, `locked`, `num_telefono`, `password`, `txt_descripcion`, `txt_dni`, `txt_email`, `url_imagen_usuario`, `username`) VALUES
(1, b'0', b'0', b'0', b'0', 621887352, '$2a$10$MUfLv9o8pOmezu1mkRGk8udtpqab2nvvACdQOyII4Dd0Qx7JITgd.', 'Este es el usuario administrador del sistema 2', '37123322W', NULL, 'http://localhost:8080/pdv-backend/resource/media/public/Berserk.png', 'admin');

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`id_perfil`, `cod_perfil`, `txt_perfil`) VALUES
(1, 'PERFIL_ADMIN', 'Administrador'),
(2, 'PERFIL_GESTOR', 'Gestor'),
(3, 'PERFIL_CLIENTE', 'Cliente'),
(4, 'OIDC_USER', 'Usuario Google');

--
-- Volcado de datos para la tabla `tipo_comodidad`
--

INSERT INTO `tipo_comodidad` (`id_tipo_comodidad`, `codigo_tipo_comodidad`, `txt_nombre`) VALUES
(1, 'INST', 'Instalaciones'),
(2, 'COM', 'Comodidad');

--
-- Volcado de datos para la tabla `usuario_perfiles`
--

INSERT INTO `usuario_perfiles` (`id_usuario`, `id_perfil`) VALUES
(1, 1),
(1, 2),
(1, 3);

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`id`, `client_id`, `client_secret`, `require_proof_key`) VALUES
(1, 'client', '$2a$10$yjuryPs6dJxsDTTsfbLDgeKNAmeJzJVT2fZ/wKZI.gdsoNtdMsN4a', b'1');

--
-- Volcado de datos para la tabla `client_authentication_methods`
--

INSERT INTO `client_authentication_methods` (`client_id`, `authentication_methods`) VALUES
(1, 0xaced0005737200436f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f72652e436c69656e7441757468656e7469636174696f6e4d6574686f6400000000000002620200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b7870740013636c69656e745f7365637265745f6261736963);

--
-- Volcado de datos para la tabla `client_authorization_grant_types`
--

INSERT INTO `client_authorization_grant_types` (`client_id`, `authorization_grant_types`) VALUES
(1, 0xaced00057372003f6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f72652e417574686f72697a6174696f6e4772616e745479706500000000000002620200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b787074000d726566726573685f746f6b656e),
(1, 0xaced00057372003f6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f72652e417574686f72697a6174696f6e4772616e745479706500000000000002620200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b7870740012636c69656e745f63726564656e7469616c73),
(1, 0xaced00057372003f6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f72652e417574686f72697a6174696f6e4772616e745479706500000000000002620200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b7870740012617574686f72697a6174696f6e5f636f6465);

--
-- Volcado de datos para la tabla `client_redirect_uris`
--

INSERT INTO `client_redirect_uris` (`client_id`, `redirect_uris`) VALUES
(1, 'http://127.0.0.1:4200/authorized');


--
-- Volcado de datos para la tabla `authorization_consent`
--

INSERT INTO `authorization_consent` (`principal_name`, `registered_client_id`, `authorities`) VALUES
('jmarcanofficial@gmail.com', 'client', 'SCOPE_openid_profile');

COMMIT;
