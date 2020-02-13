package ass2;

public class Employer extends Worker{

	private int masaSayisi=0;
	
	public Employer(String name, String authorization, double salary,int masaSayisi) {
		super(name, authorization, salary);
		setMasaSayisi(masaSayisi);
	}
	public int getMasaSayisi() {
		return masaSayisi;
	}
	public void setMasaSayisi(int artisMiktari) {
		this.masaSayisi += artisMiktari;
	}


	
}
