import javax.swing.*;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	public Frame(){
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Rendu());
		this.pack();
	}
}