package wator;

import java.util.ArrayList;
import java.util.List;

public class Shark extends AquaticAnimal {
	
	private int sharkStarveTime;
	private int sharkStarveTimeConfig = ((WatorConfigs) env.getConfigs()).getSharkStarveTime() + 1;
	protected int breedTimeConfig = ((WatorConfigs) env.getConfigs()).getSharkBreedTime() + 1;
	List<Integer> voisinsProies;
	private int digest = 0;
	private int sharkDigestionTime = (int) (((WatorConfigs) (env.getConfigs())).getSharkStarveTime()/2);

	public Shark(final WatorEnvironment env, final int line, final int column){
		super(env, line, column, ((WatorConfigs) env.getConfigs()).getSharkBreedTime());
		this.sharkStarveTime = ((WatorConfigs) (env.getConfigs())).getSharkStarveTime();
		if(comportement == 2) {
			sharkDigestionTime = (int) (((WatorConfigs) (env.getConfigs())).getSharkStarveTime()/2);
		}else {
			sharkDigestionTime = 1;
		}
	}

	public void decide() {
		if(comportement == 1) {
			normalComportement();
		}
		else if(comportement == 2) {
			if(digest > 0) {
				digest --;
				breedTime--;
				sharkStarveTime--;
			}
			else {				
				digestionComportement();
			}
		}
		else if(comportement == 3) {
			digestionComportement();
			while(digest > 0) {
				digestionComportement();
			}
		}
		else {
			// Never hit
		}
	}
	
	protected void normalComportement() {
		refreshVoisinsLibres(grid, WatorConstants.BABY_FISH);
		refreshProiesLibres(grid, WatorConstants.BABY_SHARK);
		voisinsProies.removeAll(voisinsLibres);
		// If cannot move, do nothing
		if(!voisinsProies.isEmpty() || !voisinsLibres.isEmpty()) {
			int random = 4;
			boolean eat = false;
			if(!voisinsProies.isEmpty()) {
				
				while(random == 4) {
					random = voisinsProies.get(env.getRandom().nextInt(voisinsProies.size()));
				}
				eat = true;
				
				
			}
			else {
				while(random == 4) {
					random = voisinsLibres.get(env.getRandom().nextInt(voisinsLibres.size()));
				}
			}
			verticalDirection = random/3 - 1;
			horizontalDirection = random%3 - 1;
			
			if(isTorus) {
				verticalDirection = Math.floorMod(verticalDirection, grid.length);
				horizontalDirection = Math.floorMod(horizontalDirection, grid[0].length);
			}
			// Eat if fish
			if(eat) {
				((WatorEnvironment) env).removeFish(Math.floorMod(line + verticalDirection, grid.length), Math.floorMod(column + horizontalDirection, grid[0].length));
				sharkStarveTime = sharkStarveTimeConfig;
			}
			
			// Move
			env.moveAgent(column, line, verticalDirection, horizontalDirection, WatorConstants.ADULT_SHARK);			
			if(breedTime < 1) {
				// reproduction
				((WatorEnvironment) env).addShark(line, column);
				breedTime = breedTimeConfig;
			}
			column = Math.floorMod(column + horizontalDirection, grid[0].length);
			line = Math.floorMod(line + verticalDirection, grid.length);
		}
		breedTime--;
		sharkStarveTime--;
		if(sharkStarveTime < 1) {
			((WatorEnvironment) env).removeShark(line, column);
		} else {			
			((WatorEnvironment) env).grow(line, column, WatorConstants.ADULT_SHARK);
		}
	}
	
	protected void digestionComportement() {
		refreshVoisinsLibres(grid, WatorConstants.BABY_FISH);
		refreshProiesLibres(grid, WatorConstants.BABY_SHARK);
		voisinsProies.removeAll(voisinsLibres);
		// If cannot move, do nothing
		if(!voisinsProies.isEmpty() || !voisinsLibres.isEmpty()) {
			int random = 4;
			boolean eat = false;
			if(!voisinsProies.isEmpty()) {
				
				while(random == 4) {
					random = voisinsProies.get(env.getRandom().nextInt(voisinsProies.size()));
				}
				eat = true;
				
				
			}
			else {
				while(random == 4) {
					random = voisinsLibres.get(env.getRandom().nextInt(voisinsLibres.size()));
				}
			}
			verticalDirection = random/3 - 1;
			horizontalDirection = random%3 - 1;
			
			if(isTorus) {
				verticalDirection = Math.floorMod(verticalDirection, grid.length);
				horizontalDirection = Math.floorMod(horizontalDirection, grid[0].length);
			}
			// Eat if fish
			if(eat) {
				((WatorEnvironment) env).removeFish(Math.floorMod(line + verticalDirection, grid.length), Math.floorMod(column + horizontalDirection, grid[0].length));
				sharkStarveTime = sharkStarveTimeConfig;
				digest = sharkDigestionTime;
			}
			
			// Move
			env.moveAgent(column, line, verticalDirection, horizontalDirection, WatorConstants.ADULT_SHARK);			
			if(breedTime < 1) {
				// reproduction
				((WatorEnvironment) env).addShark(line, column);
				breedTime = breedTimeConfig;
			}
			column = Math.floorMod(column + horizontalDirection, grid[0].length);
			line = Math.floorMod(line + verticalDirection, grid.length);
		}
		breedTime--;
		sharkStarveTime--;
		if(sharkStarveTime < 1) {
			((WatorEnvironment) env).removeShark(line, column);
		} else {			
			((WatorEnvironment) env).grow(line, column, WatorConstants.ADULT_SHARK);
		}
	}

	protected void eat() {
		
	}
	
	private void refreshProiesLibres(int[][] grid, int limit) {
		voisinsProies = new ArrayList<Integer>();
		int height = grid.length;
		int width = grid[0].length;
		
		// TORUS
		if(env.getConfigs().isTorus()) {
			// HAUT GAUCHE (0)
			if(grid[Math.floorMod(line-1, height)][Math.floorMod(column-1, width)] < limit) {
				voisinsProies.add(0);
			}
			// HAUT (1)
			if(grid[Math.floorMod(line-1, height)][column] < limit) {
				voisinsProies.add(1);
			}
			// HAUT DROITE (2)
			if(grid[Math.floorMod(line-1, height)][Math.floorMod(column+1, width)] < limit) {
				voisinsProies.add(2);
			}
			// GAUCHE (3)
			if(grid[line][Math.floorMod(column-1, width)] < limit) {
				voisinsProies.add(3);
			}
			// DROITE (5)
			if(grid[line][Math.floorMod(column+1, width)] < limit) {
				voisinsProies.add(5);
			}
			// BAS GAUCHE (6)
			if(grid[Math.floorMod(line+1, height)][Math.floorMod(column-1, width)] < limit) {
				voisinsProies.add(6);
			}
			// BAS (7)
			if(grid[Math.floorMod(line+1, height)][column] < limit) {
				voisinsProies.add(7);
			}
			// BAS DROITE (8)
			if(grid[Math.floorMod(line+1, height)][Math.floorMod(column+1, width)] < limit) {
				voisinsProies.add(8);
			}
		}
		
		// NOT TORUS
		else {
			// HAUT GAUCHE (0)
			if(line > 0 && column > 0 && grid[line-1][column-1] < limit) {
				voisinsProies.add(0);
			}
			// HAUT (1)
			if(line > 0 && grid[line-1][column] < limit) {
				voisinsProies.add(1);
			}
			// HAUT DROITE (2)
			if(line > 0 && column < width-1 && grid[line-1][column+1] < limit) {
				voisinsProies.add(2);
			}
			// GAUCHE (3)
			if(column > 0 && grid[line][column-1] < limit) {
				voisinsProies.add(3);
			}
			// DROITE (5)
			if(column < width-1 && grid[line][column+1] < limit) {
				voisinsProies.add(5);
			}
			// BAS GAUCHE (6)
			if(line < height-1 && column > 0 && grid[line+1][column-1] < limit) {
				voisinsProies.add(6);
			}
			// BAS (7)
			if(line < height-1 && grid[line+1][column] < limit) {
				voisinsProies.add(7);
			}
			// BAS DROITE (8)
			if(line < height-1 && column < width-1 && grid[line+1][column+1] < limit) {
				voisinsProies.add(8);
			}
		}
	}
}
