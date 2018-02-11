package wator;

import java.awt.Color;
import java.awt.Graphics;

import core.Panel;
import core.SMA;

public class WatorPanel extends Panel{

	private static final long serialVersionUID = -5578256491340281346L;
	
	public WatorPanel(final WatorEnvironment env, final SMA sma){
		super(env, sma);
	}
	
	protected void repaintGrid(Graphics g) {
		// draw background (blue)
		g.setColor(Color.BLUE);
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
		
		// draw fishes and sharks
		int[][] grid = env.getGrid();
		
		for(int line = 0; line < grid.length; line++) {
			for(int column = 0; column < grid[0].length; column++) {
				if(grid[line][column] == WatorConstants.BABY_FISH) {
					g.setColor(Color.YELLOW);
					g.fillRect(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				} else if(grid[line][column] == WatorConstants.ADULT_FISH) {
					g.setColor(Color.GREEN);
					g.fillRect(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				}  else if(grid[line][column] == WatorConstants.BABY_SHARK) {
					g.setColor(Color.PINK);
					g.fillRect(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				}  else if(grid[line][column] == WatorConstants.ADULT_SHARK) {
					g.setColor(Color.RED);
					g.fillRect(column*(boxSize+1), line*(boxSize+1), boxSize, boxSize);
				}
			}
		}
		
	}
}
