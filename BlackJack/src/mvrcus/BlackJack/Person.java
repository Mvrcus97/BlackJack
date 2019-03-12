package mvrcus.BlackJack;

public class Person {
	double hoyde;
	String navn; 
	String eyeColor;
	
	public Person(String n, double h, String c) {
		this.navn = n;
		this.hoyde = h;
		this.eyeColor = c;

		
	}
	
	
	public String getNavn() {
		return navn;
	}
	
	public String getEye() {
		return eyeColor;
	}
	
	public void changeName(String nyttNavn) {
		this.navn = nyttNavn;
	}
	

}
