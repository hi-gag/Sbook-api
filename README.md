# 스북 서버1

### 스택
- Spring boot 2.6.6
- MySQL 8.0
- Spring data jpa 2.6.6
- Jenkins 2.332.2

### 로컬 디렉토리에 클론
```shell
cd 클론받을 디렉토리
git clone https://github.com/hi-gag/Sbook-api.git
```

#### 자바 11(JDK 11) 이 없는 경우
아래 링크를 따라서 설치
```shell
[Mac OS] https://scbyun.com/1166
[Windows] https://crazykim2.tistory.com/478
```

#### 자바 11(JDK 11) 이 이미 있는 경우 혹은 설치 후
- 먼저 팀 노션 내 칸반보드 > 백엔드 > 환경변수 파일 확인 후 그대로 따라 해주세요
- 로컬 버전 실행을 좀 더 쉽게 바꾼 후 리드미 업데이트 하겠습니다!
```shell
cd sbook
./gradlew build -x test
cd build/libs
java -jar -Djasypt.encryptor.password=[암호] sbook-0.0.2-SNAPSHOT.jar
```
- 혹은 구글 드라이브에서 jar 파일 다운로드 후 (환경변수 파일 설정 불필요)
```shell
cd 다운받은 디렉토리
java -jar sbook-0.0.1-SNAPSHOT.jar
```

#### 실행 후 서버 url
```shell
http://localhost:8080
```

#### 혹은 배포된 서버 이용하시기를 추천드립니다!