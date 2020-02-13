
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.Iterator;

public class Main  
{ 

	public static void main(String[] args) throws IOException {

		
		PrintStream out = new PrintStream("output.txt");
		System.setOut(out);

		
		JSONParser parser = new JSONParser();
		SetUp setupObject = new SetUp();
		String[] liste1 = new String[22];
		String[] liste2 = new String[4];
		String[] liste3 = new String[2];
		
		String[] liste2_1 = new String[6];
		String[] liste2_2 = new String[11];

		try {

            Object obj = parser.parse(new FileReader("property.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Object obj1 = parser.parse(new FileReader("list.json"));
            JSONObject jsonObject1 = (JSONObject) obj1;
                       
            JSONArray bir = (JSONArray) jsonObject.get("1");  // Land
            JSONArray iki = (JSONArray) jsonObject.get("2");  // Railroads
            JSONArray uc = (JSONArray) jsonObject.get("3");   // Company
            JSONArray chance = (JSONArray) jsonObject1.get("chanceList");
            JSONArray community = (JSONArray) jsonObject1.get("communityChestList");

            Iterator<JSONObject> iterator = bir.iterator();
            Iterator<JSONObject> iterator2 = iki.iterator();
            Iterator<JSONObject> iterator3 = uc.iterator();                                   
            Iterator<JSONObject> iteratorA = chance.iterator();
            Iterator<JSONObject> iteratorB = community.iterator();
            
            int k=0;            
            while (iterator.hasNext()) {
                liste1[k] = iterator.next().toString();
                k++;
            }
            
            int l=0;
            while (iterator2.hasNext()) {
                liste2[l] = iterator2.next().toString();
                l++;
            }
            
            int m=0;
            while (iterator3.hasNext()) {
                liste3[m] = iterator3.next().toString();
                m++;
                if (m==4) {
                	break;
                }            
            }
            
            int n=0;            
            while (iteratorA.hasNext()) {
                liste2_1[n] = iteratorA.next().toString();
                n++;
            }
            
            int p=0;
            while (iteratorB.hasNext()) {
                liste2_2[p] = iteratorB.next().toString();
                p++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		
        for (int i=0;i<liste1.length;i++) {
        	String a = liste1[i].split(",")[0].split(":")[1];
        	String b = liste1[i].split(",")[1].split(":")[1];
        	String c = liste1[i].split(",")[2].split(":")[1];
        	
        	int cost = Integer.parseInt(a.substring(1,a.length()-1));
        	String name = b.substring(1,b.length()-1);
        	int id = Integer.parseInt(c.substring(1,c.length()-2));
        	String state = "bos"; 
    	
    		Properties land = new Land(id,name,cost,state);
    		setupObject.addLand(land);
        }
        
        for (int i=0;i<liste2.length;i++) {

        	String a = liste2[i].split(",")[0].split(":")[1];
        	String b = liste2[i].split(",")[1].split(":")[1];
        	String c = liste2[i].split(",")[2].split(":")[1];
        	
        	int cost = Integer.parseInt(a.substring(1,a.length()-1));
        	String name = b.substring(1,b.length()-1);
        	int id = Integer.parseInt(c.substring(1,c.length()-2));
        	String state = "bos";
        	
    		Properties rail = new Railroads(id,name,cost,state);
    		setupObject.addRail(rail);        	        	
        }
        
        for (int i=0;i<liste3.length;i++) {
        	
          	String a = liste3[i].split(",")[0].split(":")[1];
        	String b = liste3[i].split(",")[1].split(":")[1];
        	String c = liste3[i].split(",")[2].split(":")[1];
        	
        	int cost = Integer.parseInt(a.substring(1,a.length()-1));
        	String name = b.substring(1,b.length()-1);
        	int id = Integer.parseInt(c.substring(1,c.length()-2));
        	String state = "bos";
        	
    		Properties company = new Company(id,name,cost,state);
    		setupObject.addComp(company);         	        	        	        	        	        	
        }        
		
		// Liste chance
        for (int i=0;i<liste2_1.length;i++) {
        	String a = liste2_1[i].split(":")[1];
        	String item = a.substring(1, a.length()-2);
        	Lists chance = new ChanceList(item,i);
        	setupObject.addChance(chance);
        }
        
        // Liste community
        for (int i=0;i<liste2_2.length;i++) {
        	String a = liste2_2[i].split(":")[1];
        	String item = a.substring(1, a.length()-2);
        	Lists comm = new CommunityChestList(item,i);
        	setupObject.addCommunity(comm);
        }
        
        //--------------------------------------------------//
        
		String[] command = new String[50];
		int x=0;
		File commandFile = new File(args[0]); 	  
		BufferedReader br = new BufferedReader(new FileReader(commandFile)); 	  
		String comm; 
		while ((comm = br.readLine()) != null) {
			command[x] = comm;
			x++;
		}
		
		
		Users player1 = new Player("1",15000); 
		Users player2 = new Player("2",15000);
		Users currentPlayer = null;
		Users banker = new Banker("Oner",100000);
		Users diger = null;
		
		for (int i=0;i<x;i++) {

			if (!command[i].equals("show()")) {
				
				String player = command[i].split(" ")[1].split(";")[0];
				int zar = Integer.parseInt(command[i].split(" ")[1].split(";")[1]);
				
				if (player.equals("1")) {
					currentPlayer = player1;
					currentPlayer.setBasamak(zar);
					diger = player2;
				}			
				
				else if (player.equals("2")) {
					currentPlayer = player2;
					currentPlayer.setBasamak(zar);
					diger = player1;
				}
				if (currentPlayer.getBasamak()>40) {  // Sirayi basa sar
					currentPlayer.setBasamak(-40);
					currentPlayer.setMoney(200);
					banker.setMoney(-200);
				}
				if (currentPlayer.getJailCounter()==1 || currentPlayer.getJailCounter()==2 || currentPlayer.getJailCounter()==3) {
					currentPlayer.setJailCounter(1);
					currentPlayer.setBasamak(-zar);
					System.out.print(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player);
					System.out.println(" in jail (count="+(currentPlayer.getJailCounter()-1)+")");
					continue;
				}
				else {
					int proSayac=0;
					for (int j=0;j<setupObject.landList.length;j++) {
						if (currentPlayer.getBasamak()==setupObject.landList[j].getId()) {
							setupObject.land(currentPlayer.getBasamak(),currentPlayer,banker,diger,zar);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
							setupObject.checkMoney(currentPlayer,diger,banker);
							
							proSayac++;
						}
					}
					for (int j=0;j<setupObject.railList.length;j++) {
						if (currentPlayer.getBasamak()==setupObject.railList[j].getId()) {
							setupObject.rail(currentPlayer.getBasamak(),currentPlayer,banker,diger,zar);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
							setupObject.checkMoney(currentPlayer,diger,banker);

							proSayac++;
						}
					}
					for (int j=0;j<setupObject.compList.length;j++) {
						if (currentPlayer.getBasamak()==setupObject.compList[j].getId()) {
							setupObject.company(currentPlayer.getBasamak(),currentPlayer,banker,diger,zar);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
							setupObject.checkMoney(currentPlayer,diger,banker);

							proSayac++;
						}
					}

					if (proSayac==0) {

						if (currentPlayer.getBasamak()==3 || currentPlayer.getBasamak()==18 ||currentPlayer.getBasamak()==34) {
							setupObject.community(currentPlayer,banker,diger);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
							setupObject.checkMoney(currentPlayer,diger,banker);

						}
						else if (currentPlayer.getBasamak()==8 || currentPlayer.getBasamak()==23 ||currentPlayer.getBasamak()==37) {
							setupObject.chance(currentPlayer,banker,diger,zar);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
							setupObject.checkMoney(currentPlayer,diger,banker);

						}
						else if (currentPlayer.getBasamak()==1) { 
							setupObject.go(currentPlayer,banker);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
						}
						else if (currentPlayer.getBasamak()==5) {
							setupObject.incomeTax(currentPlayer,banker);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
							setupObject.checkMoney(currentPlayer,diger,banker);

						}
						else if (currentPlayer.getBasamak()==39) {
							setupObject.superTax(currentPlayer,banker);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
							setupObject.checkMoney(currentPlayer,diger,banker);

						}											
						else if (currentPlayer.getBasamak()==11) {
							setupObject.jail();
							currentPlayer.setJailCounter(1);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
						}
						else if (currentPlayer.getBasamak()==21) {
							setupObject.freeParking();
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
						}
						else if (currentPlayer.getBasamak()==31) {
							currentPlayer.setBasamak(-currentPlayer.getBasamak()+1);
							setupObject.goToJail(currentPlayer); 
							currentPlayer.setJailCounter(1);
							System.out.println(command[i].split(";")[0]+"	"+command[i].split(";")[1]+"	"+currentPlayer.getBasamak()+"	"+player1.getMoney()+"	"+player2.getMoney()+"	Player "+player+setupObject.getMesaj());
						}					
					}					
				}
			}
			
			else {
				setupObject.show(player1,player2,banker);
			}				
		}
       
		setupObject.show(player1,player2,banker);


	
		
		
		


		
		
		
		
		
		
	
	}

}
