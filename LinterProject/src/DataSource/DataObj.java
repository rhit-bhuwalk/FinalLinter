package DataSource;

import java.util.List;

import org.objectweb.asm.Opcodes;

public abstract class DataObj {
	
	protected String name;
	protected int access;
	protected String signature;
	
	public String getName() {
		return name;
	}

	public int getAccess() {
		return access;
	}
	public boolean isPublic() {
		return ((this.access & Opcodes.ACC_PUBLIC) !=0);
	}

	public boolean isStatic() {
		return ((this.access & Opcodes.ACC_STATIC) !=0);
	}

	public boolean isPrivate() {
		return ((this.access & Opcodes.ACC_PRIVATE) !=0);
	}
	
	public boolean isAbstract() {
		return ((this.access & Opcodes.ACC_ABSTRACT) !=0);
	}
	
	public boolean isFinal() {
		return ((this.access & Opcodes.ACC_FINAL) !=0);
	}
	
	public String getSignature() {
		return this.signature;
	}
	
	public String getReturnType() {
		return null;
	}
	
	
}
