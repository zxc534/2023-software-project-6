import java.awt.Dimension;

import javax.swing.JButton;

public class MyButton extends JButton{
	public MyButton(String content) {
		super(content);
		setPreferredSize(new Dimension(180, 40));
	}
}
