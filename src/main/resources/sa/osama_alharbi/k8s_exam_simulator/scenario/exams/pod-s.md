# S Pods
1. Create YAML file from kubectl

    `kubectl run pod1 --image=httpd:2.4.57-alpine -n ::namespace:: --dry-run=client -o=yaml > pod.yaml`
2. Edit YAML file

   `vim pod.yaml`
```
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    run: pod1
  name: pod1
  namespace: ::namespace::
spec:
  containers:
  - image: httpd:2.4.57-alpine
    name: pod1-container # change
    resources: {}
  dnsPolicy: ClusterFirst
  restartPolicy: Always
```
3. apply YAML file

   `kubectl apply -f pod.yaml`