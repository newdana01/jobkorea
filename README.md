## 잡코리아 메인화면 백엔드 서버 구축


\*\* 제출 전 확인 리스트

- 메인 화면을 토대로 기능 요구사항 리스트
- 구현 시 주요 고려 사항
- 어떤 도구 사용 했는지
- 어떤 프롬프트로 질의 했는지

## 1. 사용 도구

### (1) 분석 및 설계

- 구글링
- Grok3

### (2) 개발

- JAVA 17
- Spring Boot 3.5^
- H2 Database
- JPA, QueryDSL, Lombok
- gradle
- Postman

## 2. 기능 요구 사항 분석

### (1) 요구사항 개요

잡코리아 메인 화면의 주요 기능은 **채공 공고 조회 기능** 입니다.</br>
FirstVVIP 채용관, VVIP 채용관, VIP 채용관, 1000대 기업 공채별 공고를 확인 할 수 있으며 각 채용 공고는 기업이 지불한 광고비 및 기업 정보에 따라 노출 우선 순위와 UI 품질이 차등 제공됩니다.

### (2) 기능 요구 사항 상세

#### 채용 공고 조회 API

- 엔드 포인트: `/api/jobs`
- 요청 파라미터:
  - `section`: String. 채용관 구분 (firstvvip, vvip, vip, top1000)
  - `size`: int. 반환할 공고 수
  - `page`: int. 요청할 페이지 번호
- 응답 데이터:

```JSON
{
    "count": 12,
    "page": 1,
    "total": 30,
    "size": 12,
    "data": [
        {
            "jobId": 123,
            "title": "시니어 백엔드 개발자",
            "companyName": "VVIP Corp",
            "deadline": "2025-08-20T23:59:59Z",
            "logoUrl": "https://example.com/vvip-logo.png",
            "adLevel": "firstvvip",
        }
    ]
}
```

- 예외 처리:
  - 유효하지 않은 section 값인 경우 400 Request 응답
  - 반환 데이터 없을 경우 빈 배열 반환
- 추가 기능:
  - 광고 우선 순위 점수 계산
  - ui스타일 정보를 포함하여 프론트 엔드에서 차등 렌더링 지원

### (3) 구현 시 고려 사항

- 계약 상태 관리: 광고 계약의 상태(예: active, expired, pending)를 추적하고, 만료된 공고는 자동으로 제외.

- 광고비 차등화: FirstVVIP(최고 광고비, 예: 월 1,000만원), VVIP(고급, 예: 월 500만원), VIP(중급, 예: 월 200만원), 1000대 기업 공채(기본 또는 무료)로 구분.

- 계약 기간: 계약 시작일(start_date)과 종료일(end_date)을 명확히 관리하며, 만료 시 ad_level을 none으로 업데이트.

### (4) DB 설계

### companies

기업 정보를 담은 테이블

```sql
CREATE TABLE companies (
  company_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(1000),
  company_type VARCHAR(1000),
  logo_url VARCHAR(2000),
  ad_level VARCHAR(1000),
  created DATE
);
```

### jobs

채용공고를 담은 테이블

```sql
CREATE TABLE jobs (
  job_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(1000),
  deadline DATE
);
```

### ad_contracts

광고 계약 정보를 저장하며, 채용공고와 기업을 연결.

```sql
CREATE TABLE ad_contracts (
  contract_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  company_id BIGINT REFERENCES companies(company_id),
  job_id BIGINT REFERENCES jobs(job_id),
  section VARCHAR(20) NOT NULL, -- firstvvip, vvip, vip, top1000
  ad_level VARCHAR(20) NOT NULL, -- firstvvip, vvip, vip, top1000
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  status VARCHAR(20) NOT NULL, -- active, expired, pending
  created_at DATE
);
```

</br></br>

## 사용한 주요 프롬프트

### 기능 요구사항 분석 (Grok3)

#### (1) 메인 화면 전체 기능 분석 요청

> 나는 "잡코리아" 라는 사이트의 메인 화면 백엔드 개발을 위한 기능 요구사항 리스트를 작성하려고 해.
> 어떤 기능들이 있는지, 각 기능의 요구 사항들은 무엇인지 분석하고 가독성있게 항목화해서 알려줘.
> 헤더, 푸터, GNB 는 제외하고 로그인, 회원가입, 스크랩 같은 부가적인 기능은 제외해.
> 채용공고, 기업 정보를 메인 화면에 노출 시키는 것을 중점적으로 개발할거야.
> 참고로 README 로 작성할꺼니까 보기좋게 정리 부탁해.
> https://www.jobkorea.co.kr/

#### (2) 상세 기능 분석 요청

> 내가 봤을 때는 각 채용관의 구분은 일종의 광고 구좌 같아. 채용 기업들이 돈을 지불하고 더 좋은 자리를 선점하는 거지. 잡코리아의 안내 사이트를 보니, FirstVVIP 채용관에 갈수록 가격이 비싸고 더 눈에 띄고 보기 좋은 UI로 표시 되는 것 같아. 이런 경우일 때 어떤 요구사항이 있을까? 그리고 DB 설계에는 어떤 내용들이 추가 될까?

> 광고 기반 채용관 노출기능 구현시 고려해야할 사항엔 뭐가 있을까

### 기능 구현 (Grok3)

#### (1) 더미 데이터 작성 요구

> 난 spring boot 내장 데이터 베이스 h2 를 사용할건데
> (상단의 sql create 문 첨부) <br>
> 이 테이블을 생성할거야. 테스트를 위한 더미 데이터가 필요한데 INSERT 문을 작성해줘. 데이터를 각 테이블 마다 최소 20개 이상은 만들어줘. 테이블 간 관계가 필요하다면 그것을 추가하는 sql도 추가해도 좋아
> sql 파일로 만들어줘
