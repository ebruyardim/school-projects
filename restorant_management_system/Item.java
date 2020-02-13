package ass2;

public class Item {
	private String name;
	private double cost;
	private int amount=0;
		
	
	public Item(String name,double cost, int amount) {
		setName(name);
		setCost(cost);
		setAmount(amount);
	}
	public Item() {

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount += amount;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}



	
	
	
	
	
	
	
	
	
	
	
}
