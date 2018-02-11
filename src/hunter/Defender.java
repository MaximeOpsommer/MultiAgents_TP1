package hunter;

import core.Agent;
import core.Environment;

public class Defender extends Agent {

	private int currentLife;
	
	public Defender(Environment env, int line, int column) {
		super(env, line, column);
		currentLife = ((HunterConfigs) getConfigs()).getDefenderLife();
	}

	@Override
	public void decide() {
		currentLife--;
		if(currentLife < 1) {
			((HunterEnvironment) env).defenderDeath(line, column);
		}
	}

	@Override
	protected void move() {
		// TODO Auto-generated method stub
		
	}

}
