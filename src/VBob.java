import java.awt.*;

public class VBob implements VGameObject{

	private MBob bob;

	public VBob(MBob bob0){
		this.bob = bob0;
	}
	
	@Override
	public void draw(Graphics2D pencil){
		pencil.drawImage(this.bob.getImage(), this.bob.getX(), this.bob.getY()-this.bob.getHeight(), this.bob.getWidth(), this.bob.getHeight(), null);
		pencil.setColor(Color.CYAN);
		pencil.drawRect( this.bob.getX(), this.bob.getY()-this.bob.getHeight(), this.bob.getWidth(), this.bob.getHeight());
		pencil.setColor(Color.RED);
		pencil.drawRect(this.bob.getBounds().x,this.bob.getBounds().y,this.bob.getBounds().width,this.bob.getBounds().height);
		pencil.setColor(Color.GREEN);
		pencil.drawRect(this.bob.getLeftBounds().x,this.bob.getLeftBounds().y,this.bob.getLeftBounds().width,this.bob.getLeftBounds().height);
		pencil.setColor(Color.GREEN);
		pencil.drawRect(this.bob.getRightBounds().x,this.bob.getRightBounds().y,this.bob.getRightBounds().width,this.bob.getRightBounds().height);
	}
}