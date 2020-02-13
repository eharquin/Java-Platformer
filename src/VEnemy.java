import java.awt.*;

public class VEnemy implements VGameObject{

	private MEnemy enemy;

	public VEnemy(MEnemy enemy0){
		this.enemy = enemy0;
	}
	
	@Override
	public void draw(Graphics2D pencil){
		pencil.drawImage(this.enemy.getImage(), this.enemy.getX(), this.enemy.getY()-this.enemy.getHeight(), this.enemy.getWidth(), this.enemy.getHeight(), null);

		if(this.enemy.isCollisionVisible()){
			pencil.setColor(Color.GREEN);
			pencil.drawRect(this.enemy.getLeftBounds().x,this.enemy.getLeftBounds().y,this.enemy.getLeftBounds().width,this.enemy.getLeftBounds().height);
			pencil.drawRect(this.enemy.getRightBounds().x,this.enemy.getRightBounds().y,this.enemy.getRightBounds().width,this.enemy.getRightBounds().height);

			pencil.setColor(Color.MAGENTA);
			pencil.drawRect(this.enemy.getTopBounds().x,this.enemy.getTopBounds().y,this.enemy.getTopBounds().width,this.enemy.getTopBounds().height);
			pencil.drawRect(this.enemy.getBottomBounds().x,this.enemy.getBottomBounds().y,this.enemy.getBottomBounds().width,this.enemy.getBottomBounds().height);
		}
	}
}