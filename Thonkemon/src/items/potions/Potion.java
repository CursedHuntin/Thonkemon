package items.potions;

import items.Item;
import monsters.Monster;

public abstract class Potion extends Item {

	abstract void effect(Monster m);
}
