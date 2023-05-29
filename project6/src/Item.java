
public class Item {
	//2005; T-Shirt; 20000;CANCELED
	String ID;
	String name;
	String price;
	String state;
	
	public Item(String[] fields){
		this.ID = fields[0].trim();
		this.name = fields[1].trim();
		this.price = fields[2].trim();
		this.state = fields[3].trim();

	}
	
	@Override
	public String toString(){
		return this.name;
	}
}