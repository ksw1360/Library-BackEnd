## 🚀 실행 방법

### 요구사항
- Java 21
- Gradle

### 실행

```bash
# 프로젝트 클론
git clone https://github.com/본인아이디/레포이름.git
cd Library

# 실행
./gradlew bootRun
```

### 접속
- API 서버: http://localhost:9092
- H2 콘솔: http://localhost:9092/h2-console

### API 목록

| Method | URL | 설명 |
|--------|-----|------|
| GET | /books | 전체 조회 |
| GET | /books/{id} | 단건 조회 |
| POST | /books | 도서 등록 |
| PATCH | /books/{id}/loan | 대출 처리 |
| DELETE | /books/{id} | 삭제 |

### 기술 스택
- Spring Boot 3.5.14
- Java 21
- JPA / Hibernate
- H2 Database (In-Memory)
- Lombok
