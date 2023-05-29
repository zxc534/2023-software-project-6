
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
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
	
	public void print() {
		for(int i=0;i<arr.size();i++) {
			getOrder(i).print();
		}
	}
	
	public int numOrders() {
		return this.arr.size();
	}
	
	public Order getOrder(int i) { 	//return ith Order
		return this.arr.get(i);
	}
	
	public void sortByCustomer() {
		arr.sort(new OrderNameComparator());
		System.out.println("sorted by customer name");
		//print();
	}
	public void sortByDate() {
		arr.sort(new OrderDateComparator());
		System.out.println("sorted by date");
		//print();
	}

	class OrderNameComparator implements Comparator<Order>{
		@Override
		public int compare(Order o1, Order o2) {
	        return o1.name.compareTo(o2.name);
	    }
	}
	
	class OrderDateComparator implements Comparator<Order>{
		@Override
		public int compare(Order o1, Order o2) {
	        return o1.time.compareTo(o2.time);
	    }
	}
	
}