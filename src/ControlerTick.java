import java.util.*;

public class ControlerTick extends TimerTask {

	private MoteurJeu d;

	public ControlerTick(MoteurJeu d0){
		this.d = d0;

		Timer timer = new Timer();
        timer.schedule(this ,20, 20);
	}

	@Override
	public void run() {
		d.tick();
	}
}