package Linter;
//written by Kush Bhuwalka
import java.security.cert.CertPathChecker;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import DataSource.*;

public class MissingImplementationsOfAbstractTypes extends Check {

	public MissingImplementationsOfAbstractTypes(List<ClassDataObj> d) {
		super(d);
	
	}

	@Override
	List<String> check() {
		
		List<ClassDataObj> abstractClasses = new ArrayList<ClassDataObj>();
		
		for(ClassDataObj obj: data)
		{
			if(obj.isAbstract()) // class is abstract
				abstractClasses.add(obj);
		}
	
		for(ClassDataObj abClass: abstractClasses)
		{
			boolean implemented = false; 
			for(ClassDataObj obj: data)
			{
				if((obj.getSuperClassName()).equals(abClass.getName()))
				{
						implemented = true;
						break;
				}
			}
			
			if(!implemented)
			{	
				errors.add("Abstract class " + abClass.getName() + " is unimplemented \n");
			}
		}
		return errors;	
		}
		
		
	}
	

