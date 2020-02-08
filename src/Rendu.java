import javax.swing.*;
import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class Rendu extends JComponent implements Observer {

	private MoteurJeu donnee;

	private VBackground background;

	private VPlatform platform;

	private VBob vBob;

	public Rendu(){
		MBackground mBackground = new MBackground();
		this.background = new VBackground(mBackground);

		MPlatform mPlatform = new MPlatform();
		this.platform = new VPlatform(mPlatform);

		MBob mBob = new MBob();
		this.vBob = new VBob(mBob);

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
	protected void paintComponent(Graphics pencil0) {
		Graphics2D pencil = (Graphics2D) pencil0.create();
		pencil.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (this.isOpaque()) {
		  pencil.setColor(this.getBackground());
		  pencil.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		this.background.draw(pencil);

		this.platform.draw(pencil);

		this.vBob.draw(pencil);	
	}
}