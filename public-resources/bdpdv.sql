SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `bdpdv`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento`
--

CREATE TABLE `alojamiento` (
  `id_alojamiento` bigint(20) NOT NULL,
  `num_banyos` int(11) NOT NULL,
  `num_camas` int(11) NOT NULL,
  `num_plaza_max` int(11) NOT NULL,
  `num_plaza_min` int(11) NOT NULL,
  `num_precio_plaza` double NOT NULL,
  `txt_descripcion` varchar(255) NOT NULL,
  `txt_nombre` varchar(50) NOT NULL,
  `id_usuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento_comodidad_alojamiento`
--

CREATE TABLE `alojamiento_comodidad_alojamiento` (
  `id_alojamiento_comodidad_alojamiento` bigint(20) NOT NULL,
  `id_alojamiento` bigint(20) NOT NULL,
  `id_comodidad_alojamiento` bigint(20) NOT NULL
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
-- Estructura de tabla para la tabla `comodidad_alojamiento`
--

CREATE TABLE `comodidad_alojamiento` (
  `id_comodidad_alojamiento` bigint(20) NOT NULL,
  `codigo_comodidad` varchar(50) NOT NULL,
  `txt_descripcion` varchar(255) NOT NULL,
  `txt_nombre` varchar(50) NOT NULL,
  `id_tipo_comodidad` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagen_alojamiento`
--

CREATE TABLE `imagen_alojamiento` (
  `id_imagen_alojamiento` bigint(20) NOT NULL,
  `datos_imagen` tinyblob NOT NULL,
  `num_orden` int(11) NOT NULL,
  `id_alojamiento` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `id_perfil` bigint(20) NOT NULL,
  `cod_perfil` varchar(50) NOT NULL,
  `txt_perfil` varchar(255) NOT NULL
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
-- Estructura de tabla para la tabla `ubicacion_alojamiento`
--

CREATE TABLE `ubicacion_alojamiento` (
  `id_ubicacion_alojamiento` bigint(20) NOT NULL,
  `ciudad` varchar(60) NOT NULL,
  `codigo_postal` int(11) NOT NULL,
  `latitud` varchar(255) DEFAULT NULL,
  `linea_direccion` varchar(255) NOT NULL,
  `longitud` varchar(255) DEFAULT NULL,
  `provincia` varchar(60) NOT NULL,
  `id_alojamiento` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `num_telefono` int(11) NOT NULL,
  `txt_descripcion` varchar(255) DEFAULT NULL,
  `txt_dni` varchar(9) NOT NULL,
  `txt_email` varchar(255) NOT NULL,
  `txt_nombre_usuario` varchar(15) NOT NULL,
  `txt_password` varchar(20) NOT NULL,
  `id_perfil` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoracion_alojamiento`
--

CREATE TABLE `valoracion_alojamiento` (
  `id_valoracion_alojamiento` bigint(20) NOT NULL,
  `puntuacion` int(11) NOT NULL,
  `txt_asunto` varchar(50) NOT NULL,
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
-- Indices de la tabla `alquiler_alojamiento`
--
ALTER TABLE `alquiler_alojamiento`
  ADD PRIMARY KEY (`id_alquiler_alojamiento`),
  ADD KEY `FKgldwcw6ry356bulemaulcnsvw` (`id_alojamiento`),
  ADD KEY `FKbjvmjdjfa8x3bn4gnw7rlsm92` (`id_usuario`);

--
-- Indices de la tabla `comodidad_alojamiento`
--
ALTER TABLE `comodidad_alojamiento`
  ADD PRIMARY KEY (`id_comodidad_alojamiento`),
  ADD UNIQUE KEY `UK_b4by5t13qflun4k7fftuxwhvw` (`codigo_comodidad`),
  ADD KEY `FKd5b8ohuisr8bar2a48cvbblc` (`id_tipo_comodidad`);

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
  ADD UNIQUE KEY `UK_fvuef28cvvfukepbxnu9g8g9m` (`num_telefono`),
  ADD UNIQUE KEY `UK_qyu6yce0jt2w95uy4gsfsxo44` (`txt_dni`),
  ADD UNIQUE KEY `UK_2quswsdm0pqy89cpo78nqn96r` (`txt_email`),
  ADD UNIQUE KEY `UK_2db2m2g0608v0inj21nd6lat2` (`txt_nombre_usuario`),
  ADD KEY `FK131gkl0dt1966rsw6dmesnsxw` (`id_perfil`);

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
  MODIFY `id_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `alojamiento_comodidad_alojamiento`
--
ALTER TABLE `alojamiento_comodidad_alojamiento`
  MODIFY `id_alojamiento_comodidad_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `alquiler_alojamiento`
--
ALTER TABLE `alquiler_alojamiento`
  MODIFY `id_alquiler_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comodidad_alojamiento`
--
ALTER TABLE `comodidad_alojamiento`
  MODIFY `id_comodidad_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `imagen_alojamiento`
--
ALTER TABLE `imagen_alojamiento`
  MODIFY `id_imagen_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id_perfil` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_comodidad`
--
ALTER TABLE `tipo_comodidad`
  MODIFY `id_tipo_comodidad` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ubicacion_alojamiento`
--
ALTER TABLE `ubicacion_alojamiento`
  MODIFY `id_ubicacion_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `valoracion_alojamiento`
--
ALTER TABLE `valoracion_alojamiento`
  MODIFY `id_valoracion_alojamiento` bigint(20) NOT NULL AUTO_INCREMENT;

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
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK131gkl0dt1966rsw6dmesnsxw` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`);

--
-- Filtros para la tabla `valoracion_alojamiento`
--
ALTER TABLE `valoracion_alojamiento`
  ADD CONSTRAINT `FK8o25odsdr5u6wk5ktbvb6om0c` FOREIGN KEY (`id_alojamiento`) REFERENCES `alojamiento` (`id_alojamiento`),
  ADD CONSTRAINT `FKiiv8odw1npxkwhftkxriavp23` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);
COMMIT;