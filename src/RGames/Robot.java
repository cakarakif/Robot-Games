package RGames;

public class Robot {
	
	Module [] piece=new Module[5];
	int speed=0,ch=0,rn=0,sm=0,pp=0;
	
	public Robot(Module piece1,Module piece2,Module piece3,Module piece4){
		this.piece[0]=piece1;
		this.piece[1]=piece2;
		this.piece[2]=piece3;
		this.piece[3]=piece4;
		
		for(int i=0;i<4;i++) {   // robotta sýralama yapýldý.(tr-hd-lg-ar)
			if(piece[i].getName().substring(0,2).equals("tr")){piece[4]=piece[0]; piece[0]=piece[i]; } 
			else if(piece[i].getName().substring(0,2).equals("hd")){piece[4]=piece[1]; piece[1]=piece[i];} 
			else if(piece[i].getName().substring(0,2).equals("lg")){piece[4]=piece[2]; piece[2]=piece[i];} 
			else if(piece[i].getName().substring(0,2).equals("ar")){piece[4]=piece[3]; piece[3]=piece[i];} 
			   piece[i]=piece[4];
					}
	}

	public void calculateskills()//robotlarýn yetenekleri hesaplandý.(her hafta degisecegi icin metot icine alýndý.)
	{   
        speed=(250*(100+(Integer.parseInt(piece[2].getName().substring(2,3))*80)))/((100+(Integer.parseInt(piece[0].getName().substring(2,3))*10))+(20+Integer.parseInt(piece[1].getName().substring(2,3)))+(80+(Integer.parseInt(piece[2].getName().substring(2,3))*4))+(40+(Integer.parseInt(piece[3].getName().substring(2,3))*2)));
		ch=((Integer.parseInt(piece[1].getName().substring(2,3))*160)+100)*piece[1].getDurability()/100;
		rn=speed*piece[2].getDurability()/100;
		sm=((100+(80*Integer.parseInt(piece[0].getName().substring(2,3))))*piece[0].getDurability()/100*7/10)+((100+(Integer.parseInt(piece[2].getName().substring(2,3))*80))*piece[2].getDurability()/100*3/10);
		pp=((100+(200*Integer.parseInt(piece[3].getName().substring(2,3))))*piece[3].getDurability()/100*6/10)+((100+(160*Integer.parseInt(piece[1].getName().substring(2,3))))*piece[1].getDurability()/100*2/10)+(speed*piece[2].getDurability()/100*2/10);
	}
	
	
	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getSm() {
		return sm;
	}

	public void setSm(int sm) {
		this.sm = sm;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	public Module getPiece(int whichone) {
		return piece[whichone];
	}

	public void setPiece(Module piece,int whichone) {
		this.piece[whichone] = piece;
	}



}
