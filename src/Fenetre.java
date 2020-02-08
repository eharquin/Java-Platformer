import javax.swing.*;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {

	public Fenetre(){
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Rendu());
		this.pack();
	}
}