# Service
1. Create YAML file from kubectl

    `kubectl run project-api --image=nginx:1.25.2 --labels project=prod-api -n ::namespace:: --dry-run=client -o=yaml > pod.yaml`


2. Edit YAML file

   `vim pod.yaml`
```
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    project: prod-api
  name: project-api
  namespace: ::namespace::
spec:
  containers:
  - image: nginx:1.25.2
    name: project-api
    resources: {}
  dnsPolicy: ClusterFirst
  restartPolicy: Always
status: {}
```


3. Create YAML file from kubectl

   `kubectl expose pod project-api --name project-api-svc --port 3333 --target-port 80 -n ::namespace:: --dry-run=client -o=yaml > service.yaml`


4. Edit YAML file

   `vim service.yaml`
```
apiVersion: v1
kind: Service
metadata:
  creationTimestamp: null
  labels:
    project: prod-api
  name: project-api-svc
  namespace: ::namespace::
spec:
  ports:
  - port: 3333
    protocol: TCP
    targetPort: 80
  selector:
    project: prod-api
status:
  loadBalancer: {}
```


5. apply YAML file

   `kubectl apply -f pod.yaml`

   `kubectl apply -f service.yaml`