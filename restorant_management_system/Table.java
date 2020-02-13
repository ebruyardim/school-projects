
public class Table {
	
	private int id;
	private int capacity;
	private String state;
	private String creator;
	private String waiter;
	private int order_sayisi=0;
	private int[] added_item = new int[10];
	
	// orders array (max 5)
	// 1 orderda max 10 item olabilir.
	
	public int getId() {
		return id;
	}
	public Table(int id, int capacity,String creator, String state) {
		setId(id);
		setCapacity(capacity);
		setCreator(creator);
		setState(state);
	}
	
	
	public int getAdded_item(int y) {
		return added_item[y];
	}
	
	int x=0;
	public int setAdded_item(int added_item) {
		this.added_item[x] += added_item;
		x++;
		return x;
	}
	
	
	public int getOrder_sayisi() {
		return order_sayisi;
	}
	public void setOrder_sayisi(int order_sayisi) {
		this.order_sayisi += order_sayisi;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getWaiter() {
		return waiter;
	}
	public void setWaiter(String waiter) {
		this.waiter = waiter;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
