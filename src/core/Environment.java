package core;

import java.util.Random;

public abstract class Environment {

	protected int[][] grid;
	protected Configs configs;
	protected Random random;
	
	public Environment() {
		grid = new int[configs.getGridHeight()][configs.getGridWidth()];
		random = configs.getSeed() == 0 ? new Random() : new Random(configs.getSeed());
		init();
	}
	
	public Configs getConfigs() {
		return configs;
	}
	
	public int[][] getGrid() {
		return grid;
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
	
	protected abstract void init();
	
}
