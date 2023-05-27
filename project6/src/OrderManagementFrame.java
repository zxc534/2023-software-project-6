import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class OrderManagementFrame extends JFrame{
	//project 3 part
	private OrderList OL = new OrderList("Order-normal.txt");
	
	//project 5 part
	private boolean isAddOpen = false;
	
	private JPanel mainPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	
	private JPanel menuPanel = new JPanel();
	private JLabel menuOrderID = new MyLabel("menuOrderID",134,90);
	private JLabel menuBuyer = new MyLabel("Buyer",130,90);
	private JLabel menuDate = new MyLabel("Date",130,90);
	private JLabel menuAmount = new MyLabel("{Total} Amount",130,90);
	private JLabel menuItems = new MyLabel("Items",360,50);
	private JLabel menuAddress = new MyLabel("Ship Address",130,90);
	
	private JPanel menuItemPanel = new JPanel();
	private JPanel menuItemMenuPanel = new JPanel();
	private JLabel menuItemID = new MyLabel("ID",80,40);
	private JLabel menuItemName = new MyLabel("Name",80,40);
	private JLabel menuItemPrice = new MyLabel("Price",80,40);
	private JLabel menuItemStatus = new MyLabel("Status",80,40);
	
	private JPanel ordersPanel = new JPanel();
	private JScrollPane orderScrollPanel = new JScrollPane(ordersPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private JButton addBtn = new MyButton("ADD");
	private JButton sortBtn = new MyButton("SORT BY\nDATE/CUSTOMER");
	private JButton changeBtn = new MyButton("CHANGE/DONE");
	private JButton saveBtn = new MyButton("SAVE");
	
	private InputFrame inputFrame;
	
	public OrderManagementFrame() {
		setTitle("Order Management");
		setSize(1280, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setVisible(true);
		
		//centerPanel.setBackground(Color.RED);
		eastPanel.setBackground(Color.DARK_GRAY);
		//menuPanel.setBackground(Color.LIGHT_GRAY);
		//orderScrollPanel.setBackground(Color.LIGHT_GRAY);
		
		mainPanel.setLayout(new BorderLayout());
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		eastPanel.setLayout(new FlowLayout());
		menuItemPanel.setLayout(new GridLayout(0,1));
		ordersPanel.setLayout(new GridLayout(0,1));
		//행값에 0을 주어 행 개수 제한없이 사용
		
		eastPanel.setPreferredSize(new Dimension(190,0));
		
		add(mainPanel);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		
		centerPanel.add(menuPanel);
		centerPanel.add(orderScrollPanel);
		
		menuPanel.add(menuOrderID);
		menuPanel.add(menuBuyer);
		menuPanel.add(menuDate);
		menuPanel.add(menuAmount);
		
		menuPanel.add(menuItemPanel);
		menuItemPanel.add(menuItems);
		menuItemPanel.add(menuItemMenuPanel);
		menuItemMenuPanel.add(menuItemID);
		menuItemMenuPanel.add(menuItemName);
		menuItemMenuPanel.add(menuItemPrice);
		menuItemMenuPanel.add(menuItemStatus);
		menuPanel.add(menuAddress);
		
		orderScrollPanel.setPreferredSize(new Dimension(1050, 430));
		menuItemPanel.setPreferredSize(new Dimension(360, 90));
		
		eastPanel.add(addBtn);
		eastPanel.add(sortBtn);
		eastPanel.add(changeBtn);
		eastPanel.add(saveBtn);
		
		addBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAddOpen) {} 
				else {
					isAddOpen = true;
					inputFrame = new InputFrame();
				}
			}
		});
		

	}
	
	public void addTestOrders() {
		/*
		//202301030910010:: Park :: 2023-01-03_09:10 :: 10000 :: 1001;Apple;10000;SHIPPED  :: CAU
		//202302101310001  :: Kim :: 2023-02-10_13:10 :: 30000 :: 2005;T-Shirt;20000;CANCELED : 1001;Apple;10000;DELIVERED :: Dept. Software
		//202301101730010:: Lee :: 2023-01-10_17:30 :: 5000 :: 3001;Pencil;5000; RETURNED :: Seoul
		String[][] items1 = new String[][]{{"1001","Apple","10000","SHIPPED"}};
		String[][] items2 = new String[][]{{"2005","T-Shirt","20000","CANCELED"},{"1001","Apple","10000","DELIVERED"},{"3001","Pencil","5000","RETURNED"},{"1001","Apple","10000","SHIPPED"}};
		String[][] items3 = new String[][]{{"3001","Pencil","5000","RETURNED"}};
		ordersPanel.add(new OrderPanel("202301030910010","Park","2023-01-03_09:10","10000","CAU", items1));
		ordersPanel.add(new OrderPanel("202302101310001","Kim","2023-02-10_13:10","30000","Dept. Software",items2));
		ordersPanel.add(new OrderPanel("202301101730010","Lee","2023-01-10_17:30","5000","Seoul",items3));
		ordersPanel.add(new OrderPanel("202302101310001","Kim","2023-02-10_13:10","30000","Dept. Software",items1));
		ordersPanel.add(new OrderPanel("202301101730010","Lee","2023-01-10_17:30","5000","Seoul",items1));
		ordersPanel.revalidate();
		*/
	}
	
	public void addOrderPanel(String id, String name, String date, String price, String address, Item[] items) {
		OrderPanel tempOP = new OrderPanel(id, name, date, price, address, items);
		ordersPanel.add(tempOP);
		ordersPanel.revalidate();
	}
	
	public void readOrders() {
		Order tempO;
		for(int i =0;i<OL.numOrders();i++) {
			tempO = OL.getOrder(i);
			//tempO.print();
			addOrderPanel(tempO.ID,tempO.name,tempO.time.toString(),"" + tempO.price,tempO.adress,tempO.itemArr);
			//System.out.println("add "+i+" order panel");
		}
	}
}
