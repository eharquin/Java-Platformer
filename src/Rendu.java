import javax.swing.*;
import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class Rendu extends JComponent implements Observer {

	private GameEngine gameEngine;

	private VBackground background;

	private VPlatform[] platforms;

	private VEnemy[] enemies;

	private VBob vBob;

	public Rendu(){
		MBackground mBackground = new MBackground();
		this.background = new VBackground(mBackground);

		MPlatform mPlatform1 = new MPlatform(400,450,270,200, false);
		VPlatform vPlatform1 = new VPlatform(mPlatform1);

		MPlatform mPlatform2 = new MPlatform(1300,450,270,200, false);
		VPlatform vPlatform2 = new VPlatform(mPlatform2);

		MPlatform mPlatform3 = new MPlatform(850,350,20,200, false);
		VPlatform vPlatform3 = new VPlatform(mPlatform3);

		MEnemy mEnemy1 = new MEnemy(2000, 720, true);
		VEnemy vEnemy1 = new VEnemy(mEnemy1);

		this.platforms = new VPlatform[] {vPlatform1, vPlatform2, vPlatform3};

		this.enemies = new VEnemy[] {vEnemy1};

		MBob mBob = new MBob(true);
		this.vBob = new VBob(mBob);

		this.gameEngine = new GameEngine(mBob, mBackground, new MPlatform [] {mPlatform1, mPlatform2, mPlatform3}, new MEnemy[] {mEnemy1});
		this.gameEngine.addObserver(this);

		ControlerDirection control = new ControlerDirection(this.gameEngine);
		
		this.setFocusable(true);
		this.addKeyListener(control);

		this.setPreferredSize(new Dimension(GameEngine.FRAME_WIDTH, GameEngine.FRAME_HEIGHT));
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

		for(VPlatform platform : this.platforms){
			platform.draw(pencil);
		}
		for(VEnemy enemy : this.enemies){
			enemy.draw(pencil);
		}

		this.vBob.draw(pencil);	
	}
}