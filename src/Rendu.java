import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Rendu extends JComponent implements Observer {

	private MoteurJeu donnee;

	private Background background;

	private Platform platform;

	private Bob vBob;

	public Rendu(){
		MBackground mBackground = new MBackground();
		this.background = new Background(mBackground);

		MPlatform mPlatform = new MPlatform(mBackground);
		this.platform = new Platform(mPlatform);

		MBob mBob = new MBob(mBackground, mPlatform);
		this.vBob = new Bob(mBob);

		this.donnee = new MoteurJeu(mBob, mBackground, mPlatform);
		this.donnee.addObserver(this);

		ControlerDirection control = new ControlerDirection(donnee);
		
		this.setFocusable(true);
		this.addKeyListener(control);

		this.setPreferredSize(new Dimension(MoteurJeu.FRAME_WIDTH, MoteurJeu.FRAME_HEIGHT));
	}

	public void update(Observable obs, Object obj){
      this.repaint();
   	}

	@Override
	protected void paintComponent(Graphics pinceau) {
		Graphics2D secondPinceau = (Graphics2D) pinceau.create();
		secondPinceau.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (this.isOpaque()) {
		  secondPinceau.setColor(this.getBackground());
		  secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		this.background.draw(secondPinceau);

		this.platform.draw(secondPinceau);

		this.vBob.draw(secondPinceau);	
	}
}