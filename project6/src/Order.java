import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
	//주문 ID, 주문자, 주문 시간, 주문 가격, 주문 상품 및 주문 상태로 이루어진 리스트, 배달 주소
	
	//   ==fields 입력==
	//[0]202302101310001
	//[1]Kim
	//[2]2023-02-10_13:10
	//[3]30000
	//[4]2005; T-Shirt; 20000;CANCELED : 1001;Apple;10000;DELIVERED
	//[5]Seoul
	
	final int MAX_PRODUCT = 50;
	
	String ID;
	String name;
	LocalDateTime time;
	int price;
	Item [] itemArr = new Item [MAX_PRODUCT];
	String adress;
	
	public Order(String[] fields) throws InvalidValueException{
		this.ID = fields[0].trim();
		this.name = fields[1].trim();
		this.adress = fields[5].trim();
		
		// 시간 가격 물건에 대해서 비정상 입력 확인
		try {this.time = LocalDateTime.parse(fields[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
		} catch (Exception e){throw new InvalidValueException("time", fields[2].trim());}
		try {this.price = Integer.parseInt(fields[3].trim());
		} catch (Exception e) {throw new InvalidValueException("price", fields[3].trim());}
		try {this.makeProductArr(fields[4].trim());
		} catch (Exception e) {throw new InvalidValueException("product", fields[4].trim());}
		
	}
	
	private void makeProductArr(String str) {
		String[] t = str.split(":",0);
        for(int i=0; i < t.length; i++){
            String[] e = t[i].split(";",4);
            Item p = new Item(e);
            itemArr[i] = p;
        }
	}
	
	public void print() {
		System.out.println("ID   : " + this.ID);
		System.out.println("name : " + this.name);
		System.out.println("time : " + this.time);
		System.out.println("total: " + this.price);
	  //System.out.print("----ID00--PRICE000--NAME000000--STATE00000\n");
		System.out.print("    ID    PRICE     NAME        STATE     \n");
		for(int i=0;itemArr[i] != null;i++) {
			Item p = itemArr[i];
			System.out.printf("%-2d  %4d  %-8d  %-10s  %s\n",i,p.ID,p.price,p.name,p.state);
		}
		System.out.println();
	}
}
