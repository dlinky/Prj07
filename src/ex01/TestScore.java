package ex01;

/*  1번
num,name,kor,eng,mat
번호,이름,국어,영어,수학
1,사나,70,80,90
2,모모,0,70,100
3,쯔위,70,90,90
4,정연,80,90,70
5,원영,90,90,89
6,유진,100,100,100
*/

class Score {
	// Fields
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	
	private int tot;
	private double avg;
	private char grade;
	
	// Constructor
	public Score() {}
	public Score(int num, String name, int kor, int eng, int mat) {
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
	// Getter/Setter
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
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	// toString : 값 중간확인용
	@Override
	public String toString() {
		return "Score [num="+num+", name="+name+", kor="+kor+", eng"+eng+", mat="+mat+
				", tot="+tot+", avg="+avg+", grade="+grade+"]";
	}
	
	// Method
	public int calcTot() {
		tot = kor + eng + mat;
		return tot;
	}
	public double calcAvg() {
		avg = calcTot() / 3.0;
		return avg;
	}
	public String calcGrade() {
		avg = calcAvg();
		String grade = "";
		if (avg >= 90) grade = "A";
		else if (avg >= 80) grade = "B";
		else if (avg >= 70) grade = "C";
		else if (avg >= 60) grade = "D";
		else grade = "F";
		
		return grade;
	}
	
}

public class TestScore {

	public static void main(String[] args) {
		Score score1 = new Score(1, "사나", 70,80,90);
		System.out.println(score1.toString());
		int tot = score1.calcTot();
		String totStr = String.valueOf(tot); // 굳이??
		System.out.println("총정:" + totStr);
		System.out.println("평균:" + score1.calcAvg());
		System.out.println("학점:" + score1.calcGrade());
		
		// 5, 원영, 90,90,89
		Score score2 = new Score();
		score2.setNum(5);
		score2.setName("원영");
		score2.setKor(90);
		score2.setEng(90);
		score2.setMat(89);
		score2.calcTot();
		score2.calcAvg();
		score2.calcGrade();
		System.out.println(score2);
		
		// 원하는 양식으로 출력
		System.out.println("번호 이름 국어 영어 수학 총점 평균 등급");
		System.out.printf("%d %s %d %d %d %d %.3f %c",
				score2.getNum(), score2.getName(), score2.getKor(),
				score2.getEng(), score2.getMat(), score2.calcTot(),
				score2.getAvg(), score2.getGrade());
		
		
		
		
	}

}
