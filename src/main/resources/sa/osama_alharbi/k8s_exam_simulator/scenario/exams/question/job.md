# Job

 Create a Job. This Job should run image `busybox:1.36.1` and execute `sleep 2 && echo done`. It should be in namespace `::namespace::`, run a total of `3` times and should execute `2` runs in parallel.

Start the Job and check its history. Each pod created by the Job should have the label `id: good-job` The job should be named `new-job` and the container `new-job-container`.