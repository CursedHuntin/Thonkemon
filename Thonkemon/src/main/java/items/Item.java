package main.java.items;

import java.io.Serializable;

import main.java.monsters.Monster;

public abstract class Item implements Serializable {
	private static final long serialVersionUID = 2520068126446404262L;
	public String name;
	public int amount;
	public int price;

	public Item(String name, int amount, int price) {
		this.name = name;
		this.amount = amount;
		this.price = price;
	}

	public abstract void effect(Monster m);

}
