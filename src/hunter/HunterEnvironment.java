package hunter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.Agent;
import core.Environment;
import particules.Particle;

public class HunterEnvironment extends Environment {

	private final int diggerNumber;
	private Map<Integer, Wall> walls = new HashMap<Integer, Wall>();
	private List<Digger> diggers = new ArrayList<Digger>();
	
	public HunterEnvironment() {
		configs = new HunterConfigs();
		diggerNumber = ((HunterConfigs) configs).getDiggerNumber();
		init();
	}
	
	protected void init() {
		super.init();
		int height = grid.length;
		int width = grid[0].length;
		int total = height * width;
		int reste = diggerNumber;
		int rand;
		for(int line = 0; line < height; line++) {
			for(int column = 0; column < width; column++) {
				rand = random.nextInt(total);
				if(rand < reste) {
					grid[line][column] = HunterConstants.DIGGER;
					reste--;
					diggers.add(new Digger(this, line, column));
				} else {
					grid[line][column] = HunterConstants.WALL;
					walls.put((line*width)+column, new Wall(this, line, column));
				}
				total--;
			}
		}
	}
	
	public Map<Integer, Wall> getWalls() {
		return walls;
	}
	
	public void removeWall(final int line, final int column) {
		walls.remove((line*grid[0].length)+column);
	}
	
	public List<Digger> getDiggers() {
		return diggers;
	}
	
	@Override
	public List<? extends Agent> getAllAgents() {
		// TODO Auto-generated method stub
		return null;
	}

}
