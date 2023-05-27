
public class Item {
	//2005; T-Shirt; 20000;CANCELED
	int ID;
	String name;
	int price;
	String state;
	
	public Item(String[] fields){
		this.ID = Integer.parseInt(fields[0].trim());
		this.name = fields[1].trim();
		this.price = Integer.parseInt(fields[2].trim());
		this.state = fields[3].trim();

	}
}