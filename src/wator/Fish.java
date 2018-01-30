package wator;

import java.util.ArrayList;
import java.util.List;

public class Fish extends AquaticAnimal {
	
	public Fish(final WatorEnvironment env, final int line, final int column){
		super(env, line, column, ((WatorConfigs) env.getConfigs()).getFishBreedTime());
	}

	public void decide() {
		int[][] grid = env.getGrid();
		List<Integer> voisinsLibres = getVoisinsLibres(grid);
		// If cannot move, do nothing
		if(!voisinsLibres.isEmpty()) {			
			int random = 4;
			while(random == 4) {
				random = voisinsLibres.get(env.getRandom().nextInt(voisinsLibres.size()));
			}
			verticalDirection = random/3 - 1;
			horizontalDirection = random%3 - 1;
			if(env.getConfigs().isTorus()) {
				verticalDirection = Math.floorMod(verticalDirection, grid.length);
				horizontalDirection = Math.floorMod(horizontalDirection, grid[0].length);
			}
			
			// Move
			env.moveAgent(column, line, verticalDirection, horizontalDirection, WatorConstants.ADULT_FISH);			
			if(breedTime == 0) {
				// reproduction
				((WatorEnvironment) env).addFish(line, column);
				breedTime = ((WatorConfigs) env.getConfigs()).getFishBreedTime();
			}
			column = Math.floorMod(column + horizontalDirection, grid[0].length);
			line = Math.floorMod(line + verticalDirection, grid.length);
			
		}
		breedTime--;
		((WatorEnvironment) env).grow(line, column, WatorConstants.ADULT_FISH);
	}
	
	private List<Integer> getVoisinsLibres(int[][] grid) {
		List<Integer> voisinsLibres = new ArrayList<Integer>();
		int height = grid.length;
		int width = grid[0].length;
		
		// TORUS
		if(env.getConfigs().isTorus()) {
			// HAUT GAUCHE (0)
			if(grid[Math.floorMod(line-1, height)][Math.floorMod(column-1, width)] == 0) {
				voisinsLibres.add(0);
			}
			// HAUT (1)
			if(grid[Math.floorMod(line-1, height)][column] == 0) {
				voisinsLibres.add(1);
			}
			// HAUT DROITE (2)
			if(grid[Math.floorMod(line-1, height)][Math.floorMod(column+1, width)] == 0) {
				voisinsLibres.add(2);
			}
			// GAUCHE (3)
			if(grid[line][Math.floorMod(column-1, width)] == 0) {
				voisinsLibres.add(3);
			}
			// DROITE (5)
			if(grid[line][Math.floorMod(column+1, width)] == 0) {
				voisinsLibres.add(5);
			}
			// BAS GAUCHE (6)
			if(grid[Math.floorMod(line+1, height)][Math.floorMod(column-1, width)] == 0) {
				voisinsLibres.add(6);
			}
			// BAS (7)
			if(grid[Math.floorMod(line+1, height)][column] == 0) {
				voisinsLibres.add(7);
			}
			// BAS DROITE (8)
			if(grid[Math.floorMod(line+1, height)][Math.floorMod(column+1, width)] == 0) {
				voisinsLibres.add(8);
			}
		}
		
		// NOT TORUS
		else {
			// HAUT GAUCHE (0)
			if(line > 0 && column > 0 && grid[line-1][column-1] == 0) {
				voisinsLibres.add(0);
			}
			// HAUT (1)
			if(line > 0 && grid[line-1][column] == 0) {
				voisinsLibres.add(1);
			}
			// HAUT DROITE (2)
			if(line > 0 && column < width-1 && grid[line-1][column+1] == 0) {
				voisinsLibres.add(2);
			}
			// GAUCHE (3)
			if(column > 0 && grid[line][column-1] == 0) {
				voisinsLibres.add(3);
			}
			// DROITE (5)
			if(column < width-1 && grid[line][column+1] == 0) {
				voisinsLibres.add(4);
			}
			// BAS GAUCHE (6)
			if(line < height-1 && column > 0 && grid[line+1][column-1] == 0) {
				voisinsLibres.add(6);
			}
			// BAS (7)
			if(line < height-1 && grid[line+1][column] == 0) {
				voisinsLibres.add(7);
			}
			// BAS DROITE (8)
			if(line < height-1 && column < width-1 && grid[line+1][column+1] == 0) {
				voisinsLibres.add(8);
			}
		}
		
		return voisinsLibres;
	}

	public WatorConfigs getConfigs() {
		return (WatorConfigs) env.getConfigs();
	}

}
