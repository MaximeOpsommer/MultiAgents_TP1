package core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public abstract class Panel extends JPanel  implements Observer {

	private static final long serialVersionUID = -6064990986686154511L;
	
	protected final Environment env;
	protected final int width;
	protected final int height;
	protected int boxSize;

	public Panel(final Environment env, final SMA sma) {
		this.env = env;
		sma.addObserver(this);
		width = env.getGrid()[0].length;
		height = env.getGrid().length;
		boxSize = env.getConfigs().getBoxSize();
		setPreferredSize(new Dimension(((boxSize+1)*width)-1, ((boxSize+1)*height)-1));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		repaintGrid(g);
	}
	
	protected abstract void repaintGrid(Graphics g);
	
	public void update(Observable o, Object arg) {
		repaint();
	}

}
