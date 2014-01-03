package models.character.attributes;

//simply holds the scores used to determine how the "character" performs
//on certain tasks or whatnot
//any fancy stuff is done elsewhere
public class AbilityScores {
	//here's the variables - attributes that we are keeping track of
	private int strength;	//for use in case of emergency
	private int dexterity;	//the character's dextrousness
	private int constitution;	//the basis of health
	private int intelligence;	//means ...
	private int wisdom;	//determines ...
	private int charisma;	//influences ...
	
	//the Constructor for the AbilityScores class
	public AbilityScores(int str, int dex, int con, int intel, int wis, int chr) {
		strength = str;
		dexterity = dex;
		constitution = con;
		intelligence = intel;
		wisdom = wis;
		charisma = chr;
	}	//constructor

	//returns the value of strength field
	public int getStrength() {
		return strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public int getConstitution() {
		return constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public int getCharisma() {
		return charisma;
	}
	
	//concatenates the AbilityScores into one giant string
	public String toString() {
		return "Abilities:\n" + 
				"Strength: " + getStrength() + "\n" + 
				"Dexterity: " + getDexterity() + "\n" +
				"Constitution: " + getConstitution() + "\n" +
				"Intelligence: " + getIntelligence() + "\n" +
				"Wisdom: " + getWisdom() + "\n" +
				"Charisma: " + getCharisma();
	}	//toString
}	//AbilityScores class
