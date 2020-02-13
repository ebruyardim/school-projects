
public class Waiter extends Worker{
	
	private int state=0;
	private int masa_sayisi=0;
	
	public Waiter(String name, String authorization, double salary,int state,int masa_sayisi) {
		super(name, authorization, salary);
		setState(state);
		setMasa_sayisi(masa_sayisi);
	}
	
	
	
	public int getMasa_sayisi() {
		return masa_sayisi;
	}



	public void setMasa_sayisi(int masa_sayisi) {
		this.masa_sayisi += masa_sayisi;
	}



	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state += state;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
