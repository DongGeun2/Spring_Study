package Spring_DI4;

import java.util.Scanner;

public class NewRecordView implements RecordView{
	
	// 점수를 출력하는 클래스
	
	private NewRecord record; // member field ( setter ) 
	
	
	
	public void setRecord(Record record) {
		this.record = (NewRecord) record;
		
	}
	
	// 나는 니가 필요해
	// 1. [생성자]를 통해서 필요한 객체를 생성 or 주입 >> DI
	// 2. 함수(setter) 를 통해서 필요한 객체를 주입 >> DI2


	@Override
	public void print() {
		System.out.println( record.total() + "/" + record.avg());
	}

	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("kor : ");
		record.setKor(sc.nextInt());
		
		System.out.println("eng : ");
		record.setEng(sc.nextInt());
		
		System.out.println("math : ");
		record.setMath(sc.nextInt());
	}
	
	
}
