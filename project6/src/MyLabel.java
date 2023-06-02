import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MyLabel extends JLabel{
	private static Border blackline = BorderFactory.createLineBorder(Color.black);
	private static Border thickblackline = BorderFactory.createLineBorder(Color.black,2);
	private static Font font = new Font("",Font.BOLD,15);
	
	public MyLabel(){
		
	}
	
	public MyLabel(String content,int width, int height){
		super(content);
		setPreferredSize(new Dimension(width, height));
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(blackline);
	}
	
	public MyLabel(String content,int width, int height, boolean b) {
		super(content);
		setPreferredSize(new Dimension(width, height));
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(thickblackline);
		setFont(font);
	}
}
