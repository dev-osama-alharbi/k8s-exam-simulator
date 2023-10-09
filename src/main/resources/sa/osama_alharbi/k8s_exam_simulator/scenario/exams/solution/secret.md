# Service
___
| 1. Create secret from kubectl

    `kubectl create secret generic secret1 --from-literal user=test --from-literal pass=pwd -n ::namespace::`

___

| 2. The last command would generate this yaml and create it:

```
apiVersion: v1
data:
  pass: cHdk
  user: dGVzdA==
kind: Secret
metadata:
  creationTimestamp: null
  name: secret1
  namespace: ::namespace::
```
___

3. Create secret2.conf file

`echo "top secret" > secret2.conf`
___

4. Create secret from kubectl

`kubectl create secret generic secret2 --from-file=secret2.conf -n ::namespace::`
___

5. The last command would generate this yaml and create it:

```
apiVersion: v1
data:
  secret2.conf: dG9wIHNlY3JldAo=
kind: Secret
metadata:
  creationTimestamp: null
  name: secret2
  namespace: ::namespace::
```
___

6. Create YAML file from kubectl

   `kubectl run secret-handler --image=nginx:1.25.2 -n ::namespace:: --dry-run=client -o=yaml > secret-handler.yaml`
___

7. Edit YAML file

   `vim secret-handler.yaml`
```
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    run: secret-handler
  name: secret-handler
  namespace: ::namespace::
spec:
  volumes:
  - name: secret2-volume              # add
    secret:                           # add
      secretName: secret2             # add
  containers:
  - image: nginx:1.25.2
    name: secret-handler
    resources: {}
    env:                              # add
    - name: SECRET1_USER              # add
      valueFrom:                      # add
        secretKeyRef:                 # add
          name: secret1               # add
          key: user                   # add
    - name: SECRET1_PASS              # add
      valueFrom:                      # add
        secretKeyRef:                 # add
          name: secret1               # add
          key: pass                   # add
    volumeMounts:                     # add
    - name: secret2-volume            # add
      mountPath: /tmp/secret2         # add
  dnsPolicy: ClusterFirst
  restartPolicy: Always
```
___

8. apply YAML file

   `kubectl apply -f secret-handler.yaml`