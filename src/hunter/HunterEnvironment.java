package hunter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.Agent;
import core.Environment;

public class HunterEnvironment extends Environment {
	
	private final int diggerNumber;
	private Map<Integer, Wall> walls = new HashMap<Integer, Wall>();
	private List<Digger> diggers = new ArrayList<Digger>();
	private Map<Integer, Hunter> hunters = new HashMap<Integer, Hunter>();
	private Avatar avatar;
	private List<Integer> availableCells = new ArrayList<Integer>();
	
	public HunterEnvironment() {
		configs = new HunterConfigs();
		diggerNumber = ((HunterConfigs) configs).getDiggerNumber();
		init();
		avatar = new Avatar(this, 0, 0);
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
				
				// TODO : Diggers
				/*rand = random.nextInt(total);
				if(rand < reste) {
					grid[line][column] = HunterConstants.DIGGER;
					reste--;
					diggers.add(new Digger(this, line, column));
				} else {
					grid[line][column] = HunterConstants.WALL;
					walls.put((line*width)+column, new Wall(this, line, column));
				}
				total--;*/
				grid[line][column] = 0;
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
	
	public void removeDiggers() {
		while(!diggers.isEmpty()) {
			Digger digger = diggers.remove(0);
			grid[digger.getLine()][digger.getColumn()] = 0;
		}
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
	
	@Override
	public List<? extends Agent> getAllAgents() {
		List<Agent> agents = new ArrayList<Agent>();
		agents.add(avatar);
		agents.addAll(hunters.values());
		return agents;
	}
	

	
	public void initGame() {
		
		int height = grid.length;
		int width = grid[0].length;
		for(int i = 0; i < height*width; i++) {
			availableCells.add(i);
		}
		availableCells.removeAll(walls.keySet());
		List<Integer> remainingCells = new ArrayList<Integer>();
		remainingCells.addAll(availableCells);
		int randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
		avatar.setCoords(randomValue/width, randomValue%width);
		grid[randomValue/width][randomValue%width] = HunterConstants.AVATAR;
		refreshDistanceValues();
		for(int i = 0; i < ((HunterConfigs) getConfigs()).getHunterNumber(); i++) {
			randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
			while(grid[randomValue/width][randomValue%width] < ((HunterConfigs) getConfigs()).getHunterInitialMinimumDistance()) {
				randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
			}
			hunters.put(randomValue, new Hunter(this, randomValue/width, randomValue%width, grid[randomValue/width][randomValue%width]));
			grid[randomValue/width][randomValue%width] = HunterConstants.HUNTER;
			remainingCells.remove(Integer.valueOf(randomValue));
		}
	}
	
	public void refreshDistanceValues() {
		final int height = grid.length;
		final int width = grid[0].length;
		List<Integer> remainingCells = new ArrayList<Integer>();
		remainingCells.addAll(availableCells);
		remainingCells.remove(Integer.valueOf((avatar.getLine()*width))+avatar.getColumn());
		remainingCells.removeAll(hunters.keySet());
		List<Integer> currentCells = new ArrayList<Integer>();
		List<Integer> newCells = new ArrayList<Integer>();
		currentCells.add(avatar.getLine()*grid[0].length + avatar.getColumn());
		while(!remainingCells.isEmpty()) {
			
			// TORUS
			if(configs.isTorus()) {
				for(Integer cell : currentCells) {
					// HAUT
					int targetCell = Math.floorMod(cell-width, height*width);
					if(remainingCells.contains(targetCell)) {						
						newCells.add(targetCell);
						grid[targetCell / width][targetCell % width] = grid[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(targetCell));
					}
					// GAUCHE
					targetCell = cell % width == 0 ? cell + width - 1 : cell - 1;
					if(remainingCells.contains(targetCell)) {						
						newCells.add(targetCell);
						grid[targetCell / width][targetCell % width] = grid[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(targetCell));
					}
					// DROITE
					targetCell = cell % width == width - 1 ? cell - width + 1: cell + 1;
					if(remainingCells.contains(targetCell)) {
						newCells.add(targetCell);
						grid[targetCell / width][targetCell % width] = grid[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(targetCell));						
					}
					// BAS
					targetCell = Math.floorMod(cell+width, height*width);
					if(remainingCells.contains(targetCell)) {						
						newCells.add(targetCell);
						grid[targetCell / width][targetCell % width] = grid[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(targetCell));
					}
				}
			}
			
			// NOT TORUS
			else {
				for(Integer cell : currentCells) {
					// HAUT
					if(cell / width > 0 && remainingCells.contains(cell - width)) {
						newCells.add(cell - width);
						grid[(cell - width) / width][cell % width] = grid[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(cell - width));
					}
					// GAUCHE
					if(cell % width > 0 && remainingCells.contains(cell - 1)) {
						newCells.add(cell - 1);
						grid[cell / width][(cell - 1) % width] = grid[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(cell - 1));
					}
					// DROITE
					if(cell % width < width - 1 && remainingCells.contains(cell + 1)) {
						newCells.add(cell + 1);
						grid[cell / width][(cell + 1) % width] = grid[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(cell + 1));
					}
					// BAS
					if(cell / width < height - 1 && remainingCells.contains(cell + width)) {
						newCells.add(cell + width);
						grid[(cell + width) / width][cell % width] = grid[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(cell + width));
					}
				}
			}
			currentCells.clear();
			currentCells.addAll(newCells);
			newCells.clear();
		}
	}
	
	public void oldHunterPos(final int line, final int column, int value) {
		grid[line][column] = value;
	}
	
	public void updateHunterKey(final int oldLine, final int oldColumn, final int newLine, final int newColumn) {
		hunters.put((newLine*grid[0].length)+newColumn, hunters.remove((oldLine*grid[0].length)+oldColumn));
	}
	
	public void availableCellMove(final int oldLine, final int oldColumn, final int newLine, final int newColumn) {
		availableCells.add(Integer.valueOf(oldLine*grid[0].length)+oldColumn);
		availableCells.remove(newLine*grid[0].length+newColumn);
	}

}
