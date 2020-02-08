import java.awt.*;

public class VBackground implements VGameObject{

	private MBackground background;

	public VBackground(MBackground background0){
		this.background = background0;
	}

	@Override
	public void draw(Graphics2D pencil){
		pencil.setColor(Color.BLUE);
		pencil.fillRect(this.background.getX(), 0, this.background.getWidth(), this.background.getHeight());

		pencil.setColor(Color.BLACK);
		for(int i=0; i<20; i++){
			pencil.fillRect(this.background.getX()+2*i*50, 400, 50, 50);
		}
	}
}