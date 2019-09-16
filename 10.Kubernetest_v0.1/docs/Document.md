# 1 Build failed, do you want to continue?
   ctrl + shift + p 를 눌러 아래 사항을 입생하여 확인한다.
    Java: Open Java Language Server Log File

# 2 javaX 관련 에러 발생할 경우 Dependencies 추가

    <dependencies>
		<dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.3.0-b170201.1204</version>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jaxb</groupId>
			<artifactId>jaxb-runtime</artifactId>
			<version>2.3.0-b170127.1453</version>
		</dependency>
		<dependency>
			<groupId>javax.activation</groupId>
			<artifactId>activation</artifactId>
			<version>1.1</version>
		</dependency>
	</dependencies>

# 3 JavaX 관련 장애 발생 Card 관련 오류 발생시 아래 디렉토리로 이동하여 모두 지우고 다시 컴파일 한다.
- C:\Users\{사용자}\AppData\Roaming\Code\User\workspaceStorage 
