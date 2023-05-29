import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InputFrame extends JFrame{
	//for id generate
	private static LocalDateTime createTime = LocalDateTime.of(1,1,1,1,1);;
	private static int createCount;
	
	private Order tempO = new Order();
	
	private JPanel mainPanel = new JPanel();
	private JPanel upPanel = new JPanel();
	private JPanel downPanel = new JPanel();
	private JPanel btnPanel = new JPanel();
	
	private JPanel up1Panel = new JPanel();
	private JPanel up2Panel = new JPanel();
	private JLabel buyerNameLabel = new JLabel("Buyer Name");
	private JLabel shippingAddressLabel = new JLabel("Shipping Adrress");
	private JTextField buyerNameTF = new JTextField();
	private JTextField shippingAddressTF = new JTextField();
	
	private JPanel downMenuPanel = new JPanel();
	private JPanel downContentPanel = new JPanel();
	private JScrollPane downScrollPanel = new JScrollPane(downContentPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JLabel itemIDLabel = new MyLabel("Item ID",100, 30);
	private JLabel itemNameLabel = new MyLabel("Item Name",255, 30);
	private JLabel itemPriceLabel = new MyLabel("Item Price",255, 30);
	
	/*
	private static int[] itemsID = new int[] {1001,2005,3001,1101};
	private static String[] items = new String[]{"Apple","T-Shirt","Pencil","Fish"};
	private static int[] itemsPrice = new int[] {10000,20000,5000,3333};
	*/
	
	private static Item[] items = new Item[] {new Item(new String[] {"1001","Apple","10000","PREPARING"}),new Item(new String[] {"2005","T-Shirt","20000","PREPARING"}),new Item(new String[] {"3001","Pencil","5000","PREPARING"}),new Item(new String[] {"1101","Fish","3333","PREPARING"})};
	
	private JComboBox<Item> itemsCB = new JComboBox<Item>(items);
	private JButton newItemBtn = new JButton("+");
	private JButton inputBtn = new JButton("ADD NEW ORDER");
	
	public InputFrame() {
		setTitle("Input Order Management");
		setSize(900, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) { 
            		Main.mainFrame.setIsAddOpen(false);
                    dispose();
            }
		});
		
		//upPanel.setBackground(Color.BLUE);
		//downPanel.setBackground(Color.RED);
		//btnPanel.setBackground(Color.YELLOW);
		
		mainPanel.setLayout(new BorderLayout());
		upPanel.setLayout(new GridLayout(2,1));
		downMenuPanel.setLayout(new FlowLayout());
		downContentPanel.setLayout(new GridLayout(0,1));
		
		upPanel.setPreferredSize(new Dimension(880, 105));
		downPanel.setPreferredSize(new Dimension(640, 250));
		btnPanel.setPreferredSize(new Dimension(240, 250));
		
		downScrollPanel.setPreferredSize(new Dimension(625, 185));
		
		buyerNameLabel.setPreferredSize(new Dimension(110, 45));
		buyerNameTF.setPreferredSize(new Dimension(750, 45));
		shippingAddressLabel.setPreferredSize(new Dimension(110, 45));
		shippingAddressTF.setPreferredSize(new Dimension(750, 45));
		
		add(mainPanel);
		
		mainPanel.add(upPanel,BorderLayout.NORTH);
		mainPanel.add(downPanel,BorderLayout.CENTER);
		mainPanel.add(btnPanel,BorderLayout.EAST);
		
		upPanel.add(up1Panel);
		upPanel.add(up2Panel);
		
		up1Panel.add(buyerNameLabel);
		up1Panel.add(buyerNameTF);
		up2Panel.add(shippingAddressLabel);
		up2Panel.add(shippingAddressTF);
		
		downPanel.add(downMenuPanel);
		downPanel.add(downScrollPanel);
		downMenuPanel.add(itemIDLabel);
		downMenuPanel.add(itemNameLabel);
		downMenuPanel.add(itemPriceLabel);
		
		btnPanel.add(itemsCB);
		btnPanel.add(newItemBtn);
		btnPanel.add(inputBtn);

		newItemBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add new Item");
				Item tempItem = (Item)itemsCB.getSelectedItem();
				tempO.addItem(tempItem);
				tempO.price += Integer.parseInt(tempItem.price);
				//tempO.print();
				addItemPanel(tempO.itemArr[tempO.numOfItem-1]);
			}
		});
		
		inputBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDateTime t = LocalDateTime.now();
				tempO.name = buyerNameTF.getText();
				tempO.address = shippingAddressTF.getText();
				tempO.time = LocalDateTime.of(t.getYear(),t.getMonthValue(),t.getDayOfMonth(),t.getHour(),t.getMinute());
				if(createTime.compareTo(tempO.time) == 0) {
					//System.out.println("create in same time");
					createCount ++;
				} else {
					//System.out.println("create in diff time");
					createTime = LocalDateTime.of(t.getYear(),t.getMonthValue(),t.getDayOfMonth(),t.getHour(),t.getMinute());
					createCount = 1;
				}
				tempO.ID = ""+t.getYear()+String.format("%02d",t.getMonthValue())+String.format("%02d",t.getDayOfMonth())+String.format("%02d",t.getHour())+String.format("%02d",t.getMinute())+String.format("%03d",createCount);
				//tempO.print();
				Main.mainFrame.addOrderToOL(tempO);
				Main.mainFrame.addOrderPanel(tempO);
				Main.mainFrame.setIsAddOpen(false);
				dispose();
			}
		});
	}
	
	private void addItemPanel(Item i) {
		JPanel tempPanel = new JPanel();
		tempPanel.setPreferredSize(new Dimension(600, 40));
		tempPanel.add(new MyLabel(i.ID,100,35));
		tempPanel.add(new MyLabel(i.name,255,35));
		tempPanel.add(new MyLabel(i.price,230,35));
		downContentPanel.add(tempPanel);
		downContentPanel.revalidate();
	}
}
