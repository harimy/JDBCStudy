/*====================
   ScoreMain.java
====================*/

/*
○ 성적 처리 프로그램 구현 → 데이터베이스 연동 → ScoreDAO, ScoreDTO 클래스 활용
   
   여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
   총점, 평균을 연산하여 출력하는 프로그램을 구현한다.
   
실행 예)

1번 학생 성적 입력(이름 국어 영어 수학) : 선혜연 90 80 70
2번 학생 성적 입력(이름 국어 영어 수학) : 이상화 100 90 80
3번 학생 성적 입력(이름 국어 영어 수학) : 이유림 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학) : .

-------------------------------------------------------
번호	이름	국어	영어	수학	총점	평균
-------------------------------------------------------
 1	   선혜연	 90 	 80		 70		 xxx    xx.x	
 2     이상화	100		 90		 80		 xxx	xx.x	
 3	   이유림	 80		 85		 80		 xxx	xx.x
-------------------------------------------------------
*/

package com.test;

import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

public class ScoreMain
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		
		ScoreDAO dao = new ScoreDAO();
	
		// 인원 수 
		int count = dao.count();
		
		while (true)
		{
			System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
			String name = sc.next();
			
			// 반복문 탈출 조건
			if (name.equals("."))
				break;
			
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			// ScoreDTO 객체 생성
			ScoreDTO dto = new ScoreDTO();
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			
			dao.add(dto);
		}		
		
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("번호	이름	국어	영어	수학	총점	평균");
		System.out.println("-------------------------------------------------------");
		
		for (ScoreDTO dto : dao.lists())
		{
			System.out.printf("%3s %6s %5d %7d %7d %7d %8.2f\n"
							 , dto.getSid()
							 , dto.getName()
							 , dto.getKor()
							 , dto.getEng()
							 , dto.getMat()
							 , (dto.getKor() + dto.getEng() + dto.getMat())
							 , (double)(dto.getKor() + dto.getEng() + dto.getMat()) / 3);
		}

		System.out.println("-------------------------------------------------------\n");

		sc.close();
		DBConn.close();
		System.out.println("데이터베이스 연결 닫힘");
		System.out.println("프로그램 종료");

	}
	
}

/* [실행 결과]
1번 학생 성적 입력(이름 국어 영어 수학) : 선혜연 90 80 100
2번 학생 성적 입력(이름 국어 영어 수학) : 이상화 100 90 80
3번 학생 성적 입력(이름 국어 영어 수학) : 이유림 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학) : .

-------------------------------------------------------
번호	이름	국어	영어	수학	총점	평균
-------------------------------------------------------
  1    선혜연    90      80     100     270    90.00
  2    이상화   100      90      80     270    90.00
  3    이유림    80      85      80     245    81.67
-------------------------------------------------------

데이터베이스 연결 닫힘
프로그램 종료
*/
