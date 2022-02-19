package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import DataSource.ClassDataObj;
import DataSource.FieldDataObj;
import DataSource.MethodDataObj;
import Linter.Check;
import Linter.Linter;
import Linter.ObserverPatternCheck;

public class ObserverPatternTests {
	@Test
	public void ObserverPatternTest() {
	MethodDataObj concreteUpdate = new MethodDataObj("update", 0, null, "void", null, null);
	MethodDataObj abstractUpdate = new MethodDataObj("update", 1024, null, "void", null, null);
	MethodDataObj equals = new MethodDataObj("equals", 0, null, "boolean", null, null);
	MethodDataObj hashCode = new MethodDataObj("hashCode", 0, null, "int", null, null);
	FieldDataObj target = new FieldDataObj("target", 0, null, "Subject");
	FieldDataObj obs = new FieldDataObj("obs", 0, null, "List<Observer>");
	List<FieldDataObj> tar = new ArrayList<FieldDataObj>();
	List<FieldDataObj> obse = new ArrayList<FieldDataObj>();
	List<MethodDataObj> absUp = new ArrayList<MethodDataObj>();
	List<MethodDataObj> conUpE = new ArrayList<MethodDataObj>();
	List<MethodDataObj> conUpH = new ArrayList<MethodDataObj>();
	List<MethodDataObj> EH = new ArrayList<MethodDataObj>();
	absUp.add(abstractUpdate);
	conUpE.add(concreteUpdate);
	conUpE.add(equals);
	conUpH.add(concreteUpdate);
	conUpH.add(hashCode);
	EH.add(hashCode);
	EH.add(equals);
	tar.add(target);
	obse.add(obs);
	ClassDataObj Observer = new ClassDataObj("Observer", 1024, null, "Object", null, tar, absUp);
	ClassDataObj ConcreteObserverOne = new ClassDataObj("ConcreteObserverOne", 0, null, "Observer", null, null, conUpE);
	ClassDataObj ConcreteObserverTwo = new ClassDataObj("ConcreteObserverTwo", 0, null, "Observer", null, null, conUpH);
	ClassDataObj Subject = new ClassDataObj("Subject", 0, null, "Object", null, obse, EH);
	List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	data.add(Observer);
	data.add(ConcreteObserverOne);
	data.add(ConcreteObserverTwo);
	data.add(Subject);
	Check c = new ObserverPatternCheck(data);
	Linter l = new Linter();
	l.addToCheckList(c);
	List<String> errs = l.lint(data);
	assertEquals(errs.size(), 1);
	}

	@Test
	public void ObserverPatternTestTwo() {
	MethodDataObj concreteUpdate = new MethodDataObj("update", 0, null, "void", null, null);
	MethodDataObj abstractUpdate = new MethodDataObj("update", 1024, null, "void", null, null);
	MethodDataObj equals = new MethodDataObj("equals", 0, null, "boolean", null, null);
	MethodDataObj hashCode = new MethodDataObj("hashCode", 0, null, "int", null, null);
	FieldDataObj obs = new FieldDataObj("obs", 0, null, "List<Observer>");
	List<FieldDataObj> tar = new ArrayList<FieldDataObj>();
	List<FieldDataObj> obse = new ArrayList<FieldDataObj>();
	List<MethodDataObj> absUp = new ArrayList<MethodDataObj>();
	List<MethodDataObj> conUpE = new ArrayList<MethodDataObj>();
	List<MethodDataObj> conUpH = new ArrayList<MethodDataObj>();
	List<MethodDataObj> EH = new ArrayList<MethodDataObj>();
	absUp.add(abstractUpdate);
	conUpE.add(concreteUpdate);
	conUpE.add(equals);
	conUpH.add(concreteUpdate);
	conUpH.add(hashCode);
	EH.add(hashCode);
	EH.add(equals);
	obse.add(obs);
	ClassDataObj Observer = new ClassDataObj("Observer", 1024, null, "Object", null, null, absUp);
	ClassDataObj ConcreteObserverOne = new ClassDataObj("ConcreteObserverOne", 0, null, "Observer", null, null, conUpE);
	ClassDataObj ConcreteObserverTwo = new ClassDataObj("ConcreteObserverTwo", 0, null, "Observer", null, null, conUpH);
	ClassDataObj Subject = new ClassDataObj("Subject", 0, null, "Object", null, obse, EH);
	List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	data.add(Observer);
	data.add(ConcreteObserverOne);
	data.add(ConcreteObserverTwo);
	data.add(Subject);
	Check c = new ObserverPatternCheck(data);
	Linter l = new Linter();
	l.addToCheckList(c);
	List<String> errs = l.lint(data);
	assertEquals(errs.size(), 0);
	}

	@Test
	public void ObserverPatternTestThree() {
	MethodDataObj concreteUpdate = new MethodDataObj("update", 0, null, "void", null, null);
	MethodDataObj abstractUpdate = new MethodDataObj("update", 1024, null, "void", null, null);
	MethodDataObj equals = new MethodDataObj("equals", 0, null, "boolean", null, null);
	MethodDataObj hashCode = new MethodDataObj("hashCode", 0, null, "int", null, null);
	FieldDataObj target = new FieldDataObj("target", 0, null, "Subject");
	FieldDataObj obs = new FieldDataObj("obs", 0, null, "List<Observer>");
	List<FieldDataObj> tar = new ArrayList<FieldDataObj>();
	List<FieldDataObj> obse = new ArrayList<FieldDataObj>();
	List<MethodDataObj> absUp = new ArrayList<MethodDataObj>();
	List<MethodDataObj> conUpE = new ArrayList<MethodDataObj>();
	List<MethodDataObj> conUpH = new ArrayList<MethodDataObj>();
	List<MethodDataObj> EH = new ArrayList<MethodDataObj>();
	absUp.add(abstractUpdate);
	conUpE.add(concreteUpdate);
	conUpE.add(equals);
	conUpH.add(concreteUpdate);
	conUpH.add(hashCode);
	EH.add(hashCode);
	EH.add(equals);
	tar.add(target);
	obse.add(obs);
	ClassDataObj Observer = new ClassDataObj("Observer", 0, null, "Object", null, tar, absUp);
	ClassDataObj ConcreteObserverOne = new ClassDataObj("ConcreteObserverOne", 0, null, "Observer", null, null, conUpE);
	ClassDataObj ConcreteObserverTwo = new ClassDataObj("ConcreteObserverTwo", 0, null, "Observer", null, null, conUpH);
	ClassDataObj Subject = new ClassDataObj("Subject", 0, null, "Object", null, obse, EH);
	List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	data.add(Observer);
	data.add(ConcreteObserverOne);
	data.add(ConcreteObserverTwo);
	data.add(Subject);
	Check c = new ObserverPatternCheck(data);
	Linter l = new Linter();
	l.addToCheckList(c);
	List<String> errs = l.lint(data);
	assertEquals(errs.size(), 0);
	}

}
