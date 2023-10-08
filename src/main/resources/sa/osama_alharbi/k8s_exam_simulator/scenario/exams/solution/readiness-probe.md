# ReadinessProbe
1. Create YAML file from kubectl

    `kubectl run readiness-probe --image=busybox:1.36.1 -n ::namespace:: --dry-run=client -o=yaml > readiness-probe.yaml -- sh -c "touch /tmp/ready && sleep 1d"`
2. Edit YAML file

   `vim readiness-probe.yaml`
```
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    run: readiness-probe
  name: readiness-probe
  namespace: ::namespace::
spec:
  containers:
  - image: busybox:1.36.1
    name: readiness-probe
    args:
    - sh
    - -c
    - touch /tmp/ready && sleep 1d
    resources: {}
    readinessProbe:                             # add
      exec:                                     # add
        command:                                # add
        - sh                                    # add
        - -c                                    # add
        - cat /tmp/ready                        # add
      initialDelaySeconds: 5                    # add
      periodSeconds: 10                         # add
  dnsPolicy: ClusterFirst
  restartPolicy: Always
```
3. apply YAML file

   `kubectl apply -f readiness-probe.yaml`