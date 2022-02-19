package DataSource;

import java.util.List;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.TypeAnnotationNode;

public class FieldDataObj extends DataObj{
	
	private String desc;

	public FieldDataObj(String n, int access, String signature,
			String fieldDesc) {
		this.name = n;
		this.access = access;
		this.signature = signature;
		
		this.desc = fieldDesc;
	}

	public String getFieldDesc() {
		return desc;
	}
	
	@Override
	public String getReturnType() {
		Type t = Type.getType(this.getFieldDesc());
		String type = t.getClassName();
		return type;
	}
	
}
