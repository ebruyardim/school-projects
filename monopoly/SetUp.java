
import java.io.PrintStream;
import java.io.PrintWriter;

public class SetUp {

	public void output(final String msg, PrintStream out1, PrintWriter out2) {        
		out1.println(msg);
		out2.println(msg);
	}
	
	
	Properties[] landList = new Land[22];
	Properties[] railList = new Railroads[4];
	Properties[] compList = new Company[2];
	
	Lists[] chanceList = new ChanceList[6];
	Lists[] communityList = new CommunityChestList[11];
	String mesaj = null;
	
	int commCounter=0;

	public String getMesaj() {
		return mesaj;
	}

	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}

	int a=0;
	public int addLand(Properties x) {
		landList[a] = x; 	                                                        
		a++;
		return a;
	}
	
	int b=0;
	public int addRail(Properties x) {
		railList[b] = x;
		b++;
		return b;
	}
	
	int c=0;
	public int addComp(Properties x) {
		compList[c] = x;
		c++;
		return c;
	}
	
	int d=0;
	public int addChance(Lists x) {
		chanceList[d] = x;
		d++;
		return d;
	}
	
	int e=0;
	public int addCommunity(Lists x) { // Index ile id ayni 
		communityList[e] = x;
		e++;
		return e;
	}
	//----------------------------------------------------------------------------
	
	public void land(int id,Users player,Users banker,Users diger,int zar) {
		int counter = 0,j=0;
		for (int i=0;i<landList.length;i++) {
			if (landList[i].getId()==id) {
				if (landList[i].getState().equals("bos")) {
					checkEksi(player,diger,banker,landList[i].getCost(),zar);

					mesaj = " bought "+landList[i].getName();
					player.setMoney(-landList[i].getCost());
					banker.setMoney(landList[i].getCost());
					landList[i].setState("dolu");
					player.setBought(landList[i].getName());			
				}
				else if (landList[i].getState().equals("dolu")) {
					while (player.getBought()[j] != null) {
						if (!landList[i].getName().equals(player.getBought()[j])) {
							counter++;
							mesaj = " has "+landList[i].getName();

						}
						j++;
					}
					if (counter == player.getMalSayisi()) {
						int kira;
						if (landList[i].getCost()>0 && landList[i].getCost()<=2000) {
							kira = landList[i].getCost()*4/10;
						}
						else if (landList[i].getCost()>2000 && landList[i].getCost()<=3000) {
							kira = landList[i].getCost()*3/10;
						}
						else {
							kira = landList[i].getCost()*35/100;
						}
						checkEksi(player,diger,banker,kira,zar);
						mesaj = " paid rent for "+landList[i].getName();
						player.setMoney(-kira);
						diger.setMoney(kira);
					}
				}
			}
		}
	}
	
	public void rail(int id,Users player,Users banker,Users diger,int zar) {
		int counter = 0,j=0;
		for (int i=0;i<railList.length;i++) {
			if (railList[i].getId()==id) {
				if (railList[i].getState().equals("bos")) {
					checkEksi(player,diger,banker,railList[i].getCost(),zar);

					mesaj = " bought "+railList[i].getName();
					player.setMoney(-railList[i].getCost());
					banker.setMoney(railList[i].getCost());
					railList[i].setState("dolu");
					player.setBought(railList[i].getName());
					player.setRailSayisi(1);
				}
				else if (railList[i].getState().equals("dolu")) {
					while (player.getBought()[j] != null) {
						if (!railList[i].getName().equals(player.getBought()[j])) {
							counter++;
							mesaj = " has "+railList[i].getName();

						}
						j++;
					}
					if (counter == player.getMalSayisi()) {
						int kira = 25*diger.getRailSayisi();
						checkEksi(player,diger,banker,kira,zar);
						mesaj = " paid rent for "+railList[i].getName();
						player.setMoney(-kira);
						diger.setMoney(kira);
					}
				}
			}
		}
	}
	
	public void company(int id,Users player,Users banker,Users diger,int zar) {
		int counter = 0,j=0;
		for (int i=0;i<compList.length;i++) {
			if (compList[i].getId()==id) {
				if (compList[i].getState().equals("bos")) {
					checkEksi(player,diger,banker,compList[i].getCost(),zar);
					mesaj = " bought "+compList[i].getName();
					player.setMoney(-compList[i].getCost());
					banker.setMoney(compList[i].getCost());
					compList[i].setState("dolu");
					player.setBought(compList[i].getName());					
				}
				else if (compList[i].getState().equals("dolu")) {
					while (player.getBought()[j] != null) {
						if (!compList[i].getName().equals(player.getBought()[j])) {
							counter++;  // Zaten kendisi almis
							mesaj = " has "+compList[i].getName();
						}
						j++;
					}
					if (counter == player.getMalSayisi()) { // Kendisi almamis
						checkEksi(player,diger,banker,4*zar,zar);
						mesaj = " paid rent for "+compList[i].getName();
						player.setMoney(-4*zar);
						diger.setMoney(4*zar);
					}
				}
			}
		}
	}
	
	int chanceCounter=0;
	public int chance(Users player,Users banker,Users diger,int zar) {
		
		if (chanceList[chanceCounter].getId()==0) { //" advance to go (Collect $200)"

			player.setMoney(200);
			banker.setMoney(-200);
			player.setBasamak(-player.getBasamak()+1);
			mesaj = " draw Advance to Go (Collect $200)";
			
			chanceCounter++;
			if (chanceCounter==d) {
				chanceCounter = 1;
			}
		}
		else if (chanceList[chanceCounter].getId()==1) { // " advanced to Leicester Square"
			
			if (player.getBasamak()>27) {
				player.setBasamak(-(player.getBasamak()-27));
				player.setMoney(200);
				banker.setMoney(-200);
			}
			else {
				player.setBasamak(27-player.getBasamak());
			}
			int counter = 0,j=0;
			for (int i=0;i<landList.length;i++) {
				if (landList[i].getName().equals("Leicester Square")) {
					if (landList[i].getState().equals("dolu")) {
						
						while (player.getBought()[j] != null) {
							if (!landList[i].getName().equals(player.getBought()[j])) {
								counter++;
							}
							j++;
						}
						if (counter == player.getMalSayisi()) {
							int kira;
							mesaj = " draw Advanced to Leicester Square- Player "+player.getName()+" paid rent for "+landList[i].getName();
							if (landList[i].getCost()>0 && landList[i].getCost()<=2000) {
								kira = landList[i].getCost()*4/10;
							}
							else if (landList[i].getCost()>2000 && landList[i].getCost()<=3000) {
								kira = landList[i].getCost()*3/10;
							}
							else {
								kira = landList[i].getCost()*35/100;
							}
							checkEksi(player,diger,banker,kira,zar);
							player.setMoney(-kira);
							diger.setMoney(kira);
						}
						
					}
					else {
						checkEksi(player,diger,banker,landList[i].getCost(),zar);
						mesaj = " draw Advanced to Leicester Square- Player "+player.getName()+" bought "+landList[i].getName();
						player.setMoney(-landList[i].getCost());
						banker.setMoney(landList[i].getCost());
						landList[i].setState("dolu");
						player.setBought(landList[i].getName());
					}
				}
			}
			
			chanceCounter++;
			if (chanceCounter==d) {
				chanceCounter = 1;
				mesaj = " has Leicester Square";
			}
		}
		else if (chanceList[chanceCounter].getId()==2) { //" go back 3 spaces"
			player.setBasamak(-3);
			
			if (player.getBasamak()==5) {
				player.setMoney(-100);
				banker.setMoney(100);
				mesaj = " go back 3 spaces Paid Tax";
			}
			else if (player.getBasamak()==20) {
				
				if (landList[10].getState().equals("bos")) {
					checkEksi(player,diger,banker,landList[10].getCost(),zar);
					landList[10].setState("dolu");
					player.setMoney(-landList[10].getCost());
					banker.setMoney(landList[10].getCost());
					player.setBought(landList[10].getName());
					mesaj = " draw go back 3 spaces Player "+player.getName()+" bought Vine Street";
				}
				else {
					
					int j=0,counter=0;
					while (player.getBought()[j] != null) {
						if (!landList[10].getName().equals(player.getBought()[j])) {
							counter++;
							mesaj = " draw go back 3 spacesPlayer has Vine Street";

						}
						j++;
					}
					
					if (counter == player.getMalSayisi()) {
						int kira;
						mesaj = " draw Go back 3 spaces Player "+player.getName()+" paid rent for "+landList[10].getName();
						kira = landList[10].getCost()*4/10;
						checkEksi(player,diger,banker,kira,zar);
						player.setMoney(-kira);
						diger.setMoney(kira);
					}
				}
			}
			else {
				// 34 community chest
				community(player,banker,diger);

			}
			
			chanceCounter++;
		}
		else if (chanceList[chanceCounter].getId()==3) { // "pay poor tax of $15"
			player.setMoney(-15);
			banker.setMoney(15);
			mesaj =  " draw pay poor tax of $15";
			chanceCounter++;
			if (chanceCounter==d) {
				chanceCounter = 1;
			}
		}
		else if (chanceList[chanceCounter].getId()==4) { //" your building loan matures - collect $150"
			player.setMoney(150);
			banker.setMoney(-150);
			mesaj = " draw your building loan matures - collect $150";
			
			chanceCounter++;
			if (chanceCounter==d) {
				chanceCounter = 1;
			}
		}
		else if (chanceList[chanceCounter].getId()==5) { // " you have won a crossword competition - collect $100"
			player.setMoney(100);
			banker.setMoney(-100);
			mesaj = " draw you have won a crossword competition - collect $100";
			chanceCounter++;
			if (chanceCounter==d) {
				chanceCounter = 1;
			}
		}

		return chanceCounter;
	}
	
	public int community(Users player,Users banker,Users diger) {
		if (communityList[commCounter].getId()==0) { //" advance to go (Collect $200)"
			player.setMoney(200);
			banker.setMoney(-200);
			player.setBasamak(-player.getBasamak()+1);
			mesaj = " draw Community Chest -Advance to Go (collect $200)";	
			commCounter++;
		}
		else if (communityList[commCounter].getId()==1) { //"Bank error in your favor - collect $75"
			player.setMoney(75);
			banker.setMoney(-75);
			mesaj = " draw Community Chest -bank error in your favor - collect $75";						
			commCounter++;
		}
		else if (communityList[commCounter].getId()==2) { //"Doctor's fees - Pay $50"
			player.setMoney(-50);
			banker.setMoney(50);
			mesaj = " draw Community Chest -doctor's fees - Pay $50";						
			commCounter++;
		}
		else if (communityList[commCounter].getId()==3) { //"It is your birthday Collect $10 from each player"
			player.setMoney(10);
			diger.setMoney(-10);
			mesaj = " draw Community Chest -it is your birthday Collect $10 from each player";		
			commCounter++;

		}
		else if (communityList[commCounter].getId()==4) { //"Grand Opera Night
			player.setMoney(50);
			diger.setMoney(-50);			
			mesaj = " draw Community Chest -Grand Opera Night - collect $50 from every player for opening night seats";
			commCounter++;

		}
		else if (communityList[commCounter].getId()==5) { //"Income Tax refund - collect $20"
			player.setMoney(20);
			banker.setMoney(-20);
			mesaj = " draw Community Chest -income Tax refund - collect $20";						
			commCounter++;

		}
		else if (communityList[commCounter].getId()==6) { //"Life Insurance Matures - collect $100"
			player.setMoney(100);
			banker.setMoney(-100);
			mesaj = " draw Community Chest -life Insurance Matures - collect $100";			
			commCounter++;
	
		}
		else if (communityList[commCounter].getId()==7) { //"Pay Hospital Fees of $100"
			player.setMoney(-100);
			banker.setMoney(100);
			mesaj = " draw Community Chest -pay Hospital Fees of $100";
			commCounter++;

		}
		else if (communityList[commCounter].getId()==8) { //"Pay School Fees of $50"
			player.setMoney(-50);
			banker.setMoney(50);
			mesaj = " draw Community Chest -pay School Fees of $50";						
			commCounter++;

		}
		else if (communityList[commCounter].getId()==9) { //"You inherit $100"
			player.setMoney(100);
			banker.setMoney(-100);
			mesaj = " draw Community Chest -you inherit $100";						
			commCounter++;

		}
		else if (communityList[commCounter].getId()==10) { //"From sale of stock you get $50"
			player.setMoney(50);
			banker.setMoney(-50);
			mesaj = " draw Community Chest -from sale of stock you get $50";						
			commCounter=0;
			
		}
		
		return commCounter;
	}
	
	
	public void go(Users player,Users banker) {
		mesaj = " is in GO square";
	}
	public void incomeTax(Users player,Users banker) {
		player.setMoney(-100);
		banker.setMoney(100);
		mesaj = " Paid Tax";
	}
	public void superTax(Users player,Users banker) {
		player.setMoney(-100);
		banker.setMoney(100);
		mesaj = " paid Tax";
	}
	public void freeParking() {
		mesaj = " is in free parking";
	}
	public void goToJail(Users player) {
		mesaj = " went to jail";
		player.setBasamak(-player.getBasamak()+11);
		
	}
	public void jail() {
		mesaj = " went to jail";
	}
	
	
	
	public void show(Users player1, Users player2,Users banker) {
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.print("Player 1	"+player1.getMoney()+"	have: ");
		int i=0,j=0;
		while (player1.getBought()[i] != null) {
			if (player1.getBought()[i+1] == null) {
				System.out.print(player1.getBought()[i]);
			}
			else {
				System.out.print(player1.getBought()[i]+", ");
			}
			i++;
		}
		System.out.print("\nPlayer 2	"+player2.getMoney()+"	have: ");
		while (player2.getBought()[j] != null) {
			if (player2.getBought()[j+1] == null) {
				System.out.print(player2.getBought()[j]);
			}
			else {
				System.out.print(player2.getBought()[j]+", ");
			}
			j++;
		}
		
		System.out.println("\nBanker	"+banker.getMoney());

		if (player1.getMoney()>player2.getMoney()) {
			System.out.println("Winner Player 1");

		}
		else if (player1.getMoney()<player2.getMoney()) {
			System.out.println("Winner Player 2");
		}
		else {
			System.out.println("Scoreless");
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------------");

	}

	
	public void checkMoney(Users player,Users diger,Users banker) {
		if ((player.getMoney()<0) && (player.getName().equals("1"))){
			show(player,diger,banker);
			System.exit(0);
		}
		if ((player.getMoney()<0) && (player.getName().equals("2"))){
			show(diger,player,banker);
			System.exit(0);
		}
		
	}
	
	
	
	
	public void checkEksi(Users player,Users diger,Users banker,int x,int zar) {
		if (player.getMoney()<x) {
			if (player.getName().equals("1")) {
				System.out.println("Player 1	"+zar+"	"+player.getBasamak()+"	"+player.getMoney()+"	"+diger.getMoney()+"	Player 1 goes bankrupt");				
				show(player,diger,banker);
			}
			else if (player.getName().equals("2")){
				System.out.println("Player 2	"+zar+"	"+player.getBasamak()+"	"+diger.getMoney()+"	"+player.getMoney()+"	Player 2 goes bankrupt");
				show(diger,player,banker);
			}
			System.exit(0);
		}
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	

	

	
	
	
	
}
