FROM openjdk:17-oracle
EXPOSE 8080 9999

# Crear un directorio de trabajo
WORKDIR /app

# Copiar los archivos JAR al contenedor
ADD Pdv-backend/target/Pdv-backend-0.0.1-SNAPSHOT.jar /app/
ADD Pdv-oauth2-authorization-server/target/Pdv-oauth2-0.0.1-SNAPSHOT.jar /app/

# Crear el script de inicio directamente en el Dockerfile
RUN printf "#!/bin/bash\njava -jar /app/Pdv-oauth2-0.0.1-SNAPSHOT.jar &\njava -jar /app/Pdv-backend-0.0.1-SNAPSHOT.jar &\nwait -n\nexit \$?" > /app/start.sh

# Dar permisos de ejecución al script
RUN chmod +x /app/start.sh

# Ejecutar el script cuando se inicie el contenedor
CMD ["/app/start.sh"]