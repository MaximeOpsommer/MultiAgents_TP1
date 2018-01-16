package partie1;

import java.util.ArrayList;
import java.util.List;

public class Agent {

	private final Environment env;
	
	//private final age;
	
	private Coord coords;
	
	public Agent(final Environment env, final Coord coords) {
		this.env = env;
		this.coords = coords;
	}
	
	/**
	 * Update the agent position
	 * Méthode appelée pour update l'agent lui même, par exemple quand l'agent vieillit
	 */
	public void update() {
		
	}
	
	/**
	 * Decide what action the agent will execute
	 */
	public void decide() {
		move();
	}
	
	protected void move() {
		// check neighbors
		List<Coord> coordsList = new ArrayList<Coord>();
		
		final int x = coords.getX();
		final int y = coords.getY();
		final int width = env.getGrid()[0].length;
		final int height = env.getGrid().length;
		
		// TOP LEFT
		if(x > 0 && y > 0 && env.getGrid()[y - 1][x - 1] == 0) {
			coordsList.add(new Coord(x - 1, y - 1));
		}
		
		// TOP
		if(y > 0 && env.getGrid()[y - 1][x] == 0) {
			coordsList.add(new Coord(x, y - 1));
		}
		
		// TOP RIGHT
		if(x < width - 1 && y > 0 && env.getGrid()[y - 1][x + 1] == 0) {
			coordsList.add(new Coord(x + 1, y - 1));
		}
		
		// RIGHT
		if(x < width - 1 && env.getGrid()[y][x + 1] == 0) {
			coordsList.add(new Coord(x + 1, y));
		}
		
		// BOTTOM RIGHT
		if(x < width - 1 && y < height - 1 && env.getGrid()[y + 1][x + 1] == 0) {
			coordsList.add(new Coord(x + 1, y + 1));
		}
		
		// BOTTOM
		if(y < height - 1 && env.getGrid()[y + 1][x] == 0) {
			coordsList.add(new Coord(x, y + 1));
		}
		
		// BOTTOM LEFT
		if(x > 0 && y < height - 1 && env.getGrid()[y + 1][x - 1] == 0) {
			coordsList.add(new Coord(x - 1, y + 1));
		}
		
		// LEFT
		if(x > 0 && env.getGrid()[y][x - 1] == 0) {
			coordsList.add(new Coord(x - 1, y));
		}
		
		int rand = (int) Math.random() * coordsList.size();
		
		coords = coordsList.get(rand);
		
	}
	
	protected void action2() {
		
	}
	
}
