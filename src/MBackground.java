import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.*;

public class MBackground implements MGameObject{

	public static final int X_VELOCITY = 10;
	public static final int Y_VELOCITY = 10;

	private int pos_x;
	private int pos_y;

	private int height;
	private int width;

	private int v_x;
	private int v_y;

	private static BufferedImage[] parralax;


	public MBackground(){
		this.pos_x = 0;
		this.pos_y = GameEngine.MAP_HEIGHT;

		this.height = GameEngine.MAP_HEIGHT;
		this.width = GameEngine.MAP_WIDTH;

		this.loadImage();
	}

	@Override
	public void move(){
        if (this.v_x < 0 && !(this.pos_x + this.width == GameEngine.FRAME_WIDTH) ){ 
            this.pos_x += this.v_x;
        }
        
        if (this.v_x > 0 && !(this.pos_x == 0) ){ 
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

	public BufferedImage[] getImages(){
		return this.parralax;
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

	private void loadImage(){
		try{
			File src;

			src = new File("./img/parralax");
			File[] parralaxFile = src.listFiles();
			MBackground.parralax = new BufferedImage[parralaxFile.length];
			for(int i = 0; i < parralaxFile.length; i++){
				MBackground.parralax[i] = ImageIO.read(parralaxFile[i]);
			}
		}
		catch (IOException e) {
            System.out.println("Background images: " + e.getMessage());
        }
    }
}