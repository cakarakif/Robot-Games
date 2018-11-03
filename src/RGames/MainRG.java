package RGames;
import enigma.console.TextAttributes;
import enigma.core.Enigma;
import java.util.Scanner;
import java.awt.Color;
import enigma.console.*;

public class MainRG {
	private static enigma.console.Console consol=Enigma.getConsole();
	public static void main(String[] args) {
		TextAttributes attrs = new TextAttributes(Color.ORANGE, Color.BLACK);
		s_console.setTextAttributes(attrs);
		Scanner scanner=new Scanner(System.in);
		int input=0;
		boolean escape=true;
        String holder;

        while (input!=1)
        {
        	System.out.println("---------------------------");
            System.out.println("| ~~~~~ ROBOT GAMES ~~~~~ |");
            System.out.println("|    ~~  Main Menu  ~~    |");
            System.out.println("|    1. Play The Game     |");
            System.out.println("|    2. About Game        |");
            System.out.println("|    3. How To Play       |");
            System.out.println("|    4. Devolopers        |");
            System.out.println("|    5. Exit              |");
            System.out.println("---------------------------");
            System.out.print("Enter The Command: ");
            input=scanner.nextInt();

            switch (input) {
            
                case 1: {
                String noone = scanner.nextLine();//switch case komutu içeri iþlenmesi engellendi.
                Team team[]=new Team[6];
        		for (int i = 0; i < team.length; i++) {team[i]=new Team();}
        		
        		Game chess=new Game();
        		Game run=new Game();
        		Game sumo=new Game();
        		Game pingpong=new Game();
        		
        		boolean flag=true,flag1=true,flagfinal=true;
        		int counter=0,whowon=0;
        		String registeredRobotT1[]=new String[9];
        		
        		do
        		{
        			counter++;
        			
        			

        		do //komut alma iþleme bölümü(do-while yanlýþ yazýmlar için eklendi)
        		{
        			flag=true;
        			flag1=true;// komut girme iþlemi bitene kadar sürsün diye.
        			
        			System.out.println("Week:"+counter+" R/C: T1:"+team[0].getRobotlength()+"/"+team[0].getCredit()+"  T2:"+team[1].getRobotlength()+"/"+team[1].getCredit()+" T3:"+team[2].getRobotlength()+"/"+team[2].getCredit()+" T4:"+team[3].getRobotlength()+"/"+team[3].getCredit()+" T5:"+team[4].getRobotlength()+"/"+team[4].getCredit()+" T6:"+team[5].getRobotlength()+"/"+team[5].getCredit());//ekrana yazdýrma kýsmý.
        			System.out.println("--- Team1: Modules ---");
        			team[0].Listmodule();
        			System.out.println("");
        			team[0].Listrobot(1);
        			System.out.println("---Games (Registering)---");
        			System.out.println("Chess: "+chess.getPrize()+" Run: "+run.getPrize()+" Sumo: "+sumo.getPrize()+" Pingpong: "+pingpong.getPrize());
        			System.out.print("Team1:");
        			
        			for (int i = 0; i < registeredRobotT1.length; i++) {// team 1 in kayýtlanan robotlarýnýn yazýmý.
        				if(registeredRobotT1[i]!=null) {System.out.print(registeredRobotT1[i]);}
        			}System.out.println();
        			System.out.println("---------------------------------------------------------------------");
        			
        			
        		System.out.println("");	
        		System.out.print("Command: "); //Komut alma.
        		String command = scanner.nextLine();
        		String commands[] = command.split(" ");
        		
        		
        		if(commands[0].equals("by")&& commands[1].length() == 3  &&(commands[1].substring(0, 2).equals("tr")||commands[1].substring(0, 2).equals("hd")||commands[1].substring(0, 2).equals("lg")||commands[1].substring(0, 2).equals("ar"))&& Integer.parseInt(commands[1].substring(2,3))>0 &&Integer.parseInt(commands[1].substring(2,3))<7)
        		{
        			Module module=new Module(commands[1]);
        			team[0].addModule(module);
        			
        		}
        		
        		else if ((commands[0].equals("sl")&& commands[1].length() == 2  &&commands[1].substring(0, 1).equals("r")&& Integer.parseInt(commands[1].substring(1,2))>0 && Integer.parseInt(commands[1].substring(1,2))<10)||(commands[0].equals("sl")&& commands[1].length() == 3  &&commands[1].substring(0, 1).equals("m")&& Integer.parseInt(commands[1].substring(1,3))>0 && Integer.parseInt(commands[1].substring(1,3))<21))
        		{
        			if(commands[1].length()==3){team[0].sellModule(commands[1]);}
        			else {team[0].sellRobot(Integer.parseInt(commands[1].substring(1, 2)));}
        		}
        		
        		
        		else if (commands[0].equals("++")   )
        		{
        			if(!commands[3].substring(0,1).equals("m") && !commands[4].substring(0,1).equals("m")&& !commands[5].substring(0,1).equals("m")&& !commands[6].substring(0,1).equals("m"))
        			{
        				Module piece1=new Module(commands[3]);
        			    Module piece2=new Module(commands[4]);
        			    Module piece3=new Module(commands[5]);
        			    Module piece4=new Module(commands[6]);
        			    
        			    Robot robot=new Robot(piece1, piece2, piece3, piece4);
        			    
        			    team[0].addRobotdirect(Integer.parseInt(commands[1].substring(1,2)),robot);
        			}
        			
        			else // eger modulden parca alarak robot olusturmak istenirse.
        			{ 
        				Module piece[]=new Module[10];//iþlem için olusturuldu sadece.
        				
        				for (int i = 3; i < commands.length; i++) 
        				{
        				    if(commands[i].substring(0, 1).equals("m")){
        						piece[i]=team[0].getModule(Integer.parseInt(commands[i].substring(1,3)));
        						team[0].addRobotIndirect(piece[i]);//credit dengesi saglandý.
        						team[0].setModule(Integer.parseInt(commands[i].substring(1,3)));
        					}
        					else{
        						piece[i]=new Module(commands[i]);
        					}
        				  }
        				
        				Robot robot=new Robot(piece[3], piece[4], piece[5], piece[6]);
        				team[0].addRobotdirect(Integer.parseInt(commands[1].substring(1,2)),robot);
        						
        			 }
        					
        			}
        		
        		
        		else if (commands[0].equals("--"))
        		{
        			team[0].Segmenting(Integer.parseInt(commands[1].substring(1,2)));
        		}
        		
        		
        		else if (commands[0].equals("ch"))
        		{   
        			team[0].setPiecechange(Integer.parseInt(commands[1].substring(1,2)),Integer.parseInt(commands[2].substring(1,3)));
        		}
        		
        		else if (commands[0].equals("ls"))
        		{
        			team[Integer.parseInt(commands[1])-1].Listrobot(Integer.parseInt(commands[1]));
        		}
        		
        		else if (commands[0].equals("rg"))
        		{   
        			for (int i = 1; i < 6; i++) {// Yapay zeka çaðýrýlarak diðer takýmlarýn oyun yapýlarý oluþturuldu.
        				team[i].Artificialintelligence(i+1,chess,run,sumo,pingpong);
        			}
        			///////////////////////
        			boolean permitflag=true;
        		
        			for (int i = 0; i < 4; i++) { // durabilityler 60 altýna inerse oyuna katýlmasý engellendi.
        				if(team[0].getRobot(Integer.parseInt(commands[1].substring(1,2))).getPiece(i).getDurability()<60){permitflag=false; System.out.println("Not Available For These Games..!"); break;}
        			}
        			
        			if(permitflag=true){
        			for (int i = 0; i < commands.length; i++) {if(registeredRobotT1[i]==null){registeredRobotT1[i]="  "+commands[3]+" > "+commands[1]+" "; break;}}
        			
        			if(commands[3].substring(0,1).equals("c")){chess.addRobotchess(1,team[0].getRobot(Integer.parseInt(commands[1].substring(1,2))),Integer.parseInt(commands[3].substring(1,2))-1);}
        			if(commands[3].substring(0,1).equals("r")){run.addRobotrun(1,team[0].getRobot(Integer.parseInt(commands[1].substring(1,2))),Integer.parseInt(commands[3].substring(1,2))-1);}
        			if(commands[3].substring(0,1).equals("s")){sumo.addRobotsumo(1,team[0].getRobot(Integer.parseInt(commands[1].substring(1,2))),Integer.parseInt(commands[3].substring(1,2))-1);}
        			if(commands[3].substring(0,1).equals("p")){pingpong.addRobotpingpong(1,team[0].getRobot(Integer.parseInt(commands[1].substring(1,2))),Integer.parseInt(commands[3].substring(1,2))-1);}
        			}
        		}
        		
        		else if (commands[0].equals("pl"))
        		{
        			System.out.println("---Games (Results)---");System.out.println("");
        			chess.CalculatescoreChess();//hesaplamalar yapýldý.
        			System.out.println("---#RoboChess: "+"("+chess.getTeamsingame()+"teams) Prize: "+chess.getPrize());
        			chess.listRobotsingame();
        			if(chess.getWinner()!=-1)team[chess.getWinner()].setCredit(chess.getPrize());//kazanýlan para eklendi.
        			System.out.println();

        			run.CalculatescoreRun();
        			System.out.println("---#RoboRun: "+"("+run.getTeamsingame()+"teams) Prize: "+run.getPrize());
        			run.listRobotsingame();
        			if(run.getWinner()!=-1)team[run.getWinner()].setCredit(run.getPrize());
        			System.out.println();

        			sumo.CalculatescoreSumo();
        			System.out.println("---#RoboSumo: "+"("+sumo.getTeamsingame()+"teams) Prize: "+sumo.getPrize());
        			sumo.listRobotsingame();
        			if(sumo.getWinner()!=-1)team[sumo.getWinner()].setCredit(sumo.getPrize());
        			System.out.println();

        			pingpong.CalculatescorePingpong();
        			System.out.println("---#RoboPingPong: "+"("+pingpong.getTeamsingame()+"teams) Prize: "+pingpong.getPrize());
        			pingpong.listRobotsingame();
        			if(pingpong.getWinner()!=-1)team[pingpong.getWinner()].setCredit(pingpong.getPrize());
        			System.out.println();System.out.println();
        			
        			flag1=false;// pl bastýktan sonra oyunlar oynatýlýp diðer haftaya gecmesi saglandý.
        		}
        		
        		else {           //yazýlan commandýn kontrolü yapýldý.
        			System.out.println("Syntax Error!");
        			flag=false;
        		}
        		}while(flag==false || flag1==true);
        		
        		for (int i = 0; i < 6; i++) {// herhaftadurabilityler -2
        			team[i].decrease();
				}
                 chess.reset();run.reset();sumo.reset();pingpong.reset();
                 registeredRobotT1=new String[9];// ekrandaki t1robotlarý silindi.
                 
                 for (int i = 0; i < team.length; i++) { // oyun bitimi kontrol edildi.
        			if(team[i].getCredit()>10000 && team[i].getRobotlength()==6){flagfinal=false; whowon=i+1;}
        		}
                 
                 
        		}while (flagfinal== true);
        		
        		System.out.println();System.out.println();
        		System.out.println("The Winner of The Game is----------->t"+whowon+"<------------");
        		break;
                    
                }
                case 2: {
                    System.out.println();
                    System.out.println("General Information");
                    System.out.println("In the robot games, there are several game types. The aim of players is to build a robot team,");
                    System.out.println("maintenance it, and win the robot games");
                    System.out.println();
                    System.out.println("Teams");
                    System.out.println("There are 6 teams in the organization. The first team is controlled by the human player, others are");
                    System.out.println("controlled by the computer. Each team has 1500 credits at the beginning of the game.");
                    System.out.println();
                    System.out.println("Teams buy robot modules and build their robots. Then they register robots to the games to win. A team");
                    System.out.println("can have max. 9 robots and 20 modules.");
                    System.out.println();
                    System.out.println("Teams are evaluated at the end of every three weeks. If a team participates in min. 2 different game");
                    System.out.println("types in a 3week period, it gets additional 150 credits.");
                    System.out.println();
                    System.out.println("Objective of the game is to reach 10000 credits and 6 robots before other teams do.");
                    System.out.println();
                    System.out.print("***Input any number to go back to main menu***");
                    input=scanner.nextInt();
                    input=0;
                    break;
                }
                case 3:{
                    System.out.println();
                    System.out.println("How To Play");
                    System.out.println("You can buy module using \"by\" command");
                    System.out.println("Example: To buy hd4 module --> \"by hd4 \"");
                    System.out.println();
                    System.out.println("You can sell module or robot using \"sl\" command with module number or robot number");
                    System.out.println();
                    System.out.println("Creating new robot is done by using \"++\" command");
                    System.out.println("Example: ++ r1 = hd1 tr3 lg2 ar4");
                    System.out.println("Example: ++ r3 = m01 m04 lg2 ar6");
                    System.out.println();
                    System.out.println("You can divide your robot into modules with -- command");
                    System.out.println("Example: -- r1 ");
                    System.out.println();
                    System.out.println("ch command changes robot module with module in inventory");
                    System.out.println("Example: ch r2 m07");
                    System.out.println();
                    System.out.println("To list other team robots you need to type \"ls\" with team number");
                    System.out.println("Example:ls 2 lists team 2's robots");
                    System.out.println();
                    System.out.println("To register your robots to games you can use \"rg\"command");
                    System.out.println("\"rg r2 > c2\" registers robot 2 as second team robot in chess");
                    System.out.println();
                    System.out.println("To play the games and list result you can use \"pl\" command");

                    System.out.println();
                    System.out.print("***Input any number to go back to main menu***");
                    input=scanner.nextInt();
                    input=0;
                    break;
                }

                case 4:
                {
                    System.out.println();
                    System.out.println("Developed by");
                    System.out.println("Akif Cakar");
                    System.out.println("Fatih Topcu");
                    System.out.println("Onur Kantar");

                    System.out.println();
                    System.out.print("***Input any number to go back to main menu***");
                    input=scanner.nextInt();
                    input=0;
                    break;
                }
                case 5:
                {
                   escape=false; break;
                }
                default:
                {
                	break;
                }
            }
            if(escape==false)break;
        }
		 
		
		
		
	}
	private static final Console s_console; //Declare the Console
    static
    {
        s_console = Enigma.getConsole("RobotGames"); //Sets the console to the Enigma console.
    }

}
