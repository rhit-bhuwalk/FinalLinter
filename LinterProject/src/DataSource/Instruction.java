package DataSource;

public class Instruction {
	 private int methodCalls;
	 private int fieldCalls;
	 private int variableConstructions;

	 public Instruction(int m, int f, int v) {
		methodCalls = m;
		fieldCalls = f;
		variableConstructions = v;
		
	}
	 
	 public int getMethodCalls() {
		 return this.methodCalls;
	 }
	 
	 public int getFieldCalls() {
		 return this.fieldCalls;
	 }
	 
	 public int getVariableConstructions() {
		 return this.variableConstructions;
	 }

	 
	 
	 
}
