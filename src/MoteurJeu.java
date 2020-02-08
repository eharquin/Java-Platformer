import java.util.*;

public class MoteurJeu extends Observable {

	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 720;

	public static final int MAP_WIDTH = 2000;
	public static final int MAP_HEIGHT = 720;

	public static final int NONE = -1;
	public static final int UP = 0;
	public static final int DOWN = 1; 
	public static final int RIGHT = 2;
	public static final int LEFT = 3;

	private MBob bob;
	private MBackground background;
	private MPlatform platform;

	public MoteurJeu(MBob bob0, MBackground background0, MPlatform platform0){
		ControlerTick tick = new ControlerTick(this);

		this.bob = bob0;
		this.background = background0;
		this.platform = platform0;
	}

	public void setMove(int move){

		if(move == NONE){
			this.bob.setVelocityX(0);
			this.background.setVelocityX(0);
			this.platform.setVelocityX(0);
		}

		if(move == UP){
			this.bob.setVelocityY(-MBob.Y_VELOCITY);
		}

		if(move == DOWN){

		}

		if(move == RIGHT){
			this.background.setVelocityX(-MBackground.X_VELOCITY);
			this.platform.setVelocityX(-MPlatform.X_VELOCITY);
			this.bob.setVelocityX(MBob.X_VELOCITY);
			this.bob.setDirection(MBob.EAST);
		}

		if(move == LEFT){
			this.background.setVelocityX(MBackground.X_VELOCITY);
			this.platform.setVelocityX(MPlatform.X_VELOCITY);
			this.bob.setVelocityX(-MBob.X_VELOCITY);
			this.bob.setDirection(MBob.WEST);
		}		
	}

	public MPlatform getPlatform(){
		return this.platform;
	}

	public void tick(){
		
		this.bob.move();

		if(this.bob.getDirection() == MBob.EAST && this.bob.getX()+this.bob.getWidth()/2 >= FRAME_WIDTH/2 && !(this.bob.getRightBounds().intersects(this.platform.getLeftBounds()))){
			this.background.move();
			this.platform.move();
		}
	
		if(this.bob.getDirection() == MBob.WEST && this.bob.getX()+this.bob.getWidth()/2 <= FRAME_WIDTH/2 && !(this.bob.getLeftBounds().intersects(this.platform.getRightBounds()))){
			this.background.move();
			this.platform.move();
		}

        setChanged();
	   	notifyObservers();
	}
}