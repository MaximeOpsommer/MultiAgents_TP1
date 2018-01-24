package partie1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {
	
	private final int[][] grid;
	private final int n_agent;
	private List<Agent> agents = new ArrayList<Agent>();
	private final Configs configs = new Configs();
	private Random random;
	
	public Environment() {
		grid = new int[configs.getGridHeight()][configs.getGridWidth()];
		n_agent = configs.getAgentNumber();
		random = configs.getSeed() == 0 ? new Random() : new Random(configs.getSeed());
		init();
	}
	
	private void init() {
		int total = grid.length * grid[0].length;
		int reste = n_agent;
		int rand;
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				rand = random.nextInt(total);
				if(rand < reste) {
					grid[line][column] = 1;
					reste--;
					agents.add(new Agent(this, new Coord(column, line)));
				} else {
					grid[line][column] = 0;
				}
				total--;
			}
		}
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public List<Agent> getAgents() {
		return agents;
	}
	
	public Configs getConfigs() {
		return configs;
	}
	
	public Agent getAgent(int column, int line) throws AgentNotFoundException {
		for(Agent agent : agents) {
			Coord coords = agent.getCoords();
			if(coords.getColumn() == column && coords.getLine() == line) {
				return agent;
			}
		}
		throw new AgentNotFoundException();
	}
	
	public Random getRandom() {
		return random;
	}

	public void moveAgent(int oldColumn, int oldLine, Direction direction, int collision) {
		int value = 0;
		if((value = grid[oldLine][oldColumn]) > 0) {
			grid[oldLine][oldColumn] = 0;
			grid[Math.floorMod(oldLine + direction.getVerticalMove(), grid.length)][Math.floorMod(oldColumn + direction.getHorizontalMove(), grid[0].length)] = collision > value ? collision : value;
		} else {
			System.err.println("An error occured during move process");
		}
	}
	
	public void collision(Coord c1, Coord c2) {
		grid[c1.getLine()][c1.getColumn()] = Constants.MARBLE_COLLISION;
		grid[c2.getLine()][c2.getColumn()] = Constants.MARBLE_COLLISION;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				builder.append(grid[line][column]);
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
}
