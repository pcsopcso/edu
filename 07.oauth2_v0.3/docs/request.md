1. 클라이언트 서버 등록
- http://localhost:8080/client/dashboard

2. 토큰을 발급 받기 위한 Code 발급 
너가 등록된 클라이언트라는 것을 알겠는데 다시 내가 보낸 코드와 함께 토큰 발급 요청을 해라
http://{Authorization 서버 주소}:{포트}/oauth/authorize?client_id={등록한 Client ID}&redirect_uri={Callback 주소(클라이언트 주소)}&response_type={code}&scope={read}&state={내가 보낸 상태 코드}
Ex) http://192.168.99.102:8080/oauth/authorize?client_id=a321f920-0fc2-4ffa-8007-6b85768b86e4&redirect_uri=http://192.168.99.102:9000/callback&response_type=code&scope=read&state=xyz

code redirect
http://localhost:9000/callback?code=1qezTK&state=xyz


3. 토큰 발급 요청
Basic Auth : 
username - 315ce741-08cc-4fbb-a3c1-b45a72fc2da4 / 
password - e2c5128a-cdd4-464c-a812-43dcd890642d
Headers : Content-Type - application/x-www-form-urlencoded (body Pre-request Script로 넣어야 동작)
Method : POST
Url : http://localhost:8080/oauth/token?code={code}&grant_type=authorization_code&scope=read&redirect_uri=http://localhost:9000/callback

4. Rrsource 요청
GET - http://localhost:8090/api/profile
Headers : Authorization Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjcyNjQ2ODcsInVzZXJfbmFtZSI6InBjc29AbmF2ZXIuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjcxNGQzMjUyLTZjYWQtNGMyMC1iY2EyLThmZDNlY2Y1OTQwZCIsImNsaWVudF9pZCI6IjZhNzI5NDVjLWZiYjctNDc2ZC1iMDAwLWIxYjRlMWNlMDYxYiIsInNjb3BlIjpbInJlYWQiXX0.UxTWnUgH_hV2F5uBgVMnTu7EVs4bUjE36Gn1hKHZwQE

