echo Eliminando todos los servicios...
kubectl delete --all services
echo Eliminando todos los deployments...
kubectl delete --all deployments
echo Eliminando todos los replicasets...
kubectl delete --all replicasets
echo Eliminando todos los pods...
kubectl delete --all pods
echo Eliminando todos los ingresses...
kubectl delete --all ingresses
echo Eliminando todos los jobs...
kubectl delete --all jobs
echo Eliminando todos los cronjobs...
kubectl delete --all cronjobs
echo Eliminando todos los persistent volume claims...
kubectl delete --all persistentvolumeclaims
