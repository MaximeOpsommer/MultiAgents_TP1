package wator;

import core.Agent;
import core.Environment;

public abstract class AquaticAnimal extends Agent {

	protected int breedTime;
	
	public AquaticAnimal(Environment env, int line, int column, int breedTime) {
		super(env, line, column);
		this.breedTime = breedTime;
	}
	
	protected void move() {
		
	}
	
	protected void breed() {
		
	}

}
