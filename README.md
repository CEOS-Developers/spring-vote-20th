# spring_vote_20th

## 💡 구현 기능

**ERD**

![vote (1) (1)](https://github.com/user-attachments/assets/97c8d1d2-0247-48b3-845d-d1a0ec284b9a)

1. **Member**
    - 로그인한 사용자(멤버)를 의미한다.
    - 투표 권한이 있으며, 아래의 투표에 각각 최대 **1회 참여**할 수 있다.
        - **vote_back, vote_front**: 개발자 투표
        - **vote_team**: 팀 투표
2. **Team**과 **Developer**
    - 투표 가능한 **후보**를 의미한다.
    - 각 객체는 자신의 투표수를 나타내는 **count** 필드를 가진다.

<img width="586" alt="스크린샷 2025-01-06 오전 12 18 48" src="https://github.com/user-attachments/assets/a190ea29-7616-458d-9652-fd8407afac06" />

**Auth**

- JWT 방식 로그인을 사용합니다.
    - **Access Token**은 헤더에 발급하고, **Refresh Token**은 쿠키로 발급합니다.
    - **Refresh Token**은 별도의 `Refresh` 엔티티에 저장하여 검증합니다.
    - 로그아웃 시 **Refresh Token**을 삭제하여 재사용을 방지합니다.

**Vote**

- 에러 처리
    1. 같은 팀에 투표: `BAD_REQUEST_TEAM`
    2. 다른 파트에 투표: `BAD_REQUEST_DEVELOPER`
    3. 중복 투표
        - 개발자 투표: `ALREADY_VOTE_DEVELOPER`
        - 팀 투표: `ALREADY_VOTE_TEAM`

## 🧩 배포

### 가장 간단한 배포 방법

1. 스프링부트에서 BootJar을 실행해주면 build/libs 폴더 안에 jar 파일이 생성됨
2. 아래 명령어를 통해 jar 파일을 내 인스턴스에 옮겨줌
    
    ```java
    scp -i "{my-key}.pem" ./build/libs/{jar-file-name}.jar ubuntu@{퍼블릭 IP}:/home/ubuntu
    ```
    
    - **scp (Secure Copy Protocol)**
        - SSH를 이용해 파일을 안전하게 복사하는 명령어이다. 로컬 시스템과 원격 시스템 간, 또는 원격 시스템들 간에 파일을 전송할 때 사용한다.
3. 인스턴스 터미널에 접속해 아래 명령어를 통해 jar 파일을 백그라운드에서 실행시켜주면 끝!
    
    ```java
    nohup java -jar backend-0.0.1-SNAPSHOT.jar &
    ```
    
    - **nohup (no hang up)**
        - 터미널이 종료되어도 명령어 실행이 중단되지 않도록 보장하는 명령어이다.
    - **&**
        - 명령을 백그라운드에서 실행하는 쉘 연산자이다.

## 🚨 트러블 슈팅

프론트까지 배포를 하고 백엔드에 api 요청을 했는데 다음과 같은 에러가 발생했다.

<img width="1029" alt="KakaoTalk_Photo_2025-01-05-20-33-15 (1)" src="https://github.com/user-attachments/assets/19a42380-1cda-408d-be4d-538adfe8765c" />

### Mixed Content 란?

브라우저에서 HTTPS로 제공되는 웹 페이지가 보안되지 않은 HTTP 리소스를 로드하거나 요청할 때 발생하는 상황을 말한다.

HTTPS는 데이터가 암호화 되어 안전하게 전송됨을 보장하는데 HTTP는 암호화되지 않은 연결을 사용하여 HTTPS 페이지에서 HTTP 리소스를 로드하면 보안 문제가 발생한다고 한다.

→ 우리 프론트가 Https로 배포를 했는데, 백엔드에서 http로 배포를 해서 발생한 문제였다. 우리 백엔드 서버에 Https 를 적용해주기로 했다.

### https 적용하기

일단 도메인이 없던 상황이라, 도메인 없이 https를 적용할 수 있는 방법을 찾던 중 caddy를 알게되었습니다.

**caddy의 주요 역할**

1. 자동으로 tls 인증서를 발급해준다
2. nginx.conf와 같은 Caddyfile이 존재해, 리버스 프록시 설정이 가능하다.

**CaddyFile**

```java
{
        admin 0.0.0.0:2020
}

[ec2 PUBLIC IP주소].nip.io {

        tls [이메일 주소]
        reverse_proxy localhost:8080
       
}
```

- `[ec2 PUBLIC IP주소]`: EC2 인스턴스의 퍼블릭 IP 주소를 포함합니다.
- **`.nip.io`**: **동적 DNS 서비스**로, 특정 IP 주소를 포함하는 임시 도메인 이름을 생성해 줍니다.
    - 예: `123.45.67.89.nip.io`로 접속하면 `123.45.67.89`로 연결됩니다.
    - → 이를 통해 도메인이 없어도 HTTPS를 사용할 수 있습니다.
- `tls [이메일 주소]` : Let's Encrypt를 사용해 인증서를 자동으로 발급받도록 이메일 설정을 해줍니다.
    - 만약 **tls internal** 을 적는다면 외부 인증서 발급 없이 caddy 자체 인증서를 생성하여 발급합니다. → 주의할 점은 로컬에서만 사용 가능하다는 점..ㅎㅎ

**동작 흐름**

`https://123.45.67.89.nip.io/api/data`

1. 클라이언트가 도메인(`123.45.67.89.nip.io`)로 HTTPS 요청.
2. Caddy가 요청 수신 → 인증서 확인 및 암호화된 연결 설정.
3. Caddy는 요청을 분석 후, `/api/data`를 `localhost:8080`으로 전달.
4. 내부 애플리케이션(Spring Boot 서버)이 요청을 처리하고 응답 반환.
5. Caddy가 응답을 받아 클라이언트에게 전달.
