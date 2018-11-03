package RGames;

import java.util.Random;

public class Game {

	int prize=0,typeprize=5,transferredprize=0,teamsingame=0,winner=-1;
	
	double Teamscore[]=new double[6];
	static int bonus[]=new int[6];
	
	Robot robot[]=new Robot[54];
	
	Random random;
	double randomnumber=0;
	
	public Game(){
		for (int i = 1; i < Teamscore.length; i++) {Teamscore[i]=1;}//random ile çarpýlcaklarý içi ai lerin scorelarý 1 den baslatýldý.
	}
	
	public void listRobotsingame(){ // hangi oyun oynatýldýysa onun içindeki robotlar ekrana yazdýrýldý ve score hesaplamarý.
		int k=1;
		for (int i = 0; i < robot.length; i++) {
			if(i%9==0 && i!=0){k++;}
		    if(robot[i]!=null){
				System.out.print("t"+k+"-r"+(i+1)%9+":  "+robot[i].getPiece(0).getName()+"-"+robot[i].getPiece(0).getDurability()+"   "+robot[i].getPiece(1).getName()+"-"+robot[i].getPiece(1).getDurability()+"   "+robot[i].getPiece(2).getName()+"-"+robot[i].getPiece(2).getDurability()+"   "+robot[i].getPiece(3).getName()+"-"+robot[i].getPiece(3).getDurability());
			    System.out.println(" (Ch:"+robot[i].getCh()+"  Rn:"+robot[i].getRn()+"  Sm:"+robot[i].getSm()+"  Pp:"+robot[i].getPp()+")");
			}
		}
		System.out.println("");
		for (int i = 0; i < Teamscore.length; i++) {if(Teamscore[i]>10) {System.out.println("t"+(i+1)+" score ="+Double.toString(Teamscore[i]).substring(0,7));}}
		
		double score=0;
		for (int i = 0; i < Teamscore.length; i++) {
			if(Teamscore[i]>score && Teamscore[i]>10){//kimse robotunu sokmazsa kazanan olmasý engellendi.
				score=Teamscore[i];
				winner=i;
			}
		}
		if(winner!=-1){System.out.println("Winner:  Team"+(winner+1)); transferredprize=0;}
		else {System.out.println("Winner: No winner. Prize transferred to the next week!"); transferredprize=prize;}
	}
	
	public void CalculatescoreChess(){
        
		int j=0;
		for (int i = 0; i < 54; i++) {
			if(i%9==0 && i!=0){j++;}
		if(robot[i]!=null)
		{if(i%9==0)Teamscore[j]+=robot[i].getCh();else Teamscore[j]+=robot[i].getCh()/4*(i%9);}
		if(i%9==8){
			randomnumber=Math.random()*0.100+0.950;
			Teamscore[j]*=randomnumber;}
		} 
			typeprize=0;	//sýrasýyla toplama yapýlarak her oyunucunun son elemanýndan sonra çarpma iþlemi yapýlarak score hesaplandý.
           }
	
    public void CalculatescoreRun(){
		int j=0;
		for (int i = 0; i < 54; i++) {if(i%9==0 && i!=0){j++;}if(robot[i]!=null){if(i%9==0)Teamscore[j]+=robot[i].getRn();else Teamscore[j]+=robot[i].getRn()/4*(i%9);} if(i%9==8){	randomnumber=Math.random()*0.100+0.950;Teamscore[j]*=randomnumber;}} 
			typeprize=5;
           }
    
    public void CalculatescoreSumo(){
		int j=0;
		for (int i = 0; i < 54; i++) {if(i%9==0 && i!=0){j++;}if(robot[i]!=null){if(i%9==0)Teamscore[j]+=robot[i].getSm();else Teamscore[j]+=robot[i].getSm()/4*(i%9);} if(i%9==8){	randomnumber=Math.random()*0.100+0.950;Teamscore[j]*=randomnumber;}} 
		typeprize=10;
           }
    
    public void CalculatescorePingpong(){
		int j=0;
		for (int i = 0; i < 54; i++) {if(i%9==0 && i!=0){j++;}if(robot[i]!=null){if(i%9==0)Teamscore[j]+=robot[i].getPp();else Teamscore[j]+=robot[i].getPp()/4*(i%9);} if(i%9==8){	randomnumber=Math.random()*0.100+0.950;Teamscore[j]*=randomnumber;}} 
		typeprize=15;
          }
	
	public void addRobotchess(int who ,Robot robot,int where){
		this.robot[where]=robot;//(1.robota-1+9*(1-1))=robot[0] mantýgýyla gönderilen parca yerlestirildi.
		robot.getPiece(0).setDurability(); robot.getPiece(1).setDurability();robot.getPiece(2).setDurability();robot.getPiece(3).setDurability();//oyuna girdikleri için durabilityler -2 düþürüldü.
	}
	
    public void addRobotrun(int who ,Robot robot,int where){
    	this.robot[where]=robot; 
    	robot.getPiece(0).setDurability(); robot.getPiece(1).setDurability();robot.getPiece(2).setDurability();robot.getPiece(3).setDurability();
	}

    public void addRobotsumo(int who ,Robot robot,int where){
    	this.robot[where]=robot;;
    	robot.getPiece(0).setDurability(); robot.getPiece(1).setDurability();robot.getPiece(2).setDurability();robot.getPiece(3).setDurability();
    }

    public void addRobotpingpong(int who ,Robot robot,int where){
    	this.robot[where]=robot;
    	robot.getPiece(0).setDurability(); robot.getPiece(1).setDurability();robot.getPiece(2).setDurability();robot.getPiece(3).setDurability();
    }
    public int getTeamsingame() {
		boolean flag=true;
		for (int i = 0; i < robot.length; i++) {if(i%9==0 && i!=0){flag=true;}if(robot[i]!=null){if(flag==true){teamsingame++; flag=false;}}}
	    return teamsingame;
	}
	public void setTeamsingame(int teamsingame) {
		this.teamsingame = teamsingame;
	}
	public int getPrize() {
		prize=200+teamsingame*(25+typeprize)+transferredprize;
		return prize;
	}
	public void setPrize(int prize) {
		this.prize = prize;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public void addExtrabonus(){// +150
		for (int j = 0; j < bonus.length; j++) {
			for (int i=j*9; i < j*9+9; i++) {
				if(robot[j]!=null){bonus[j]+=1;break;}}
			}
	}
	public int emptyrobotAI(int who){//yapay zeka için robot sokacak sýra belirlendi.
	   int a=0;//eger tüm yerler doluysa ilkiyle degissin.
		for (int i = 0; i < 9; i++) {
		if(robot[i+9*who]==null) {a=i+9*who; break;}
	}
		return a;
	}
	public void reset(){// aktarýlan para hariç digerlerini yeni olusmus sekline döndürme iþlemi
		prize=0;
		typeprize=5;
		teamsingame=0;
		Teamscore=new double[6];
		robot=new Robot[54];
		winner=-1;
	}
    
	
    
    
}
