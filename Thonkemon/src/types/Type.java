package types;

import java.util.ArrayList;
import java.util.List;

import monsters.Monster;

public class Type {
	public String name;
	public List<String> weakness = new ArrayList<String>();
	public List<String> strength = new ArrayList<String>();

	public Type(String name) {
		this.name = name;

	}

	public static double getSTAB(Type atkType, Monster m) {
		double mult = 1;
		if (atkType.name.equals(m.stats.getType1().name))
			mult += 0.5;
		if (m.stats.getType2() != null) {
			if (atkType.name.equals(m.stats.getType2().name))
				mult += 0.5;
		}
		return mult;
	}

	public static double getMult(Type atkType, Monster m) {
		double mult = 1;
		for (String type : m.stats.getType1().weakness) {
			if (atkType.name.equals(type))
				mult *= 2;
		}
		if (m.stats.getType2() != null) {
			for (String type : m.stats.getType2().weakness) {
				if (atkType.name.equals(type))
					mult *= 2;
			}
		}

		for (String type : m.stats.getType1().strength) {
			if (atkType.name.equals(type))
				mult /= 2;
		}
		if (m.stats.getType2() != null) {
			for (String type : m.stats.getType2().strength) {
				if (atkType.name.equals(type))
					mult /= 2;
			}
		}
		return mult;

	}
}
