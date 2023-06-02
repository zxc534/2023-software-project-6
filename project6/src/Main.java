
public class Main {
	public static final int MAX_ITEM = 50;
	public static OrderManagementFrame mainFrame;
	
	public static OrderList OL;
	
	public static void main(String[] args) {
		//Declare and manage OrderList in frame
		// -> Declare and manage OL in Main
		// -> separate OL and frame
	
		// project5 test code
		OL = new OrderList("test4.txt");
		//OL = new OrderList("Order-normal.txt");
		mainFrame = new OrderManagementFrame();
		mainFrame.readOrders();
	}
}
