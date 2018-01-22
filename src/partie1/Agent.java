package partie1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Agent {

	private final Environment env;
	
	//private final age;
	
	private Coord coords;
	private Direction direction;
	
	public Agent(final Environment env, final Coord coords) {
		this.env = env;
		this.coords = coords;
		direction = null;
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
		
		final int currentColumn = coords.getColumn();
		final int currentLine = coords.getLine();
		final int width = env.getGrid()[0].length;
		final int height = env.getGrid().length;
		
		// Init Direction
		if(direction == null) {
			List<Direction> directionList = Arrays.asList(Direction.values());
			int random = (int) (Math.random() * directionList.size());
			direction = directionList.get(random);
		}
		
		boolean outOfBounds = false;
		boolean move = true;
		
		// Manage bounces
		Coord target = new Coord(currentColumn + direction.getHorizontalMove(), currentLine + direction.getVerticalMove());
		
		// Check vertical edge bounce
		if(target.getLine() < 0 || target.getLine() >= height) {
			direction = direction.reverseVerticalDirection();
			outOfBounds = true;
			move = false;
		}
		
		// Check horizontal edge bounce
		if(target.getColumn() < 0 || target.getColumn() >= width) {
			direction = direction.reverseHorizontalDirection();
			outOfBounds = true;
			move = false;
		}
		
		// Check marble bounce
		if(!outOfBounds && env.getGrid()[target.getLine()][target.getColumn()] != 0) {
			direction = direction.reverseDirection();
			move = false;
		}
		
		if(move) {			
			coords = new Coord(coords.getColumn() + direction.getHorizontalMove(), coords.getLine() + direction.getVerticalMove());
			env.moveAgent(currentColumn, currentLine, direction);
		}
	}
	
	protected void action2() {
		
	}
	
}
