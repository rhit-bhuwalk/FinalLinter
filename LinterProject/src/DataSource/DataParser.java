package DataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import Linter.*;

public class DataParser {

	private List<ClassDataObj> classData;

	public DataParser() {
		classData = new ArrayList<ClassDataObj>();
	}

	public void parse(List<File> files) throws FileNotFoundException, IOException {
		classData.clear();
		for (File file : files) {

			// populate classNode
			ClassReader reader = new ClassReader(new FileInputStream(file));
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);
			
			// important data structures
			List<FieldDataObj> fieldList = populateFieldList(classNode.fields);
			List<MethodDataObj> methodList = populateMethodList(classNode.methods);

			// get class information
			String className = classNode.name;
			String superClass = classNode.superName;
			List<String> interfaces = classNode.interfaces;
			int access = classNode.access;
			String signature = classNode.signature;
			
			ClassDataObj c = new ClassDataObj(className, access, signature, 
					superClass, interfaces, fieldList, methodList);
			
			this.classData.add(c);
		}
	}

	public List<ClassDataObj> getData() {
		return this.classData;
	}

	private List<MethodDataObj> populateMethodList(List<MethodNode> methods) {
		List<MethodDataObj> m = new ArrayList<MethodDataObj>();
		for (MethodNode method : methods) {
			List<ParameterDataObj> paramsList = new ArrayList<ParameterDataObj>();
			if (method.parameters != null) {
				paramsList = populateParams(method.parameters);
			}
			InsnList i = method.instructions;
			AbstractInsnNode[] instructions = i.toArray();
			
			int methodCalls = 0;
			int fieldCalls = 0;
			int localVariableConstructs = 0;
			int lastInstruction = instructions.length;
			for(int j=0;j<lastInstruction;j++) {
				AbstractInsnNode node = instructions[j];
				if(node.getType() == AbstractInsnNode.METHOD_INSN)
					methodCalls++;
				else if(node.getType() == AbstractInsnNode.FIELD_INSN)
					fieldCalls++;
				else if(node.getType() == AbstractInsnNode.VAR_INSN)
					localVariableConstructs++;
				else continue;
			}
			Instruction in = new Instruction(methodCalls, fieldCalls, localVariableConstructs);
			String methodName = method.name;
			String methodDesc = method.desc;
			String signature = method.signature;
			int access = method.access;
			m.add(new MethodDataObj(methodName,access, signature, methodDesc, paramsList, in));
		}
		return m;
	}

	private List<ParameterDataObj> populateParams(List<ParameterNode> parameters) {
		List<ParameterDataObj> p = new ArrayList<ParameterDataObj>();

		for (ParameterNode param : parameters) {
			int accessCode = param.access;
			String paramName = param.name;
			p.add(new ParameterDataObj(accessCode, paramName));
		}
		return p;
	}

	private List<FieldDataObj> populateFieldList(List<FieldNode> fields) {
		List<FieldDataObj> f = new ArrayList<FieldDataObj>();
		for (FieldNode field : fields) {
			String fieldName = field.name;
			String fieldDesc = field.desc;
			String signature = field.signature;
			int access = field.access;
			f.add(new FieldDataObj(fieldName, access, signature, fieldDesc));
		}
		return f;
	}
}
