package ass3;

public class Properties{  // LAND, RAILROADS, COMPANY
	
	private int id;
	private String name;
	private int cost;
	private String state;
	


	public Properties(int id, String name, int cost, String state) {
		setId(id);
		setName(name);
		setCost(cost);
		setState(state);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}