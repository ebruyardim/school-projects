package ass2;

public class Order {
	
	private String waiter_name;
	private int no_of_customer;
	private String item_name[] = new String[10];
	private int order_count[] = new int[10];
	private int table_id;
	
	private int add_sayisi=0;
	private int no_added_item;
	private int new_item_sayisi;
	private int oner=0;


	public Order(String waiter_name, int no_of_customer, String[] item_name, int[] order_count,int id) {
		
		setWaiter_name(waiter_name);
		setNo_of_customer(no_of_customer);
		setItem_name(item_name);
		setOrder_count(order_count);
		setTable_id(id);
		setAdd_sayisi();
	}	


	public Order(String w_name,String[] i_name,int[] i_count,int id) {
		setWaiter_name(w_name);
		setItem_name(i_name);
		setOrder_count(i_count);
		setTable_id(id);
		setAdd_sayisi();
	}
	public int getOner() {
		return oner;
	}
	public void setOner(int oner) {
		this.oner += oner;
	}
	
	public int getNew_item_sayisi() {
		return new_item_sayisi;
	}
	public void setNew_item_sayisi(int new_item_sayisi) {
		this.new_item_sayisi += new_item_sayisi;
	}
	
	public int getNo_added_item() {
		return no_added_item;
	}
	public void setNo_added_item(int no_added_item) {
		this.no_added_item += no_added_item;
	}
	
	public int getAdd_sayisi() {
		return add_sayisi;
	}
	public void setAdd_sayisi() {
		this.add_sayisi++;
	}


	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	
	
	public String getWaiter_name() {
		return waiter_name;
	}
	public void setWaiter_name(String waiter_name) {
		this.waiter_name = waiter_name;
	}

	
	public int getNo_of_customer() {
		return no_of_customer;
	}
	public void setNo_of_customer(int no_of_customer) {
		this.no_of_customer = no_of_customer;
	}


	public String[] getItem_name() {
		return item_name;
	}
	public void setItem_name(String[] item_name) {
		this.item_name = item_name;
	}


	public int[] getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int[] order_count) {
		this.order_count = order_count;
	}
























}
