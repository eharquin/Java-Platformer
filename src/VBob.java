import java.awt.*;

public class VBob implements VGameObject{

	private MBob bob;

	public VBob(MBob bob0){
		this.bob = bob0;
	}
	
	@Override
	public void draw(Graphics2D pencil){
		pencil.drawImage(this.bob.getImage(), this.bob.getX(), this.bob.getY()-this.bob.getHeight(), this.bob.getWidth(), this.bob.getHeight(), null);

		if(this.bob.isCollisionVisible()){
			pencil.setColor(Color.GREEN);
			pencil.drawRect(this.bob.getLeftBounds().x,this.bob.getLeftBounds().y,this.bob.getLeftBounds().width,this.bob.getLeftBounds().height);
			pencil.drawRect(this.bob.getRightBounds().x,this.bob.getRightBounds().y,this.bob.getRightBounds().width,this.bob.getRightBounds().height);

			pencil.setColor(Color.MAGENTA);
			pencil.drawRect(this.bob.getTopBounds().x,this.bob.getTopBounds().y,this.bob.getTopBounds().width,this.bob.getTopBounds().height);
			pencil.drawRect(this.bob.getBottomBounds().x,this.bob.getBottomBounds().y,this.bob.getBottomBounds().width,this.bob.getBottomBounds().height);
		}
	}
}