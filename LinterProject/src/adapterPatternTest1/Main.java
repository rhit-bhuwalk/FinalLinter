package adapterPatternTest1;

public class Main {

	public static void main(String[] args) {
		Adapter adapter = new Adapter(new Adaptee());
		Client client = new Client(adapter);
		client.test();
	}

}
