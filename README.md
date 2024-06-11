# Portal del Viajero

![Logo Portal del Viajero](public-resources/Logo.png)

## ¿Qué es Portal del Viajero?

Portal del viajero es un proyecto realizado por estudiantes del instituto IES Almudeyne localizado en Los Palacios y Villafranca, Sevilla.
En este proyecto se trata de replicar una aplicación de alquileres rurales (como puede ser AirBnb).

## ¿Dónde puedo encontrar información referente al proyecto?

Todos los recursos públicos se encuentran dentro del directo public-resources/, aquí podreis encontrar información cómo:

* Documentación del proyecto en formato PDF.
* Imágenes de Diagramas de Análisis.
* Imágenes de diagramas E/R y esquema de la base de datos (.sql).
* Diagramas de Navegación.

## Despliegue del proyecto

Al ser un proyecto desarrollado en Angular, Spring boot y con una base de datos de MYSQL, el despliegue de la aplicación puede resultar ser tedioso,
ya para desplegar este proyecto se usan tecnologías como Docker y Kubernetes. Toda esta información aparece dentro del apartado de Kubernetes de la documentación.

## ¿Cómo Desplegar el proyecto en entorno de desarrollo?

***SE DEBE SEGUIR CADA PASO AL PIE DE LA LETRA, EN CASO CONTRARIO, NO PODRÁS DESPLEGAR NADA BIEN***

1. Instalar XAMPP, ejecutar el despliegue de MYSQL y Tomcat.
2. Ir al phpMyAdmin proporcionado por XAMPP y crear un usuario 'usuario' con contraseña 'usuario' que tenga todos los permisos.
3. Crear una base de datos manualmente que se llame bdpdv
4. Ir a la carpeta public-resources/Base de Datos/bdpdv.sql y ejecutar todo el script (para esto simplemente vas a la sección de importar dentro de la BBDD de bdpdv y seleccionas el archivo).
5. Una vez todo esté importado tenemos que importar los proyectos MAVEN en un workspace con el IDE Spring tools suite 4.
6. Ejecutaremos en primer lugar el servidor de autenticación. Una vez levantado, ejecutamos el servidor del backend.
7.Con node y Angular CLI instalados en nuestro equipo, nos dirigimos a la carpeta de Pdv-frontend/ y desde una terminal ejecutamos el comando `npm install crypto-js`, después `npm install`.
8. Una vez instaladas estas dependencias ejecutamos el comando `npm i --save-dev @types/crypto-js
9. Ejecutamos el comando `ng serve --host 127.0.0.1` 
10. ¡Ya está la aplicación lista para usarse!

## Reuniones del grupo

Las reuniones del grupo se realizan todos los dias no festivos de Lunes a Viernes y de 15:00 a 19:00.

## Integrantes del Proyecto

* Juan Martín Candela
* David Dorante Lucas