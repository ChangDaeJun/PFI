# PFI
### 여러 주식 가격을 조합하여 자신만의 맞춤 지수를 만들 수 있게 해주는 웹 프로젝트입니다.

<추후 홈페이지 사진>

## 목차
* [프로젝트 계획](#1-프로젝트-계획)

  * [서비스 목표](#1-1-서비스-목표)
  
  * [사용할 기술](#1-2-사용할-기술)
  
  * [서비스 페이지](#1-3-서비스-페이지-구상)
  
  * [계발 계획](#-1-4-계발-계획)
  
* [프로젝트 진행 기록](#2-프로젝트-진행-기록)

## 1. 프로젝트 계획
### 1-1. 서비스 목표

1. 한국, 미국 시장의 상장회사 주식 가격과 코스피, 나스닥, 환율 등 주요 지표 제공(30일, 그래프)
2. 나만의 지수 제작 서비스

### 1-2. 사용할 기술

이름 | 종류 | 사용 목적
---|---|---|
스프링 프레임워크 & 스프링부트 | 웹 개발 프레임워크 | 웹 프로젝트 구동 및 테스트 코드 작성
스프링 시큐리티 | 보안 프레임워크 | 웹 사용자에 대한 인증, 권한부여, 암호화
thymeleaf | 템플릿 엔진 | 웹 뷰 작성, 데이터 교환, 로그인 시 CSRF 토큰을 이용한 공격 보호
MySQL | 데이터베이스 | 유저 정보 저장
jdbcTemplate | ?? | 데이터베이스 컨트롤
jsoup | 라이브러리 | 웹에서 주식 정보 추출
구글 차트 | API | List기반의 주식 데이터를 그래프로 표현

### 1-3. 서비스 페이지 구상
<img width="553" alt="image" src="https://user-images.githubusercontent.com/97227920/185072494-eb563fd4-1c78-4b00-82e7-0f85acc07036.png">


### 1-4. 개발 계획
우선순위 | 개발 내용 | 개발자
---| ---| ---|
1 | 주식 서비스 제작, 홈페이지에 대표 주식 또는 지수 가격과 그래프 노출 | 장대준
1 | 주식 제작 서비스, 해당 서비스 페이지 | 장대준
2 | 관리자 계정 제작, 관리자 페이지 제작 | 장대준
2 | 유저 정보 관리 페이지 제작, 관련 서비스 제작 | 장대준
2 | 유저가 만든 지수 정보 저장 서비스 | 장대준
3 | 유저별 홈페이지 커스텀 기능 | 장대준
3 | 주식 간 비율 비교 서비스 및 페이지 | 장대준
4 | 주식 전체 데이터 CSV 파일로 프로젝트에 올리기 | 장대준

## 2. 프로젝트 진행 기록
날짜 | 개발 내용 | 개발자
---| ---| ---|
2022년 7월 29일 | 프로젝트 파일 생성 후 깃 관리 시작, 유저 데이터베이스 연동과 유저 도메인 작성 | 장대준
2022년 7월 30일 | 유저 서비스 작성 | 장대준
2022년 7월 31일 ~ 8월 10일 | 유저 데이터 보안을 위한 방안 고민, 스프링 시큐리티 학습 및 적용 | 장대준
2022년 8월 11일 ~ 8월 12일 | 주식 도메인, 주식 데이터베이스 연동 | 장대준
2022년 8월 13일 ~ 8월 14일 | 주식 데이터 크롤링 작성, 데이터베이스에 저장. | 장대준
-----개편 이후----- | ----------개편 이후---------- |
[2022년 8월 17일](#2022년-8월-17일) | 계획 수정, 스프링 시큐리티 적용, User도메인 Memeber로 이름 변경 | 장대준

### 2022년 8월 17일
1. 주요 변경점
 - readme 파일 생성, 프로젝트 계획 관리
 - 주식 도메인 삭제
 - 기존 User 도메인을 Member도메인으로 이름 변경(Repository, Service 포함)
 - User, SecurityUserDetails -> Member로 병합

2. 스프링 시큐리티 적용
<img width="604" alt="image" src="https://user-images.githubusercontent.com/97227920/185153128-edd1ae67-cb54-4dea-9f35-30a97a18dedd.png">
 - Member에 implements UserDetails 후, 추상메서드 구현.
 <img width="400" alt="image" src="https://user-images.githubusercontent.com/97227920/185156228-06a5ebd1-d2cb-41bc-a474-571079937152.png">
 - UserDetails에 implements UserDetailsService 후, 메서드 구현.
<img width="400" alt="image" src="https://user-images.githubusercontent.com/97227920/185156893-65c6e025-ce97-4da0-808f-7abcb30bdfc4.png">

