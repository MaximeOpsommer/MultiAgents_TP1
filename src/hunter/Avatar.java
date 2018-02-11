package hunter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Agent;
import core.Environment;

public class Avatar extends Agent implements KeyListener {

	private int[][] grid;
	private int tick = 0;
	private final int speed;
	
	public Avatar(Environment env, int line, int column) {
		super(env, line, column);
		grid = getGrid();
		speed = ((HunterConfigs) getConfigs()).getAvatarSpeed();
	}
	
	public void setCoords(final int line, final int column) {
		super.line = line;
		super.column = column;
	}

	@Override
	public void decide() {
		if(tick++ % speed == 0) {
			move();
		}
	}

	@Override
	protected void move() {
		// TORUS
		if(getConfigs().isTorus()) {
			if(grid[Math.floorMod(line+verticalDirection, grid.length)][Math.floorMod(column+horizontalDirection, grid[0].length)] == HunterConstants.WALL
					|| grid[Math.floorMod(line+verticalDirection, grid.length)][Math.floorMod(column+horizontalDirection, grid[0].length)] == HunterConstants.HUNTER) {
				verticalDirection = 0;
				horizontalDirection = 0;
			}	
		}
		
		// NOT TORUS
		else {
			if(line+verticalDirection < 0 || line+verticalDirection >= grid.length) {
				verticalDirection = 0;
			} else if(column+horizontalDirection < 0 || column+horizontalDirection >= grid[0].length) {
				horizontalDirection = 0;
			} else if(grid[line+verticalDirection][column+horizontalDirection] == HunterConstants.WALL
					|| grid[line+verticalDirection][column+horizontalDirection] == HunterConstants.HUNTER) {
				verticalDirection = 0;
				horizontalDirection = 0;
			}
		}
		if(horizontalDirection != 0 || verticalDirection != 0) {
			final int targetLine = Math.floorMod(line + verticalDirection, grid.length);
			final int targetColumn = Math.floorMod(column + horizontalDirection, grid[0].length);
			if(grid[targetLine][targetColumn] == HunterConstants.DEFENDER) {
				((HunterEnvironment) env).activateDefender(targetLine, targetColumn);
			}
			else if(grid[targetLine][targetColumn] == HunterConstants.WINNER) {
				((HunterEnvironment) env).victory();
			}
			env.moveAgent(column, line, verticalDirection, horizontalDirection, HunterConstants.AVATAR);
			line = targetLine;
			column = targetColumn;
			((HunterEnvironment) env).refreshDistanceValues();
			
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			verticalDirection = -1;
			horizontalDirection = 0;
			break;
		case KeyEvent.VK_DOWN:
			verticalDirection = 1;
			horizontalDirection = 0;
			break;
		case KeyEvent.VK_LEFT:
			verticalDirection = 0;
			horizontalDirection = -1;
			break;
		case KeyEvent.VK_RIGHT:
			verticalDirection = 0;
			horizontalDirection = 1;
			break;
		default:
			break;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
