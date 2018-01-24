package partie1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Panel extends JPanel implements Observer {
	
	private static final long serialVersionUID = -9151020618649934164L;
	
	private final Environment env;
	private final int width;
	private final int height;
	
	@Override
	public void paintComponent(Graphics g) {
		repaintGrid(g);
	}
	
	public Panel(final Environment env, final SMA sma) {
		this.env = env;
		sma.addObserver(this);
		width = env.getGrid()[0].length;
		height = env.getGrid().length;
		setPreferredSize(new Dimension((11*width)-1, (11*height)-1));
	}
	
	private void repaintGrid(Graphics g) {
		// draw background (white)
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (11*width)-1, (11*height)-1);
		
		// draw grid
		if(env.getConfigs().gridDisplay()) {			
			g.setColor(Color.BLACK);
			// vertical bars
			for(int n = 1; n < width; n++) {
				g.drawRect((n*11)-1, 0, 1, (11*height)-1);
			}
			// horizontal bars
			for(int n = 1; n < height; n++) {
				g.drawRect(0, (n*11)-1, (11*width)-1, 1);
			}
		}
		
		// draw marbles
		int[][] grid = env.getGrid();
		
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				if(grid[line][column] == Constants.NO_COLLISION) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(column*11, line*11, 10, 10);
				} else if(grid[line][column] == Constants.WALL_COLLISION) {
					g.setColor(Color.ORANGE);
					g.fillOval(column*11, line*11, 10, 10);
				}  else if(grid[line][column] == Constants.MARBLE_COLLISION) {
					g.setColor(Color.RED);
					g.fillOval(column*11, line*11, 10, 10);
				}
			}
		}
	}

	public void update(Observable o, Object arg) {
		repaint();
	}

}
