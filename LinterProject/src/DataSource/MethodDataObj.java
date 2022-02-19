package DataSource;

import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MethodDataObj extends DataObj {
	private String desc;
	private List<ParameterDataObj> paramList;
	private Instruction in;

	public MethodDataObj(String methodName, int access, String signature,
			String methodDesc, List<ParameterDataObj> paramsList, Instruction in) {
		this.name = methodName;
		this.access = access;
		this.signature = signature;
		this.in = in;
		this.desc = methodDesc;
		this.paramList = paramsList;
	}

	public String getDesc() {
		return desc;
	}

	public List<ParameterDataObj> getParamList() {
		return paramList;
	}
	
	public Instruction getInstructionData() {
		return in;
	}
	
	@Override
	public String getReturnType() {
		return Type.getReturnType(this.desc).getClassName();
	}

}
