package partie1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private static final long serialVersionUID = -9151020618649934164L;
	
	private final Environment env;
	private final int width;
	private final int height;
	
	@Override
	public void paintComponent(Graphics g) {
		repaintGrid(g);
	}
	
	public Panel(final Environment env) {
		this.env = env;
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
		
		g.setColor(Color.BLACK);
		// draw billes
		int[][] grid = env.getGrid();
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[0].length; x++) {
				if(grid[y][x] == 1) {
					g.fillRect(y*10, x*10, 10, 10);
				}
			}
		}
	}

}
