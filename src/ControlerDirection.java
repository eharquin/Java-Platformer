import java.awt.event.*;

public class ControlerDirection extends KeyAdapter {

	private MoteurJeu d;

	public ControlerDirection(MoteurJeu d0){
		this.d = d0;
	}

	public void keyPressed(KeyEvent e){

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            d.setMove(MoteurJeu.LEFT);
        } 
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            d.setMove(MoteurJeu.RIGHT);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            d.setMove(MoteurJeu.DOWN);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            d.setMove(MoteurJeu.UP);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            d.setMove(MoteurJeu.NONE);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            d.setMove(MoteurJeu.NONE);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            d.setMove(MoteurJeu.NONE);
        }  
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            d.setMove(MoteurJeu.NONE);
        }    
    }
}