package ass2;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.IntStream;


public class Main {
	
	public static void main(String[] args)throws IOException{
		Locale.setDefault(Locale.US);

		
		NumberFormat formatter = new DecimalFormat("#0.000");     
		
		String[] setupArray = new String[100];
		String[] commandArray = new String[100];
		int x=0, y=0;
		
		File setup_file = new File("setup.dat"); 	  
		BufferedReader br = new BufferedReader(new FileReader(setup_file)); 	  
		String setup; 
		while ((setup = br.readLine()) != null) {
			setupArray[x] = setup;
			x++;
		}
//--------------------------------------------------------------------//
		File command_file = new File("commands.dat"); 	  
		BufferedReader commandOku = new BufferedReader(new FileReader(command_file)); 	  
		String command; 
		while ((command = commandOku.readLine()) != null) {
			commandArray[y] = command;
			y++;
		}
		
		setUp ebru = new setUp();
		int emp_sayac=0,waiterSayisi=0,itemSayisi=0;
		for (int i=0;i<x;i++) {
			if (setupArray[i].split(" ")[0].equals("add_item")) {
				
				String isim = setupArray[i].split(" ")[1].split(";")[0];
				double fiyat = Double.parseDouble(setupArray[i].split(" ")[1].split(";")[1]);
				int miktar = Integer.parseInt(setupArray[i].split(" ")[1].split(";")[2]);
				
				Item item = new Item(isim,fiyat,miktar);

				ebru.add_item(item);
				itemSayisi++;
				
			}
			else if (setupArray[i].split(" ")[0].equals("add_employer")) {
				
				String isim = setupArray[i].split(" ")[1].split(";")[0];
				double salary = Integer.parseInt(setupArray[i].split(" ")[1].split(";")[1]);
				String autho = "employer";
				int no_of_table = 0;
				if (emp_sayac == 5) {
					System.out.println("Waiter "+isim+" cannot be created. Max number of waiter should be 5.");
				}
				else {
					Employer emp = new Employer(isim,autho,salary,no_of_table);
					
					ebru.add_employer(emp);
					emp_sayac++;
				}
				
			}
			else if (setupArray[i].split(" ")[0].equals("add_waiter")) {
				
				String isim = setupArray[i].split(" ")[1].split(";")[0];
				double salary = Integer.parseInt(setupArray[i].split(" ")[1].split(";")[1]);
				String autho = "waiter";
				int state = 0;
				if (waiterSayisi == 5) {
					System.out.println("Waiter "+isim+" cannot be created. Max number of waiter should be 5.");
				}
				else {
					Waiter work = new Waiter(isim,autho,salary,state,0);
					ebru.add_waiter(work);
					waiterSayisi++;
				}
			}
		}
//---------------------------------------------------------------------------//
		int table_id=0;
		int order_sayaci=0;
		int add_order_sayaci=0;
		for (int j=0;j<y;j++) {
			int sayac = 0;

			if (commandArray[j].split(" ")[0].equals("create_table")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: create_table");
				
				String emp_name = commandArray[j].split(" ")[1].split(";")[0];
				int capacity = Integer.parseInt(commandArray[j].split(" ")[1].split(";")[1]);
				String state = "bos";
				
				for (int a=0;a<emp_sayac;a++) {
					if (!emp_name.equals(ebru.getEmployers()[a].getName())){
						sayac++;
					}
				}
				
				if (sayac==emp_sayac) {
					System.out.println("There is no employer named "+emp_name);
				}	
				else if (table_id == 5) {
					System.out.println("Not allowed to exceed. Max number of tables "+table_id);				}																	
				else {
					for (int n=0;n<emp_sayac;n++) {
						
						if (ebru.employers[n].getName().equals(emp_name) && ebru.employers[n].getMasaSayisi()==2){
							System.out.println(ebru.employers[n].getName()+" has already created 2 tables!");
						}
						else if (ebru.employers[n].getName().equals(emp_name)){
							ebru.employers[n].setMasaSayisi(1);
							System.out.println("A new table has successfully been added");				
							
							Table table = new Table(table_id,capacity,emp_name,state);
							ebru.create_table(table);
							table_id++;
						}
						else {
						}
					}
				}																			
			}
			
			else if (commandArray[j].split(" ")[0].equals("new_order")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: new_order");

				String waiter_name = commandArray[j].split(" ")[1].split(";")[0];
				int no_of_customer = Integer.parseInt(commandArray[j].split(" ")[1].split(";")[1]);
				int len = commandArray[j].split(" ")[1].split(";")[2].split(":").length;
		
				String[] itemNameArray = new String[len];
				int[] orderCountArray = new int[len];
				int id=-1;
				for (int z=0;z<len;z++) {
					
					String item_name = commandArray[j].split(" ")[1].split(";")[2].split(":")[z].split("-")[0];
					int order_count = Integer.parseInt(commandArray[j].split(" ")[1].split(";")[2].split(":")[z].split("-")[1]);
					
						itemNameArray[z] = item_name;
						orderCountArray[z] = order_count;	
						
				}
				
				Order order = new Order(waiter_name,no_of_customer,itemNameArray,orderCountArray,id);
				ebru.new_order(order);
				
				int indeks=0,temp=0;
				for (int e=0;e<waiterSayisi;e++) {
					if(ebru.waiters[e].getName().equals(waiter_name)) {
						indeks = e;
						break;
					}
					else {
						temp++;
					}
				}

				int a=0, orderss=0;
				for (int q=0;q<table_id;q++) {
					
					if (ebru.tables[q].getState().equals("dolu")) {
						a++;
					}
					else if ( ebru.tables[q].getCapacity()<no_of_customer) {
						a++;
					}
					else if (ebru.waiters[indeks].getState()>=3) {
						System.out.println("Given waiter exceeds allowed max number of tables for service 3");		
						order_sayaci++;
						break;
					}
					else if ((temp==waiterSayisi)) {
						System.out.println("There is no waiter named "+waiter_name);
						order_sayaci++;
						break;
					}
					else if (orderss == 5) {
						System.out.println("5'ten fazla order !!!");
						break;
					}
					
					if (a==table_id) {
						System.out.println("There is no appropriate table for this order!");
						order_sayaci++;
					}
					else {
						
						if (ebru.tables[q].getState().equals("bos") && ebru.tables[q].getCapacity()>=no_of_customer && ebru.waiters[indeks].getState()<3 && (temp!=waiterSayisi)) {
							System.out.println("Table (= ID "+q+") has been taken into service");
							order.setTable_id(q);
							order_sayaci++;
							ebru.waiters[indeks].setState(1);
							ebru.waiters[indeks].setMasa_sayisi(1);
							ebru.tables[q].setState("dolu");
							ebru.tables[q].setOrder_sayisi(1);
							
							int seyma=0;
							for (int g=0;g<itemNameArray.length;g++) {
								int counter=0;
								for (int s=0;s<itemSayisi;s++) {						
									if (itemNameArray[g].equals(ebru.items[s].getName())) {
										
										for (int d=0;d<orderCountArray[g];d++) {
											if (ebru.items[s].getAmount()>0) {
												if (seyma<10) {
												System.out.println("Item "+itemNameArray[g]+" added into order");
												ebru.items[s].setAmount(-1);;
												order.setNew_item_sayisi(1);
												seyma++;
												}
												else {
													System.out.println("Not allowed to exceed max no. of max item in a single order!");
												}
											}
											else {
												System.out.println("Sorry! No "+itemNameArray[g]+" in the stock!");
											}
										}								
									}
									else {
										counter++;
									}
								}
								if (counter==itemSayisi) {
									System.out.println("Unknown item "+itemNameArray[g]);
									break;
								}
							}						
							break;					
						}
					}
					
				}																																														
			}
			
			else if (commandArray[j].split(" ")[0].equals("add_order")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: add_order");

				String waiter_name = commandArray[j].split(" ")[1].split(";")[0];
				int tableId = Integer.parseInt(commandArray[j].split(" ")[1].split(";")[1]);
				int lent = commandArray[j].split(" ")[1].split(";")[2].split(":").length;
				String[] nameArray = new String[lent];
				int[] countArray = new int[lent];
				
				
				for (int v=0;v<lent;v++) {
					String item_name = commandArray[j].split(" ")[1].split(";")[2].split(":")[v].split("-")[0];
					int order_count = Integer.parseInt(commandArray[j].split(" ")[1].split(";")[2].split(":")[v].split("-")[1]);	
					
					nameArray[v] = item_name;
					countArray[v] = order_count;
				}
								
				
				Order addOrder = new Order(waiter_name,nameArray,countArray,tableId);
				ebru.add_order(addOrder);
				add_order_sayaci++;
				int sum = IntStream.of(countArray).sum();
	
				
				int counter=0;
				for (int i=0;i<order_sayaci;i++) {

					if (tableId == ebru.orders[i].getTable_id()) {	
						if(ebru.tables[tableId].getOrder_sayisi()==5) {
							System.out.println("Max order number (5) has been reached.");
						}

						else if(waiter_name.equals(ebru.orders[i].getWaiter_name())) {
							
							ebru.tables[tableId].setOrder_sayisi(1);
							
							for (int s=0;s<waiterSayisi;s++) {
								if (waiter_name.equals(ebru.waiters[s].getName())) {
									ebru.waiters[s].setMasa_sayisi(1);
								}
							}
							int seyma=0;
							for (int g=0;g<nameArray.length;g++) {
																
								for (int s=0;s<itemSayisi;s++) {												
									
									if (nameArray[g].equals(ebru.items[s].getName())) {
										
										for (int d=0;d<countArray[g];d++) {											
											
											if (ebru.items[s].getAmount()>0) {
												if (seyma<10) {
													System.out.println("Item "+nameArray[g]+" added into order");											
													ebru.items[s].setAmount(-1);	
													seyma++;
												}
												else {
													System.out.println("Not allowed to exceed max no. of max item in a single order!");
												}
											}
											else {
												System.out.println("Sorry! No "+nameArray[g]+" in the stock!");
												sum--;
											}
										}								
									}
								}
							}
							ebru.tables[tableId].setAdded_item(sum);													

						}

						else if (ebru.tables[tableId].getState().equals("bos")) {
							System.out.println("This table is either not in service now or "+waiter_name+" cannot be assigned this table!");
						}
						else if (!ebru.orders[i].getWaiter_name().equals(waiter_name)) {
							System.out.println("This table is either not in service now or "+waiter_name+" cannot be assigned this table!");
						}
					}
					else {
						counter++;
						if (counter==order_sayaci) {
							System.out.println("This table is either not in service now or "+waiter_name+" cannot be assigned this table!");
						}
					}
				}
			}
			
			else if (commandArray[j].split(" ")[0].equals("check_out")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: check_out");

				String waiter_name = commandArray[j].split(" ")[1].split(";")[0];
				int tableId = Integer.parseInt(commandArray[j].split(" ")[1].split(";")[1]);

				int say=0,say2=0;
				double total=0;
				for (int i=0;i<order_sayaci;i++) {
					if(waiter_name.equals(ebru.orders[i].getWaiter_name())) {
						if (tableId == ebru.orders[i].getTable_id()) {	
							
							ebru.orders[i].setTable_id(-1);
							ebru.tables[tableId].setState("bos");
							for (int c=0;c<waiterSayisi;c++) {
								if (waiter_name.equals(ebru.waiters[c].getName())) {
									ebru.waiters[c].setState(-1);
								}
							}
														
							int n=0;
							try {
								while (ebru.orders[i].getOrder_count()[n] != 0 ){
									for (int h=0;h<itemSayisi;h++) {
										if (ebru.items[h].getName().equals(ebru.orders[i].getItem_name()[n])) {
											String isim = ebru.orders[i].getItem_name()[n];
											double ucret = ebru.items[h].getCost();
											int tane = ebru.orders[i].getOrder_count()[n];
											double tutar = ucret*tane;
											System.out.println(isim+":	"+formatter.format(ucret)+" (x "+tane+") "+formatter.format(tutar)+" $");
											total += tutar;
										}
									}
									n++;
								}
							}catch(ArrayIndexOutOfBoundsException ignored) {}
				// ADD ORDER																	
							double add_total=0;
							for (int u=0;u<add_order_sayaci;u++) {
								if(waiter_name.equals(ebru.addOrders[u].getWaiter_name())) {
									if (tableId == ebru.addOrders[u].getTable_id()) {							
										int m=0;
										try {
											while (ebru.addOrders[u].getOrder_count()[m] != 0 ){
												for (int h=0;h<itemSayisi;h++) {
													if (ebru.items[h].getName().equals(ebru.addOrders[u].getItem_name()[m])) {
														String isim = ebru.addOrders[u].getItem_name()[m];
														double ucret = ebru.items[h].getCost();
														int tane = ebru.addOrders[u].getOrder_count()[m];
														double tutar = ucret*tane;
														System.out.println(isim+":	"+formatter.format(ucret)+" (x "+tane+") "+formatter.format(tutar)+" $");
														add_total += tutar;
													}
												}
												m++;
											}
										}catch(ArrayIndexOutOfBoundsException ignored) {}
									}
								}				
							}
							double toplam_tutar = total+add_total;
							System.out.println("Total:	"+formatter.format(toplam_tutar));						
						}
						else {
							say++;
							if (say==order_sayaci-1) {
								System.out.println("This table is either not in service now or "+waiter_name+" cannot be assigned this table!");
							}
						}
					}
					else {
						say2++;
						if (say2==order_sayaci) {
							System.out.println("There is no waiter named "+waiter_name);
						}
					}					
				}						
			}
			
			else if (commandArray[j].split(" ")[0].equals("stock_status")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: stock_status");
				Item item = new Item();
				ebru.showItem(item);				
			}
			
			else if (commandArray[j].split(" ")[0].equals("get_table_status")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: get_table_status");

				for (int h=0;h<table_id;h++) {
					if (ebru.tables[h].getState().equals("bos")) {
						System.out.println("Table "+h+": Free");
					}
					else {     // DOLU
						for (int k=0;k<order_sayaci;k++) {

							if (h==ebru.orders[k].getTable_id()) {
								System.out.println("Table "+h+": Reserved ("+ebru.orders[k].getWaiter_name()+")");
							}
						}
					}
				}								
			}
			
			else if (commandArray[j].split(" ")[0].equals("get_order_status")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: get_order_status");

				for (int i=0;i<table_id;i++) {
					int e_sayac=0;
					System.out.println("Table: "+i);
					for (int k=0;k<order_sayaci;k++) {
						if (i==ebru.orders[k].getTable_id()) {

							System.out.println("	"+ebru.tables[i].getOrder_sayisi()+" order(s)");							
							System.out.println("		"+ebru.orders[k].getNew_item_sayisi()+" item(s)");
							
							for (int b=0;b<ebru.tables[i].getOrder_sayisi()-1;b++) {
								System.out.println("		"+ebru.tables[i].getAdded_item(b)+" item(s)");
							}
						}
						else {
							e_sayac++;
							if (e_sayac==order_sayaci) {
								System.out.println("	0 order(s)");
							}
						}
					}
				}
								
			}
			
			else if (commandArray[j].split(" ")[0].equals("get_employer_salary")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: get_employer_salary");
				
				for (int i=0;i<emp_sayac;i++) {
					String isim = ebru.employers[i].getName();
					double masa_sayisi = ebru.employers[i].getMasaSayisi();
					double maas = ebru.employers[i].getSalary();
					double award = masa_sayisi*maas*(0.1);
					System.out.println("Salary for "+isim+":	"+(maas+award));
				}				
			}
			
			else if (commandArray[j].split(" ")[0].equals("get_waiter_salary")) {
				System.out.println("***********************************\nPROGRESSING COMMAND: get_waiter_salary");
				
				for (int i=0;i<waiterSayisi;i++) {
					String isim = ebru.waiters[i].getName();
					double order_sayisi = ebru.waiters[i].getMasa_sayisi();
					double maas = ebru.waiters[i].getSalary();
					double award = order_sayisi*maas*(0.05);
					
					System.out.println("Salary for "+isim+":	"+(maas+award));
				}
			}

			
			
			
			
		
		
		
		
		
		
		
		
		}
	
	
	
	
	}
}
