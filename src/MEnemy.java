import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.*;

public class MEnemy implements MGameObject{

	public static final int X_VELOCITY = 10;
	public static final int Y_VELOCITY = 10;

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

	private boolean mapEnd;
	private boolean mapStart;

	private static BufferedImage[] walk;
	private int actualWalk;
	private static BufferedImage[] idle;
	private int actualIdle;
	private static BufferedImage[] attack;
	private int actualAttack;
	private static BufferedImage[] dead;

	private BufferedImage img;
	private int imgDirection;

	private boolean collisionVisible;

	public MEnemy(int pos_x0, int pos_y0, boolean collisionVisible0){
		this.pos_x = pos_x0;
		this.pos_y = pos_y0;

		this.loadImage("male");

		this.img = MEnemy.idle[0];

		this.height = this.img.getHeight()/3;
		this.width = this.img.getWidth()/3;

		this.direction = EAST;
        this.imgDirection = EAST;

        this.collisionVisible = collisionVisible0;
	}

	@Override
	public void move(){

		if(this.v_x < 0 && !this.mapEnd)
        	this.pos_x += v_x;

        if(this.v_x > 0 && !this.mapStart)
        	this.pos_x += v_x;
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

	public BufferedImage getImage(){
		return this.img;
	}

	public boolean isCollisionVisible(){
		return this.collisionVisible;
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

	public void setMapEnd(boolean mapEnd0){
		this.mapEnd = mapEnd0;
	}

	public void setMapStart(boolean mapStart0){
		this.mapStart = mapStart0;
	}

	private void loadImage(String character){
		try{
			File src;

			src = new File("./img/enemy/"+character+"/walk");
			File[] walkFile = src.listFiles();
			MEnemy.walk = new BufferedImage[walkFile.length];
			for(int i = 0; i < walkFile.length; i++){
				MEnemy.walk[i] = trim(ImageIO.read(walkFile[i]));
			}

			src = new File("./img/enemy/"+character+"/idle");
			File[] idleFile = src.listFiles();
			MEnemy.idle = new BufferedImage[idleFile.length];
			for(int i = 0; i < idleFile.length; i++){
				MEnemy.idle[i] = trim(ImageIO.read(idleFile[i]));
			}

			src = new File("./img/enemy/"+character+"/attack");
			File[] attackFile = src.listFiles();
			MEnemy.attack = new BufferedImage[attackFile.length];
			for(int i = 0; i < attackFile.length; i++){
				MEnemy.attack[i] = trim(ImageIO.read(attackFile[i]));
			}

			src = new File("./img/enemy/"+character+"/dead");
			File[] deadFile = src.listFiles();
			MEnemy.dead = new BufferedImage[deadFile.length];
			for(int i = 0; i < deadFile.length; i++){
				MEnemy.dead[i] = trim(ImageIO.read(deadFile[i]));
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