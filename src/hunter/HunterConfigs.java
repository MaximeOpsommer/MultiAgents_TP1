package hunter;

import core.Configs;

public class HunterConfigs extends Configs {
	
	private int DEFENDER_TO_WIN = 4;
	private int DIGGER_NUMBER = 10;
	private int HUNTER_INITIAL_MINIMUM_DISTANCE =  2;
	private int HUNTER_NUMBER = 1;
	private int WALL_PERCENT = 15;
	
	public HunterConfigs() {
		
	}

	public int getDefenderToWin() {
		return DEFENDER_TO_WIN;
	}
	
	public void setDefenderToWin(final int defenderToWin) {
		DEFENDER_TO_WIN = defenderToWin;
	}
	
	public int getDiggerNumber() {
		return DIGGER_NUMBER;
	}
	
	public void setDiggerNumber(final int diggerNumber) {
		DIGGER_NUMBER = diggerNumber;
	}
	
	public int getHunterInitialMinimumDistance() {
		return HUNTER_INITIAL_MINIMUM_DISTANCE;
	}
	
	public void setHunterInitialMinimumDistance(final int hunterInitialDistance) {
		HUNTER_INITIAL_MINIMUM_DISTANCE = hunterInitialDistance;
	}
	
	public int getHunterNumber() {
		return HUNTER_NUMBER;
	}
	
	public void setHunterNumber(final int hunterNumber) {
		HUNTER_NUMBER = hunterNumber;
	}
	
	public int getWallPercent() {
		return WALL_PERCENT;
	}
	
	public void setWallPercent(final int wallPercent) {
		WALL_PERCENT = wallPercent;
	}
	
}
