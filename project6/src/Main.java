
public class Main {
	
	public static void main(String[] args) {
		// project5 test code
		OrderManagementFrame mainFrame = new OrderManagementFrame();
		mainFrame.addTestOrders();
		
		//project3 test code
		OrderList OL = new OrderList("Order-normal.txt");	
		
		System.out.println("Order Length : " + OL.numOrders());
		System.out.println();
		
		for(int i=0;i<OL.length;i++) {
			OL.getOrder(i).print();
		}
	}
}
