import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class InputFrame extends JFrame{
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
	
	private JScrollPane downScrollPanel = new JScrollPane(downPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JPanel downMenuPanel = new JPanel();
	private JPanel downContentPanel = new JPanel();
	private JLabel itemIDLabel = new MyLabel("Item ID",100, 30);
	private JLabel itemNameLabel = new MyLabel("Item Name",255, 30);
	private JLabel itemPriceLabel = new MyLabel("Item Price",255, 30);
	
	private static String[] items = new String[]{"Apple","T-Shirt","Pencil","Fish"};
	private JComboBox<String> itemsCB = new JComboBox<String>(items);
	private JButton newItemBtn = new JButton("+");
	private JButton inputBtn = new JButton("ADD NEW ORDER");
	
	public InputFrame() {
		setTitle("Input Order Management");
		setSize(900, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setVisible(true);
		
		upPanel.setBackground(Color.BLUE);
		downPanel.setBackground(Color.RED);
		btnPanel.setBackground(Color.YELLOW);
		
		mainPanel.setLayout(new BorderLayout());
		upPanel.setLayout(new GridLayout(2,1));
		downMenuPanel.setLayout(new FlowLayout());
		
		upPanel.setPreferredSize(new Dimension(880, 105));
		downPanel.setPreferredSize(new Dimension(640, 250));
		btnPanel.setPreferredSize(new Dimension(240, 250));
		
		buyerNameLabel.setPreferredSize(new Dimension(110, 45));
		buyerNameTF.setPreferredSize(new Dimension(750, 45));
		shippingAddressLabel.setPreferredSize(new Dimension(110, 45));
		shippingAddressTF.setPreferredSize(new Dimension(750, 45));
		
		add(mainPanel);
		
		mainPanel.add(upPanel,BorderLayout.NORTH);
		mainPanel.add(downScrollPanel,BorderLayout.CENTER);
		mainPanel.add(btnPanel,BorderLayout.EAST);
		
		upPanel.add(up1Panel);
		upPanel.add(up2Panel);
		
		up1Panel.add(buyerNameLabel);
		up1Panel.add(buyerNameTF);
		up2Panel.add(shippingAddressLabel);
		up2Panel.add(shippingAddressTF);
		
		downPanel.add(downMenuPanel);
		downPanel.add(downContentPanel);
		downMenuPanel.add(itemIDLabel);
		downMenuPanel.add(itemNameLabel);
		downMenuPanel.add(itemPriceLabel);
		
		btnPanel.add(itemsCB);
		btnPanel.add(newItemBtn);
		btnPanel.add(inputBtn);
	}
}
