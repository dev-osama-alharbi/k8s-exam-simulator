# ReadinessProbe

Create a single Pod named `readiness-probe` in Namespace `::namespace::` of image `busybox:1.36.1`. The Pod should have a readiness-probe executing `cat /tmp/ready`. It should initially wait `5` and periodically wait `10` seconds. This will set the container ready only if the file `/tmp/ready` exists.

The Pod should run the command `touch /tmp/ready && sleep 1d`, which will create the necessary file to be ready and then idles. Create the Pod and confirm it starts.