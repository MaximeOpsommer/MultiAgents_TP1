package partie1;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Main {

	public static void main(String[] args) {
		Environment env = new Environment();
		
		SMA sma = new SMA(env);
		
		JFrame frame = new JFrame();
		frame.setContentPane(new JScrollPane(new Panel(env, sma)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(env.getConfigs().getCanvasWidth(), env.getConfigs().getCanvasHeight()));
		frame.setMinimumSize(new Dimension(Constants.MINIMUM_CANVAS_WIDTH, Constants.MINIMUM_CANVAS_HEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		sma.run();
	}
	
}
