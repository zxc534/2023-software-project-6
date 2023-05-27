import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyLabel extends JLabel{
	private static Border blackline = BorderFactory.createLineBorder(Color.black);
	
	public MyLabel(){
		
	}
	
	public MyLabel(String content,int width, int height){
		super(content);
		setPreferredSize(new Dimension(width, height));
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(blackline);
	}
}
