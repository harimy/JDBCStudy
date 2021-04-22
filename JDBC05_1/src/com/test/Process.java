package com.test;

import java.util.Scanner;

public class Process
{
	private MemberDAO dao;

	public Process()
	{
		dao = new MemberDAO();
	}
	
	//public void MemberInsert()
	//public void MemberSelectAll()
	//public void MemberSearchName() 
	//public void MemberUpdate()
	//public void MemberDelete()
	
	// 직원 입력
	public void MemberInsert()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("직원 정보 입력 ------------------------------------------------------------------");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("주민등록번호(yymmdd-nnnnnn) : ");
		String ssn = sc.next();
		System.out.print("입사일(yyyy-mm-dd) : ");
		String ibsa = sc.next();
		System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/) : ");
		String city = sc.next();
		System.out.print("전화번호 : ");
		
		System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) :  ");
		System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원) : ");
		System.out.print("기본급(최소 %d원 이상) : ");
		System.out.print("수당 : ");
		
//		이름 : 선혜연
//		주민등록번호(yymmdd-nnnnnn) : 951102-2234567
//		입사일(yyyy-mm-dd) : 2020-02-12
//		지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/) : 경기
//		전화번호 : 010-1111-1111
//		부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : 개발부
//		직위(사장/전무/상무/이사/부장/차장/과장/대리/사원) : 사원
//		기본급(최소 840000원 이상) : 850000
//		수당 : 2000000

		System.out.println("직원 정보 입력 완료~!!!");
		System.out.println("---------------------------------------------------------------------------------");
		
		
	}
	
	// 직원 출력
	public void MemberSelectAll()
	{
		
	}
	
	// 직원 검색
	public void MemberSearchName()
	{
		
	}
	
	
	
	
	
}
