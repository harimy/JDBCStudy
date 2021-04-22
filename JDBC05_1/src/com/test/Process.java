package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Process
{
	private MemberDAO dao;

	public Process()
	{
		dao = new MemberDAO();
	}
	
	//public void memberInsert()
	//public void memberSelectAll()
	//public void memberSearchName() 
	//public void memberUpdate()
	//public void memberDelete()
	
	// 직원 입력
	public void memberInsert() 
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			
			System.out.println();
			System.out.println("직원 정보 입력 ------------------------------------------------------------------\n");
			System.out.print("이름 : ");
			String name = sc.next();
			System.out.print("주민등록번호(yymmdd-nnnnnn) : ");
			String ssn = sc.next();
			System.out.print("입사일(yyyy-mm-dd) : ");
			String ibsadate = sc.next();
			System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/) : ");
			String city = sc.next();
			System.out.print("전화번호 : ");
			String tel = sc.next();
			System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : ");
			String buseo = sc.next();
			System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원) : ");
			String jikwi = sc.next();
			System.out.print("기본급(최소 840000원 이상) : ");
			int basicpay = sc.nextInt();
			System.out.print("수당 : ");
			int sudang = sc.nextInt();
			
			MemberDTO dto = new MemberDTO();
			dto.setName(name);
			dto.setSsn(ssn);
			dto.setIbsadate(ibsadate);
			dto.setCity(city);
			dto.setTel(tel);
			dto.setBuseo(buseo);
			dto.setJikwi(jikwi);
			dto.setBasicpay(basicpay);
			dto.setSudang(sudang);
			
			int result = dao.add(dto);
			if (result > 0)
				System.out.println("\n직원 정보 입력 완료~!!!");
			else
				System.out.println("\n직원 정보 입력 실패~!!!");
			System.out.println("---------------------------------------------------------------------------------");
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	// 직원 출력
	public void memberSelectAll()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			
			// 선택지 출력 
			System.out.println();
			System.out.println("1. 사번 정렬");
			System.out.println("2. 이름 정렬");
			System.out.println("3. 부서 정렬");
			System.out.println("4. 직위 정렬");
			System.out.println("5. 급여 내림차순 정렬");
			System.out.print(">> 선택(1~5, -1종료) : ");
			int sel = sc.nextInt();
			
			// 입력값 체크
			if (sel == -1)
				return;
			else if (sel > 0 && sel <= 5)
			{
				int count = dao.count();
				
				// 조회 결과 출력
				System.out.println();
				System.out.printf(" 전체 인원 : %d명\n", count);
				System.out.println(" 사번    이름       주민번호            입사일          지역      전화번호"
								 + "      부서    직위    기본급   수당     급여");
				for (MemberDTO dto : dao.lists(sel))
				{
					System.out.printf("%5d %5s %16s %21s %4s %15s %5s %4s %9d %7d %9d\n"
									, dto.getId(), dto.getName(), dto.getSsn(), dto.getIbsadate()
									, dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi()
									, dto.getBasicpay(), dto.getSudang()
									, (dto.getBasicpay() + dto.getSudang()) );
				}		
				
			}
			else
			{
				System.out.println("\n잘못된 번호 선택으로 프로그램 종료");
				return;
			}
			
		
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// 직원 검색
	public void memberSearchName() throws SQLException
	{
		
		try
		{
			Scanner sc = new Scanner(System.in);
			
			// 선택지 출력
			System.out.println();
			System.out.println("1. 사번 검색");
			System.out.println("2. 이름 검색");
			System.out.println("3. 부서 검색");
			System.out.println("4. 직위 검색");
			System.out.print(">> 선택(1~4, -1종료) : ");
			int sel = sc.nextInt();
			
			// 입력값 체크 
			if (sel == -1)
			{
				System.out.println("\n프로그램 종료");
				return;
			}
			else if (sel < 0 || sel > 5)
			{
				System.out.println("\n잘못된 번호 선택으로 프로그램 종료");
				return;
			}
			
			// 선택에 따른 안내문구 출력
			switch (sel)
			{
			case 1: System.out.print("\n검색할 사번 입력 : ");
				break;
			case 2: System.out.print("\n검색할 이름 입력 : ");
				break;
			case 3: System.out.print("\n검색할 부서 입력 : ");
				break;
			case 4: System.out.print("\n검색할 직위 입력 : ");
				break;
			}
			String val = sc.next();
			
			// 검색 결과 출력
			System.out.println();
			System.out.println(" 사번    이름       주민번호            입사일          지역      전화번호"
					 + "      부서    직위    기본급   수당     급여");
			for (MemberDTO dto : dao.search(sel, val))
			{
				System.out.printf("%5d %5s %16s %21s %4s %15s %5s %4s %9d %7d %9d\n"
						, dto.getId(), dto.getName(), dto.getSsn(), dto.getIbsadate()
						, dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi()
						, dto.getBasicpay(), dto.getSudang()
						, (dto.getBasicpay() + dto.getSudang()) );
			}

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	public void memberUpdate()
	{
		try
		{
			// 수정할 번호 입력받기
			Scanner sc = new Scanner(System.in);
			
			System.out.println();
			System.out.print("수정할 직원의 사번 입력 : ");
			String id = sc.next();
			
			ArrayList<MemberDTO> arrayList = dao.search(1, id);
			
			if (arrayList.size() > 0)
			{
				System.out.println();
				System.out.println(" 사번    이름       주민번호            입사일          지역      전화번호"
						 + "      부서    직위    기본급   수당     급여");
				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%5d %5s %16s %21s %4s %15s %5s %4s %9d %7d %9d\n"
							, dto.getId(), dto.getName(), dto.getSsn(), dto.getIbsadate()
							, dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi()
							, dto.getBasicpay(), dto.getSudang()
							, (dto.getBasicpay() + dto.getSudang()) );
				}
				
				
				System.out.println();
				System.out.println("수정할 데이터 입력 --------------------------------------------------------------\n");
				System.out.print("이름 : ");
				String name = sc.next();
				System.out.print("주민등록번호(yymmdd-nnnnnn) : ");
				String ssn = sc.next();
				System.out.print("입사일(yyyy-mm-dd) : ");
				String ibsadate = sc.next();
				System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/) : ");
				String city = sc.next();
				System.out.print("전화번호 : ");
				String tel = sc.next();
				System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : ");
				String buseo = sc.next();
				System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원) : ");
				String jikwi = sc.next();
				System.out.print("기본급(최소 840000원 이상) : ");
				int basicpay = sc.nextInt();
				System.out.print("수당 : ");
				int sudang = sc.nextInt();
				
				// MemberDTO 객체에 정보 입력
				MemberDTO dto = new MemberDTO();
				dto.setId(Integer.parseInt(id));
				dto.setName(name);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsadate);
				dto.setCity(city);
				dto.setTel(tel);
				dto.setBuseo(buseo);
				dto.setJikwi(jikwi);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);
				
				int result = dao.modify(dto);
				if (result > 0)
					System.out.println("\n직원 정보 수정 완료~!!!");
				else
					System.out.println("\n직원 정보 수정 실패~!!!");
				System.out.println("---------------------------------------------------------------------------------");
				
			}
			else
			{
				System.out.println("\n해당 번호의 직원이 존재하지 않음\n");
			}

			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}	
		
	}
	
	
	public void memberDelete()
	{
		
		try
		{
			// 삭제할 번호 입력받기
			Scanner sc = new Scanner(System.in);
			
			System.out.println();
			System.out.print("삭제할 직원의 사번 입력 : ");
			String id = sc.next();
			
			ArrayList<MemberDTO> arrayList = dao.search(1, id);
			
			if (arrayList.size() > 0)
			{
				System.out.println();
				System.out.println(" 사번    이름       주민번호            입사일          지역      전화번호"
						 + "      부서    직위    기본급   수당     급여");
				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%5d %5s %16s %21s %4s %15s %5s %4s %9d %7d %9d\n"
							, dto.getId(), dto.getName(), dto.getSsn(), dto.getIbsadate()
							, dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi()
							, dto.getBasicpay(), dto.getSudang()
							, (dto.getBasicpay() + dto.getSudang()) );
				}
				
				System.out.println();
				System.out.print(">> 정말 삭제하시겠습니까?(Y/N) : ");
				String response = sc.next();
				
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(Integer.parseInt(id));
					
					if (result > 0)
						System.out.println("\n삭제가 완료되었습니다.");
				}
				else
					System.out.println("\n삭제가 취소되었습니다.");
				
			}
			else 
			{
				System.out.println("\n해당 번호의 직원이 존재하지 않음\n");
			}
				
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	
}
