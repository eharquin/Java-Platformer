import java.awt.*;

public class Background {

	private MBackground background;

	public Background(MBackground background0){
		this.background = background0;
	}

	public void draw(Graphics2D pinceau){
		pinceau.setColor(Color.BLUE);
		pinceau.fillRect(this.background.getX(), 0, this.background.getWidth(), this.background.getHeight());

		pinceau.setColor(Color.BLACK);
		for(int i=0; i<20; i++){
			pinceau.fillRect(this.background.getX()+2*i*50, 200, 50, 50);
		}
	}
}