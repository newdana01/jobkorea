CREATE TABLE companies (
  company_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(1000),
  company_type VARCHAR(1000),
  logo_url VARCHAR(2000),
  ad_level VARCHAR(1000),
  created DATE
);

-- companies 테이블 더미 데이터 삽입
INSERT INTO companies (name, company_type, logo_url, ad_level, created) VALUES
('삼성전자', '대기업', 'https://example.com/samsung.png', 'firstvvip', '2025-01-01'),
('현대자동차', '대기업', 'https://example.com/hyundai.png', 'firstvvip', '2025-01-02'),
('SK하이닉스', '대기업', 'https://example.com/skhynix.png', 'vvip', '2025-01-03'),
('LG화학', '대기업', 'https://example.com/lgchem.png', 'vvip', '2025-01-04'),
('카카오', '중견기업', 'https://example.com/kakao.png', 'vip', '2025-01-05'),
('네이버', '중견기업', 'https://example.com/naver.png', 'vip', '2025-01-06'),
('현대모비스', '대기업', 'https://example.com/mobis.png', 'top1000', '2025-01-07'),
('포스코', '대기업', 'https://example.com/posco.png', 'top1000', '2025-01-08'),
('신한은행', '대기업', 'https://example.com/shinhan.png', 'firstvvip', '2025-01-09'),
('KT', '대기업', 'https://example.com/kt.png', 'vvip', '2025-01-10'),
('넷마블', '중견기업', 'https://example.com/netmarble.png', 'vip', '2025-01-11'),
('CJ제일제당', '대기업', 'https://example.com/cj.png', 'top1000', '2025-01-12'),
('롯데케미칼', '대기업', 'https://example.com/lotte.png', 'none', '2025-01-13'),
('두산중공업', '대기업', 'https://example.com/doosan.png', 'none', '2025-01-14'),
('한화솔루션', '대기업', 'https://example.com/hanwha.png', 'top1000', '2025-01-15'),
('쿠팡', '중견기업', 'https://example.com/coupang.png', 'vip', '2025-01-16'),
('배달의민족', '중견기업', 'https://example.com/baemin.png', 'vip', '2025-01-17'),
('현대글로비스', '대기업', 'https://example.com/glovis.png', 'top1000', '2025-01-18'),
('SK텔레콤', '대기업', 'https://example.com/skt.png', 'firstvvip', '2025-01-19'),
('라인플러스', '중견기업', 'https://example.com/line.png', 'vvip', '2025-01-20');


CREATE TABLE jobs (
  job_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  company_id BIGINT REFERENCES companies(company_id),
  title VARCHAR(1000),
  deadline DATE
);


-- jobs 테이블 더미 데이터 삽입
INSERT INTO jobs (title, deadline, company_id) VALUES
('시니어 백엔드 개발자', '2025-08-10', 1),
('프론트엔드 개발자', '2025-08-15', 1),
('AI 엔지니어', '2025-08-20', 2),
('자동차 설계 엔지니어', '2025-08-25', 2),
('데이터 분석가', '2025-08-12', 3),
('반도체 공정 엔지니어', '2025-08-18', 3),
('화학공학 연구원', '2025-08-22', 4),
('배터리 개발자', '2025-08-30', 4),
('모바일 앱 개발자', '2025-08-14', 5),
('서비스 기획자', '2025-08-16', 5),
('웹 개발자', '2025-08-19', 6),
('검색 엔진 최적화 전문가', '2025-08-21', 6),
('부품 개발 엔지니어', '2025-08-17', 7),
('품질 관리 전문가', '2025-08-23', 7),
('철강 생산 관리자', '2025-08-13', 8),
('기술 영업 담당자', '2025-08-24', 8),
('금융 데이터 분석가', '2025-08-11', 9),
('기업 금융 컨설턴트', '2025-08-26', 9),
('네트워크 엔지니어', '2025-08-15', 10),
('클라우드 아키텍트', '2025-08-27', 10),
('게임 개발자', '2025-08-18', 11),
('게임 QA 엔지니어', '2025-08-28', 11),
('식품 R&D 연구원', '2025-08-16', 12),
('마케팅 매니저', '2025-08-29', 12),
('화학공학 기술자', '2025-08-14', 13),
('물류 운영 관리자', '2025-08-20', 15),
('데이터 사이언티스트', '2025-08-22', 16),
('물류 시스템 개발자', '2025-08-23', 16),
('서비스 운영 매니저', '2025-08-19', 17),
('UI/UX 디자이너', '2025-08-25', 20);

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

-- ad_contracts 테이블 더미 데이터 삽입
INSERT INTO ad_contracts (company_id, job_id, section, ad_level, start_date, end_date, status, created_at) VALUES
(1, 1, 'firstvvip', 'firstvvip', '2025-07-01 00:00:00', '2025-08-01 23:59:59', 'active', '2025-07-01'),
(1, 2, 'firstvvip', 'firstvvip', '2025-07-01 00:00:00', '2025-08-01 23:59:59', 'active', '2025-07-01'),
(2, 3, 'firstvvip', 'firstvvip', '2025-07-05 00:00:00', '2025-08-05 23:59:59', 'active', '2025-07-05'),
(2, 4, 'firstvvip', 'firstvvip', '2025-07-05 00:00:00', '2025-08-05 23:59:59', 'active', '2025-07-05'),
(3, 5, 'vvip', 'vvip', '2025-07-10 00:00:00', '2025-08-10 23:59:59', 'active', '2025-07-10'),
(3, 6, 'vvip', 'vvip', '2025-07-10 00:00:00', '2025-08-10 23:59:59', 'active', '2025-07-10'),
(4, 7, 'vvip', 'vvip', '2025-07-12 00:00:00', '2025-08-12 23:59:59', 'active', '2025-07-12'),
(4, 8, 'vvip', 'vvip', '2025-07-12 00:00:00', '2025-08-12 23:59:59', 'active', '2025-07-12'),
(5, 9, 'vip', 'vip', '2025-07-15 00:00:00', '2025-08-15 23:59:59', 'active', '2025-07-15'),
(5, 10, 'vip', 'vip', '2025-07-15 00:00:00', '2025-08-15 23:59:59', 'active', '2025-07-15'),
(6, 11, 'vip', 'vip', '2025-07-16 00:00:00', '2025-08-16 23:59:59', 'active', '2025-07-16'),
(6, 12, 'vip', 'vip', '2025-07-16 00:00:00', '2025-08-16 23:59:59', 'active', '2025-07-16'),
(7, 13, 'top1000', 'top1000', '2025-07-18 00:00:00', '2025-08-18 23:59:59', 'active', '2025-07-18'),
(7, 14, 'top1000', 'top1000', '2025-07-18 00:00:00', '2025-08-18 23:59:59', 'active', '2025-07-18'),
(8, 15, 'top1000', 'top1000', '2025-07-20 00:00:00', '2025-08-20 23:59:59', 'active', '2025-07-20'),
(8, 16, 'top1000', 'top1000', '2025-07-20 00:00:00', '2025-08-20 23:59:59', 'active', '2025-07-20'),
(9, 17, 'firstvvip', 'firstvvip', '2025-07-22 00:00:00', '2025-08-22 23:59:59', 'active', '2025-07-22'),
(9, 18, 'firstvvip', 'firstvvip', '2025-07-22 00:00:00', '2025-08-22 23:59:59', 'active', '2025-07-22'),
(10, 19, 'vvip', 'vvip', '2025-07-25 00:00:00', '2025-08-25 23:59:59', 'active', '2025-07-25'),
(10, 20, 'vvip', 'vvip', '2025-07-25 00:00:00', '2025-08-25 23:59:59', 'active', '2025-07-25'),
(11, 21, 'vip', 'vip', '2025-07-01 00:00:00', '2025-07-15 23:59:59', 'expired', '2025-07-01'),
(12, 23, 'top1000', 'top1000', '2025-07-10 00:00:00', '2025-08-10 23:59:59', 'active', '2025-07-10'),
(15, 26, 'top1000', 'top1000', '2025-07-12 00:00:00', '2025-08-12 23:59:59', 'active', '2025-07-12'),
(16, 27, 'vip', 'vip', '2025-07-15 00:00:00', '2025-08-15 23:59:59', 'pending', '2025-07-15'),
(20, 30, 'vvip', 'vvip', '2025-07-20 00:00:00', '2025-08-20 23:59:59', 'active', '2025-07-20');

-- 외래 키 제약 조건 추가 (H2에서 REFERENCES만으로 외래 키가 자동 생성되지 않을 경우)
ALTER TABLE jobs ADD CONSTRAINT fk_jobs_company_id FOREIGN KEY (company_id) REFERENCES companies(company_id);
ALTER TABLE ad_contracts ADD CONSTRAINT fk_ad_contracts_company_id FOREIGN KEY (company_id) REFERENCES companies(company_id);
ALTER TABLE ad_contracts ADD CONSTRAINT fk_ad_contracts_job_id FOREIGN KEY (job_id) REFERENCES jobs(job_id);