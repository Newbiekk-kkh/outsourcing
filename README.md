# Outsourcing TeamProject 
---
## 🛠️ Tools :  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=github&logoColor=Green"> <img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/>  <img alt="Java" src ="https://img.shields.io/badge/intellijidea-000000.svg?&style=for-the-badge&logo=intellijidea&logoColor=white"/>
---
## 👨‍💻 Period : 2024/12/3 ~ 2024/12/~
---
## 👨‍💻 ERD
![image](https://github.com/user-attachments/assets/8a1dc77e-c463-4d20-afb3-a531473d8040)


---
## 👨‍💻 API명세서
<a-href>https://documenter.getpostman.com/view/39378739/2sAYBUCXcc</a-hrefhttps://nbcamp2024.slack.com/archives/C083CP3LS8K/p1733289247289799

---
## 👨‍💻 About Project

- 회원가입/로그인
  - 아이디는 이메일 형식 
  - 탈퇴한 이메일 재사용 불가능
  - 비밀번호는 대소문자 포함 영문 + 숫자 + 특수문자 최소 1글자씩 포함 
  - 권한 부여 : 유저/사장님 
- 가게
  - 최소 주문 금액 존재 
  - 사장님 권한의 유저만 최대 3개까지 운영 가능 
  - 폐업시 가게 상태만 변경, 가게 추가 등록 가능
  - 일반 유저 폐업 가게 조회 x 
- 메뉴 
  - 사장님 권한을 가진 본인 가게에서만 메뉴를 생성, 수정 가능
  - 삭제시 메뉴 상태만 변경, 가게 메뉴 조회 시 x ,주문 내역 조회는 가능
- 주문 
  - 유저는 하나의 메뉴 주문 가능 / 최소 주문 금액 이상, 가게 오픈 ~ 마감시간 이내 주문 가능 
  - 사장님은 주문 수락, 주문 상태 변경 
- 리뷰
  - 주문 건에 대한 리뷰 작성 / 별점 부여, 배달 완료 상태의 주문은 리뷰 작성 x 
  - 가게 정보 기준 다건 조회 / 생성일자 기준, 본인 작성 리뷰 조회 x , 별점 범위 조회 가능

---
## 🥵 Trouble Shooting & 🚀 Refactoring
- Member 
  - 정규 표현식으로 이메일이나 비밀번호를 검사할때 가져온 표현식 중 모든 특수문자가 포함이 아니여서 특수문자 일부를 추가했다.

---
## 😭 아쉬운점
- 패키지 구조를 정확히 정하지 않아 패키지 구조 변경하는 작업이 여러번 이루어졌다. 
- 공통적인 로직이 들어있는 클래스를 처음에 미리 만들어놓고 시작하지 않아 같은 기능을 하는 중복되는 클래스를 정리해야했다.
