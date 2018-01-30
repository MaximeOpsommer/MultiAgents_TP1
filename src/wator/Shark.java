package wator;

import core.Agent;

public class Shark extends AquaticAnimal {
	
	private int sharkStarveTime;

	public Shark(final WatorEnvironment env, final int line, final int column){
		super(env, line, column, ((WatorConfigs) env.getConfigs()).getSharkBreedTime());
		this.sharkStarveTime = ((WatorConfigs) (env.getConfigs())).getSharkStarveTime();
	}

	public void decide() {
		
	}

	protected void eat() {
		
	}
	
}
