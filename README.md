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

<img width="600" src="https://github.com/user-attachments/assets/7cfa1438-74b2-40ed-abae-7bfdf0002275" />

### **2) 로그인**

<img width="600" src="https://github.com/user-attachments/assets/392b46d6-edd4-4009-bd18-490259e77cd0" />

### **3) 회원 프로필 조회**

<img width="600" src="https://github.com/user-attachments/assets/0cc0fa32-a52a-4860-a945-684536515cec" />

### **4) 후보 조회**

**팀 조회**

<img width="600" src="https://github.com/user-attachments/assets/b0ed6967-647c-446b-9693-550185681d71" />

**파트별 조회**

<img width="600" src="https://github.com/user-attachments/assets/f4d3b6a2-800a-45ea-8e15-b0d2451123fb" />

<img width="600" src="https://github.com/user-attachments/assets/d659f1ce-0ba9-4305-b930-072d4bbe8fe6" />

### **5) 투표 생성**

**팀 투표**

**201 - 생성 성공**

<img width="600" src="https://github.com/user-attachments/assets/d2b33141-960d-4896-b115-d66b905c3e53" />

**400 - 자신의 팀에 투표 시도**

<img width="600"  src="https://github.com/user-attachments/assets/20576297-6897-455c-8545-09b3ef572fd2" />


**409 - 중복 투표 시도**

<img width="600" src="https://github.com/user-attachments/assets/97ad797d-8f43-42f2-9c64-329c15187502" />

**파트 리더 투표**

**201 - 생성 성공**

<img width="600" src="https://github.com/user-attachments/assets/c67ce97c-0fb3-4471-8c66-130fbd82b5d2" />

**400 - 다른 파트에 투표 시도**

<img width="600" src="https://github.com/user-attachments/assets/4c2cdf3b-e0ca-4fbc-a4c6-f40fb7e2835c" />

**400 - 해당 파트에 속하지 않는 구성원에 투표 시도**

<img width="600" src="https://github.com/user-attachments/assets/8e46b4e2-3a86-4fc5-bdec-092004b08511" />

**409 - 중복 투표 시도**

<img width="600" src="https://github.com/user-attachments/assets/fc71f145-ed0f-4278-82d3-1b70fe1b3d18" />


