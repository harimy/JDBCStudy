package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoreProcess
{
	private ScoreDAO dao;
	
	public ScoreProcess()
	{
		dao = new ScoreDAO();
	}
	
	// 성적 입력 
	public void scoreInsert()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			do
			{
				// 사용자 입력받기
				int count = dao.count();
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
				String name = sc.next();
				
				// 반복 탈출 
				if(name.equals("."))
					break;
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				// dto 객체 구성
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				// dao의 add() 메소드 호출 
				int result = dao.add(dto);
				
				if (result > 0)
				{
					System.out.println("성적 입력이 완료되었습니다.");
				}
				
			} while (true);
			
			// 데이터베이스 연결 종료 
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	// 성적 전체 출력 
	public void scoreSelectAll()
	{
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			// 전체 인원 수 받아오기 
			int count = dao.count();
			
			// 성적 출력
			System.out.println();
			System.out.printf("전체 인원 수 : %d\n", count);
			System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
			for (ScoreDTO dto : dao.lists())
			{
				System.out.printf( "%3s %6s %5d %7d %7d %7d %8.1f %5d\n"
						, dto.getSid(), dto.getName()
						, dto.getKor(), dto.getEng(), dto.getMat()
						, dto.getTot(), dto.getAvg(), dto.getRank() );
			}
			
			// 데이터베이스 연결 종료 
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
	// 이름 검색
	public void searchName()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("검색할 이름 입력 : ");
		String name = sc.next();
		
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.lists(name);
			
			if (arrayList.size() > 0)
			{
				System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
				for (ScoreDTO dto : dao.lists(name))
				{
					System.out.printf("%3s %6s %5d %7d %7d %7d %8.1f %5d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank());
				}
			}
			else
				System.out.println("검색된 결과가 없습니다.");
			
			// 데이터베이스 연결 종료 
			dao.close();	
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}		
		
	}
	
	// 성적 수정
	public void scoreUpdate()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("수정할 번호 입력 : ");
		int sid = sc.nextInt();
		
		try
		{
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
			
			if (arrayList.size() > 0)
			{
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %6s %5d %7d %7d %7d %8.1f %5d\n"
           		         , dto.getSid(), dto.getName()
           		         , dto.getKor(), dto.getEng(), dto.getMat()
           		         , dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.print("수정할 데이터 입력(이름 국어 영어 수학) : ");
				String name = sc.next();
		        int kor = sc.nextInt();
		        int eng = sc.nextInt();
		        int mat = sc.nextInt();
		        
		        // dto 객체 구성
		        ScoreDTO dto = new ScoreDTO();
		        dto.setName(name);
		        dto.setKor(kor);
		        dto.setEng(eng);
		        dto.setMat(mat);
		        dto.setSid(String.valueOf(sid));
		        
		        // 데이터 수정
		        int result = dao.modify(dto);
				
		        if (result > 0)
				{
					System.out.println("수정이 완료되었습니다.");
				}
			
			}
			else
				System.out.println("수정 대상이 존재하지 않습니다.");
			
			// 데이터베이스 연결 종료 
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

	}
	
	// 성적 삭제
	public void scoreDelete()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.printf("삭제할 번호 입력 : ");
		int sid = sc.nextInt();
		
		try
		{
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			if (arrayList.size() > 0)
			{
				System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n"
	        		           , dto.getSid(), dto.getName()
	        		           , dto.getKor(), dto.getEng(), dto.getMat()
	        		           , dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.println();
		        System.out.print(">> 정말 삭제하시겠습니까?(Y/N) : ");
		        String response = sc.next();
		        
		        if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(sid);
					
					if (result > 0)
					{
						System.out.println("삭제가 완료되었습니다.");
					}
				}
		        else
		        	System.out.println("삭제가 취소되었습니다.");
		
			}
			else
				System.out.println("삭제할 대상이 존재하지 않습니다.");
			
			// 데이터베이스 연결 종료 
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		

	}
	
}
