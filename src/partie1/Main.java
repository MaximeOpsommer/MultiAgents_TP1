package partie1;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Main {

	public static void main(String[] args) {
		int N_AGENTS = 200;
		Environment env = new Environment(100, 100, N_AGENTS);
		
		SMA sma = new SMA(env);
		
		JFrame frame = new JFrame();
		frame.setContentPane(new JScrollPane(new Panel(env, sma)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(800, 600));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		sma.run();
	}
	
}
