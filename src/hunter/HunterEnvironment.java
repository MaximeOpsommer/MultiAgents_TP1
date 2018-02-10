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
	private Map<Integer, Defender> defenders = new HashMap<Integer, Defender>();
	private Winner winner;
	
	private List<Integer> availableCells = new ArrayList<Integer>();
	private int[][] distances;
	private int defenderToWin;
	
	public HunterEnvironment() {
		configs = new HunterConfigs();
		diggerNumber = ((HunterConfigs) configs).getDiggerNumber();
		init();
		avatar = new Avatar(this, 0, 0);
		distances = new int[height][width];
		defenderToWin = ((HunterConfigs) configs).getDefenderToWin();
	}
	
	public int[][] getDistances() {
		return distances;
	}
	
	protected void init() {
		super.init();
		int total = height * width;
		int reste = diggerNumber;
		int rand;
		for(int line = 0; line < height; line++) {
			for(int column = 0; column < width; column++) {
				
				// TODO : Diggers
				rand = random.nextInt(total);
				if(rand < reste) {
					grid[line][column] = HunterConstants.DIGGER;
					reste--;
					diggers.add(new Digger(this, line, column));
				} else {
					grid[line][column] = HunterConstants.WALL;
					walls.put((line*width)+column, new Wall(this, line, column));
				}
				total--;
			}
		}
	}
	
	public Map<Integer, Wall> getWalls() {
		return walls;
	}
	
	public void removeWall(final int line, final int column) {
		walls.remove((line*width)+column);
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
		for(int i = 0; i < height*width; i++) {
			availableCells.add(i);
		}
		availableCells.removeAll(walls.keySet());
		List<Integer> remainingCells = new ArrayList<Integer>();
		remainingCells.addAll(availableCells);
		int randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
		// Init Avatar position
		avatar.setCoords(randomValue/width, randomValue%width);
		grid[randomValue/width][randomValue%width] = HunterConstants.AVATAR;
		refreshDistanceValues();
		// Init hunters position
		for(int i = 0; i < ((HunterConfigs) getConfigs()).getHunterNumber(); i++) {
			randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
			while(distances[randomValue/width][randomValue%width] < ((HunterConfigs) getConfigs()).getHunterMinimumInitialDistance()) {
				randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
			}
			hunters.put(randomValue, new Hunter(this, randomValue/width, randomValue%width, distances[randomValue/width][randomValue%width]));
			grid[randomValue/width][randomValue%width] = HunterConstants.HUNTER;
			remainingCells.remove(Integer.valueOf(randomValue));
		}
		// Init Defender position
		randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
		defenders.put(randomValue, new Defender(this, randomValue/width, randomValue%width));
		grid[randomValue/width][randomValue%width] = HunterConstants.DEFENDER;
	}
	
	public void refreshDistanceValues() {
		List<Integer> remainingCells = new ArrayList<Integer>();
		remainingCells.addAll(availableCells);
		List<Integer> currentCells = new ArrayList<Integer>();
		List<Integer> newCells = new ArrayList<Integer>();
		distances[avatar.getLine()][avatar.getColumn()] = 0;
		currentCells.add(avatar.getLine()*width + avatar.getColumn());
		while(!remainingCells.isEmpty()) {
			
			// TORUS
			if(configs.isTorus()) {
				for(Integer cell : currentCells) {
					// HAUT
					int targetCell = Math.floorMod(cell-width, height*width);
					if(remainingCells.contains(targetCell)) {						
						newCells.add(targetCell);
						distances[targetCell / width][targetCell % width] = distances[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(targetCell));
					}
					// GAUCHE
					targetCell = cell % width == 0 ? cell + width - 1 : cell - 1;
					if(remainingCells.contains(targetCell)) {						
						newCells.add(targetCell);
						distances[targetCell / width][targetCell % width] = distances[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(targetCell));
					}
					// DROITE
					targetCell = cell % width == width - 1 ? cell - width + 1: cell + 1;
					if(remainingCells.contains(targetCell)) {
						newCells.add(targetCell);
						distances[targetCell / width][targetCell % width] = distances[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(targetCell));						
					}
					// BAS
					targetCell = Math.floorMod(cell+width, height*width);
					if(remainingCells.contains(targetCell)) {						
						newCells.add(targetCell);
						distances[targetCell / width][targetCell % width] = distances[cell / width][cell % width] + 1;
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
						distances[(cell - width) / width][cell % width] = distances[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(cell - width));
					}
					// GAUCHE
					if(cell % width > 0 && remainingCells.contains(cell - 1)) {
						newCells.add(cell - 1);
						distances[cell / width][(cell - 1) % width] = distances[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(cell - 1));
					}
					// DROITE
					if(cell % width < width - 1 && remainingCells.contains(cell + 1)) {
						newCells.add(cell + 1);
						distances[cell / width][(cell + 1) % width] = distances[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(cell + 1));
					}
					// BAS
					if(cell / width < height - 1 && remainingCells.contains(cell + width)) {
						newCells.add(cell + width);
						distances[(cell + width) / width][cell % width] = distances[cell / width][cell % width] + 1;
						remainingCells.remove(Integer.valueOf(cell + width));
					}
				}
			}
			currentCells.clear();
			currentCells.addAll(newCells);
			newCells.clear();
		}
	}
	
	public void activateDefender(final int line, final int column) {
		defenders.remove((line*width)+column);
		defenderToWin--;
		if(defenderToWin < 1) {
			System.out.println("POP WINNER");
			popNewWinner();
		}
		
		else {
			popNewDefender();
		}
	}
	
	private void popNewDefender() {
		List<Integer> remainingCells = new ArrayList<Integer>();
		remainingCells.addAll(availableCells);
		remainingCells.add(avatar.getLine()*width + avatar.getColumn());
		remainingCells.addAll(hunters.keySet());
		int randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
		defenders.put(randomValue, new Defender(this, randomValue/width, randomValue%width));
		grid[randomValue/width][randomValue%width] = HunterConstants.DEFENDER;
	}
	
	private void popNewWinner() {
		List<Integer> remainingCells = new ArrayList<Integer>();
		remainingCells.addAll(availableCells);
		remainingCells.add(avatar.getLine()*width + avatar.getColumn());
		remainingCells.addAll(hunters.keySet());
		int randomValue = remainingCells.get(random.nextInt(remainingCells.size()));
		winner = new Winner(this, randomValue/width, randomValue%width);
		grid[randomValue/width][randomValue%width] = HunterConstants.WINNER;
	}
	
	public void win() {
		System.out.println("WIN !");
	}

}
