package DataSource;

import java.util.List;

import org.objectweb.asm.Opcodes;

public class ClassDataObj extends DataObj {

	private String superClassName;
	private List<String> interfaces;
	private List<FieldDataObj> fields;
	private List<MethodDataObj> methods;

	public ClassDataObj(String n, int access, String signature, 
			String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {
		this.name = n;
		this.access = access;
		this.signature = signature;
		
		this.interfaces = ints;
		this.fields = f;
		this.methods = m;
		this.superClassName = superClass;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	public List<FieldDataObj> getFields() {
		return fields;
	}

	public List<MethodDataObj> getMethods() {
		return methods;
	}
	
	public boolean isInterface() {
		return ((this.access & Opcodes.ACC_INTERFACE) !=0);
	}
	
	public String getOnlyClassName() {
		int idx = name.indexOf('/');
		if(idx!= -1)
			return name.substring(idx+1);
		else
			return name;
	}
	
}
