
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderList {
	ArrayList<Order> arr = new ArrayList<Order>();
	
	public OrderList() {
	}
	
	public OrderList (String OrderFileName) {
		this.loadOrders(OrderFileName);
	}
	
	void loadOrders(String OrderFileName) {
		File file = new File(OrderFileName);
		Scanner input = null;
		try {
			input = new Scanner(file);
			while (input.hasNext()) {
				String line = input.nextLine();
				if (line.matches("//.*")) {
					//정규 표현식 사용
					//System.out.println("// detect");
				} else {
					//입력 받은 order에 대해서 정상 입력만 처리하고 
					//비정상 입력들은 오류메시지를 출력하고 처리하지 않는다
					//InvalidInputException을 받음
					try {	
						String[] tokens = line.split("::",0);
				        arr.add(new Order(tokens));
					} catch(InvalidValueException e) {
						e.print(line);
					}
			        
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Invalid file input : " + OrderFileName);
		}
		
	}
	
	public int numOrders() {
		return this.arr.size();
	}
	
	public Order getOrder(int i) { 	//return ith Order
		return this.arr.get(i);
	}
	
	public void sortByCustomer() {
		System.out.println("");
	}
	public void sortByDate() {
		System.out.println("");
	}
}