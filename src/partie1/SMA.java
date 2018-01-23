package partie1;

import java.util.Observable;

public class SMA extends Observable {

	private Environment env;
	
	public SMA(Environment env) {
		this.env = env;
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
		while(true) {
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(Agent agent : env.getAgents()) {
				agent.decide();
				update();
			}
		}
	}
	
}
