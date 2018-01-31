package wator;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environment;

public abstract class AquaticAnimal extends Agent {

	protected int breedTime;
	
	public AquaticAnimal(Environment env, int line, int column, int breedTime) {
		super(env, line, column);
		this.breedTime = breedTime;
	}
	
	protected void move() {
		
	}
	
	protected void breed() {
		
	}
	
	protected List<Integer> getVoisinsLibres(int[][] grid, int limit) {
		List<Integer> voisinsLibres = new ArrayList<Integer>();
		int height = grid.length;
		int width = grid[0].length;
		
		// TORUS
		if(env.getConfigs().isTorus()) {
			// HAUT GAUCHE (0)
			if(grid[Math.floorMod(line-1, height)][Math.floorMod(column-1, width)] < limit) {
				voisinsLibres.add(0);
			}
			// HAUT (1)
			if(grid[Math.floorMod(line-1, height)][column] < limit) {
				voisinsLibres.add(1);
			}
			// HAUT DROITE (2)
			if(grid[Math.floorMod(line-1, height)][Math.floorMod(column+1, width)] < limit) {
				voisinsLibres.add(2);
			}
			// GAUCHE (3)
			if(grid[line][Math.floorMod(column-1, width)] < limit) {
				voisinsLibres.add(3);
			}
			// DROITE (5)
			if(grid[line][Math.floorMod(column+1, width)] < limit) {
				voisinsLibres.add(5);
			}
			// BAS GAUCHE (6)
			if(grid[Math.floorMod(line+1, height)][Math.floorMod(column-1, width)] < limit) {
				voisinsLibres.add(6);
			}
			// BAS (7)
			if(grid[Math.floorMod(line+1, height)][column] < limit) {
				voisinsLibres.add(7);
			}
			// BAS DROITE (8)
			if(grid[Math.floorMod(line+1, height)][Math.floorMod(column+1, width)] < limit) {
				voisinsLibres.add(8);
			}
		}
		
		// NOT TORUS
		else {
			// HAUT GAUCHE (0)
			if(line > 0 && column > 0 && grid[line-1][column-1] < limit) {
				voisinsLibres.add(0);
			}
			// HAUT (1)
			if(line > 0 && grid[line-1][column] < limit) {
				voisinsLibres.add(1);
			}
			// HAUT DROITE (2)
			if(line > 0 && column < width-1 && grid[line-1][column+1] < limit) {
				voisinsLibres.add(2);
			}
			// GAUCHE (3)
			if(column > 0 && grid[line][column-1] < limit) {
				voisinsLibres.add(3);
			}
			// DROITE (5)
			if(column < width-1 && grid[line][column+1] < limit) {
				voisinsLibres.add(5);
			}
			// BAS GAUCHE (6)
			if(line < height-1 && column > 0 && grid[line+1][column-1] < limit) {
				voisinsLibres.add(6);
			}
			// BAS (7)
			if(line < height-1 && grid[line+1][column] < limit) {
				voisinsLibres.add(7);
			}
			// BAS DROITE (8)
			if(line < height-1 && column < width-1 && grid[line+1][column+1] < limit) {
				voisinsLibres.add(8);
			}
		}
		return voisinsLibres;
	}

}
