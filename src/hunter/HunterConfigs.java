package hunter;

import core.Configs;

public class HunterConfigs extends Configs {
	
	private int DIGGER_NUMBER = 10;
	private int WALL_PERCENT = 15;
	
	public HunterConfigs() {
		
	}

	public int getDiggerNumber() {
		return DIGGER_NUMBER;
	}
	
	public void setDiggerNumber(final int diggerNumber) {
		DIGGER_NUMBER = diggerNumber;
	}
	
	public int getWallPercent() {
		return WALL_PERCENT;
	}
	
	public void setWallPercent(final int wallPercent) {
		WALL_PERCENT = wallPercent;
	}
	
}
