package garage;

public class User{
	private String name;
	private String telNr;
	private String personNr;
	private String barcode;
	private String pin;
	private int bikesInGarage;
	
	/**
	 * Konstruktorn f�r en User.
	 * 
	 * @param n
	 * 	userns name
	 * @param t
	 * 	userns telefonnummer
	 * @param b
	 * 	userns streckkod
	 * @param p
	 * 	userns PIN-kod
	 */
	public User(String n, String t, String b, String p, String pNr){
		name = n;
		telNr = t;
		barcode = b;
		pin = p;
		personNr = pNr;
	}
	
	/**
	 * Returnerar userns streckkod.
	 */
	public String getBarcode(){
		return barcode;
	}
	
	/**
	 * Returnerar userns PIN-kod.
	 */
	public String getPin(){
		return pin;
	}
	
	/**
	 * Returnerar userns namn.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returnerar userns telefonnummer.
	 */
	public String getTelNr(){
		return telNr;
	}
	
	/**
	 * Returnerar userns personnummer.
	 */
	public String getPersonNr(){
		return personNr;
	}
	
	/**
	 * Returnerar antalet cyklar usern har i garaget.
	 */
	public int getBikesInGarage(){
		return bikesInGarage;
	}
	

	/** OBS! F�R ENDAST ANV�NDAS I/GENOM DATABASEN!
	 * Metod som �ndrar en users pin
	 * 
	 * @param p
	 * 		userns nya pin
	 * */
	public void setPin(String p){
		pin = p;
	}
	
	/**
	 * Tilldelar usern ett nytt namn.
	 * 
	 * @param n
	 * 	userns nya namn
	 */
	public void setName(String n){
		name = n;
	}
	
	/**
	 * OBS! F�R ENDAST ANV�NDAS I/GENOM DATABASEN! Metod som 
	 * �ndrar en users antal cyklar i garaget
	 *
	 * @param m
	 * 	m�ngden cyklar som l�ggs/tas bort hos 
	 */
	public void modBikesInGarage(int m){
		bikesInGarage+=m;
	}
}