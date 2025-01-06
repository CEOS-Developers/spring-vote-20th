# spring_vote_20th
---
## DB 설계
**BaseEntity**
  - createdAt: 생성 날짜/시간 (LocalDateTime)
  - updatedAt: 수정 날짜/시간 (LocalDateTime)
  - deletedAt

**Member**

- memberId (PK)
- loginId
- password
- email
- name (이름)
- part(ENUM)
- team(ENUM)
- createdAt

**Part(ENUM)**

```sql
public enum Part {
    FRONT(Arrays.asList("A", "B", "C")),
    BACK(Arrays.asList("D", "E", "F"));

    private final List<String> partMembers;
}
```

**Team(ENUM)**

```sql
public enum Team {
    PHOTO_GROUND,
    ANGEL_BRIDGE,
    PEDAL_GENIE,
    CAKE_WAY,
    COFFEE_DEAL
}
```

**Vote (추상클래스)**

- voteId (PK)
- memberId (FK - 투표한 사용자ID)
- createdAt

**LeaderVote**

- part(ENUM)
- partMember(String)

**TeamVote**

- team (ENUM)
- editedAt


---
## API 명세서 & 테스트

<img width="600" src="https://github.com/user-attachments/assets/586d7cd8-39bb-4efc-9127-649fd29a7789" />

### **1) 회원 가입**

**201 - 회원가입 성공**

<img width="600" alt="스크린샷 2025-01-06 13 37 40" src="https://github.com/user-attachments/assets/0f97fbfe-0ba1-4213-9741-2148fb38d039" />

**409 - 아이디 중복 가입 시도하는 경우**

<img width="600" alt="스크린샷 2025-01-06 13 38 27" src="https://github.com/user-attachments/assets/09dc47cc-7cbc-46f1-b847-82cd4b610f8a" />

### **2) 로그인**

<img width="600" alt="스크린샷 2025-01-06 13 39 42" src="https://github.com/user-attachments/assets/295a36a8-5b60-47ff-82c2-4e9892ea1e25" />

### **3) 회원 프로필 조회**

<img width="600" alt="스크린샷 2025-01-06 13 42 01" src="https://github.com/user-attachments/assets/314fa2ee-b54c-4073-9b66-92ccf55af71e" />

### **4) 후보 조회**

**팀 조회**

<img width="600" alt="스크린샷 2025-01-06 13 43 39" src="https://github.com/user-attachments/assets/8cb749ad-7c56-4713-a4a6-95a6714a52a3" />

**파트별 조회**

<img width="600" alt="스크린샷 2025-01-06 13 44 09" src="https://github.com/user-attachments/assets/dc61dcd2-4776-44be-8562-1cc66bb8272e" />

<img width="600" alt="스크린샷 2025-01-06 13 44 28" src="https://github.com/user-attachments/assets/206207af-a031-4871-8f4d-3a277353d0dd" />


### **5) 투표 생성**

**팀 투표**

**201 - 생성 성공**

<img width="600" alt="스크린샷 2025-01-06 13 45 50" src="https://github.com/user-attachments/assets/6af3baa3-4981-4f60-a093-3cf96848b47b" />

**400 - 자신의 팀에 투표 시도**

<img width="600" alt="스크린샷 2025-01-06 13 46 37" src="https://github.com/user-attachments/assets/4fa025bd-00d4-4fa2-8017-9bf27c8201cc" />


**409 - 중복 투표 시도**

<img width="600" alt="스크린샷 2025-01-06 13 47 05" src="https://github.com/user-attachments/assets/0d075c03-97e1-4328-b28d-c4e1f4a49f02" />


**파트 리더 투표**

**201 - 생성 성공**

<img width="600" alt="스크린샷 2025-01-06 13 49 00" src="https://github.com/user-attachments/assets/52b9c43c-0285-4cbd-9dd3-d78ff95d5612" />

**400 - 다른 파트에 투표 시도**

<img width="600" alt="스크린샷 2025-01-06 13 47 57" src="https://github.com/user-attachments/assets/b64ec8fd-6a30-44f9-ba92-20f21801439a" />


**400 - 해당 파트에 속하지 않는 구성원에 투표 시도**

<img width="600" alt="스크린샷 2025-01-06 13 48 24" src="https://github.com/user-attachments/assets/282df89e-8dde-437c-9cf0-b136e9982630" />

**409 - 중복 투표 시도**

<img width="600" alt="스크린샷 2025-01-06 13 49 18" src="https://github.com/user-attachments/assets/e8da61d2-8cb4-43f9-85db-2392f2f75310" />

### 6) 투표 결과 조회
**1. 팀 투표 결과 조회**

<img width="600" alt="스크린샷 2025-01-06 13 50 57" src="https://github.com/user-attachments/assets/70218b0a-61f5-4bf0-b584-6e63a8ef5eb6" />


**2. 파트 투표 결과 조회**

<img width="600" alt="스크린샷 2025-01-06 13 51 21" src="https://github.com/user-attachments/assets/91ab2e6d-02ce-4248-81a0-e3023c5a20e1" />


<img width="600" alt="스크린샷 2025-01-06 13 53 00" src="https://github.com/user-attachments/assets/1ec08c47-0b1b-465c-aea8-19aa3db3ed37" />
