# S Pods
1. Create YAML file from kubectl

    `kubectl create deploy my-website --image=nginx:1.25.2 -n ::namespace:: --dry-run=client -o=yaml > deploy.yaml`
2. Edit YAML file

   `vim deploy.yaml`
```
apiVersion: apps/v1
kind: Deployment
metadata:
  creationTimestamp: null
  labels:
    app: my-website
  name: my-website
  namespace: abc
spec:
  replicas: 1
  selector:
    matchLabels:
      app: my-website
  strategy: {}
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: my-website
    spec:
      volumes:                                                    # ADD
      - name: web-content                                         # ADD
        emptyDir: {}                                              # ADD
      initContainers:                                             # ADD
      - name: init-con                                            # ADD
        image: busybox:1.36.1                                     # ADD
        command:                                                  # ADD
        - sh                                                      # ADD
        - -c                                                      # ADD
        - 'echo "hello world!" > /tmp/web-content/index.html'     # ADD
        volumeMounts:                                             # ADD
        - name: web-content                                       # ADD
          mountPath: /tmp/web-content                             # ADD
      containers:
      - image: nginx:1.25.2
        name: nginx
        volumeMounts:                                             # ADD
        - name: web-content                                       # ADD
          mountPath: /usr/share/nginx/html                        # ADD
        resources: {}
status: {}
```
3. apply YAML file

   `kubectl apply -f deploy.yaml`