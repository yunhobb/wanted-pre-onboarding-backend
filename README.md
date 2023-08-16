# 원티드 프리온보딩 백엔드 인턴십(8월) - 선발 과제


## 1. 지원자의 성명
안녕하세요. **정윤호** 입니다.

## 2. 애플리케이션의 실행 방법 ( with docker )
```text
개발환경 
java 11
springboot 2.7.14
MySQL 8.0
```

1. git clone
```text
https://github.com/yunhobb/wanted-pre-onboarding-backend.git
```
2. docker compoise up으로 MySQL과 backend 컨테이너 실행
```text
docker compose up --build 
```

## 3. 데이터베이스 테이블 구조 
![image](https://github.com/yunhobb/wanted-pre-onboarding-backend/assets/87285536/3aec55c0-841c-4b5d-8799-13525e59ba37)


## 4. 구현한 API의 동작을 촬영한 데모 영상
[Link](https://drive.google.com/file/d/18XLTILW1Qd6wIPRJz0CQHE51fBfdmiNi/view?usp=share_link)

## 5. 구현 방법 및 이유에 대한 간략한 설명

* 과제 1. 사용자 회원가입 엔드포인트
  * DTO에서 @Email, @Min(size = 8) 어노테이션을 사용해 유효성 검사를 진행
  * PasswordEncryptor를 사용해 password 단방향 암호화 구현

* 과제 2. 사용자 로그인 엔드포인트
  * JWT토큰을 Header에 실어서 보내고 별도로 저장을 하지 않음
  * Header에 실리는 토큰이 유요한 값인지 확인하기 위해 ArgumentResolver를 구현
  * PasswordEncryptor를 사용해 password 단방향 암호화 검증
  
* 과제 3. 새로운 게시글을 생성하는 엔드포인트
  * 게시글을 생성하기 위해 제목과 내용을 받음
  
* 과제 4. 게시글 목록을 조회하는 엔드포인트
  * offset 방식으로 페이지네이션을 구현 (default: offset = 0, size = 10)

* 과제 5. 특정 게시글을 조회하는 엔드포인트
  * postId로 특정 게시물 데이터 접근
* 과제 6. 특정 게시글을 수정하는 엔드포인트
  * postId로 특정 게시물 수정
* 과제 7. 특정 게시글을 삭제하는 엔드포인트
  * deleted_at 컬럼을 DB에 추가해 NULL이 아닐시 soft delete 되도록 구현 
* 추가사항
  * Service Layer 테스트 코드 작성완료 
  * docker compose와 JIB를 사용해 어플리케이션 환경을 구성
  * 재사용성이 높은 공용 IdResponse를 DTO를 사용 
  * 전역 에러처리를 위해 GlobalErroorAdvice를 추가
  




## 6. API 명세
[Postman LINK](https://documenter.getpostman.com/view/21887547/2s9Y5QzkMD)
