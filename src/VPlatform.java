import java.awt.*;

public class VPlatform implements VGameObject{

	private MPlatform platform;

	public VPlatform(MPlatform platform0){
		this.platform = platform0;
	}

	@Override
	public void draw(Graphics2D pencil){
		pencil.setColor(Color.ORANGE);
		pencil.fillRect(this.platform.getX(), this.platform.getY(), this.platform.getWidth(), this.platform.getHeight());
		pencil.setColor(Color.RED);
		pencil.drawRect(this.platform.getBounds().x,this.platform.getBounds().y,this.platform.getBounds().width,this.platform.getBounds().height);
		pencil.setColor(Color.GREEN);
		pencil.drawRect(this.platform.getLeftBounds().x,this.platform.getLeftBounds().y,this.platform.getLeftBounds().width,this.platform.getLeftBounds().height);
		pencil.setColor(Color.GREEN);
		pencil.drawRect(this.platform.getRightBounds().x,this.platform.getRightBounds().y,this.platform.getRightBounds().width,this.platform.getRightBounds().height);
	}
}