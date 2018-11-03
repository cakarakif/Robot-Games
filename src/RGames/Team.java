package RGames;

import java.util.Random;

public class Team {
	Robot robot[];
	Module module[];
	int credit=1500;
	
	public Team(){
	 robot=new Robot[9];
	 module=new Module[20];
	}
	public void Listmodule(){ //Mod�lleri ekrana yazd�rma i�lemi(teker teker i�leme al�nd�lar.)
		
		for (int i = 0; i < module.length; i++) 
		{
			if(i>0 && i%5==0){ System.out.println("");}//her 5 say�da bir alta ge�ildi.
			
			if(module[i]!=null){
     		if(i<9){System.out.print("m0"+(i+1)+"."+module[i].getName()+"-"+module[i].getDurability()+"    ");} //01 02 gibiler i�in '0' eklendi.
			else{System.out.print("m"+(i+1)+"."+module[i].getName()+"-"+module[i].getDurability()+"    ");}
			}
			else{
	     		if(i<9){System.out.print("m0"+(i+1)+".   -       ");} //01 02 gibiler i�in '0' eklendi.
				else{System.out.print("m"+(i+1)+".   -       ");}
				}
		}
	}
	public void Listrobot(int m){ //Robotlar� ekrana yazd�rma i�lemi(teker teker i�leme al�nd�lar.)
		boolean flag=true;
		for (int i = 0; i < robot.length; i++) 
		{
			if(robot[i]!=null){
				robot[i].calculateskills();
				if(flag==true){System.out.println("--- Team"+m+": Robots ---"); flag=false;}
			    System.out.print("r"+(i+1)+":  "+robot[i].getPiece(0).getName()+"-"+robot[i].getPiece(0).getDurability()+"   "+robot[i].getPiece(1).getName()+"-"+robot[i].getPiece(1).getDurability()+"   "+robot[i].getPiece(2).getName()+"-"+robot[i].getPiece(2).getDurability()+"   "+robot[i].getPiece(3).getName()+"-"+robot[i].getPiece(3).getDurability()); 
			    System.out.print(" (Ch:"+robot[i].getCh()+"  Rn:"+robot[i].getRn()+"  Sm:"+robot[i].getSm()+"  Pp:"+robot[i].getPp()+")");
			    System.out.println("");
			}
		}
	}

	public void decrease(){ // her hafta durabilitylerden -2 d�s�ld�.
		for (int i = 0; i < module.length; i++) {
			if(module[i]!=null){
				module[i].setDurability();
			}
		}
		for (int i = 0; i < robot.length; i++) 
		{
			if(robot[i]!=null){
				robot[i].getPiece(0).setDurability(); robot[i].getPiece(1).setDurability();robot[i].getPiece(2).setDurability();robot[i].getPiece(3).setDurability();
			}
		}
		
	}
	
	public void setPiecechange(int whichone,int towhichone) {//ch komutu i�lendi.
	    String ch=module[towhichone-1].getName();
		Module ch1=module[towhichone-1];// degistirilecek mod�l cekildi.
		
		if(ch.substring(0,2).equals("tr")){towhichone=0; }//alakal� parca bulundu.(tekrar degisken olusturulmay�p var olan kullan�ld�.) 
		else if(ch.substring(0,2).equals("hd")){towhichone=1;} 
		else if(ch.substring(0,2).equals("lg")){towhichone=2;} 
		else if(ch.substring(0,2).equals("ar")){towhichone=3;}
		
		module[towhichone-1]=robot[whichone-1].piece[towhichone];// parca degisimi oldu.
		robot[whichone-1].piece[towhichone]=ch1;
	}
	
	
	public void Segmenting(int whichone){ // robot par�alara ayr�ld�.
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < module.length; i++) {if(module[i]==null){module[i]=robot[whichone-1].getPiece(j);break;}}
		}
		robot[whichone-1]=null;
	}
	
	public void addModule(Module module) {//yeni mod�l olu�turuldu.
		if(credit-module.getPrice()<0)//credit miktar� yeterlimi sat�n almak i�in kontrol edildi.
		{ System.out.println("!!!You don't have enough money to buy this module!!!"); }
		else{
		for (int i = 0; i < this.module.length; i++) {
			if(this.module[i]==null){
				this.module[i] = module; break;
			}
		}
		credit-=module.getPrice(); // parca al�nd�g�nda credit eksiltildi.
		}
	}
	public void setModule(int whichone) {//mod�l�n i�i bosalt�ld� robot olusturuldugu icin.
		module[whichone-1]=null;
	}
	
	public Module getModule(int whichone) {
		return module[whichone-1];
	}
	
	public void sellModule(String whichmodule) {//satma i�lemi yap�ld�.
		int whichone=Integer.parseInt(whichmodule.substring(1,3));
		credit+=module[whichone-1].getPrice()/2*module[whichone-1].getDurability()/100;
		module[whichone-1] =null;
	}
	
	public void sellRobot(int noname) {// mod�ldeki bir parcadan robot olusturuldurgunda o parcan�n fiyat� burda art�r�l�k �stte azalt�larak degisikli�e ugrat�lmam�s oldu.
		
		for (int i = 0; i < 4; i++) {credit+=robot[noname-1].piece[i].getPrice()/2*robot[noname-1].piece[i].getDurability()/100;}
		robot[noname-1]=null;
	}
	
	public void addRobotdirect(int noname, Robot robot) {//robot olusturma islemi yap�ld�..
	
		int notenoughmoney=credit;
		for (int i = 0; i < 4; i++) {
			credit-=robot.getPiece(i).getPrice(); // maliyetler hesapland�.
		}
		if(credit<0){credit=notenoughmoney; System.out.println("!!!You don't have enough money to buy this robot!!!");}// para - ye d��erse sat�n almas� engellendi.
		else{this.robot[noname-1]=robot;}
	}
	public void addRobotIndirect(Module module) {// mod�ldeki bir parcadan robot olusturuldurgunda o parcan�n fiyat� burda art�r�l�k �stte azalt�larak degisikli�e ugrat�lmam�s oldu.
		credit+=module.getPrice();
	}

	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit += credit;
	}
	
	public int getRobotlength() {
		int numberofrobot=0;
		for (int i = 0; i < robot.length; i++) {
			if(robot[i]!=null){numberofrobot++;}
		}
		return numberofrobot;
	}
	public void Artificialintelligence(int who,Game chess,Game run,Game sumo,Game pingpong){
		Module robotai[]=new Module[4];
		boolean flag1=true;
		int randomnumber=0,enoughmoney=0,count=0,random=0,where=0;
		Random rand= new Random();
		do{
			flag1=true;
		for (int i = 0; i < robotai.length; i++) {//random olarak par�alar al�nd�.
			randomnumber=rand.nextInt(6)+1;
			if(i==0){robotai[i]=new Module("hd"+randomnumber); enoughmoney+=robotai[i].getPrice();}
			else if(i==1){robotai[i]=new Module("tr"+randomnumber);enoughmoney+=robotai[i].getPrice();}
			else if(i==2){robotai[i]=new Module("lg"+randomnumber);enoughmoney+=robotai[i].getPrice();}
			else if(i==3){robotai[i]=new Module("ar"+randomnumber);enoughmoney+=robotai[i].getPrice();}
		}
		if(credit < enoughmoney){flag1=false; count++;}
		if(count>15){flag1=false; break;}//eger 15 denemeden sonra herhangi bir robot olusmuyorsa paras� yetmiyordur.
	
		}while(flag1==false);
		
		if(flag1==true){
		for (int i = 0; i < robot.length; i++) {//par�alardan olu�an robot eklendi.
			if(robot[i]==null){
				robot[i]=new Robot(robotai[0],robotai[1],robotai[2],robotai[3]); 
				credit-=enoughmoney;robot[i].calculateskills();
				break; }
		}
		}
		///////////////////robotlar olusturuldu.
		///////////////////robotlar oyunlara sokma k�sm�.
		boolean permitflag=true;
		for (int i = 0; i < robot.length; i++) {
			if(robot[i]!= null){
				permitflag=true;
				random=rand.nextInt(4);
				for (int j = 0; j < robotai.length; j++) {// durability kontrol� yap�ld�.(60 �n alt�ndaysa satar.)
					if(robot[i].getPiece(i).getDurability()<60){
						permitflag=false;
					    for (int k = 0; j < 4; j++) {credit+=robot[i].piece[k].getPrice()/2*robot[i].piece[k].getDurability()/100;}
					    robot[i]=null;
					}
				}
				if(permitflag==true){
				if(random==0){where=chess.emptyrobotAI(who-1);chess.addRobotchess(who, robot[i], where);}
				else if(random==1){where=run.emptyrobotAI(who-1);run.addRobotrun(who, robot[i], where);}
				else if(random==2){where=sumo.emptyrobotAI(who-1);sumo.addRobotsumo(who, robot[i], where);}
				else{where=pingpong.emptyrobotAI(who-1);pingpong.addRobotpingpong(who, robot[i], where);}
				}
			}
		}
	 	
	 	
	 	
	}
	public Robot getRobot(int i) {
		return robot[i-1];
	}
	public void setRobot(Robot[] robot) {
		this.robot = robot;
	}
	
	

}
