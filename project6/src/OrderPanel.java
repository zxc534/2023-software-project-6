import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class OrderPanel extends JPanel{
	
	//  ==fields 입력==
	//[0]202302101310001
	//[1]Kim
	//[2]2023-02-10_13:10
	//[3]30000
	//[4]2005; T-Shirt; 20000;CANCELED : 1001;Apple;10000;DELIVERED
	//[5]Seoul
	Order srcOrder;
	
	ItemPanel [] itemArr = new ItemPanel [Main.MAX_ITEM];
	
	private MyLabel idLabel;
	private MyLabel nameLabel;
	private MyLabel dateLabel;
	private MyLabel priceLabel;
	private JTextField addressTF;
	
	private JPanel itemPanel = new JPanel();
	private JScrollPane itemScrollPanel = new JScrollPane(itemPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private ItemPanel tempIP;

	private MouseListener ML = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("click!");
			
			if(Main.mainFrame.currentSelectedPanel == null) {
				setBorderRed();
				Main.mainFrame.currentSelectedPanel = (OrderPanel)e.getSource();
				//System.out.println(e.getSource());
			} else {
				Main.mainFrame.currentSelectedPanel.setBorderBlack();
				setBorderRed();
				Main.mainFrame.currentSelectedPanel = (OrderPanel)e.getSource();
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			//System.out.println("press!");
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			//System.out.println("release!");
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			//System.out.println("enter!");
		}
		@Override
		public void mouseExited(MouseEvent e) {
			//System.out.println("exit!");
		}
	};
	
	private static Border blackline = BorderFactory.createLineBorder(Color.black);
	private static Border redline = BorderFactory.createLineBorder(Color.red,2);
	private static Border yellowline = BorderFactory.createLineBorder(Color.yellow,2);

	public OrderPanel(Order o) {
		srcOrder = o;
		
		idLabel = new MyLabel(o.ID,130, 90);
		nameLabel = new MyLabel(o.name,130, 90);
		dateLabel = new MyLabel(""+o.time,120, 90);
		priceLabel = new MyLabel(""+o.price,130, 90);
		addressTF = new JTextField(o.address);
		addressTF.setPreferredSize(new Dimension(110, 90));
		addressTF.setEditable(false);
		addressTF.setBorder(blackline);
		
		itemScrollPanel.setPreferredSize(new Dimension(360, 90));
		itemPanel.setLayout(new GridLayout(0,1));

		for(int i=0;o.itemArr[i] != null;i++) {
			tempIP = new ItemPanel(o.itemArr[i]);
			itemArr[i] = tempIP;
			itemPanel.add(tempIP);
		}
		
		add(idLabel);
		add(nameLabel);
		add(dateLabel);
		add(priceLabel);
		add(itemScrollPanel);
		add(addressTF);
		
		addMouseListener(ML);
	}
	
	//OL and Panel Synchronization
	public void startEdit() {
		addressTF.setEditable(true);
		for(int i=0;itemArr[i] != null;i++) {
			itemArr[i].setEditable(true);
		}
	}
	public void endEdit() {
		addressTF.setEditable(false);
		srcOrder.address = addressTF.getText();
		for(int i=0;itemArr[i] != null;i++) {
			itemArr[i].setEditable(false);
			itemArr[i].srcItem.state = itemArr[i].getStatus();
		}
		
	}
	
	public void setBorderBlack() {
		setBorder(blackline);
	}
	public void setBorderRed() {
		setBorder(redline);
	}
	public void setBorderYellow() {
		setBorder(yellowline);
	}
}
