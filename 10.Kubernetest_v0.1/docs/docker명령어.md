0. Docker machine이 동작하고 있는 지 확인
 # broswer에서 http://192.168.99.100:2376 입력 하고 다음과 같은 응답이 나오면 된다.
   Client sent an HTTP request to an HTTPS server.

1. Docker image 생성
 # docker build -t  <이미지이름>:<태그> <경로> .
 $ docker build -t authorizationserver:latetest .

 # maven Docker 빌드
 ## 첫번째 방법 com.spotify plugin 사용 방법 (Multimodule Docker Build 가 가능)
    
    <profile>
    <id>dockerBuild</id>
			<build>
				<plugins>
					<plugin>
						<artifactId>maven-resources-plugin</artifactId>
						<executions>
							<execution>
								<id>copy-resources</id>
								<phase>validate</phase>
								<goals>
									<goal>copy-resources</goal>
								</goals>
								<configuration>
									<outputDirectory>${basedir}/target</outputDirectory>
									<resources>
										<resource>
											<directory>src/main/docker</directory>
											<filtering>true</filtering>
										</resource>
									</resources>
								</configuration>
							</execution>
						</executions>
					</plugin>
					<plugin>
						<groupId>org.apache.maven.plugins</groupId>
						<artifactId>maven-antrun-plugin</artifactId>
						<version>1.8</version>
						<executions>
							<execution>
								<id>prepare-package</id>
								<phase>package</phase>
								<inherited>false</inherited>
								<configuration>
									<target>
									<!--docker build -t pcsopcso/authorizationserver:0.0.0-SNAPSHOT .}"/-->
										<exec executable="docker">
											<arg value="build"/>
											<arg value="-t"/>
											<arg value="${docker.image.prefix}/${project.artifactId}:${project.version}"/>
											<arg value="target"/>
										</exec>
									</target>
								</configuration>
								<goals>
									<goal>run</goal>
								</goals>
							</execution>
						</executions>
					</plugin>
				</plugins>
			</build>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
    </profile>

 ### mvn clean install -p [profileid]  
    $ mvn clean package -P dockerBuild      

 ## 두번째 방법 com.spotify plugin 사용 방법 (단점은 Multimoule Docker Build 불가능)
    <plugin>
      <groupId>com.spotify</groupId>
      <artifactId>docker-maven-plugin</artifactId>
      <version>1.2.0</version>
      <configuration>
        <imageName>${docker.image.prefix}/${project.artifactId}:${project.version}</imageName>
        <dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>
        <resources>
          <resource>
            <targetPath>/</targetPath>
            <directory>${project.build.directory}</directory>
            <include>${project.build.finalName}.jar</include>
          </resource>
        </resources>
      </configuration>
    </plugin>
    
    $ mvn clean package docker:build -f ".\pom.xml"

2. Docker image 확인
 $ docker images

3. Docker image 실행(이미지 실행, 컨테이너 실행)
 # docker run -p [호스트 포트:컨테이너 포트] [dockerImageName]
 $ docker run -p 8080:8080 pcsopcso/authorizationserver:latest

 # 옵션 설명
 # --detach, -d 	  : 컨테이너를 실행할 때 백그라운드("detached mode")로 실행할 지에 대한 여부를 설정합니다.
    -d 		          : 옵션을 사용하면 백그라운드로 실행되며 기본 설정은 포그라운드 모드입니다.
                      포그라운드 모드로 실행하면 표준 입력, 출력 및 오류가 콘솔에 보여질 수 있습니다.
 # --env, -e	      : 컨테이너를 실행할 때 간단한 환경 변수를 설정합니다. (배열 사용 불가)
                      Dockerfile에 정의된 변수는 덮어씁니다.
 # --link	          : 해당 컨테이너로 다른 컨테이너를 연결합니다.
                      Docker 는 환경 변수나 호스트 파일을 통해 소스 컨테이너의 연결 정보를 해당 컨테이너로 노출합니다.
                      소스 컨테이너의 환경변수가 공유되기 때문에 보안에 취약하므로 해당 옵션을 통한 연결은 권장되지 않는 방식입니다.
                      network를 정의하여 사용하기를 권장합니다.
# --publish, -p	    : 컨테이너의 포트를 호스트의 특정 포트나 특정 범위를 지정하여 매핑하여 publish합니다.
# --publish-all, -P : 컨테이너 내부의 모든 포트를 호스트의 임시 포트 범위 내의 임의의 포트에 매핑하여 publish합니다.
# --volume, -v	    : bind 방식으로 호스트 디렉토리에 컨테이너의 데이터를 마운트합니다.
# --mount	          : 이 옵션은 volume, bind, tmpfs 세가지 방식으로 마운트하도록 지원합니다.
                      --volume 플래그가 지원하는 대부분의 옵션을 지원하지만 문법에 차이가 있습니다.
                      --volume 플래그보다 --mount 플래그의 사용을 권장합니다.
# -it		            : Docker 는 컨테이너에 bash 쉘을 생성하여 컨테이너의 STDIN에 연결된 가상 TTY을 할당합니다.

4. 어플리케이션 확인
 $ docker ps

5. 컨테이너 중지
 # docker stop [컨테이너ID]
 $ docker stop f4345141af03

6. 컨테이너 목록 확인
 # docker ps 명령어에 -a 옵션을 통해 전체 컨테이너 목록을 확인
 $ docker ps -a

7. 컨테이너 삭제
 # docker rm [컨테이너ID]
 $ docker rm f4345141af03

8. Docker Hub 로그인
 $ docker login
 # docker login 시 에러가 발생하는 경우
 $ winpty docker login

9. 로컬 이미지 태깅 및 Docker Hub 이미지 업로드
 # docker tag image [username/repository:tag]
 $ docker tag pcsopcso/authorizationserver:latest

 # 로컬이미지 Docker Hub 이미지 업로드
 ## docker push [imagename]
 $ docker push pcsopcso/authorizationserver:latest

10. Maven을 이용한 이미지 태깅 및 Docker Hub 이미지 업로드
 # porm.xml 아래와 같이 설정
		<profile>
            <id>dockerRelease</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-antrun-plugin</artifactId>
                        <version>1.8</version>
                        <executions>
                            <execution>
                                <phase>install</phase>
                                <inherited>false</inherited>
                                <configuration>
                                    <target>
                                      <exec executable="docker">
                                        <arg value="tag"/>
                                        <arg value="${docker.image.prefix}/${project.artifactId}:${project.version}"/>
                                        <arg value="${docker.image.prefix}/${project.artifactId}:latest"/>
                                      </exec>
                                      <exec executable="docker">
                                        <arg value="push"/>
                                        <arg value="${docker.image.prefix}/${project.artifactId}:latest"/>
                                      </exec>
                                    </target>
                                </configuration>
                                <goals>
                                    <goal>run</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>

 # mvn clean install -p [profileid]  
 $ mvn -e clean install -P dockerBuild,dockerRelease

11. 업로드한 이미지 실행
 # docker run -p [호스트 포트:컨테이너 포트] [username/repository:tag]
 $ docker run -p 8080:8080 pcsopcso/gs-spring-boot-docker:1.0

 ## docker ToolBox 설치시에는 Host IP와 매핑되지 않을 경우 아래와 같은 방식으로 테스트 진행 
 ### docker run -p [Docker machine IP:호스트 포트:컨테이너 포트] [username/repository:tag]
 $ docker run -p [docker machin ip]:8080:8080 pcsopcso/gs-spring-boot-docker:1.0

12. Docker Image 삭제
 # docker rmi [repository:tag]
 $ docker rmi pcsopcso/hello:v0.1  

13. 사용하지 않는 사용하지 않은 모든 컨테이너, 이미지, 네트워크 및 볼륨은 삭제
 $ docker system prune
 
 # 개별적으로 컨테이너, 이미지, 네트워크, 볼륨 삭제
 $ docker container prune
 $ docker image prune
 $ docker network prune
 $ docker volume prune

 # 컨테이너가 생성되지 않은(실행 및 중지 상태가 아닌) 모든 이미지를 삭제한다. 
   종료되었지만 프로세스에 남아있는 컨테이너의 이미지는 삭제하지 못한다
 $ docker image prune -a -f(Yes or No)

 # Docker에서 모든 컨테이너 중지 시키고 삭제
 $ docker stop $(docker ps -a -q)
 $ docker rm $(docker ps -a -q)

 # 모든 이미지 삭제
 ## 리눅스에서 삭제
 $ docker system prune -af or docker rmi $(docker images -q)

 ## 윈도우에서 삭제
 $ docker system prune -af

 ### powershell 에서 삭제
 $ Powershell delete images
 $ docker images -aq | foreach {docker rmi $_}


