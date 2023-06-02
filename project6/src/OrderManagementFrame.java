import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class OrderManagementFrame extends JFrame{
	
	OrderPanel currentSelectedPanel;
	OrderList OL = Main.OL;
	
	//Frame Manage Value
	private boolean isAddOpen = false;
	private boolean isSortDate = false;
	private boolean isChangeMode = false;
	
	//Frame Components
	private JPanel mainPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	
	private JPanel menuPanel = new JPanel();
	private JLabel menuOrderID = new MyLabel("menuOrderID",134,90,true);
	private JLabel menuBuyer = new MyLabel("Buyer",130,90,true);
	private JLabel menuDate = new MyLabel("Date",130,90,true);
	private JLabel menuAmount = new MyLabel("{Total} Amount",130,90,true);
	private JLabel menuItems = new MyLabel("Items",360,50,true);
	private JLabel menuAddress = new MyLabel("Ship Address",130,90,true);
	
	private JPanel menuItemPanel = new JPanel();
	private JPanel menuItemMenuPanel = new JPanel();
	private JLabel menuItemID = new MyLabel("ID",80,40,true);
	private JLabel menuItemName = new MyLabel("Name",80,40,true);
	private JLabel menuItemPrice = new MyLabel("Price",80,40,true);
	private JLabel menuItemStatus = new MyLabel("Status",80,40,true);
	
	private JPanel ordersOuterPanel = new JPanel();
	private JPanel ordersPanel = new JPanel();
	private JScrollPane orderScrollPanel = new JScrollPane(ordersOuterPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private JButton addBtn = new MyButton("ADD");
	private JButton sortBtn = new MyButton("SORT BY\nDATE/CUSTOMER");
	private JButton changeBtn = new MyButton("CHANGE/DONE");
	private JButton saveBtn = new MyButton("SAVE");

	public OrderManagementFrame() {
		//1. General Setting
		setTitle("Order Management");
		setSize(1280, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setVisible(true);
		
		//2. Background Color
		//centerPanel.setBackground(Color.RED);
		eastPanel.setBackground(Color.DARK_GRAY);
		//orderScrollPanel.setBackground(Color.LIGHT_GRAY);
		
		//3.Panel Layout
		mainPanel.setLayout(new BorderLayout());
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		eastPanel.setLayout(new FlowLayout());
		menuItemPanel.setLayout(new GridLayout(0,1));
		ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
		
		//4.Component size
		eastPanel.setPreferredSize(new Dimension(190,0));
		orderScrollPanel.setPreferredSize(new Dimension(1050, 430));
		menuItemPanel.setPreferredSize(new Dimension(360, 90));
		
		//5.Component add
		ordersOuterPanel.add(ordersPanel);
		
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
		
		eastPanel.add(addBtn);
		eastPanel.add(sortBtn);
		eastPanel.add(changeBtn);
		eastPanel.add(saveBtn);
		
		//6.EventListener add
		addBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAddOpen) {} 
				else {
					isAddOpen = true;
					new InputFrame();
				}
			}
		});
		
		sortBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentSelectedPanel = null;
				if(isSortDate) {
					isSortDate = false;
					OL.sortByCustomer();
					ordersPanel.removeAll();
					readOrders();	
				} 
				else {
					isSortDate = true;
					OL.sortByDate();
					ordersPanel.removeAll();
					readOrders();
				}
			}
		});
		
		changeBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentSelectedPanel != null) {
					if(isChangeMode) {	//in change mode
						isChangeMode = false;
						currentSelectedPanel.setBorderBlack();
						currentSelectedPanel.endEdit();
						currentSelectedPanel = null;
						//OL.print();
					} else {			//not in change mode
						isChangeMode = true;
						currentSelectedPanel.setBorderYellow();
						currentSelectedPanel.startEdit();
					}
				} else {
					System.out.println("selected nothing");
				}
			}
		});
		
		saveBtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("SAVE Btn Clicked!");
				JFileChooser fc = new JFileChooser(new File(".\\"));
				FileFilter filter = new FileNameExtensionFilter("Text file", new String[] {"txt"});
				fc.setFileFilter(filter);
				fc.setDialogTitle("Save text file");
				int result = fc.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						String fileName = fc.getSelectedFile().toString();
						FileWriter fw;
						if (fileName.endsWith(".txt")) {
							fw = new FileWriter(fc.getSelectedFile());
						} else {
							fw = new FileWriter(fc.getSelectedFile() + ".txt");
						}
						fw.write(OL.makeStringForSave());
						fw.close();
					} catch (Exception err) {
						System.out.println(err.getMessage());
					}
				}
			}
		});
	}
	
	public void addOrderPanel(Order o) {
		OrderPanel tempOP = new OrderPanel(o);
		ordersPanel.add(tempOP);
		ordersPanel.revalidate();
		ordersPanel.repaint();
	}
	
	public void readOrders() {
		Order tempO;
		for(int i =0;i<OL.numOrders();i++) {
			tempO = OL.getOrder(i);
			//tempO.print();
			addOrderPanel(tempO);
			//System.out.println("add "+i+" order panel");
		}
	}
	
	public void setIsAddOpen(boolean b) {
		this.isAddOpen = b; 
	}
}
