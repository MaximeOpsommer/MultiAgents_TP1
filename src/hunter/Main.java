package hunter;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import core.CommonConstants;
import core.SMA;

public class Main {
	
	public static void main(String[] args) {
		HunterEnvironment env = new HunterEnvironment();
		
		SMA sma = new HunterSMA(env);
		
		JFrame frame = new JFrame();
		frame.setContentPane(new JScrollPane(new HunterPanel(env, sma)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(env.getConfigs().getCanvasWidth(), env.getConfigs().getCanvasHeight()));
		frame.setMinimumSize(new Dimension(CommonConstants.MINIMUM_CANVAS_WIDTH, CommonConstants.MINIMUM_CANVAS_HEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//sma.run();
		new Thread(sma).start();
	}

}
