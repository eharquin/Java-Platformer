import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.*;

public class MBob {

	public static final int X_VELOCITY = 10;
	public static final int Y_VELOCITY = 10;

	public static final int MAX_JUMP = 400;

	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int EAST = 2;
	public static final int WEST = 3;

	private int pos_x;
	private int pos_y;

	private int height;
	private int width;

	private int v_x;
	private int v_y;

	private int direction;

	private boolean isFloating;
	private boolean isJumpEnd;

	private BufferedImage img;
	private int imgDirection;

	private static BufferedImage[] walk;
	private int actualWalk;
	private static BufferedImage[] idle;
	private int actualIdle;
	private static BufferedImage[] jump;
	private int actualJump;
	private static BufferedImage[] run;
	private static BufferedImage[] dead;

	private MBackground background;

	private MPlatform platform;


	private int startJump;

	public MBob(MBackground background0, MPlatform platform0){
		this.pos_x = 0;
		this.pos_y = MoteurJeu.MAP_HEIGHT;

		this.height = 200;
		this.width = 182;

		this.loadImage();

        this.img = this.idle[0];

        this.direction = EAST;
        this.imgDirection = EAST;

        this.background = background0;
        this.platform = platform0;
	}

	public void move(){
		if(!this.isFloating && v_y != 0){
        	this.isFloating = true;
        	this.startJump = pos_y-MoteurJeu.MAP_HEIGHT;

        }
 		
 		if (this.v_x > 0 && !(this.getRightBounds().intersects(this.platform.getLeftBounds()))){
        	if( (pos_x+width/2 <= MoteurJeu.FRAME_WIDTH/2 || this.background.getX()+this.background.getWidth() == MoteurJeu.FRAME_WIDTH) && this.pos_x + this.width < MoteurJeu.FRAME_WIDTH){
        		this.pos_x += this.v_x; 
        	}
        }

		if (this.v_x < 0 && !(this.getLeftBounds().intersects(this.platform.getRightBounds()))){
			if(pos_x+width/2 >= MoteurJeu.FRAME_WIDTH/2 || this.background.getX() == 0){
				if(this.pos_x > 0){
		        	this.pos_x += this.v_x;
		        }
		    }
        }

        if (this.isFloating) {
            if (!this.isJumpEnd) this.pos_y += this.v_y;
            else{
            	this.pos_y -= this.v_y;
            }
        }

        if (this.pos_y <= this.MAX_JUMP+this.startJump || this.getTopBounds().intersects(this.platform.getTopBounds())) this.isJumpEnd = true;

        if ( (this.isFloating && this.pos_y == MoteurJeu.MAP_HEIGHT) || this.getBottomBounds().intersects(this.platform.getBounds())) {
        	this.isFloating = false;
            this.isJumpEnd = false;
            this.v_y = 0;
        }

        if ( !this.isFloating && pos_y != MoteurJeu.MAP_HEIGHT && !this.getBottomBounds().intersects(this.platform.getBounds())) {
        	this.isFloating = true;
            this.isJumpEnd = true;
            this.v_y = -Y_VELOCITY;
        }
        
        if(this.v_x == 0 && this.v_y == 0){
        	img = idle[(this.actualIdle++)%idle.length];
        }
        else if(this.isJumpEnd){
        	img = jump[14];
        }
        else if(this.isFloating){
        	img = jump[jump.length-1];
        }
        else{
        	img = walk[(this.actualWalk++)%walk.length];
        }

        this.rotateImage(this.direction);
	}

	public void rotateImage(int direction){
		if(direction != imgDirection){
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	    	tx.translate(-this.img.getWidth(null), 0);
	    	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    	this.img = op.filter(this.img, null);
	    }
	}

	public int getX(){
		return this.pos_x;
	}

	public int getY(){
		return this.pos_y;
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	public int getVelocityX(){
		return this.v_x;
	}

	public int getVelocityY(){
		return this.v_y;
	}

	public int getDirection(){
		return this.direction;
	}

	public BufferedImage getImage(){
		return this.img;
	}

	public Rectangle getBounds() {
    	return new Rectangle(pos_x, pos_y-height, width, height);
	}

	public Rectangle getRightBounds() {
    	return new Rectangle(pos_x+width-10, pos_y-height, 20, height);
	}

	public Rectangle getLeftBounds() {
    	return new Rectangle(pos_x-10, pos_y-height, 20, height);
	}

	public Rectangle getTopBounds() {
    	return new Rectangle(pos_x, pos_y-height, width, 1);
	}

	public Rectangle getBottomBounds() {
    	return new Rectangle(pos_x, pos_y, width, 1);
	}


	public void setX(int x0){
		this.pos_x = x0;
	}

	public void setY(int y0){
		this.pos_y = y0;
	}

	public void setHeight(int height0){
		this.height = height0;
	}

	public void setWidth(int width0){
		this.width = width0;
	}

	public void setVelocityX(int vx0){
		this.v_x = vx0;
	}

	public void setVelocityY(int vy0){
		this.v_y = vy0;
	}

	public void setDirection(int direction0){
		this.direction = direction0;
	}

	private void loadImage(){
		try{
			File src;

			src = new File("./img/walk");
			File[] walkFile = src.listFiles();
			this.walk = new BufferedImage[walkFile.length];
			for(int i = 0; i < walkFile.length; i++){
				this.walk[i] = ImageIO.read(walkFile[i]);
			}

			src = new File("./img/idle");
			File[] idleFile = src.listFiles();
			this.idle = new BufferedImage[idleFile.length];
			for(int i = 0; i < idleFile.length; i++){
				this.idle[i] = ImageIO.read(idleFile[i]);
			}

			src = new File("./img/jump");
			File[] jumpFile = src.listFiles();
			this.jump = new BufferedImage[jumpFile.length];
			for(int i = 0; i < jumpFile.length; i++){
				this.jump[i] = ImageIO.read(jumpFile[i]);
			}

			src = new File("./img/run");
			File[] runFile = src.listFiles();
			this.run = new BufferedImage[runFile.length];
			for(int i = 0; i < runFile.length; i++){
				this.run[i] = ImageIO.read(runFile[i]);
			}

			src = new File("./img/dead");
			File[] deadFile = src.listFiles();
			this.dead = new BufferedImage[deadFile.length];
			for(int i = 0; i < deadFile.length; i++){
				this.dead[i] = ImageIO.read(deadFile[i]);
			}
		}
		catch (IOException e) {
            System.out.println("Walk sprite:" + e.getMessage());
        }
	}
}