# Service

Create a `ClusterIP` Service named `project-api-svc` in Namespace `::namespace::`. This Service should expose a single Pod named `project-api` of image `nginx:1.25.2`, create that Pod as well. The Pod should be identified by label `project: prod-api`. The Service should use tcp port redirection of `3333:80`.