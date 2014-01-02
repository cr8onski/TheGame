package models.character;

import models.character.attributes.AbilityScores;
import models.character.attributes.skills.Skills;
import models.character.items.exceptions.NoWeaponException;
import models.character.race.EquipSlots;
import models.character.race.Race;
import models.items.Item;

//the Character class for the Game
public class Character {
	//enums - I've got a rudimentary understanding - I'll figure out more later
	public enum Gender {MALE, FEMALE};
	public enum Movement {AIR, GROUND, WATER, DIG, TELEPORT};	//seems nifty
	
	//okay not sure of protected - we'll keep going
	protected String charName;	//the character's name
	protected Race charRace;	//the character's race
	protected Gender gender;	//the character's gender
	protected float height;	//the character's height
	protected int weight;	//the character's weight
	
	protected int xp = 0;	//the character's experience points
	
	protected AbilityScores abilityScores;	//array of the character's abilities 
	
	protected Skills skills;	//the character's skills - not sure what those are yet
	
	private EquipSlots equipslots;	//where the character holds equipment
	
	private int baseHP = 100;	//Starting point for the character's hit points
	private int baseAC = 10;	//starting point for the character's armor class
	private int baseDamage = 1;	//starting point for the character's attack damage
	
//	protected Language[] languages;	//to be used later - the character's spoken and written language
	
	//the constructor for the Character class
	public Character(String name, Race race, Gender gender, float height, int weight, AbilityScores ab) {
		//assigning starting values
		charName = name;
		charRace = race;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.abilityScores = ab;
		skills = null;	//start with no skills
		//these are influenced by the character's race
		equipslots = charRace.getEquipSlots();
		baseHP = (int)((baseHP + abilityScores.getConstitution()) * charRace.getHpMod());
		baseAC = (int)((baseAC + abilityScores.getDexterity()) * charRace.getAcMod());
		baseDamage = (int)((baseDamage + abilityScores.getStrength()/33) * charRace.getDamageMod());
	}	//constructor Character
	
	//base attributes are retrieved and modified based on race
	//returns the character's strength
	public int getStrength() {
		return Math.round(abilityScores.getStrength() * charRace.getStrMod());
	}

	//returns the character's dexterity
	public int getDexterity() {
		return Math.round(abilityScores.getDexterity() * charRace.getDexMod());
	}

	//returns the character's constitution
	public int getConstitution() {
		return Math.round(abilityScores.getConstitution() * charRace.getConMod());
	}

	//returns the character's intelligence
	public int getIntelligence() {
		return Math.round(abilityScores.getIntelligence() * charRace.getIntMod());
	}

	//returns the character's wisdom
	public int getWisdom() {
		return Math.round(abilityScores.getWisdom() * charRace.getWisMod());
	}

	//returns the character's charisma
	public int getCharisma() {
		return Math.round(abilityScores.getCharisma() * charRace.getChrMod());
	}
	
	//calcuate and return the character's damage
	public int getDamage() {
		float mod = 1;	//initialize mod to 1
		//get the damage mod from the character's current weapon or coninues with mod as is
		try {
			mod = getCurrentWeapon().getDamageMod();
		} catch (NoWeaponException e) {
			// ignore.. no weapon 
		}
		return Math.round(baseDamage * mod);
	}	//getDamage
	
	//returns the character's currently equipped weapon
	public Item getCurrentWeapon() throws NoWeaponException {
		//don't understand this expression
		//my best guess is - nope don't get it
		return (isWeaponEquipped()) ? getEquippedWeapon() : charRace.getBaseWeapon();
	}
	
	//returns a special weapon for the character
	public Item getSpecialWeapon() throws NoWeaponException {
		return charRace.getSpecialWeapon();
	}
	
	//returns the character's armor class for defensive purposes
	public int getAC() {
		return baseAC;
	}
	
	//returns character's hit points
	public int getHP() {
		return baseHP;// should we add weapon/armor mods
		//to your question - yes
		//and what about a current of maximum hit points - 62/78 HP
//		return (baseHP * weaponHPMod + armorHPMod;	//something like that
	}
	
	//returns whether or not a weapon is equipped - at a particular slot??
	private boolean isWeaponEquipped() {
		return equipslots.hasWeapon();
	}
	
	//returns name of equipped weapon
	private Item getEquippedWeapon() {
		return equipslots.getWeapon();
	}
	
	//converts Character's information/status to a string
	public String toString() {
		//start with assigning no weapon
		String weapon = "none";
		//assigns current weapon or uses default "none" if no weapon is equipped
		try {
			weapon = getCurrentWeapon().toString();
		} catch (NoWeaponException e) {
			// ignore
		}
		//same concept as above
		String special = "none";
		try {
			special = getSpecialWeapon().toString();
		} catch (NoWeaponException e) {
			// ignore
		}
		//those being established - we now create the string to be returned
		//this is one long string concatanation
		return "Hi I'm " + charName + "\n" +
	           "A " + height + "ft, " + weight + "lb, " + gender.toString().toLowerCase() + " " + charRace.getName() + "\n" +
			   "Hit Points: " + getHP() + "\n" +
	           "AC: " + getAC() + "\n" +
			   "Damage: " + getDamage() + "\n" +
			   "Abilities:\n" + 
				"Strength: " + getStrength() + "\n" + 
				"Dexterity: " + getDexterity() + "\n" +
				"Constitution: " + getConstitution() + "\n" +
				"Intelligence: " + getIntelligence() + "\n" +
				"Wisdom: " + getWisdom() + "\n" +
				"Charisma: " + getCharisma() + "\n" +
				"Current Weapon: " + weapon + "\n" + 
				"Special Weapon: " + special;
	}	//toString
}	//Character class
