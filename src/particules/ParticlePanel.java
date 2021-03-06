package particules;

import java.awt.Color;
import java.awt.Graphics;

import core.Panel;
import core.SMA;

public class ParticlePanel extends Panel {
	
	private static final long serialVersionUID = -9151020618649934164L;
	
	public ParticlePanel(final ParticleEnvironment env, final SMA sma) {
		super(env, sma);
	}
	
	protected void repaintGrid(Graphics g) {
		// draw background (white)
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, ((boxSize+1)*width)-1, ((boxSize+1)*height)-1);
		
		// draw grid
		if(env.getConfigs().gridDisplay()) {			
			g.setColor(Color.BLACK);
			// vertical bars
			for(int n = 1; n < width; n++) {
				g.drawRect((n*(boxSize+1))-1, 0, 1, ((boxSize+1)*height)-1);
			}
			// horizontal bars
			for(int n = 1; n < height; n++) {
				g.drawRect(0, (n*(boxSize+1))-1, ((boxSize+1)*width)-1, 1);
			}
		}
		
		// draw particles
		int[][] grid = env.getGrid();
		
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				if(grid[line][column] == ParticleConstants.NO_COLLISION) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				} else if(grid[line][column] == ParticleConstants.WALL_COLLISION) {
					g.setColor(Color.ORANGE);
					g.fillOval(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				}  else if(grid[line][column] == ParticleConstants.PARTICLE_COLLISION) {
					g.setColor(Color.RED);
					g.fillOval(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				}
			}
		}
	}

}
