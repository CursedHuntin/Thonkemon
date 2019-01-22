package main.java.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import main.java.monsters.Monster;

public class Type implements Serializable {
	private static final long serialVersionUID = -2760281175438773945L;
	public String name;
	public List<String> weakness = new ArrayList<String>();
	public List<String> strength = new ArrayList<String>();
	public List<String> immunity = new ArrayList<String>();

	public Type(String name) {
		this.name = name;
	}

	public static double getSTAB(Type atkType, Monster m) {
		double mult = 1;

		for (Type t : m.types) {
			if (atkType.name.equals(t.name))
				mult += 0.5;
		}
		return mult;
	}

	public static double getMult(Type atkType, Monster m) {
		double mult = 1;

		for (Type t : m.types) {
			for (String type : t.weakness) {
				if (atkType.name.equals(type))
					mult *= 2;
			}
		}

		for (Type t : m.types) {
			for (String type : t.strength) {
				if (atkType.name.equals(type))
					mult /= 2;
			}
		}
		return mult;
	}
}