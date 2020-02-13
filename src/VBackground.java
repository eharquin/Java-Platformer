import java.awt.*;
import java.awt.image.*;

public class VBackground implements VGameObject{

	private MBackground background;

	public VBackground(MBackground background0){
		this.background = background0;
	}

	@Override
	public void draw(Graphics2D pencil){
		//pencil.setColor(Color.WHITE);
		//pencil.fillRect(this.background.getX(), 0, this.background.getWidth(), this.background.getHeight());

		BufferedImage[] imgs = this.background.getImages();

		Double speed = 1.0/imgs.length;

		int compteur = 0;

		for(int j=0; j < GameEngine.MAP_WIDTH; j+=imgs[0].getWidth()){
			for(int i=0; i < imgs.length; i++){
				pencil.drawImage(imgs[i],
							 (int) (((double) this.background.getX())*((i+1)*speed)) + j,
							 this.background.getY()-imgs[i].getHeight()+250,
							 imgs[i].getWidth(),
							 imgs[i].getHeight(),
							 null);
			}
		}
		/*pencil.setColor(Color.BLACK);
		for(int i=0; i<20; i++){
			pencil.fillRect(this.background.getX()+2*i*50, 400, 50, 50);
		}*/
	}
}