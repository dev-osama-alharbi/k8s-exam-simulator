# Deployment
1. Create Deployment from kubectl

    `kubectl create deploy my-store --image=nginx:1.25.2 -n ::namespace:: -r 3`
   
### | 2. The last command would generate this yaml and create it:
```
apiVersion: apps/v1
kind: Deployment
metadata:
  creationTimestamp: null
  labels:
    app: my-store
  name: my-store
  namespace: ::namespace::
spec:
  replicas: 3
  selector:
    matchLabels:
      app: my-store
  strategy: {}
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: my-store
    spec:
      containers:
      - image: nginx:1.25.2
        name: nginx
        resources: {}
status: {}
```