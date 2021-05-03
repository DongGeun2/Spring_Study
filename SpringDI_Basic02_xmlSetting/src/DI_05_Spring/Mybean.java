package DI_05_Spring;

public class Mybean {
	public Mybean() {
		System.out.println("Default");
	}
	
	
	
	public Mybean(String name) {
		System.out.println("overload : " + name);
	}
}
