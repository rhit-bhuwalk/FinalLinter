package Linter;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsAnything;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

import DataSource.*;

public class ExposeThirdPartyTypeCheck extends Check {

	String[] validPrimitives;
	List<String> validObjects;
	String[] specialObjects;

	public ExposeThirdPartyTypeCheck(List<ClassDataObj> d) {
		super(d);
		String[] validTypes = { "int", "double", "char", "short", "float", "long", "byte", "boolean",
				"java.lang.Integer", "java.lang.Character", "java.lang.Short", "java.lang.Float", "java.lang.Long",
				"java.lang.Byte", "java.lang.Double", "java.lang.Boolean", "java.lang.String", "java.lang.Object" };
		validPrimitives = validTypes;
		validObjects = new ArrayList<String>();
		for (ClassDataObj classObj : data) {
			validObjects.add(classObj.getName());  // package/classname for fields 
			validObjects.add(classObj.getName().replace('/','.')); //package/classname for methods
		}
		String[] specialTypes = { "java.util.List", "java.util.Set", "java.util.Map" };
		specialObjects = specialTypes;
	}

	@Override
	List<String> check() {

		for (ClassDataObj classObj : data) {
			List<FieldDataObj> fields = classObj.getFields();
			List<MethodDataObj> methods = classObj.getMethods();
			
			for (FieldDataObj f : fields) {
				if (!checkValidity(f))
					errors.add("Field " + f.getName() + " in class " + classObj.getName() + " of type "
							+ f.getReturnType() + " is exposed.");
			}

			for (MethodDataObj f : methods) {
				if(f.getReturnType().equals("void"))
					continue;
				if (!checkValidity(f))
					errors.add("Method " + f.getName() + " in class " + classObj.getName() + " of type "
							+ f.getReturnType() + " is exposed.");
			}
		}
		return errors;
	}

	private boolean checkValidity(DataObj d) {
		if(d.isPrivate())
			return true;
		
		String returnType = d.getReturnType();
//		System.out.println("returnType of " + d.getName() +" = " + d.getReturnType());
		if(returnType.endsWith("[]"))
		{
			returnType = returnType.substring(0, returnType.length()-2);
		}
		for(int i=0;i<validPrimitives.length;i++)
		{
			if(validPrimitives[i].equals(returnType))
				return true;
		}
		if(validObjects.contains(returnType))
				return true;
		
		for(int i=0;i<specialObjects.length;i++)
		{
			if(specialObjects[i].equals(returnType))
					 return checkDSvalidity(d, returnType);
		}
		return false;
	}

	// the case where the type is a list, set, or map
	private boolean checkDSvalidity(DataObj d, String returnType) {
		String signature = d.getSignature();
		// the signature tells more information on the data structure
		if (returnType.contains("Map")) {
			int idx = signature.indexOf(';');
			signature = signature.substring(idx);
		}
		
		for (int i = 8; i < validPrimitives.length; i++) {
			if (signature.contains(validPrimitives[i].substring(11)))
				return true;
		}
		for (String s : validObjects) {
			if (signature.contains(s))
				return true;
		}
		return false;
	}
}
