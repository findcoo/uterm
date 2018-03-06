# uterm
**URL 간결화 서비스**

특정 URL( 예: https://mail.google.com/mail/u/0/#inbox/1618abef65f )을 짧은 형태의 URL( 예: https://localhost.com/aSDdasd9 ) 로 변환하고
변환된 URL을 통해 원래 URL을 참조할 수 있게합니다. 이 프로젝트는 **8자 내로 간결화한다는 제약 조건**이 있습니다. 

## 어떻게 줄일 것인가?
일반적으로 문자 혹은 바이트의 축소에는 해쉬 함수 혹은 Base64처럼 진법 변환을 통한 방법이 있습니다.

1. 단축된 문자는 하나의 원본 URL을 가르켜야한다.
2. 문자의 수는 8자를 초과하면 안된다.

1번 조건을 만족하는 해쉬 함수는 암호학적 목적으로 사용되는 해쉬 함수들이 만족하지만 그러한 해쉬 함수들은 2번 조건을 만족하지 못합니다.
반면 Base64처럼 진법 변환을 통한 값은 오직 하나의 값을 뜻하는 단사 함수의 성질을 갖게 됩니다. Base64는 64진법을 사용하지만 URL 단축에는 오로지
영문과 숫자만을 사용할 것임으로 62진법인 Base62함수를 사용하여 문자의 길이를 단축시킵니다.

## 사용법
* 사전 요구 사항
    * jdk1.8.0
    * ^docker 17.12
    * ^npm 5.6.0 or ^yarn 1.3.2
### 빌드 및 실행
* 일반적인 방법

    사전에 mysql을 설정해야 합니다. 설정 정보는 [application.yml](src/main/resources/application.yml)을 확인해주세요.
    ```shell
    yarn install # or npm install 
    yarn build # or npm build
    gradle clean build
    java -jar build/libs/uterm-0.0.1-SNAPSHOT.jar
    ```
* docker-compose를 이용한 방법

    `docker-compose up -d` 설정된 주소는 http://localhost:8080<br/>
    컨테이너가 안정화되어 실행 될 때까지 기다려주세요.

* 개발 환경

    서버 환경은 `gradle bootRun` 웹 UI 환경은 `yarn start` 명령으로 개발환경을 실행 할 수 있습니다.<br/>
    미리 mysql 환경 설정이 필요합니다.
## History
* spring boot + data jpa(mysql)를 이용한 rest api 서버 어플리케이션 환경 구축.
* base62 알고리즘 설계
* 서비스의 가용성 확보를 위해서는 중복된 URL 막도록 SHA-256 함수를 이용한 해쉬 검증.
* **TODO** 테스트 데이터베이스 환경 구축.
* **TODO** sha256의 충돌을 대비하기 위해 url, hashedUrl을 unique index로 구성하는 방안을 생각해보기.
* 브라우저 캐쉬 해결을 위한 응답 헤더 추가
* **TODO** 삭제 혹은 여타 다른 이유로 공백이 되는 id값을 다시 채우기 위해 참조 빈도수를 이용한 공간 할당 알고리즘이 필요하게됨.

## 개발참조
* [Testing](doc/TESTING.md)