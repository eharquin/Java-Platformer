import java.util.*;

public class ControlerTick extends TimerTask {

	private GameEngine d;

	public ControlerTick(GameEngine d0){
		this.d = d0;

		Timer timer = new Timer();
        timer.schedule(this ,25, 25);
	}

	@Override
	public void run() {
		d.tick();
	}
}