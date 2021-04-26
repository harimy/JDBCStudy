SELECT USER
FROM DUAL;
--==>> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>> 
/*
1	한혜림	010-1111-1111
2	전혜림	010-2222-2222
3	장서현	010-3333-3333
4	이새롬	010-4444-4444
5	정주희	010-5555-5555
6	이희주	010-6666-6666
7	심혜진	010-7777-7777
*/


--○ CallableStatement 실습을 위한 프로시저 작성
--  프로시저명 : PRC_MEMBERINSERT
--  프로시저 기능 : TBL_MEMBER 테이블에 데이터를 입력하는 프로시저
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    VSID    TBL_MEMBER.SID%TYPE;
BEGIN
    -- 기존 SID 의 최대값 얻어오기
    SELECT NVL(MAX(SID), 0) INTO VSID
    FROM TBL_MEMBER;

    -- 데이터 입력
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES((VSID+1), VNAME, VTEL);
    
    -- 커밋
    COMMIT;
    
END;
--==>> Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.

--○ 생성된 프로시저 테스트(확인)
EXEC PRC_MEMBERINSERT('이상화', '010-1234-1234');
--==>> PL/SQL 프로시저가 성공적으로 완료되었습니다.

--○ 테이블 조회
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	한혜림	010-1111-1111
2	전혜림	010-2222-2222
3	장서현	010-3333-3333
4	이새롬	010-4444-4444
5	정주희	010-5555-5555
6	이희주	010-6666-6666
7	심혜진	010-7777-7777
8	이상화	010-1234-1234
*/

--○ 테이블 조회
SELECT *
FROM TBL_MEMBER
ORDER BY SID;


--○ CallableStatement 실습을 위한 프로시저 작성
--  프로시저명 : PRC_MEMBERSELECT
--  프로시저 기능 : TBL_MEMBER 테이블의 데이터를 ㅇ릭어오는 기능의 프로시저
--  ※ 『SYS_REFCURSOR』 자료형을 이용해 커서 다루기
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT   OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN VRESULT FOR
        SELECT SID, NAME, TEL
        FROM TBL_MEMBER
        ORDER BY SID;
    --CLOSE VRESULT;
    -- 여기서 CLOSE 로 닫아주게 되면 밖에서 꺼내쓸 수가 없음 
    
END;
--==>> Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.












