import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.*;

public class MBob implements MGameObject{

	public static final int X_VELOCITY = 10;
	public static final int Y_VELOCITY = 12;

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
	private int actualDead;
	private static BufferedImage[] dead;

	private int startJump;

	private boolean rightCollision;
	private boolean leftCollision;
	private boolean topCollision;
	private boolean bottomCollision;

	private boolean mapEnd;
	private boolean mapStart;

	private boolean collisionVisible;

	public MBob(boolean collisionVisible0){
		this.pos_x = 0;
		this.pos_y = GameEngine.MAP_HEIGHT;

		this.loadImage("boy");

        this.img = MBob.idle[0];

        this.height = this.img.getHeight()/3;
		this.width = this.img.getWidth()/3;

        this.direction = EAST;
        this.imgDirection = EAST;

        this.collisionVisible = collisionVisible0;
	}

	@Override
	public void move(){

		System.out.println(v_y);


		// make bob floating
		if(!this.floating && this.v_y < 0){
        	this.floating = true;
        	this.startJump = this.pos_y - GameEngine.MAP_HEIGHT;
        }

        // make bob go to right
		if (this.v_x > 0 && !this.rightCollision && (this.pos_x + this.width/2 <= GameEngine.FRAME_WIDTH/2 || this.mapEnd) && this.pos_x + this.width < GameEngine.FRAME_WIDTH) {
			this.pos_x	+= this.v_x;
		}

		// make bob go to left
		if (this.v_x < 0 && !this.leftCollision && (this.pos_x + this.width/2 >= GameEngine.FRAME_WIDTH/2 || this.mapStart) && this.pos_x > 0){
		    this.pos_x	+= this.v_x;
        }

        // make bob go up or down
        if(this.floating){
            if (!this.jumpEnd) this.v_y = -MBob.Y_VELOCITY;
            else this.v_y = MBob.Y_VELOCITY;
            this.pos_y += this.v_y;
        }

        // stop bob jump
        if(this.pos_y <= MBob.MAX_JUMP + this.startJump || this.topCollision)
			this.jumpEnd = true;

		// make bob landing
		if(this.v_y > 0 && ((this.floating && this.pos_y == GameEngine.MAP_HEIGHT) || this.bottomCollision)){
			this.floating = false;
            this.jumpEnd = false;
            this.v_y = 0;
		}

		// knocks bob off a platform
		if (!this.floating && this.pos_y != GameEngine.MAP_HEIGHT && !this.bottomCollision){
			this.floating = true;
            this.jumpEnd = true;
            this.v_y = -MBob.Y_VELOCITY;
        }


        // set the right image
        if(this.v_x == 0 && this.v_y == 0){
        	this.img = MBob.idle[(this.actualIdle++) % MBob.idle.length];
        }
        else if(this.jumpEnd){
        	this.img = MBob.jump[MBob.jump.length-1];
        }
        else if(this.floating){
        	this.img = MBob.jump[MBob.jump.length-1];
        }
        else{
        	this.img = MBob.walk[(this.actualWalk++) % MBob.walk.length];
        }

        this.rotateImage(this.direction);

        this.height = this.img.getHeight()/3;
		this.width = this.img.getWidth()/3;
	}

	private void rotateImage(int direction){
		if(this.direction != this.imgDirection){
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
    	return new Rectangle(this.pos_x, this.pos_y-this.height, this.width, this.height);
	}

	public Rectangle getRightBounds() {
    	return new Rectangle(this.pos_x + this.width, this.pos_y - this.height, 12, this.height);
	}

	public Rectangle getLeftBounds() {
    	return new Rectangle(this.pos_x - 12, this.pos_y - this.height, 12, this.height);
	}

	public Rectangle getTopBounds() {
    	return new Rectangle(this.pos_x, this.pos_y - this.height - 12, this.width, 12);
	}

	public Rectangle getBottomBounds() {
    	return new Rectangle(this.pos_x, this.pos_y, this.width, 12);
	}

	public boolean isJumpEnd(){
		return this.jumpEnd;
	}

	public boolean isFloating(){
		return this.floating;
	}

	public boolean isRightCollision(){
		return this.rightCollision;
	}

	public boolean isLeftCollision(){
		return this.leftCollision;
	}

	public boolean isTopCollision(){
		return this.topCollision;
	}

	public boolean isBottomCollision(){
		return this.bottomCollision;
	}

	public boolean isCollisionVisible(){
		return this.collisionVisible;
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

	public void setRightCollision(boolean rightCollision0){
		this.rightCollision = rightCollision0;
	}

	public void setLeftCollision(boolean leftCollision0){
		this.leftCollision = leftCollision0;
	}

	public void setTopCollision(boolean topCollision0){
		this.topCollision = topCollision0;
	}

	public void setBottomCollision(boolean bottomCollision0){
		this.bottomCollision = bottomCollision0;
	}

	public void setMapEnd(boolean mapEnd0){
		this.mapEnd = mapEnd0;
	}

	public void setMapStart(boolean mapStart0){
		this.mapStart = mapStart0;
	}

	// load bob images
	private void loadImage(String character){
		try{
			File src;

			src = new File("./img/"+character+"/walk");
			File[] walkFile = src.listFiles();
			MBob.walk = new BufferedImage[walkFile.length];
			for(int i = 0; i < walkFile.length; i++){
				MBob.walk[i] = trim(ImageIO.read(walkFile[i]));
			}

			src = new File("./img/"+character+"/idle");
			File[] idleFile = src.listFiles();
			MBob.idle = new BufferedImage[idleFile.length];
			for(int i = 0; i < idleFile.length; i++){
				MBob.idle[i] = trim(ImageIO.read(idleFile[i]));
			}

			src = new File("./img/"+character+"/jump");
			File[] jumpFile = src.listFiles();
			MBob.jump = new BufferedImage[jumpFile.length];
			for(int i = 0; i < jumpFile.length; i++){
				MBob.jump[i] = trim(ImageIO.read(jumpFile[i]));
			}

			src = new File("./img/"+character+"/run");
			File[] runFile = src.listFiles();
			MBob.run = new BufferedImage[runFile.length];
			for(int i = 0; i < runFile.length; i++){
				MBob.run[i] = trim(ImageIO.read(runFile[i]));
			}

			src = new File("./img/"+character+"/dead");
			File[] deadFile = src.listFiles();
			MBob.dead = new BufferedImage[deadFile.length];
			for(int i = 0; i < deadFile.length; i++){
				MBob.dead[i] = trim(ImageIO.read(deadFile[i]));
			}
		}
		catch (IOException e) {
            System.out.println("Bob sprites: " + e.getMessage());
        }
    }

    // trim the image
    private static BufferedImage trim(BufferedImage img) {
	    int width = img.getWidth();
	    int height = img.getHeight();

	    int top = height / 2;
	    int bottom = top;

	    int left = width / 2 ;
	    int right = left;

	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            if (isFg(img.getRGB(x, y))){

	                top    = Math.min(top, y);
	                bottom = Math.max(bottom, y);

	                left   = Math.min(left, x);
	                right  = Math.max(right, x);
	            }
	        }
	    }
	    return img.getSubimage(left, top, right - left, bottom - top);
	}

	private static boolean isFg(int v) {
	    Color c = new Color(v);
	    return(isColor((c.getRed() + c.getGreen() + c.getBlue())/2));
	}

	private static boolean isColor(int c) {
	    return c > 0 && c < 255;
	}
}