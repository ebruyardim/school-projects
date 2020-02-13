
import java.util.Collections;

public class setUp {


	public Employer[] getEmployers() {
		return employers;
	}

	public void setEmployers(Employer[] employers) {
		this.employers = employers;
	}

	public Waiter[] getWaiters() {
		return waiters;
	}

	public void setWaiters(Waiter[] waiters) {
		this.waiters = waiters;
	}

	Employer[] employers = new Employer[5];
	Waiter[] waiters = new Waiter[5];
	
	int employer_count = 0;
	int waiter_count = 0;
	
	public int add_employer(Employer e) {
		
		employers[employer_count] = e;
		/*System.out.println("Name: "+employers[employer_count].getName());
		System.out.println("Salary: "+employers[employer_count].getSalary());
		System.out.println("Autho: "+employers[employer_count].getAuthorization());
		System.out.println("Masa Sayisi: "+employers[employer_count].getMasaSayisi());
		System.out.println("-----");*/
		employer_count++;

		return employer_count;
		
	}	
	
	int add_waiter(Waiter w) {
		waiters[waiter_count] = w;

		/*System.out.println("Name: "+waiters[waiter_count].getName());
		System.out.println("Salary: "+waiters[waiter_count].getSalary());
		System.out.println("Autho: "+waiters[waiter_count].getAuthorization());
		System.out.println("-----");*/
		
		waiter_count++;

		return waiter_count;
	}
	
	
	
	
	
	
	Item[] items = new Item[50];
	
	int item_count = 0;
	
	int add_item(Item i) {
		
		items[item_count] = i;
		
		/*System.out.println("Name: "+items[item_count].getName());
		System.out.println("Cost: "+items[item_count].getCost());
		System.out.println("Amount: "+items[item_count].getAmount());
		System.out.println("-----");*/
		
		item_count++;
		return item_count;
	}
	
	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}
	
	void showItem(Item i) {
		for (int t=0;t<item_count;t++) {
			int a = items[t].getName().length();
			System.out.println(items[t].getName()+":	"+items[t].getAmount());

		}
	}
	
	
	
	
	
	Table[] tables =new Table[5];
		
	int table_count = 0;
	
	int create_table(Table x) {
		tables[table_count] = x;
		
		/*System.out.println("ID: "+ tables[table_count].getId());
		System.out.println("Creator: "+ tables[table_count].getCreator());
		System.out.println("Capacity: "+ tables[table_count].getCapacity());
		System.out.println("State: "+tables[table_count].getState());
		System.out.println("-----");*/
		
		table_count++;
		return table_count;
	}
	

	
	
	Order[] orders = new Order[10];
	int no_customer = 0;
	
	public int new_order(Order x) {
		
		orders[no_customer] = x;
		
		/*System.out.println("Waiter name: "+orders[no_customer].getWaiter_name());
		System.out.println("No of customer: "+orders[no_customer].getNo_of_customer());
		System.out.println("Table id: "+orders[no_customer].getTable_id());
		for (int v=0; v<(orders[no_customer].getItem_name()).length; v++) {
			System.out.println("Item names: "+orders[no_customer].getItem_name()[v]);
			System.out.println("Order Count: "+orders[no_customer].getOrder_count()[v]);
		}*/
	
		no_customer++;
		return no_customer;
	}
	
	
	Order[] addOrders = new Order[10];
	int x=0;

	public void add_order(Order y) {
		
		addOrders[x] = y;

		/*System.out.println("Waiter name: "+addOrders[x].getWaiter_name());
		System.out.println("Table id: "+addOrders[x].getTable_id());
		for (int v=0; v<(addOrders[x].getItem_name()).length; v++) {
			System.out.println("Item names: "+addOrders[x].getItem_name()[v]);
			System.out.println("Order Count: "+addOrders[x].getOrder_count()[v]);
		}*/
		x++;
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
}
