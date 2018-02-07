package hunter;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environment;

public class Hunter extends Agent {

	private List<Integer> targetCells = new ArrayList<Integer>();
	private int currentDistance;
	
	public Hunter(Environment env, int line, int column, int currentDistance) {
		super(env, line, column);
		this.currentDistance = currentDistance;
	}

	@Override
	public void decide() {
		if(currentDistance > 0) {			
			refreshVoisinsLibres(getGrid());
			if(!targetCells.isEmpty()) {
				int random = targetCells.get(env.getRandom().nextInt(targetCells.size()));
				verticalDirection = random/3 - 1;
				horizontalDirection = random%3 - 1;
				if(isTorus) {
					verticalDirection = Math.floorMod(verticalDirection, getGrid().length);
					horizontalDirection = Math.floorMod(horizontalDirection, getGrid()[0].length);
				}
				int newDistance = getGrid()[line+verticalDirection][column+horizontalDirection];
				env.moveAgent(column, line, verticalDirection, horizontalDirection, HunterConstants.HUNTER);
				((HunterEnvironment) env).oldHunterPos(line, column, currentDistance);
				((HunterEnvironment) env).updateHunterKey(line, column, line+verticalDirection, column+horizontalDirection);
				//((HunterEnvironment) env).availableCellMove(line, column, line+verticalDirection, column+horizontalDirection);
				column += horizontalDirection;
				line += verticalDirection;
				currentDistance = newDistance;
			}
		} else {
			//System.exit(0);
		}
		
	}

	@Override
	protected void move() {
		// TODO Auto-generated method stub
		
	}
	
	protected void refreshVoisinsLibres(int[][] grid) {
		int min = -1;
		targetCells.clear();
		int height = grid.length;
		int width = grid[0].length;
		
		// TORUS
		if(env.getConfigs().isTorus()) {
			// HAUT (1)
			if(grid[Math.floorMod(line-1, height)][column] >= 0) {
				if(min == -1) {
					targetCells.clear();
					min = grid[Math.floorMod(line-1, height)][column];
					targetCells.add(1);
				}
				if (grid[Math.floorMod(line-1, height)][column] <= min) {
					if(grid[Math.floorMod(line-1, height)][column] < min) {						
						targetCells.clear();
						min = grid[Math.floorMod(line-1, height)][column];
					}
					targetCells.add(1);
				}
			}
			// GAUCHE (3)
			if(grid[line][Math.floorMod(column-1, width)] >= 0) {
				if(min == -1) {
					targetCells.clear();
					min = grid[line][Math.floorMod(column-1, width)];
					targetCells.add(3);
				} else if(grid[line][Math.floorMod(column-1, width)] <= min) {
					if(grid[line][Math.floorMod(column-1, width)] < min) {						
						targetCells.clear();
						min = grid[line][Math.floorMod(column-1, width)];
					}
					targetCells.add(3);
				}
			}
			// DROITE (5)
			if(grid[line][Math.floorMod(column+1, width)] >= 0) {
				if(min == -1) {
					targetCells.clear();
					min = grid[line][Math.floorMod(column+1, width)];
					targetCells.add(5);
				} else if(grid[line][Math.floorMod(column+1, width)] <= min) {
					if(grid[line][Math.floorMod(column+1, width)] < min) {						
						targetCells.clear();
						min = grid[line][Math.floorMod(column+1, width)];
					}
					targetCells.add(5);
				}
			}
			// BAS (7)
			if(grid[Math.floorMod(line+1, height)][column] >= 0) {
				if(min == -1) {
					targetCells.clear();
					min = grid[Math.floorMod(line+1, height)][column];
					targetCells.add(7);
				} else if(grid[Math.floorMod(line+1, height)][column] <= min) {
					if(grid[Math.floorMod(line+1, height)][column] < min) {						
						targetCells.clear();
						min = grid[Math.floorMod(line+1, height)][column];
					}
					targetCells.add(7);
				}
			}
		}
		
		// NOT TORUS
		else {
			// HAUT (1)
			if(line > 0 && grid[line-1][column] >= 0) {
				if(min == -1) {
					targetCells.clear();
					min = grid[line-1][column];
					targetCells.add(1);
				}else if(grid[line-1][column] <= min) {
					if(grid[line-1][column] < min) {						
						targetCells.clear();
						min = grid[line-1][column];
					}
					targetCells.add(1);
				}
			}
			// GAUCHE (3)
			if(column > 0 && grid[line][column-1] >= 0) {
				if(min == -1) {
					targetCells.clear();
					min = grid[line][column-1];
					targetCells.add(3);
				}else if(grid[line][column-1] <= min) {
					if(grid[line][column-1] < min) {						
						targetCells.clear();
						min = grid[line][column-1];
					}
					targetCells.add(3);
				}
			}
			// DROITE (5)
			if(column < width-1 && grid[line][column+1] >= 0) {
				if(min == -1) {
					targetCells.clear();
					min = grid[line][column+1];
					targetCells.add(5);
				} else if(grid[line][column+1] <= min) {
					if(grid[line][column+1] < min) {						
						targetCells.clear();
						min = grid[line][column+1];
					}
					targetCells.add(5);
				}
			}
			// BAS (7)
			if(line < height-1 && grid[line+1][column] >= 0) {
				if(min == - 1) {
					targetCells.clear();
					min = grid[line+1][column];
					targetCells.add(7);
				}else if(grid[line+1][column] <= min) {
					if(grid[line+1][column] < min) {						
						targetCells.clear();
						min = grid[line+1][column];
					}
					targetCells.add(7);
				}
			}
		}
	}

}
