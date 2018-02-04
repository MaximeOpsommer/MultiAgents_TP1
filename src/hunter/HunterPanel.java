package hunter;

import java.awt.Color;
import java.awt.Graphics;

import core.Environment;
import core.Panel;
import core.SMA;
import particules.ParticleConstants;

public class HunterPanel extends Panel {

	private static final long serialVersionUID = 6744389542478623718L;

	public HunterPanel(Environment env, SMA sma) {
		super(env, sma);
	}

	@Override
	protected void repaintGrid(Graphics g) {
		// draw background (black)
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ((boxSize+1)*width)-1, ((boxSize+1)*height)-1);
		
		// draw grid
		if(env.getConfigs().gridDisplay()) {			
			g.setColor(Color.WHITE);
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
				if(grid[line][column] == HunterConstants.DIGGER) {
					g.setColor(Color.RED);
					g.fillOval(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				}
				else if(grid[line][column] == HunterConstants.WALL) {
					g.setColor(Color.ORANGE);
					g.fillRect(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				}
				else if(grid[line][column] == HunterConstants.AVATAR) {
					g.setColor(Color.BLUE);
					g.fillRect(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				}
				else {
					g.setColor(Color.WHITE);
					g.drawString(grid[line][column]+"", column*(boxSize+1) + (boxSize/2)-3, line*(boxSize+1) + (boxSize/2)+5);
				}
			}
		}
		
	}

}
