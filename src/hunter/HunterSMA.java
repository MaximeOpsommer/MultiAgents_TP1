package hunter;

import java.util.List;

import core.Environment;
import core.SMA;

public class HunterSMA extends SMA {

	private List<Digger> diggers;
	private final int wallNumber; 
	
	public HunterSMA(Environment env) {
		super(env);
		diggers = ((HunterEnvironment) env).getDiggers();
		final double wallPercent = (double) ((HunterConfigs) env.getConfigs()).getWallPercent() / 100;
		final int height = env.getConfigs().getGridHeight();
		final int width = env.getConfigs().getGridWidth();
		wallNumber = (int) (height * width * wallPercent);
	}
	
	public void run() {
		// build the terrain
		int builderTick = 0;
		int currentWallNumber = ((HunterEnvironment) env).getWalls().size();
		final boolean showTerrainConstruction = ((HunterConfigs) env.getConfigs()).showTerrainConstruction();
		while(currentWallNumber > wallNumber) {
			diggers.get(0).decide();
			diggers.add(diggers.remove(0));
			currentWallNumber = ((HunterEnvironment) env).getWalls().size();
			builderTick++;
			if(showTerrainConstruction && builderTick == diggers.size()) {				
				update();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				builderTick = 0;
			}
		}
		((HunterEnvironment) env).removeDiggers();
		((HunterEnvironment) env).initGame();
		update();
		
		super.run();
	}

}
