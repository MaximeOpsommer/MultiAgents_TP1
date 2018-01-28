package wator;

import core.Agent;

public class Fish extends Agent{
	
	private int fishBreedTime;
	
	public Fish(final WatorEnvironment env, final int line, final int column){
		super(env, line, column);
		this.fishBreedTime = getConfigs().getFishBreedTime();
	}

	@Override
	public void decide() {
		// TODO Alex : Write method
		
	}

	public int getFishBreedTime() {
		return fishBreedTime;
	}

	public WatorConfigs getConfigs() {
		return (WatorConfigs) env.getConfigs();
	}

	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}

}
