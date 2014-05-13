package garage;

public class User implements Comparable<User>{
	String name;
	String telNr;
	String barcode;
	String pin;
	int bikesInGarage;
	
	public User(String n, String t, String b, String p){
		name = n;
		telNr = t;
		barcode = b;
		pin = p;
	}
	
	public String getBarcode(){
		return barcode;
	}
	
	public String getPin(){
		return pin;
	}
	
	public String getName(){
		return name;
	}
	
	public String getTelNr(){
		return telNr;
	}
	
	public int getBikesInGarage(){
		return bikesInGarage;
	}
	

	@Override
	public int compareTo(User other) {
		return barcode.compareTo(other.getBarcode());
	}
	
	//public void setPin m�ste diskuteras!
	
	public void setName(String n){
		name = n;
	}
}