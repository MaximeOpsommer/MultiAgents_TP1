package wator;

import core.Agent;
import core.Coord;

public class Shark extends Agent {
	
	private WatorEnvironment env;
	private int sharkBreedTime;
	private int sharkStarveTime;
	private Coord coords;

	public Shark(final WatorEnvironment env, final Coord coords){
		this.setEnv(env);
		this.setCoords(coords);
		
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

	public Coord getCoords() {
		return coords;
	}

	public void setCoords(Coord coords) {
		this.coords = coords;
	}

	public WatorEnvironment getEnv() {
		return env;
	}

	public void setEnv(WatorEnvironment env) {
		this.env = env;
	}

}
