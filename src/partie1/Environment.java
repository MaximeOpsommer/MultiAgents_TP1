package partie1;

import java.util.ArrayList;
import java.util.List;

public class Environment {
	
	private final int[][] grid;
	private final int n_billes;
	private List<Agent> agents = new ArrayList<Agent>();
	
	public Environment(final int height, final int width, final int n) {
		grid = new int[height][width];
		n_billes = n;
		init();
	}
	
	private void init() {
		int total = grid.length * grid[0].length;
		int reste = n_billes;
		int rand;
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[0].length; x++) {
				rand = (int) Math.random() * total;
				if(rand < reste) {
					grid[y][x] = 1;
					reste--;
					agents.add(new Agent(this, new Coord(x, y)));
				} else {
					grid[y][x] = 0;
				}
				total--;
			}
		}
	}
	
	public boolean emptyCell(final int posX, final int posY) {
		return grid[posY][posX] == 1;
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public List<Agent> getAgents() {
		return agents;
	}

}
