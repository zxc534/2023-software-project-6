import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class OrderPanel extends JPanel{
	
	//  ==fields 입력==
	//[0]202302101310001
	//[1]Kim
	//[2]2023-02-10_13:10
	//[3]30000
	//[4]2005; T-Shirt; 20000;CANCELED : 1001;Apple;10000;DELIVERED
	//[5]Seoul
	
	private MyLabel idLabel;
	private MyLabel nameLabel;
	private MyLabel dateLabel;
	private MyLabel priceLabel;
	private MyLabel addressLabel;
	
	private JPanel itemPanel = new JPanel();
	private JScrollPane itemScrollPanel = new JScrollPane(itemPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private ItemPanel[] itemPanels;
	
	private static Border blackline = BorderFactory.createLineBorder(Color.black);
	
	public OrderPanel() {
		
	}
	
	public OrderPanel(String id, String name, String date, String price, String address ,String[][] items) {
		idLabel = new MyLabel(id,130, 90);
		nameLabel = new MyLabel(name,130, 90);
		dateLabel = new MyLabel(date,130, 90);
		priceLabel = new MyLabel(price,130, 90);
		addressLabel = new MyLabel(address,110, 90);
		
		itemScrollPanel.setPreferredSize(new Dimension(360, 90));
		itemPanel.setLayout(new GridLayout(0,1));
		itemPanels = new ItemPanel[items.length];
		for(int i=0;i<items.length;i++) {
			itemPanels[i] = new ItemPanel(items[i]);
			itemPanels[i].setBorder(blackline);
		}
		for(int i=0;i<items.length;i++) {
			itemPanel.add(itemPanels[i]);
		}
		
		add(idLabel);
		add(nameLabel);
		add(dateLabel);
		add(priceLabel);
		add(itemScrollPanel);
		add(addressLabel);
		
	}
}
