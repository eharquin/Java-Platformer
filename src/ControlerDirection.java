import java.awt.event.*;

public class ControlerDirection extends KeyAdapter {

	private GameEngine d;

	public ControlerDirection(GameEngine d0){
		this.d = d0;
	}

	public void keyPressed(KeyEvent e){

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            d.setMove(GameEngine.LEFT);
        } 
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            d.setMove(GameEngine.RIGHT);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            d.setMove(GameEngine.DOWN);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            d.setMove(GameEngine.UP);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            d.setMove(GameEngine.NONE);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            d.setMove(GameEngine.NONE);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            d.setMove(GameEngine.NONE);
        }  
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            d.setMove(GameEngine.NONE);
        }    
    }
}