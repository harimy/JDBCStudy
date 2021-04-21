SELECT USER
FROM DUAL;
--==>> SCOTT

TRUNCATE TABLE TBL_SCORE;
--==>> Table TBL_SCORE이(가) 잘렸습니다.

DROP SEQUENCE SCORESEQ;
--==>> Sequence SCORESEQ이(가) 삭제되었습니다.

--○ 시퀀스 생성
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ이(가) 생성되었습니다.

--○ 쿼리문 준비

-- 1. 데이터 입력 쿼리문 구성
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)
VALUES(SCORESEQ.NEXTVAL, '홍길동', 90, 80, 70);
--> 한 줄 구성
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '홍길동', 90, 80, 70)
;
--==>> 1 행 이(가) 삽입되었습니다.


-- 2. 리스트 출력 쿼리문 구성
SELECT SID, KOR, ENG, MAT
     , KOR + ENG + MAT AS TOT
     , (KOR + ENG + MAT)/3 AS AVG
     , RANK() OVER (ORDER BY (KOR + ENG + MAT)) AS RANK
FROM TBL_SCORE
ORDER BY SID ASC;
--> 한 줄 구성
SELECT SID, KOR, ENG, MAT, KOR + ENG + MAT AS TOT, (KOR + ENG + MAT)/3 AS AVG, RANK() OVER (ORDER BY (KOR + ENG + MAT)) AS RANK FROM TBL_SCORE ORDER BY SID ASC
;
--==>> 1	90	80	70	240	80	1


-- 3. 인원 수 조회 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
--> 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;
--==>> 1


-- 4. 이름 검색 쿼리문 구성 
SELECT *
FROM
(
    SELECT SID, NAME, KOR, ENG, MAT
         , KOR + ENG + MAT AS TOT
         , (KOR + ENG + MAT)/3 AS AVG
         , RANK() OVER (ORDER BY (KOR + ENG + MAT)) AS RANK
    FROM TBL_SCORE
)
WHERE NAME = '홍길동';
--> 한 줄 구성
SELECT * FROM (SELECT SID, NAME, KOR, ENG, MAT, KOR + ENG + MAT AS TOT, (KOR + ENG + MAT)/3 AS AVG, RANK() OVER (ORDER BY (KOR + ENG + MAT)) AS RANK FROM TBL_SCORE) WHERE NAME = '홍길동'
;
--==>> 1	홍길동	90	80	70	240	80	1


-- 5. 번호 검색 쿼리문 구성
SELECT *
FROM
(
    SELECT SID, NAME, KOR, ENG, MAT
         , KOR + ENG + MAT AS TOT
         , (KOR + ENG + MAT)/3 AS AVG
         , RANK() OVER (ORDER BY (KOR + ENG + MAT)) AS RANK
    FROM TBL_SCORE
)
WHERE SID = 1;
--> 한 줄 구성 
SELECT * FROM (SELECT SID, NAME, KOR, ENG, MAT, KOR + ENG + MAT AS TOT, (KOR + ENG + MAT)/3 AS AVG, RANK() OVER (ORDER BY (KOR + ENG + MAT)) AS RANK FROM TBL_SCORE) WHERE SID = 1
;
--==>> 1	홍길동	90	80	70	240	80	1


-- 6. 데이터 수정 쿼리문 구성
UPDATE TBL_SCORE
SET NAME = '고길동', KOR = 10, ENG = 10, MAT = 10
WHERE SID = 1;
--> 한 줄 구성
UPDATE TBL_SCORE SET NAME = '고길동', KOR = 10, ENG = 10, MAT = 10 WHERE SID = 1
;
--==>> 1 행 이(가) 업데이트되었습니다.

COMMIT;
--==>> 커밋 완료.


-- 7. 데이터 삭제 쿼리문 구성
DELETE
FROM TBL_SCORE
WHERE SID = 1;
--> 한 줄 구성
DELETE FROM TBL_SCORE WHERE SID = 1
;
--==>> 1 행 이(가) 삭제되었습니다.

COMMIT;
--==>> 커밋 완료.





