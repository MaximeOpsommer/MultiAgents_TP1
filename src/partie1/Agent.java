package partie1;

import java.util.Arrays;
import java.util.List;

public class Agent {

	// 1 = gris : pas encore de collision
	// 2 = rouge : collision
	
	private final Environment env;
	
	//private final age;
	
	private Coord coords;
	private Direction direction;
	private int collision;
	
	public Agent(final Environment env, final Coord coords) {
		this.env = env;
		this.coords = coords;
		direction = null;
		collision = 1;
	}
	
	public Coord getCoords() {
		return coords;
	}
	
	
	public void collisionFrom(int line, int column) {
		
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
			if(collision < Constants.WALL_COLLISION) {				
				collision = Constants.WALL_COLLISION;
			}
		}
		
		// Check horizontal edge bounce
		if(target.getColumn() < 0 || target.getColumn() >= width) {
			direction = direction.reverseHorizontalDirection();
			outOfBounds = true;
			move = false;
			if(collision < Constants.WALL_COLLISION) {				
				collision = Constants.WALL_COLLISION;
			}
		}
		
		// Check marble bounce
		if(!outOfBounds && env.getGrid()[target.getLine()][target.getColumn()] != 0) {
			// TODO : Improve new direction calculation
			direction = direction.reverseDirection();
			move = false;
			if(collision < Constants.MARBLE_COLLISION) {				
				collision = Constants.MARBLE_COLLISION;
			}
			try {
				Agent targetAgent = env.getAgent(target.getColumn(), target.getLine());
				targetAgent.collisionFrom(currentLine, currentColumn);
			} catch(Exception e) {
				System.err.println("Collided agent not found");
			}
			
		}
		
		if(move) {			
			coords = new Coord(coords.getColumn() + direction.getHorizontalMove(), coords.getLine() + direction.getVerticalMove());
			env.moveAgent(currentColumn, currentLine, direction, collision);
		}
	}
	
}
