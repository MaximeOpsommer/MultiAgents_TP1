package partie1;

import java.util.Arrays;
import java.util.List;

public class Agent {

	// 1 = gris : pas encore de collision
	// 2 = orange : collision avec un mur
	// 3 = rouge : collision avec une bille
	
	private final Environment env;
	
	private Coord coords;
	private Direction direction;
	private int collision;
	
	public Agent(final Environment env, final Coord coords) {
		this.env = env;
		this.coords = coords;
		
		// Init direction
		List<Direction> directionList = Arrays.asList(Direction.values());
		int random = (int) (Math.random() * directionList.size());
		direction = directionList.get(random);
		
		collision = 1;
	}
	
	public Configs getConfigs() {
		return env.getConfigs();
	}
	
	public Coord getCoords() {
		return coords;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setCollision(int collision) {
		this.collision = collision;
	}
	
	public void collisionFrom(int lineFrom, int columnFrom) {
		collision = Constants.MARBLE_COLLISION;
		direction = direction.reverseDirection();
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
		
		boolean outOfBounds = false;
		boolean move = true;
		
		// Manage bounces
		Coord target;
		// TORUS
		if(getConfigs().isTorus()) {
			target = new Coord(Math.floorMod(currentColumn + direction.getHorizontalMove(), width), Math.floorMod(currentLine + direction.getVerticalMove(), height));
			
		// NOT TORUS
		} else {			
			target = new Coord(currentColumn + direction.getHorizontalMove(), currentLine + direction.getVerticalMove());
			// Check vertical edge bounce
			if(target.getLine() < 0 || target.getLine() >= height) {
				if(getConfigs().isTorus()) {
					
				}
				else {
					direction = direction.reverseVerticalDirection();
					outOfBounds = true;
					move = false;
					if(collision < Constants.WALL_COLLISION) {				
						collision = Constants.WALL_COLLISION;
					}
				}
			}
			
			// Check horizontal edge bounce
			if(target.getColumn() < 0 || target.getColumn() >= width) {
				if(getConfigs().isTorus()) {
					
				}
				else {				
					direction = direction.reverseHorizontalDirection();
					outOfBounds = true;
					move = false;
					if(collision < Constants.WALL_COLLISION) {				
						collision = Constants.WALL_COLLISION;
					}
				}
			}
		}
		
		
		// Check marble bounce
		if(!outOfBounds && env.getGrid()[target.getLine()][target.getColumn()] != 0) {
			move = false;
			if(collision < Constants.MARBLE_COLLISION) {				
				collision = Constants.MARBLE_COLLISION;
			}
			try {
				Agent targetAgent = env.getAgent(target.getColumn(), target.getLine());
				
				// Swap Directions
				Direction oldDirection = direction;
				Direction oldTargetDirection = targetAgent.getDirection();
				
				
				direction = oldTargetDirection;
				targetAgent.setDirection(oldDirection);
				targetAgent.setCollision(Constants.MARBLE_COLLISION);
				env.collision(coords, targetAgent.getCoords());
				
				//targetAgent.collisionFrom(currentLine, currentColumn);
			} catch(Exception e) {
				direction = direction.reverseDirection();
				System.err.println("Collided agent not found, direction has been reverse instead");
			}
			
		}
		
		if(move) {			
			coords = new Coord(Math.floorMod(coords.getColumn() + direction.getHorizontalMove(), width), Math.floorMod(coords.getLine() + direction.getVerticalMove(), height));
			env.moveAgent(currentColumn, currentLine, direction, collision);
		}
	}
	
}
