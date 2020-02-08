import java.awt.*;

public class Platform {

	private MPlatform platform;

	public Platform(MPlatform platform0){
		this.platform = platform0;
	}

	public void draw(Graphics2D pinceau){
		pinceau.setColor(Color.ORANGE);
		pinceau.fillRect(this.platform.getX(), this.platform.getY(), this.platform.getWidth(), this.platform.getHeight());
		pinceau.setColor(Color.RED);
		pinceau.drawRect(this.platform.getBounds().x,this.platform.getBounds().y,this.platform.getBounds().width,this.platform.getBounds().height);
		pinceau.setColor(Color.GREEN);
		pinceau.drawRect(this.platform.getLeftBounds().x,this.platform.getLeftBounds().y,this.platform.getLeftBounds().width,this.platform.getLeftBounds().height);
		pinceau.setColor(Color.GREEN);
		pinceau.drawRect(this.platform.getRightBounds().x,this.platform.getRightBounds().y,this.platform.getRightBounds().width,this.platform.getRightBounds().height);
	}
}