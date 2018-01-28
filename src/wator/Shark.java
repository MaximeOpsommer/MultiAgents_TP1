package wator;

import core.Agent;

public class Shark extends Agent {
	
	private int sharkBreedTime;
	private int sharkStarveTime;

	public Shark(final WatorEnvironment env, final int line, final int column){
		super(env, line, column);
		
		this.sharkBreedTime = ((WatorConfigs) (env.getConfigs())).getFishBreedTime();
		this.sharkStarveTime = ((WatorConfigs) (env.getConfigs())).getFishBreedTime();
	}

	@Override
	public void decide() {
		// TODO Auto-generated method stub
		
	}

	public int getSharkBreedTime() {
		return sharkBreedTime;
	}

	public void setSharkBreedTime(int sharkBreedTime) {
		this.sharkBreedTime = sharkBreedTime;
	}

	public int getSharkStarveTime() {
		return sharkStarveTime;
	}

	public void setSharkStarveTime(int sharkStarveTime) {
		this.sharkStarveTime = sharkStarveTime;
	}

}
