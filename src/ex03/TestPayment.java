package ex03;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

//입력 1줄 -> 결과 1줄
//입력datprivate String : 사번,이름,입사일,월급,부서번호
//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명

//금액은 소수이하 두자리로 반올림
//보너스   =  근무연수에 따라 월급의 0.5% 로 계산한다
//수령액   =  월급 + 보너스 
//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스

//모든기능은 class에 구현한다.
//입력data
//사번,이름,입사일,월급,부서번호 
/*
100,사나,20110101,300.0,10      
200,모모,20120301,270.0,20      
300,정연,20091003,250.0,30      
400,나연,20110105,220.0,40      
500,미나,20180401,170.0,60      
600,쯔위,20150801,200.0,50      
*/

interface Ipo {
	void input();
	void process();
	void output();
}

class PaymentDto {
	// Fields
	// 입력 : 사번, 이름, 입사일, 월급, 부서번호
	private int num;
	private String name;
	private String hireDate;
	private double salary;
	private int deptNum;
	
	// 출력 : 사번, 이름, 입사일, 월급, 보너스, 수령액, 부서명
	private double bonus;
	private double netPay;
	private String deptName;
	public PaymentDto(int num, String name, String hireDate, double salary, int deptNum) {
		this.num = num;
		this.name = name;
		this.hireDate = hireDate;
		this.salary = salary;
		this.deptNum = deptNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getDeptNum() {
		return deptNum;
	}
	public void setDeptNum(int deptNum) {
		this.deptNum = deptNum;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	// toString
	@Override
	public String toString() {
		String fmt = "PaymentDto [num=%d, name=%s, hireDate=%s, salary=%.0f, deptNum=%d, bonus=%.3f, netPay=%.3f, deptName=%s]";
		return String.format(fmt, num, name, hireDate, salary, deptNum, bonus, netPay, deptName);
	}
}

// 기능 중심의 class
class Payment implements Ipo {
	
	private PaymentDto p;
	
	@Override
	public void input() {
		Scanner in = new Scanner(System.in);
		
		// num, name, hireDate, salary, deptNum 입력
		System.out.println("입력 : 사번, 이름, 입사일, 월급, 부서번호");
		String line = in.nextLine();
		// line : "100,사나,20110101,300.0,10"
		String[] li = line.trim().split(",");
		int num = Integer.parseInt(li[0].trim());
		String name = li[1].trim();
		String hireDate = li[2].trim();
		double salary = Double.parseDouble(li[3].trim());
		int deptNum = Integer.parseInt(li[4].trim());
		
		p = new PaymentDto(num, name, hireDate, salary, deptNum);
	}

	@Override
	public void process() {
		//보너스   =  근무연수에 따라 월급의 0.5% 로 계산한다
		//수령액   =  월급 + 보너스 
		//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		Period period = Period.between(LocalDate.parse(p.getHireDate(), formatter), LocalDate.now());
		System.out.printf("today=%s, hiredate=%s\n", LocalDate.now(), LocalDate.parse(p.getHireDate(), formatter));
		int wYear = period.getYears();
		System.out.printf("wYear=%d\n",wYear);
		p.setBonus(wYear*0.005*p.getSalary());
		p.setNetPay(p.getBonus()+p.getSalary());
		
		if (p.getDeptNum() == 10) p.setDeptName("인사");
		if (p.getDeptNum() == 20) p.setDeptName("자재");
		if (p.getDeptNum() == 30) p.setDeptName("총무");
		if (p.getDeptNum() == 40) p.setDeptName("연구개발");
		if (p.getDeptNum() == 50) p.setDeptName("생산");
		if (p.getDeptNum() == 60) p.setDeptName("서비스");
		
	}

	@Override
	public void output() {
		// 사번,이름,입사일,월급,보너스,수령액,부서명
		System.out.println("사번   이름  입사일   월급    보너스   수령액    부서명");
		String fmt = "%d %s %s %.3f %.3f %.3f %s";
		String msg = String.format(fmt, p.getNum(), p.getName(), p.getHireDate(), p.getSalary(),
				p.getBonus(), p.getNetPay(), p.getDeptName());
		System.out.println(msg);
		
	}
	
}

public class TestPayment {

	public static void main(String[] args) {
		Payment p1 = new Payment();
		p1.input();
		p1.process();
		p1.output();

	}

}
