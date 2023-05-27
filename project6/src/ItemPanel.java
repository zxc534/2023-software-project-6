import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ItemPanel extends JPanel {
	private MyLabel itemId;
	private MyLabel itemName;
	private MyLabel itemPrice;
	private JComboBox<String> itemStatus ;
	
	private static String[] status = new String[] {"PREPARING","SHIPPED","RETURNED","CANCELED","DELIVERED"};
	
	public ItemPanel(Item item) {
		itemId = new MyLabel(""+item.ID,70, 40);
		itemName = new MyLabel(item.name,70, 40);
		itemPrice = new MyLabel(""+item.price,70, 40);
		itemStatus = new JComboBox<String>(status);
		
		char statChar = item.state.charAt(0);
		if(statChar =='P') {
			itemStatus.setSelectedIndex(0);
		} else if(statChar =='S') {
			itemStatus.setSelectedIndex(1);
		} else if (statChar == 'R') {
			itemStatus.setSelectedIndex(2);
		} else if (statChar == 'C') {
			itemStatus.setSelectedIndex(3);
		} else {
			itemStatus.setSelectedIndex(4);
		}
		itemStatus.setPreferredSize(new Dimension(90, 40));
		
		add(itemId);
		add(itemName);
		add(itemPrice);
		add(itemStatus);
	}
}
