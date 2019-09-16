1. maven Dockerfile build
    $ "c:\Users\Daniel\Documents\Workspace\java\hello\mvnw" clean package docker:build -f "c:\Users\Daniel\Documents\Workspace\java\hello\pom.xml"
2. docker run
    $ docker run -p 6000:8080 pcsopcso/hello:v0.1
3. docker ps
4. docker stop [containerID]
5. docker rm [containerID]
6. docker rmi [repository:tag]
    $ docker rmi pcsopcso/hello:v0.1  