package wator;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environment;

public class WatorEnvironment extends Environment {
	
	// 0 = rien
	// 1 = jaune : Fish venant de naître
	// 2 = vert : Fish
	// 3 = rose : Shark venant de naître
	// 4 = rouge : Shark
	
	private final int fishNumber;
	private List<Fish> fishs = new ArrayList<Fish>();
	private final int sharkNumber;
	private List<Shark> sharks = new ArrayList<Shark>();
	
	public WatorEnvironment(){
		configs = new WatorConfigs();
		fishNumber = ((WatorConfigs) (configs)).getFishNumber();
		sharkNumber = ((WatorConfigs) (configs)).getSharkNumber();
		System.out.println(fishNumber);
		System.out.println(sharkNumber);
		init();
	}
	
	protected void init() {
		super.init();
		int total = grid.length * grid[0].length;
		int restAll = fishNumber+sharkNumber;
		int restFish = fishNumber;
		int rand;
		for(int line = 0; line < grid.length; line++){
			for(int column = 0; column < grid[0].length; column++){
				rand = random.nextInt(total);
				if(rand < restFish){
					grid[line][column] = 2;
					//TODO Ajouter un Fish
					restFish--;
					restAll--;
				}else if(rand < restAll){
					//TODO Ajouter un Shark
					grid[line][column] = 4;
					restAll--;
				}else{
					grid[line][column] = 0;
				}
				total--;
			}
		}
		
	}
	
	public List<? extends Agent> getAllAgents() {
		List<Agent> res = new ArrayList<Agent>();
		res.addAll(fishs);
		res.addAll(sharks);
		return res;
	}
	
	public List<Fish> getFishs(){
		return fishs;
	}
	
	public List<Shark> getSharks(){
		return sharks;
	}

}
