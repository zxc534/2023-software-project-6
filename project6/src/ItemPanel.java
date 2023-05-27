import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ItemPanel extends JPanel {
	private MyLabel itemId;
	private MyLabel itemName;
	private MyLabel itemPrice;
	private JComboBox<String> itemStatus ;
	
	private static String[] status = new String[] {"SHIPPED","RETURNED","CANCELED","DELIVERED"};
	
	public ItemPanel(String [] item) {
		itemId = new MyLabel(item[0],70, 40);
		itemName = new MyLabel(item[1],70, 40);
		itemPrice = new MyLabel(item[2],70, 40);
		itemStatus = new JComboBox<String>(status);
		
		char statChar = item[3].charAt(0);
		if(statChar =='S') {
			itemStatus.setSelectedIndex(0);
		} else if (statChar == 'R') {
			itemStatus.setSelectedIndex(1);
		} else if (statChar == 'R') {
			itemStatus.setSelectedIndex(2);
		} else {
			itemStatus.setSelectedIndex(3);
		}
		itemStatus.setPreferredSize(new Dimension(90, 40));
		
		add(itemId);
		add(itemName);
		add(itemPrice);
		add(itemStatus);
	}
}
