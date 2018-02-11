package wator;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environment;

public class WatorEnvironment extends Environment {
	
	// 0 = rien
	// 1 = jaune : Fish venant de na�tre
	// 2 = vert : Fish
	// 3 = rose : Shark venant de na�tre
	// 4 = rouge : Shark
	
	private final int fishNumber;
	private List<Fish> fishs = new ArrayList<Fish>();
	private final int sharkNumber;
	private List<Shark> sharks = new ArrayList<Shark>();
	
	public WatorEnvironment() {
		configs = new WatorConfigs();
		fishNumber = ((WatorConfigs) (configs)).getFishNumber();
		sharkNumber = ((WatorConfigs) (configs)).getSharkNumber();
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
					fishs.add(new Fish(this, line, column));
					restFish--;
					restAll--;
				}else if(rand < restAll){
					sharks.add(new Shark(this, line, column));
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
	
	public void addFish(int line, int column) {
		grid[line][column] = WatorConstants.BABY_FISH;
		fishs.add(new Fish(this, line, column));
	}
	
	public void addShark(int line, int column) {
		grid[line][column] = WatorConstants.BABY_SHARK;
		sharks.add(new Shark(this, line, column));
	}
	
	public List<Shark> getSharks(){
		return sharks;
	}
	
	public void grow(int line, int column, int value) {
		grid[line][column] = value;
	}
	
	public void removeFish(int line, int column) {
		for(Fish fish : fishs) {
			if(fish.getLine() == line && fish.getColumn() == column) {
				fishs.remove(fish);
				break;
			}
		}
		grid[line][column] = 0;
	}
	
	public void removeShark(int line, int column) {
		for(Shark shark : sharks) {
			if(shark.getLine() == line && shark.getColumn() == column) {
				sharks.remove(shark);
				break;
			}
		}
		grid[line][column] = 0;
	}

}
