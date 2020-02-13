import java.util.*;

public class GameEngine extends Observable {

	public static final int FRAME_WIDTH = 1920;
	public static final int FRAME_HEIGHT = 1080;

	public static final int MAP_WIDTH = 5000;
	public static final int MAP_HEIGHT = 720;

	public static final int NONE = -1;
	public static final int UP = 0;
	public static final int DOWN = 1; 
	public static final int RIGHT = 2;
	public static final int LEFT = 3;

	private MBob bob;
	private MBackground background;
	private MPlatform[] platforms;
	private MEnemy[] enemies;

	public GameEngine(MBob bob0, MBackground background0, MPlatform[] platforms0, MEnemy[] enemies0){
		
		ControlerTick tick = new ControlerTick(this);

		this.bob = bob0;
		this.background = background0;
		this.platforms = platforms0;
		this.enemies = enemies0;

	}

	public void setMove(int move){

		if(move == NONE){
			this.bob.setVelocityX(0);
			this.background.setVelocityX(0);
			for(MPlatform platform : this.platforms){
				platform.setVelocityX(0);
			}
			for(MEnemy enemy : this.enemies){
				enemy.setVelocityX(0);
			}
		}

		if(move == UP){
			this.bob.setVelocityY(-MBob.Y_VELOCITY);
		}

		if(move == DOWN){

		}

		if(move == RIGHT){
			this.background.setVelocityX(-MBackground.X_VELOCITY);
			for(MPlatform platform : this.platforms){
				platform.setVelocityX(-MPlatform.X_VELOCITY);
			}
			for(MEnemy enemy : this.enemies){
				enemy.setVelocityX(-MPlatform.X_VELOCITY);
			}
			this.bob.setVelocityX(MBob.X_VELOCITY);
			this.bob.setDirection(MBob.EAST);
		}

		if(move == LEFT){
			this.background.setVelocityX(MBackground.X_VELOCITY);
			for(MPlatform platform : this.platforms){
				platform.setVelocityX(MPlatform.X_VELOCITY);
			}
			for(MEnemy enemy : this.enemies){
				enemy.setVelocityX(MPlatform.X_VELOCITY);
			}
			this.bob.setVelocityX(-MBob.X_VELOCITY);
			this.bob.setDirection(MBob.WEST);
		}		
	}

	public void tick(){
		
		this.setBobCollision();
		this.setMapBorder();
		
		this.bob.move();

		if((this.bob.getVelocityX() > 0 && this.bob.getX()+this.bob.getWidth()/2 >= FRAME_WIDTH/2 && !this.bob.isRightCollision()) ||
		   (this.bob.getVelocityX() < 0 && this.bob.getX()+this.bob.getWidth()/2 <= FRAME_WIDTH/2 && !this.bob.isLeftCollision())){
		   	for(MPlatform platform : this.platforms){
				platform.move();
			}
			for(MEnemy enemy : this.enemies){
				enemy.move();
			}
			this.background.move();
		}

        setChanged();
	   	notifyObservers();
	}


	private void setBobCollision(){

		this.bob.setRightCollision(false);
		this.bob.setLeftCollision(false);
		this.bob.setTopCollision(false);
		this.bob.setBottomCollision(false);

		for(MPlatform platform : this.platforms){
			if (this.bob.getRightBounds().intersects(platform.getLeftBounds())) 
				this.bob.setRightCollision(true);

			if (this.bob.getLeftBounds().intersects(platform.getRightBounds())) 
				this.bob.setLeftCollision(true);

			if(this.bob.getTopBounds().intersects(platform.getBounds()))
				this.bob.setTopCollision(true);

			if(this.bob.getBottomBounds().intersects(platform.getBounds()))
				this.bob.setBottomCollision(true);
		}

		for(MEnemy enemy : this.enemies){
			if (this.bob.getRightBounds().intersects(enemy.getLeftBounds())) 
				this.bob.setRightCollision(true);

			if (this.bob.getLeftBounds().intersects(enemy.getRightBounds())) 
				this.bob.setLeftCollision(true);

			if(this.bob.getTopBounds().intersects(enemy.getBounds()))
				this.bob.setTopCollision(true);

			if(this.bob.getBottomBounds().intersects(enemy.getBounds()))
				this.bob.setBottomCollision(true);
		}
	}

	private void setMapBorder(){

		this.bob.setMapEnd(false);
		this.bob.setMapStart(false);

		for(MPlatform platform : this.platforms){
			platform.setMapEnd(false);
			platform.setMapStart(false);
		}
		for(MEnemy enemy : this.enemies){
			enemy.setMapEnd(false);
			enemy.setMapStart(false);
		}

		if (this.background.getX()+this.background.getWidth() == GameEngine.FRAME_WIDTH){
        	this.bob.setMapEnd(true);
        	for(MPlatform platform : this.platforms){
        		platform.setMapEnd(true);
        	}
        	for(MEnemy enemy : this.enemies){
				enemy.setMapEnd(true);
			}
		}

		if (this.background.getX() == 0){
		    this.bob.setMapStart(true);
		    for(MPlatform platform : this.platforms){
		    	platform.setMapStart(true);
		    }
		    for(MEnemy enemy : this.enemies){
				enemy.setMapStart(true);
			}
		}

	}
}