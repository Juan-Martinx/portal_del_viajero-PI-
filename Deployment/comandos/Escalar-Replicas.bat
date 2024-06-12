@echo off
setlocal

echo --------------DEPLOYMENTS DISPONIBLES------------------
kubectl get deployments
echo -------------------------------------------------------
:: Solicitar al usuario el nombre del deployment
set /p DEPLOYMENT_NAME=Introduce el nombre del deployment: 

:: Solicitar al usuario el número de réplicas
set /p REPLICAS=Introduce el número de réplicas: 

:: Ejecutar el comando kubectl para escalar el deployment
kubectl scale deployment %DEPLOYMENT_NAME% --replicas=%REPLICAS%

:: Comprobar si el comando fue exitoso
if %errorlevel% equ 0 (
    echo El deployment %DEPLOYMENT_NAME% ha sido escalado a %REPLICAS% replicas.
) else (
    echo Hubo un error al intentar escalar el deployment.
)

echo --------------PODS DISPONIBLES------------------
kubectl get pods
echo ------------------------------------------------
pause
endlocal