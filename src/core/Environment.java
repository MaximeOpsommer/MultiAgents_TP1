package core;

import java.util.List;
import java.util.Random;

public abstract class Environment {

	protected int[][] grid;
	protected Configs configs;
	protected Random random;
	
	public Configs getConfigs() {
		return configs;
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public Random getRandom() {
		return random;
	}
	
	public void moveAgent(int oldColumn, int oldLine, int verticalDirection, int horizontalDirection, int newValue) {
		grid[oldLine][oldColumn] = 0;
		grid[Math.floorMod(oldLine + verticalDirection, grid.length)][Math.floorMod(oldColumn + horizontalDirection, grid[0].length)] = newValue;
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
	
	protected void init() {
		grid = new int[configs.getGridHeight()][configs.getGridWidth()];
		random = configs.getSeed() == 0 ? new Random() : new Random(configs.getSeed());
	}
	
	public abstract List<? extends Agent> getAllAgents();
	
}
