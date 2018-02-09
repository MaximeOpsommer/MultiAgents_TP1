package particules;

import core.Agent;

public class Particle extends Agent {
	
	private int collision;
	private int timer;
	
	public Particle(final ParticleEnvironment env, final int line, final int column) {
		super(env, line, column);
		while(verticalDirection == 0 && horizontalDirection == 0) {
			verticalDirection = env.getRandom().nextInt(3) - 1;
			horizontalDirection = env.getRandom().nextInt(3) - 1;
		}
		collision = 1;
		timer = 5;
	}
	
	public int getCollision() {
		return collision;
	}
	
	public void setCollision(int collision) {
		this.collision = collision;
	}
	
	// TODO Maxime : future methode pour les trajectoires realistes
	public void collisionFrom(int lineFrom, int columnFrom) {
		collision = ParticleConstants.PARTICLE_COLLISION;
		// Calcul directions
	}
	
	/**
	 * Decide what action the agent will execute
	 */
	public void decide() {
		if(((ParticleConfigs) env.getConfigs()).getComportement() == 1) {
			move();
		}
		else if(((ParticleConfigs) env.getConfigs()).getComportement() == 2){
			if(env.getRandom().nextInt(3) == 0) {
				turnRight();
			}
			if(env.getRandom().nextInt(3) == 0) {
				turnLeft();
			}
			move();
		}
		else if(((ParticleConfigs) env.getConfigs()).getComportement() == 3) {
			if(env.getRandom().nextInt(3) == 0) {
				turnBadRight();
			}
			if(env.getRandom().nextInt(3) == 0) {
				turnBadLeft();
			}
			move();
		}
		else {
			// Never Here
		}
		
	}
	
	protected void turnRight() {
		if(verticalDirection == 1 && horizontalDirection != -1) {
			horizontalDirection--;
		}
		else if(verticalDirection != -1 && horizontalDirection == -1) {
			verticalDirection--;
		}
		else if(verticalDirection == -1 && horizontalDirection != 1) {
			horizontalDirection++;
		}
		else {
			verticalDirection++;
		}
	}
	
	protected void turnBadRight() {
		if(verticalDirection == 1 && horizontalDirection != -1) {
			horizontalDirection--;
		}
		else if(verticalDirection != -1 && horizontalDirection == -1) {
			verticalDirection++;
		}
		else if(verticalDirection == -1 && horizontalDirection != 1) {
			horizontalDirection++;
		}
		else {
			verticalDirection--;
		}
		if(verticalDirection > 3 || verticalDirection < -3) {
			verticalDirection = 0;
		}
		if(horizontalDirection > 3 || horizontalDirection < -3) {
			horizontalDirection = 0;
		}
	}
	
	protected void turnLeft() {
		if(verticalDirection == 1 && horizontalDirection != 1) {
			horizontalDirection++;
		}
		else if(verticalDirection != 1 && horizontalDirection == -1) {
			verticalDirection++;
		}
		else if(verticalDirection == -1 && horizontalDirection != -1) {
			horizontalDirection--;
		}
		else {
			verticalDirection--;
		}
	}
	
	protected void turnBadLeft() {
		if(verticalDirection == 1 && horizontalDirection != 1) {
			horizontalDirection--;
		}
		else if(verticalDirection != 1 && horizontalDirection == -1) {
			verticalDirection++;
		}
		else if(verticalDirection == -1 && horizontalDirection != -1) {
			horizontalDirection++;
		}
		else {
			verticalDirection--;
		}
		if(verticalDirection > 3 || verticalDirection < -3) {
			verticalDirection = 0;
		}
		if(horizontalDirection > 3 || horizontalDirection < -3) {
			horizontalDirection = 0;
		}
	}
	
	protected void move() {
		
		final int width = env.getGrid()[0].length;
		final int height = env.getGrid().length;
		
		boolean outOfBounds = false;
		boolean move = true;
		
		// Manage bounces
		int targetLine;
		int targetColumn;
		
		// TORUS
		if(getConfigs().isTorus()) {
			targetLine = Math.floorMod(line + verticalDirection, height);
			targetColumn = Math.floorMod(column + horizontalDirection, width);
		}
		
		// NOT TORUS
		else {
			targetLine = line + verticalDirection;
			targetColumn = column + horizontalDirection;
			// Check vertical edge bounce
			if(targetLine < 0 || targetLine >= height) {
				if(getConfigs().isTorus()) {
					
				}
				else {
					verticalDirection *= -1;
					outOfBounds = true;
					move = false;
					if(collision < ParticleConstants.WALL_COLLISION) {				
						collision = ParticleConstants.WALL_COLLISION;
					}
					if(getConfigs().trace()) {
						System.out.println("Agent;column=" + column + ";line=" + line + ";vertical_direction=" + verticalDirection + ";horizontal_direction=" + horizontalDirection + ";bounced on wall");						
					}
				}
			}
			
			// Check horizontal edge bounce
			if(targetColumn < 0 || targetColumn >= width) {
				if(getConfigs().isTorus()) {
					
				}
				else {				
					horizontalDirection *= -1;
					outOfBounds = true;
					move = false;
					if(collision < ParticleConstants.WALL_COLLISION) {				
						collision = ParticleConstants.WALL_COLLISION;
					}
					if(getConfigs().trace()) {
						System.out.println("Agent;column=" + column + ";line=" + line + ";vertical_direction=" + verticalDirection + ";horizontal_direction=" + horizontalDirection + ";bounced on wall");						
					}
				}
			}
		}
		
		
		// Check particules bounce
		if(!outOfBounds && env.getGrid()[targetLine][targetColumn] != 0) {
			move = false;
			if(collision < ParticleConstants.PARTICLE_COLLISION) {				
				collision = ParticleConstants.PARTICLE_COLLISION;
			}
			try {
				Particle targetAgent = ((ParticleEnvironment) env).getParticle(targetColumn, targetLine);
				
				// Swap Directions
				int oldVerticalDirection = verticalDirection;
				int oldHorizontalDirection = horizontalDirection;
				
				// Log
				if(getConfigs().trace()) {
					System.out.println("Agent;column=" + column + ";line=" + line + ";vertical_direction=" + verticalDirection + ";horizontal_direction=" + horizontalDirection + ";bounced on Agent;column=" + targetColumn + ";line=" + targetLine + ";vertical_direction=" + targetAgent.getVerticalDirection() + ";horizontal_direction=" + targetAgent.getHorizontalDirection());
				}
				
				verticalDirection = targetAgent.getVerticalDirection();
				horizontalDirection = targetAgent.getHorizontalDirection();
				targetAgent.setVerticalDirection(oldVerticalDirection);
				targetAgent.setHorizontalDirection(oldHorizontalDirection);
				targetAgent.setCollision(ParticleConstants.PARTICLE_COLLISION);
				((ParticleEnvironment) env).collision(line, column, targetLine, targetColumn);
				
				//targetAgent.collisionFrom(currentLine, currentColumn);
			} catch(Exception e) {
				verticalDirection *= -1;
				horizontalDirection *= -1;
				System.err.println("Collided agent not found, direction has been reverse instead");
			}
			
		}
		
		if(move) {			
			env.moveAgent(column, line, verticalDirection, horizontalDirection, collision);
			// maybe modulo
			column = Math.floorMod(column + horizontalDirection, width);
			line = Math.floorMod(line + verticalDirection, height);
		}
	}
	
}
