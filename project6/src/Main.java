
public class Main {
	
	public static void main(String[] args) {
		//project3 test code
		//move this code into Frame Class
		//Declare and manage OrderList in frame
		/*
		OrderList OL = new OrderList("Order-normal.txt");	
				
		System.out.println("Order Length : " + OL.numOrders());
		System.out.println();
			
		for(int i=0;i<OL.arr.size();i++) {
			OL.getOrder(i).print();
		}
		*/
		
		// project5 test code
		OrderManagementFrame mainFrame = new OrderManagementFrame();
		//mainFrame.addTestOrders();
		mainFrame.readOrders();
	}
}
