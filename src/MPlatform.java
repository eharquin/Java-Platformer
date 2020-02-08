import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.*;

public class MPlatform {

	public static final int X_VELOCITY = 10;
	public static final int Y_VELOCITY = 10;

	private MBackground background;

	private int pos_x;
	private int pos_y;

	private int height;
	private int width;

	private int v_x;
	private int v_y;

	public MPlatform(MBackground background0){
		this.pos_x = 1000;
		this.pos_y = 450;

		this.height = 20;
		this.width = 300;

		this.background = background0;
	}

	public void move(){
        if (this.v_x < 0 && !(this.background.getX()+this.background.getWidth() == MoteurJeu.FRAME_WIDTH) ){ 
            this.pos_x += this.v_x;
        }
        
        if (this.v_x > 0 && !(this.background.getX() == 0 && this.pos_x > 0) ){ 
            this.pos_x += this.v_x;
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

	public Rectangle getBounds() {
    	return new Rectangle(pos_x, pos_y, width, height);
	}

	public Rectangle getLeftBounds(){
		return new Rectangle(pos_x-10, pos_y, 20, height);
	}

	public Rectangle getRightBounds(){
		return new Rectangle(pos_x+width-10, pos_y, 20, height);
	}

	public Rectangle getTopBounds(){
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
}