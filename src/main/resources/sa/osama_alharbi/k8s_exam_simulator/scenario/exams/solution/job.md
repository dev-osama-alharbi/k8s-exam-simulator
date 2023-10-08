# S Pods
1. Create YAML file from kubectl

    `kubectl create job new-job --image=busybox:1.36.1 -n ::namespace:: --dry-run=client -o=yaml > job.yaml -- sh -c "sleep 2 && echo done"`
2. Edit YAML file

   `vim job.yaml`
```
piVersion: batch/v1
kind: Job
metadata:
  creationTimestamp: null
  name: new-job
  namespace: ::namespace::
spec:
  completions: 3          # add
  parallelism: 2          # add
  template:
    metadata:
      creationTimestamp: null
      labels:             # add
        id: awesome-job   # add
    spec:
      containers:
      - command:
        - sh
        - -c
        - sleep 2 && echo done
        image: busybox:1.36.1
        name: new-job-container # update
        resources: {}
      restartPolicy: Never
status: {}
```
3. apply YAML file

   `kubectl apply -f job.yaml`