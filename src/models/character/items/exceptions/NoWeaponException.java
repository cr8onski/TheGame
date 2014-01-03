package models.character.items.exceptions;

//wow so you made up your own exception
public class NoWeaponException extends Exception {
	private static final long serialVersionUID = 7344831918888867552L;

	public NoWeaponException(String msg) {
		super(msg);
	}
}
