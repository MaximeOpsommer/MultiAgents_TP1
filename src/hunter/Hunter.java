package hunter;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environment;

public class Hunter extends Agent {

	private List<Integer> targetCells = new ArrayList<Integer>();
	private int defenderTime = 0;
	private int tick = 0;
	private final int speed;
	
	public Hunter(Environment env, int line, int column, int currentDistance) {
		super(env, line, column);
		speed = ((HunterConfigs) getConfigs()).getHunterSpeed();
	}

	@Override
	public void decide() {
		if(tick++ % speed == 0) {
			//if(((HunterEnvironment) env).getDistances()[line][column] > 1) {
				if(defenderTime > 0) {
					getVoisinsMax(getGrid(), ((HunterEnvironment) env).getDistances());
					defenderTime--;
				} else {				
					getVoisinsMin(getGrid(), ((HunterEnvironment) env).getDistances());
				}
				if(!targetCells.isEmpty()) {
					int random = targetCells.get(env.getRandom().nextInt(targetCells.size()));
					verticalDirection = random/3 - 1;
					horizontalDirection = random%3 - 1;
					if(isTorus) {
						verticalDirection = Math.floorMod(verticalDirection, getGrid().length);
						horizontalDirection = Math.floorMod(horizontalDirection, getGrid()[0].length);
					}
					if(defenderTime > 0) {
						env.moveAgent(column, line, verticalDirection, horizontalDirection, HunterConstants.FLEEING_HUNTER);
					} else {						
						env.moveAgent(column, line, verticalDirection, horizontalDirection, HunterConstants.HUNTER);
					}
					column = Math.floorMod(column + horizontalDirection, getGrid()[0].length);
					line = Math.floorMod(line + verticalDirection, getGrid().length);
					Avatar avatar = ((HunterEnvironment) env).getAvatar();
					if(avatar.getLine() == line && avatar.getColumn() == column) {
						((HunterEnvironment) env).defeat();
					}
				}
			//} else {
				//
			//}
		}
	}

	@Override
	protected void move() {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unused")
	protected void getVoisinsMin(int[][] grid, int[][] distances) {
		Integer min = null;
		targetCells.clear();
		int height = grid.length;
		int width = grid[0].length;
		
		// TORUS
		if(env.getConfigs().isTorus()) {
			// HAUT (1)
			if(grid[Math.floorMod(line-1, height)][column] <= HunterConstants.AVATAR) {
				if(min == null) {
					targetCells.clear();
					min = distances[Math.floorMod(line-1, height)][column];
					targetCells.add(1);
				}
				if (distances[Math.floorMod(line-1, height)][column] <= min) {
					if(distances[Math.floorMod(line-1, height)][column] < min) {						
						targetCells.clear();
						min = distances[Math.floorMod(line-1, height)][column];
					}
					targetCells.add(1);
				}
			}
			// GAUCHE (3)
			if(grid[line][Math.floorMod(column-1, width)] <= HunterConstants.AVATAR) {
				if(min == null) {
					targetCells.clear();
					min = distances[line][Math.floorMod(column-1, width)];
					targetCells.add(3);
				} else if(distances[line][Math.floorMod(column-1, width)] <= min) {
					if(distances[line][Math.floorMod(column-1, width)] < min) {						
						targetCells.clear();
						min = distances[line][Math.floorMod(column-1, width)];
					}
					targetCells.add(3);
				}
			}
			// DROITE (5)
			if(grid[line][Math.floorMod(column+1, width)] <= HunterConstants.AVATAR) {
				if(min == null) {
					targetCells.clear();
					min = distances[line][Math.floorMod(column+1, width)];
					targetCells.add(5);
				} else if(distances[line][Math.floorMod(column+1, width)] <= min) {
					if(distances[line][Math.floorMod(column+1, width)] < min) {						
						targetCells.clear();
						min = distances[line][Math.floorMod(column+1, width)];
					}
					targetCells.add(5);
				}
			}
			// BAS (7)
			if(grid[Math.floorMod(line+1, height)][column] <= HunterConstants.AVATAR) {
				if(min == null) {
					targetCells.clear();
					min = distances[Math.floorMod(line+1, height)][column];
					targetCells.add(7);
				} else if(distances[Math.floorMod(line+1, height)][column] <= min) {
					if(distances[Math.floorMod(line+1, height)][column] < min) {						
						targetCells.clear();
						min = distances[Math.floorMod(line+1, height)][column];
					}
					targetCells.add(7);
				}
			}
		}
		
		// NOT TORUS
		else {
			// HAUT (1)
			if(line > 0 && grid[line-1][column] <= HunterConstants.AVATAR) {
				if(min == null) {
					targetCells.clear();
					min = distances[line-1][column];
					targetCells.add(1);
				}else if(distances[line-1][column] <= min) {
					if(distances[line-1][column] < min) {						
						targetCells.clear();
						min = distances[line-1][column];
					}
					targetCells.add(1);
				}
			}
			// GAUCHE (3)
			if(column > 0 && grid[line][column-1] <= HunterConstants.AVATAR) {
				if(min == null) {
					targetCells.clear();
					min = distances[line][column-1];
					targetCells.add(3);
				}else if(distances[line][column-1] <= min) {
					if(distances[line][column-1] < min) {						
						targetCells.clear();
						min = distances[line][column-1];
					}
					targetCells.add(3);
				}
			}
			// DROITE (5)
			if(column < width-1 && grid[line][column+1] <= HunterConstants.AVATAR) {
				if(min == null) {
					targetCells.clear();
					min = distances[line][column+1];
					targetCells.add(5);
				} else if(distances[line][column+1] <= min) {
					if(distances[line][column+1] < min) {						
						targetCells.clear();
						min = distances[line][column+1];
					}
					targetCells.add(5);
				}
			}
			// BAS (7)
			if(line < height-1 && grid[line+1][column] <= HunterConstants.AVATAR) {
				if(min == null) {
					targetCells.clear();
					min = distances[line+1][column];
					targetCells.add(7);
				}else if(distances[line+1][column] <= min) {
					if(distances[line+1][column] < min) {						
						targetCells.clear();
						min = distances[line+1][column];
					}
					targetCells.add(7);
				}
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	protected void getVoisinsMax(int[][] grid, int[][] distances) {
		Integer max = null;
		targetCells.clear();
		int height = grid.length;
		int width = grid[0].length;
		
		// TORUS
		if(env.getConfigs().isTorus()) {
			// HAUT (1)
			if(grid[Math.floorMod(line-1, height)][column] <= HunterConstants.AVATAR) {
				if(max == null) {
					targetCells.clear();
					max = distances[Math.floorMod(line-1, height)][column];
					targetCells.add(1);
				}
				if (distances[Math.floorMod(line-1, height)][column] >= max) {
					if(distances[Math.floorMod(line-1, height)][column] > max) {						
						targetCells.clear();
						max = distances[Math.floorMod(line-1, height)][column];
					}
					targetCells.add(1);
				}
			}
			// GAUCHE (3)
			if(grid[line][Math.floorMod(column-1, width)] <= HunterConstants.AVATAR) {
				if(max == null) {
					targetCells.clear();
					max = distances[line][Math.floorMod(column-1, width)];
					targetCells.add(3);
				} else if(distances[line][Math.floorMod(column-1, width)] >= max) {
					if(distances[line][Math.floorMod(column-1, width)] > max) {						
						targetCells.clear();
						max = distances[line][Math.floorMod(column-1, width)];
					}
					targetCells.add(3);
				}
			}
			// DROITE (5)
			if(grid[line][Math.floorMod(column+1, width)] <= HunterConstants.AVATAR) {
				if(max == null) {
					targetCells.clear();
					max = distances[line][Math.floorMod(column+1, width)];
					targetCells.add(5);
				} else if(distances[line][Math.floorMod(column+1, width)] >= max) {
					if(distances[line][Math.floorMod(column+1, width)] > max) {						
						targetCells.clear();
						max = distances[line][Math.floorMod(column+1, width)];
					}
					targetCells.add(5);
				}
			}
			// BAS (7)
			if(grid[Math.floorMod(line+1, height)][column] <= HunterConstants.AVATAR) {
				if(max == null) {
					targetCells.clear();
					max = distances[Math.floorMod(line+1, height)][column];
					targetCells.add(7);
				} else if(distances[Math.floorMod(line+1, height)][column] >= max) {
					if(distances[Math.floorMod(line+1, height)][column] > max) {						
						targetCells.clear();
						max = distances[Math.floorMod(line+1, height)][column];
					}
					targetCells.add(7);
				}
			}
		}
		
		// NOT TORUS
		else {
			// HAUT (1)
			if(line > 0 && grid[line-1][column] <= HunterConstants.AVATAR) {
				if(max == null) {
					targetCells.clear();
					max = distances[line-1][column];
					targetCells.add(1);
				}else if(distances[line-1][column] >= max) {
					if(distances[line-1][column] > max) {						
						targetCells.clear();
						max = distances[line-1][column];
					}
					targetCells.add(1);
				}
			}
			// GAUCHE (3)
			if(column > 0 && grid[line][column-1] <= HunterConstants.AVATAR) {
				if(max == null) {
					targetCells.clear();
					max = distances[line][column-1];
					targetCells.add(3);
				}else if(distances[line][column-1] >= max) {
					if(distances[line][column-1] > max) {						
						targetCells.clear();
						max = distances[line][column-1];
					}
					targetCells.add(3);
				}
			}
			// DROITE (5)
			if(column < width-1 && grid[line][column+1] <= HunterConstants.AVATAR) {
				if(max == null) {
					targetCells.clear();
					max = distances[line][column+1];
					targetCells.add(5);
				} else if(distances[line][column+1] >= max) {
					if(distances[line][column+1] > max) {						
						targetCells.clear();
						max = distances[line][column+1];
					}
					targetCells.add(5);
				}
			}
			// BAS (7)
			if(line < height-1 && grid[line+1][column] <= HunterConstants.AVATAR) {
				if(max == null) {
					targetCells.clear();
					max = distances[line+1][column];
					targetCells.add(7);
				}else if(distances[line+1][column] >= max) {
					if(distances[line+1][column] > max) {						
						targetCells.clear();
						max = distances[line+1][column];
					}
					targetCells.add(7);
				}
			}
		}
	}
	
	public void defenderActivated() {
		defenderTime += ((HunterConfigs) getConfigs()).getDefenderEffectDuration();
	}

}
