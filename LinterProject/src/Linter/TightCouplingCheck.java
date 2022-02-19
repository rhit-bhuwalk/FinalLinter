//package Linter;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class TightCouplingCheck extends Check {
//
//	public TightCouplingCheck(List<ClassDataObj> d) {
//		super(d);
//	}
//
//	@Override
//	List<String> check() {
//		List<String> sb = new ArrayList<String>();
//		List<String> l = new ArrayList<String>();
//		for (ClassDataObj a : data) {
//			l.add(a.getName());
//		}
//		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
//		for (ClassDataObj frst : data) {
//			if (map.get(frst.getName()) == null) {
//				map.put(frst.getName(), new ArrayList<String>());
//			}
//			ArrayList<String> data = map.get(frst.getName());
//			// iterate through fields inside of class
//			for (FieldDataObj scnd : frst.getFields()) {
//				// add any fields that are user defined classes to the hashmap
//				if (l.contains(scnd.getFieldDesc())) {
//					data.add(scnd.getFieldDesc());
//				}
//
//			}
//			// add the superclass
//			if (frst.getSuperClass() != "java/lang/Object") {
//				data.add(frst.getSuperClass());
//			}
//			map.put(frst.getName(), data);
//		}
//		// look for circular dependencies
//		for (String s : map.keySet()) {
//			ArrayList<String> targets = map.get(s);
//			for (int count = 0; count < targets.size(); count++) {
//				if (map.get(targets.get(count)).contains(s)) {
//					sb.add("Tight coupling detected:\n"
//							+ "Loop between "+s+" and "+targets.get(count));
//				}
//			}
//		}
//		// TODO Auto-generated method stub
//		return sb;
//	}
//
//}
