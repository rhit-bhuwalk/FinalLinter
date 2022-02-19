package adapterPatternTest1;

public class Client {
	Target t;
	
	public Client(Target t) {
		this.t = t;
	}
	
	public void test() {
		System.out.println("Client giving instruction");
		t.test();
	}
	
}
