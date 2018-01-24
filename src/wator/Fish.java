package wator;

import core.Agent;
import core.Coord;

public class Fish extends Agent{
	
	private int fishBreedTime;
	private WatorEnvironment env;
	private Coord coords;
	
	public Fish(final WatorEnvironment env, final Coord coords){
		this.setEnv(env);
		this.setCoords(coords);
		this.fishBreedTime = ((WatorConfigs) (env.getConfigs())).getFishBreedTime();
	}

	@Override
	public void decide() {
		// TODO Auto-generated method stub
		
	}

	public int getFishBreedTime() {
		return fishBreedTime;
	}

	public void setFishBreedTime(int fishBreedTime) {
		this.fishBreedTime = fishBreedTime;
	}

	public WatorEnvironment getEnv() {
		return env;
	}

	public void setEnv(WatorEnvironment env) {
		this.env = env;
	}

	public Coord getCoords() {
		return coords;
	}

	public void setCoords(Coord coords) {
		this.coords = coords;
	}

}
