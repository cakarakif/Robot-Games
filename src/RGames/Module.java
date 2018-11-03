package RGames;

public class Module {

	String name;
	int durability,price=0;
	
	public Module(String name){
		this.name=name;
		durability=100;
		
		int n1=Integer.parseInt(name.substring(2,3));
		if(name.substring(0,2).equals("tr")){price+=n1*150;}
		else if(name.substring(0,2).equals("hd")){price+=n1*100;}
		else if(name.substring(0,2).equals("lg")){price+=n1*50;}
		else if(name.substring(0,2).equals("ar")){price+=n1*40;}
	}
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability() {
		durability -=2 ;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
