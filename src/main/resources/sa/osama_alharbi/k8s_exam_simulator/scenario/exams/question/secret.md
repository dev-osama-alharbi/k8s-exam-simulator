# Secret
___
Depend on this YAML file
# | secret-handler.yaml
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
  containers:
  - image: nginx:1.25.2
    name: secret-handler
    resources: {}
  dnsPolicy: ClusterFirst
  restartPolicy: Always
```

Create a new Secret `secret1` in Namespace `::namespace::` which contains `user=test` and `pass=pwd`. The Secret's content should be available in Pod `secret-handler` as environment variables `SECRET1_USER` and `SECRET1_PASS`.

Also, Create another Secret `secret2` in Namespace `::namespace::` create this Secret and mount it inside the same Pod at `/tmp/secret2`. the secret contains file `secret2.conf`
# | secret2.conf
```
top secret
```