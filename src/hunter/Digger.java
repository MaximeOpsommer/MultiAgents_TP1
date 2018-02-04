package hunter;

import java.util.ArrayList;
import java.util.List;

import core.Agent;
import core.Environment;

public class Digger extends Agent {

	private int[][] grid;
	private List<Integer> voisinsLibres;
	
	public Digger(Environment env, int line, int column) {
		super(env, line, column);
		grid = getGrid();
	}

	@Override
	public void decide() {
		refreshVoisinsLibres(grid);
		if(!voisinsLibres.isEmpty()) {
			int random = voisinsLibres.get(env.getRandom().nextInt(voisinsLibres.size()));
			verticalDirection = random/3 - 1;
			horizontalDirection = random%3 - 1;
			if(isTorus) {
				verticalDirection = Math.floorMod(verticalDirection, grid.length);
				horizontalDirection = Math.floorMod(horizontalDirection, grid[0].length);
			}
			env.moveAgent(column, line, verticalDirection, horizontalDirection, HunterConstants.DIGGER);
			column = Math.floorMod(column + horizontalDirection, grid[0].length);
			line = Math.floorMod(line + verticalDirection, grid.length);
			((HunterEnvironment) env).removeWall(line, column);
		}
		
	}

	@Override
	protected void move() {
		// TODO Auto-generated method stub
		
	}
	
	protected void refreshVoisinsLibres(int[][] grid) {
		voisinsLibres = new ArrayList<Integer>();
		int height = grid.length;
		int width = grid[0].length;
		
		// TORUS
		if(env.getConfigs().isTorus()) {
			// HAUT (1)
			if(grid[Math.floorMod(line-1, height)][column] != HunterConstants.DIGGER) {
				voisinsLibres.add(1);
			}
			// GAUCHE (3)
			if(grid[line][Math.floorMod(column-1, width)] != HunterConstants.DIGGER) {
				voisinsLibres.add(3);
			}
			// DROITE (5)
			if(grid[line][Math.floorMod(column+1, width)] != HunterConstants.DIGGER) {
				voisinsLibres.add(5);
			}
			// BAS (7)
			if(grid[Math.floorMod(line+1, height)][column] != HunterConstants.DIGGER) {
				voisinsLibres.add(7);
			}
		}
		
		// NOT TORUS
		else {
			// HAUT (1)
			if(line > 0 && grid[line-1][column] != HunterConstants.DIGGER) {
				voisinsLibres.add(1);
			}
			// GAUCHE (3)
			if(column > 0 && grid[line][column-1] != HunterConstants.DIGGER) {
				voisinsLibres.add(3);
			}
			// DROITE (5)
			if(column < width-1 && grid[line][column+1] != HunterConstants.DIGGER) {
				voisinsLibres.add(5);
			}
			// BAS (7)
			if(line < height-1 && grid[line+1][column] != HunterConstants.DIGGER) {
				voisinsLibres.add(7);
			}
		}
	}

}
