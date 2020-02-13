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
		
		if(this.platform.isCollisionVisible()){
			pencil.setColor(Color.GREEN);
			pencil.drawRect(this.platform.getLeftBounds().x,this.platform.getLeftBounds().y,this.platform.getLeftBounds().width,this.platform.getLeftBounds().height);
			pencil.drawRect(this.platform.getRightBounds().x,this.platform.getRightBounds().y,this.platform.getRightBounds().width,this.platform.getRightBounds().height);

			pencil.setColor(Color.MAGENTA);
			pencil.drawRect(this.platform.getTopBounds().x,this.platform.getTopBounds().y,this.platform.getTopBounds().width,this.platform.getTopBounds().height);
			pencil.drawRect(this.platform.getBottomBounds().x,this.platform.getBottomBounds().y,this.platform.getBottomBounds().width,this.platform.getBottomBounds().height);
		}
	}
}