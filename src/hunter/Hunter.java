package hunter;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environment;

public class Hunter extends Agent {

	private List<Integer> targetCells;
	
	public Hunter(Environment env, int line, int column) {
		super(env, line, column);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decide() {
		refreshVoisinsLibres(getGrid());
		if(!targetCells.isEmpty()) {
			int random = targetCells.get(env.getRandom().nextInt(targetCells.size()));
			verticalDirection = random/3 - 1;
			horizontalDirection = random%3 - 1;
			if(isTorus) {
				verticalDirection = Math.floorMod(verticalDirection, getGrid().length);
				horizontalDirection = Math.floorMod(horizontalDirection, getGrid()[0].length);
			}
			int oldDistance = getGrid()[line+verticalDirection][column+horizontalDirection] + 1;
			env.moveAgent(column, line, verticalDirection, horizontalDirection, HunterConstants.HUNTER);
			((HunterEnvironment) env).oldHunterPos(line, column, oldDistance);
			column = Math.floorMod(column + horizontalDirection, getGrid()[0].length);
			line = Math.floorMod(line + verticalDirection, getGrid().length);
		}
		
	}

	@Override
	protected void move() {
		// TODO Auto-generated method stub
		
	}
	
	protected void refreshVoisinsLibres(int[][] grid) {
		int min = -1;
		targetCells = new ArrayList<Integer>();
		int height = grid.length;
		int width = grid[0].length;
		
		// TORUS
		if(env.getConfigs().isTorus()) {
			// HAUT (1)
			if(grid[Math.floorMod(line-1, height)][column] >= 0) {
				if (grid[Math.floorMod(line-1, height)][column] < min || min == -1) {
					targetCells.clear();
					min = grid[Math.floorMod(line-1, height)][column];
				}
				targetCells.add(1);
			}
			// GAUCHE (3)
			if(grid[line][Math.floorMod(column-1, width)] >= 0) {
				if(grid[line][Math.floorMod(column-1, width)] < min || min == -1) {
					targetCells.clear();
					min = grid[line][Math.floorMod(column-1, width)];
				}
				targetCells.add(3);
			}
			// DROITE (5)
			if(grid[line][Math.floorMod(column+1, width)] >= 0) {
				if(grid[line][Math.floorMod(column+1, width)] < min || min == -1) {
					targetCells.clear();
					min = grid[line][Math.floorMod(column+1, width)];
				}
				targetCells.add(5);
			}
			// BAS (7)
			if(grid[Math.floorMod(line+1, height)][column] >= 0) {
				if(grid[Math.floorMod(line+1, height)][column] < min || min == -1) {
					targetCells.clear();
					min = grid[Math.floorMod(line+1, height)][column];
				}
				targetCells.add(7);
			}
		}
		
		// NOT TORUS
		else {
			// HAUT (1)
			if(line > 0 && grid[line-1][column] >= 0) {
				if(grid[line-1][column] < min || min == -1) {
					targetCells.clear();
					min = grid[line-1][column];
				}
				targetCells.add(1);
			}
			// GAUCHE (3)
			if(column > 0 && grid[line][column-1] >= 0) {
				if(grid[line][column-1] < min || min == -1) {
					targetCells.clear();
					min = grid[line][column-1];
				}
				targetCells.add(3);
			}
			// DROITE (5)
			if(column < width-1 && grid[line][column+1] >= 0) {
				if(grid[line][column+1] < min || min == -1) {
					targetCells.clear();
					min = grid[line][column+1];
				}
				targetCells.add(5);
			}
			// BAS (7)
			if(line < height-1 && grid[line+1][column] >= 0) {
				if(grid[line+1][column] < min || min == -1) {
					targetCells.clear();
					min = grid[line+1][column];
				}
				targetCells.add(7);
			}
		}
		System.out.println(min);
	}

}
