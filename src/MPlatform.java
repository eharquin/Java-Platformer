import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.*;

public class MPlatform implements MGameObject{

	public static final int X_VELOCITY = 10;
	public static final int Y_VELOCITY = 10;
	
	private int pos_x;
	private int pos_y;

	private int height;
	private int width;

	private int v_x;
	private int v_y;

	private boolean mapEnd;
	private boolean mapStart;

	private boolean collisionVisible;

	public MPlatform(int pos_x0, int pos_y0, int height0, int width0, boolean collisionVisible0){
		this.pos_x = pos_x0;
		this.pos_y = pos_y0;

		this.height = height0;
		this.width = width0;

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

	public boolean isCollisionVisible(){
		return this.collisionVisible;
	}

	public Rectangle getBounds() {
    	return new Rectangle(this.pos_x, this.pos_y, this.width, this.height);
	}

	public Rectangle getLeftBounds(){
		return new Rectangle(this.pos_x, this.pos_y,1 , this.height);
	}

	public Rectangle getRightBounds(){
		return new Rectangle(this.pos_x + this.width - 1, this.pos_y, 1, this.height);
	}

	public Rectangle getTopBounds(){
		return new Rectangle(this.pos_x, this.pos_y, this.width, 1);
	}

	public Rectangle getBottomBounds(){
		return new Rectangle(this.pos_x, this.pos_y + this.height-1, this.width, 1);
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
}