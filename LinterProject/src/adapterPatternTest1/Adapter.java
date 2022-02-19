package adapterPatternTest1;

public class Adapter implements Target{
	Adaptee a;
	public Adapter(Adaptee a) {
		this.a = a;
	}
	@Override
	public void test() {
		System.out.println("Adapter translating behavior");
		a.test();
	}
}
