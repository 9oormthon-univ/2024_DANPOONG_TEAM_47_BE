## 공유 주차장 서비스 - 공주 API Server Repository
![1](https://github.com/user-attachments/assets/bcc6c3a8-ab32-406b-95c4-624f9dad8932)
<hr>

### Server Architecture
![image](https://github.com/user-attachments/assets/286fc728-745c-4120-acca-449fa7fa0b6e)

- Frontend : React Native
- Backend : Spring Boot
- Proxy : Nginx
- CI/CD : Github Actions, AWS S3, AWS EC2, AWS CodeDeploy

### Commit Message Prefix

| **Prefix**      | **설명**                                                   |
|------------------|-----------------------------------------------------------|
| **`feat`**      | 새로운 기능 추가                                           |
| **`fix`**       | 버그 수정                                                 |
| **`refactor`**  | 코드 리팩토링                            |
| **`build`**     | 빌드 시스템, 의존성 추가/수정                              |
| **`chore`**     | 코드 변경 외의 자잘한 작업 (빌드 파일, 설정 변경 등)        | 
| **`docs`**      | 문서 수정                                                 |
| **`test`**      | 테스트 코드 추가/수정                                      | 
| **`perf`**      | 성능 개선                                                 | 

### 기술 스택
- Spring Boot 3.3.5
- Java 17
- Spring Security 6.3.4
- JJWT
- AWS S3
- AWS CodeDeploy
- AWS EC2
- MySQL
- Github Actions

### 로그인 처리 방법
- React Native의 Kakao SDK를 사용하여 로그인을 진행하고, 카카오로부터 받은 access token을 이용하여 서버에서 자체적으로 JWT를 발급
