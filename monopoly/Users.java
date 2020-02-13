
public class Users {
	private String name;
	private int money;
	private int basamak=1;
	private int jailCounter=0;
	private String[] bought = new String[28];
	private int malSayisi = 0;
	private int railSayisi = 0;
	
	
	public int getRailSayisi() {
		return railSayisi;
	}
	public void setRailSayisi(int railSayisi) {
		this.railSayisi += railSayisi;
	}
	public String[] getBought() {
		return bought;
	}
	public void setBought(String alinan) {
		bought[malSayisi] = alinan;
		malSayisi++;
		
	}

	
	public int getJailCounter() {
		return jailCounter;
	}
	public void setJailCounter(int jailCounter) {
		this.jailCounter += jailCounter;
	}

	public Users(String name, int money) {
		this.name = name;
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money += money;
	}
		
	public int getBasamak() {
		return basamak;
	}

	public void setBasamak(int basamak) {
		this.basamak += basamak;
	}
	public int getMalSayisi() {
		return malSayisi;
	}

	public void setMalSayisi(int malSayisi) {
		this.malSayisi = malSayisi;
	}
	
	
	
	
	
}
