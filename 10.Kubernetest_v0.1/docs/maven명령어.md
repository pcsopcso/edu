# maven 멀티 모듈일때 개별로 패키징 하는 방법
## mvn package -rf :[모듈 이름]
 $ mvn package -rf :todo-service 

# Docker file로 이미지 생성
## mvn clean install -p [profileid]  
 $ mvn -e clean install -P dockerBuild,dockerRelease

# mavent 멀티 모듈일때 패키징 후 docker image 생성
## mvn clean -rf :[모듈 이름]  install -P dockerBuild,dockerRelease   
 $ mvn clean -rf :todo-service  install -P dockerBuild,dockerRelease   
