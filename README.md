# 빌리어 - BILLIAR

### 프로젝트 소개
https://www.notion.so/4cb43b6d606b4261aaf0d96c2ac66aaa

### 이 앱은 언제 사용하나요 ?
* 집에 전자기기, 자전거, 기타 물품들을 구매했는데 사용하고 있지 않은경우
* 물건을 사기전에 한번 직접 사용해보고 싶을경우

### 구체적인 사례?
* 번개장터, 당근마켓과 비슷한 사용자에게 거래 서비스를 제공하는 기능을 만들어 보고 싶었습니다.
* 비슷한 시스템으로 물건을 특정 기간동안 도서관처럼 대여해주는 기능을 만들어 본다면 재미있을것 같아 선택하게 되었습니다.

------------------------------------------------------------------------------------------------

### 필수기능

* 상품
- [x] 상품등록 (/rent/products)
- [x] 상품조회 (/rent/products)
- [x] 상품 상세조회 (/rent/products/{productId})
- [x] 상품수정 (/rent/products/{productId})
- [x] 상품삭제 (/rent/products/{productId})

* 회원
- [x] 회원가입 (/rent/user)
- [x] 중복확인 (/rent/user/userEmailCheck)
- [x] 사용자수정 (/rent/user/{userEmail})
- [x] 로그인 (/rent/login)
- [ ] 로그아웃

* 대여

- [x] 상품대여 (/rent/rental)
- [x] 내가 신청한 대여 상품 (/rent/rental/myrental/{userEmail})
- [x] 내가 올린 상품의 대여신청목록 (/rent/rental/reservation/{userEmail})
- [ ] 상품문의

* ERD

![image](https://user-images.githubusercontent.com/50834204/138483240-d11d2200-8b94-48e9-b1c4-8e9fccd58575.png)

### 시나리오

* 미가입 사용자
첫 화면(상품리스트) > 상품 조회, 상세조회 / 검색  / 회원가입 기능만 제공

* 가입사용자
첫 화면(상품리스타) > 로그인 > 첫화면
  * 상품등록 > 상품정보 입력 > 등록완료 (완료)
  * 상품수정 > 상품상세조회 > 수정버튼 > 수정정보 입력 > 수정완료 > 첫화면 (완료)
  * 상품삭제 > 상품상세조회 > 삭제버튼 > 삭제완료 > 첫화면 (완료)
  * 상품조회, 상세조회, 검색
  * 마이페이지 > 사용자 정보 변경 > 수정정보 입력 > 수정완료 > 마이페이지 
  * 상품대여 > 상품상세조회 > 대여신청 > 대여정보입력 > 대여완료 > 대여페이지
  * 상품문의 > 상품상세조회 > 문의 > 채팅방생성 > 채팅
  * 상품반납 > 상품반납처리 > 반납처리대기 > 반납처리완료
