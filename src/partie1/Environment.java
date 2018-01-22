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
	
	public boolean emptyCell(final int column, final int line) {
		return grid[line][column] == 1;
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public List<Agent> getAgents() {
		return agents;
	}

	public void moveAgent(int oldColumn, int oldLine, Direction direction) {
		if(grid[oldLine][oldColumn] == 1) {
			grid[oldLine][oldColumn] = 0;
			grid[oldLine + direction.getVerticalMove()][oldColumn + direction.getHorizontalMove()] = 1;
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
