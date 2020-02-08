import java.awt.*;

public class Bob {

	private MBob bob;

	public Bob(MBob bob0){
		this.bob = bob0;
	}

	public void draw(Graphics2D pinceau){
		pinceau.drawImage(this.bob.getImage(), this.bob.getX(), this.bob.getY()-this.bob.getHeight(), this.bob.getWidth(), this.bob.getHeight(), null);
		pinceau.setColor(Color.CYAN);
		pinceau.drawRect( this.bob.getX(), this.bob.getY()-this.bob.getHeight(), this.bob.getWidth(), this.bob.getHeight());
		pinceau.setColor(Color.RED);
		pinceau.drawRect(this.bob.getBounds().x,this.bob.getBounds().y,this.bob.getBounds().width,this.bob.getBounds().height);
		pinceau.setColor(Color.GREEN);
		pinceau.drawRect(this.bob.getLeftBounds().x,this.bob.getLeftBounds().y,this.bob.getLeftBounds().width,this.bob.getLeftBounds().height);
		pinceau.setColor(Color.GREEN);
		pinceau.drawRect(this.bob.getRightBounds().x,this.bob.getRightBounds().y,this.bob.getRightBounds().width,this.bob.getRightBounds().height);
	}
}