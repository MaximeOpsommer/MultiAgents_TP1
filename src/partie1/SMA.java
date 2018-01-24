package partie1;

import java.util.Observable;

public class SMA extends Observable {

	private Environment env;
	private final int nbTicks;
	private int delay;
	
	public SMA(Environment env) {
		this.env = env;
		nbTicks = env.getConfigs().getNbTicks();
		delay = env.getConfigs().getDelay();
	}
	
	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers();
	}
	
	public void update() {
		notifyObservers();
	}
	
	public void run() {
		int tick = 0;
		while(nbTicks == 0 || tick < nbTicks) {
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(Agent agent : env.getAgents()) {
				agent.decide();
				update();
			}
			tick++;
		}
	}
	
}
