# InitContainer
___
 Create a Deployment name `my-website` of image `nginx:1.25.2` in Namespace `::namespace::` with an InitContainer. 
 
 The InitContainer named `init-con` of image `busybox:1.36.1` that should create html file contains `hello world!` save with name `index.html` hint [execute `echo "hello world!" > /tmp/web-content/index.html`] and mount it with container `my-website` in path `/usr/share/nginx/html` with volume named `web-content`