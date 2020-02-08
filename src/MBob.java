import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.*;

public class MBob implements MGameObject{

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

	private boolean floating;
	private boolean jumpEnd;

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

	private int startJump;

	private void isRightCollision;

	private void isLeftCollision;

	private void isTopCollision;

	private void isBottomCollision;

	public MBob(){
		this.pos_x = 0;
		this.pos_y = MoteurJeu.MAP_HEIGHT;

		this.height = 200;
		this.width = 182;

		this.loadImage();

        this.img = MBob.idle[0];

        this.direction = EAST;
        this.imgDirection = EAST;
	}

	@Override
	public void move(){

		// make bob floating
		if(!this.bob.isFloating() && this.bob.getVelocityY() < 0){
        	this.bob.setFloating(true);
        	this.bob.setStartJump(this.bob.getY() - MoteurJeu.MAP_HEIGHT);
        }

        // test the right collisions of bob
		if (this.bob.getVelocityX() > 0 && this.isRightCollision ) {
			this.bob.setVelocityX(0);
		}




        // set the right image
        if(this.v_x == 0 && this.v_y == 0){
        	img = idle[(this.actualIdle++)%idle.length];
        }
        else if(this.jumpEnd){
        	img = jump[14];
        }
        else if(this.floating){
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

	public int getStartJump(){
		return this.startJump;
	}

	public BufferedImage getImage(){
		return this.img;
	}

	public Rectangle getBounds() {
    	return new Rectangle(pos_x, pos_y-height, width, height);
	}

	public Rectangle getRightBounds() {
    	return new Rectangle(pos_x+width-10, pos_y-height-10, 20, height+20);
	}

	public Rectangle getLeftBounds() {
    	return new Rectangle(pos_x-10, pos_y-height-10, 20, height+20);
	}

	public Rectangle getTopBounds() {
    	return new Rectangle(pos_x, pos_y-height-10, width, 20);
	}

	public Rectangle getBottomBounds() {
    	return new Rectangle(pos_x, pos_y-10, width, 20);
	}

	public boolean isJumpEnd(){
		return this.jumpEnd;
	}

	public boolean isFloating(){
		return this.floating;
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

	public void setStartJump(int startJump0){
		this.startJump = startJump0;
	}

	public void setJumpEnd(boolean jumpEnd0){
		this.jumpEnd = jumpEnd0;
	}

	public void setFloating(boolean floating0){
		this.floating = floating0;
	}

	private void loadImage(){
		try{
			File src;

			src = new File("./img/walk");
			File[] walkFile = src.listFiles();
			MBob.walk = new BufferedImage[walkFile.length];
			for(int i = 0; i < walkFile.length; i++){
				MBob.walk[i] = ImageIO.read(walkFile[i]);
			}

			src = new File("./img/idle");
			File[] idleFile = src.listFiles();
			MBob.idle = new BufferedImage[idleFile.length];
			for(int i = 0; i < idleFile.length; i++){
				MBob.idle[i] = ImageIO.read(idleFile[i]);
			}

			src = new File("./img/jump");
			File[] jumpFile = src.listFiles();
			MBob.jump = new BufferedImage[jumpFile.length];
			for(int i = 0; i < jumpFile.length; i++){
				MBob.jump[i] = ImageIO.read(jumpFile[i]);
			}

			src = new File("./img/run");
			File[] runFile = src.listFiles();
			MBob.run = new BufferedImage[runFile.length];
			for(int i = 0; i < runFile.length; i++){
				MBob.run[i] = ImageIO.read(runFile[i]);
			}

			src = new File("./img/dead");
			File[] deadFile = src.listFiles();
			MBob.dead = new BufferedImage[deadFile.length];
			for(int i = 0; i < deadFile.length; i++){
				MBob.dead[i] = ImageIO.read(deadFile[i]);
			}
		}
		catch (IOException e) {
            System.out.println("Walk sprite:" + e.getMessage());
        }
	}
}