package partie1;

import java.util.ArrayList;
import java.util.List;

public class Environment {
	
	private final int[][] grid;
	private final int n_billes;
	private List<Agent> agents = new ArrayList<Agent>();
	private final Configs configs = new Configs();
	
	public Environment(final int height, final int width, final int n) {
		grid = new int[height][width];
		n_billes = n;
		init();
	}
	
	private void init() {
		int total = grid.length * grid[0].length;
		int reste = n_billes;
		int rand;
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				rand = (int) (Math.random() * total);
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

	public void moveAgent(int oldColumn, int oldLine, Direction direction, int collision) {
		int value = 0;
		if((value = grid[oldLine][oldColumn]) > 0) {
			grid[oldLine][oldColumn] = 0;
			grid[Math.floorMod(oldLine + direction.getVerticalMove(), grid.length)][Math.floorMod(oldColumn + direction.getHorizontalMove(), grid[0].length)] = collision > value ? collision : value;
		} else {
			System.err.println("An error occured during move process");
		}
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
