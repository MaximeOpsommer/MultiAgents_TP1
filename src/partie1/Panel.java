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
		setPreferredSize(new Dimension(10*width, 10*height));
	}
	
	private void repaintGrid(Graphics g) {
		// draw background (white)
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 10*width, 10*height);
		
		// draw grid
		//g.setColor(Color.BLACK);
		//for(int x)
		
		// draw marbles
		int[][] grid = env.getGrid();
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				if(grid[line][column] == Constants.NO_COLLISION) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillOval(column*10, line*10, 10, 10);
				} else if(grid[line][column] == Constants.WALL_COLLISION) {
					g.setColor(Color.ORANGE);
					g.fillOval(column*10, line*10, 10, 10);
				}  else if(grid[line][column] == Constants.MARBLE_COLLISION) {
					g.setColor(Color.RED);
					g.fillOval(column*10, line*10, 10, 10);
				}
			}
		}
	}

	public void update(Observable o, Object arg) {
		repaint();
	}

}
