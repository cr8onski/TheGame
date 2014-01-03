package models.character.race;

import java.util.HashMap;

import models.items.Item;

//this controls where and how items are equipped
public class EquipSlots {
	
	//where you will be able to equip things
	public enum Location {HEAD, NECK, SHOULDERS, CHEST, ARMS, HANDS, LEGS, FEET};
	
	//I'm thinking slotlist is to add things to weapons??
	private SlotList weapons;
	//don't understand this yet
	private HashMap<Location, Item> armor;
	
	//the constructor for the class EquipSlots
	public EquipSlots(int numOfAttackableSlots, Location[] armorslots){
		this.weapons = new SlotList(numOfAttackableSlots);
		armor = new HashMap<EquipSlots.Location, Item>(armorslots.length);
		for (Location l : armorslots) {
			armor.put(l, null);
		}
	}	//constructor EquipSlots
	//okay I don't really understnad all that
	
	//sets an item as equipped
	public boolean equip(Item item) {
		//if the item can not be equipped we quit and go home early
		//otherwise we move on to the next test
		if (! item.isEquipable()) return false;
		
		//if the item is equippable and a weapon we equip it and go home
		if (item.isWeapon()) {
			return equipWeapon(item);
		}
		
		//if we've made it this far the item is equipable and armor
		//so we equip that and tear down the circus
		return equipArmor(item);
	}	//equip
	
	//sets an item as unequipped
	public boolean unequip(Item item) {
		//check that the waepon is equipable
		if (item.isEquipable()) {
			//unequip it as a weapon ...
			if (item.isWeapon()) {
				return unequipWeapon(item);
			}
			//... or as armor
			if (item.isArmor()) {
				return unequipArmor(item);
			}
		}
		//otherwise don't unequip it
		return false;
	}	//unequip
	
	public boolean hasWeapon() {
		return ! weapons.isEmpty();
	}
	
	//method overloading - I believe these next three - nice
	public Item getWeapon() {
		return weapons.get(0);
	}
	
	public Item getWeapon(int i) {
		return weapons.get(i);
	}
	
	public Item getWeapon(Item item) {
		int idx = weapons.indexOf(item);
		if (idx < 0) return null;
		return weapons.get(idx);
	}
	
	//not to be confused with the singular versions
	public Item[] getWeapons() {
		return weapons.toArray(new Item[]{});
	}
	
	//sets a weapon as equipped
	private boolean equipWeapon(Item item) {
		if (weapons.areSlotsAvailable(item.size()))
			return weapons.add(item);
		return false;	//do or do not there is no try
	}
	
	//sets a weapon as unequipped
	private boolean unequipWeapon(Item item) {
		return weapons.remove(item);
	}
	
	//sets armor as equipped
	private boolean equipArmor(Item item) {
		if (armor.keySet().contains(item.location())) {
			if (armor.get(item.location()) != null) {
				return armor.put(item.location(), item) == null;
			}
		}
		return false;
	}
	
	//sets armor as unequipped
	private boolean unequipArmor(Item item) {
		if (armor.keySet().contains(item.location())) {
			return armor.remove(item) != null;
		}
		return false;
	}
}	//EquipSlots class
