/*==================
   Process.java
==================*/

package com.test;

import java.util.Scanner;

public class Process
{
	// 주요 속성 구성 → 데이터베이스 액션 처리 전담 객체 → ScoreDAO
	private ScoreDAO dao;

	// 생성자
	public Process()
	{
		dao = new ScoreDAO();
	}
	
	// 성적 입력 기능
	public void sungjukInsert()
	{
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			// 레코드 수 확인
			int count = dao.count();
			
			Scanner sc = new Scanner(System.in);
			
			do
			{
				System.out.println();
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
				String name = sc.next();
				
				// 반복의 조건을 무너뜨리는 코드 구성
				if(name.equals("."))
					break;
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				// ScoreDTO 객체 구성
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				// dao 의 add() 메소드 호출
				int result = dao.add(dto);
				
				if (result > 0)
					System.out.println(">> 성적 입력이 완료되었습니다.");
				
			} while (true);
			
			// 데이터베이스 연결 종료
			dao.close();
			sc.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}//end sungjukInsert()
	
	// 성적 전체 출력
	public void sungjukSelectAll()
	{
		// 사전 작성
		/*
		try
		{
			dao.connection();
			
			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명", count);
			System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
			for (ScoreDTO dto : dao.lists())
			{
				System.out.printf("%3s %6s %5d %7d %7d %7d %8.2f %5d\n"
								, dto.getSid(), dto.getName(), dto.getKor(), dto.getEng(), dto.getMat()
								, dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		*/
		
		try
		{
			// dao 의 connection() 메소드 호출 → 데이터베이스 연결
			dao.connection();
			
			// dao 의 count() 메소드 호출 → 인원 수 확인
			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명", count);
			System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
			
			for (ScoreDTO dto : dao.lists())
			{
				System.out.printf("%3s %4s %4d %4d %4d %5d %5.1f %5d\n"
								, dto.getSid(), dto.getName(), dto.getKor(), dto.getEng(), dto.getMat()
								, dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			// dao 의 close() 메소드 호출 → 데이터베이스 연결 종료	
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}//end sungjukSelectAll
	
	// 이름 검색 출력
	public void sungjukSearchName()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			dao.connection();
			System.out.print("검색하실 이름을 입력하세요 : ");
			String name = sc.next();
			
			for (ScoreDTO dto : dao.lists(name))
			{
				System.out.printf("%3s %4s %4d %4d %4d %5d %5.1f %5d\n"
								, dto.getSid(), dto.getName(), dto.getKor(), dto.getEng(), dto.getMat()
								, dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			dao.close();
			sc.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}//end sungjukSearchName
	
	// 성적 수정   
	public void sungjukUpdate()
	{
		
	}
	
	// 성적 삭제
	public void sungjukDelete()
	{
		
	}
	
	
}
