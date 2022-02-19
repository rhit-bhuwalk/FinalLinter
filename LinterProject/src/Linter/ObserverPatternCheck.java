//Coded by Richard Costa

package Linter;

import java.util.ArrayList;
import java.util.List;

import DataSource.ClassDataObj;

public class ObserverPatternCheck extends Check{

	public ObserverPatternCheck(List<ClassDataObj> d) {
		super(d);
	}

	@Override
	List<String> check() {
		List<String> sb = new ArrayList<String>();
		//observer is an abstract class w/ an update method
		for (ClassDataObj obs : data) {
			if(obs.isAbstract()) {
				boolean valid = false;
				for(int count=0; count<obs.getMethods().size(); count++) {
					if(obs.getMethods().get(count).getName().contains("update")) {
						valid=true;
						break;
					}
				}
				if(valid) {
					String target = "List<"+obs.getName()+">";
					for (ClassDataObj subj : data) {
						for(int count=0; count<subj.getFields().size(); count++) {
							//check if this class has a list of the observer
							if(subj.getFields().get(count).getFieldDesc().contains(target)) {
								for(int count1=0; count1<obs.getFields().size(); count1++) {
									//check if the observer has an instance of this class
									if(obs.getFields().get(count1).getFieldDesc().contains(subj.getName())) {
										sb.add("Observer pattern detected: "+obs.getName()+" observes "+subj.getName());
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		//concrete observer extenders observer
		//subject has a list of observers
		//observer has a field of type subject
		return sb;
	}
	
}
