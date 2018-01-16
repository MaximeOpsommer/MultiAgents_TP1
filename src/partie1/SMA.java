package partie1;

public class SMA {

	private Environment env;
	
	public SMA(Environment env) {
		this.env = env;
	}
	
	public void run() {
		while(true) {
			for(Agent agent : env.getAgents()) {
				agent.decide();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
