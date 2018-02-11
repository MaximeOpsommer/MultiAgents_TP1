package hunter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import core.Configs;

public class HunterConfigs extends Configs {
	
	private int AVATAR_SPEED = 1;
	private int DEFENDER_EFFECT_DURATION = 10;
	private int DEFENDER_LIFE = 30;
	private int DEFENDER_TO_WIN = 4;
	private int DIGGER_NUMBER = 10;
	private int HUNTER_BEHAVIOUR = 0;
	private int HUNTER_MINIMUM_INITIAL_DISTANCE =  2;
	private int HUNTER_NUMBER = 1;
	private int HUNTER_SPEED = 2;
	private boolean SHOW_DISTANCE = true;
	private boolean SHOW_TERRAIN_CONSTRUCTION = true;
	private int WALL_PERCENT = 15;
	
	public HunterConfigs() {
		
		SETTINGS_FILE = "hunter-settings.json";
		
		// Read configs file
				Gson gson = new Gson();
				JsonObject json;
				try {
					URL url = getClass().getResource(SETTINGS_FILE);
					File file = new File(url.getPath());
					json = gson.fromJson(new FileReader(file), JsonObject.class);
					
					// AVATAR SPEED
					try {				
						AVATAR_SPEED = json.get("avatar_speed").getAsInt();
						AVATAR_SPEED = Math.min(HunterConstants.AVATAR_MINIMUM_SPEED, AVATAR_SPEED);
						AVATAR_SPEED = Math.max(HunterConstants.AVATAR_MAXIMUM_SPEED, AVATAR_SPEED);
					} catch(Exception e) {
						System.err.println("avatar speed value in hunter-settings.json is invalid");
					}
					
					// DEFENDER EFFECT DURATION
					try {				
						DEFENDER_EFFECT_DURATION = json.get("defender_effect_duration").getAsInt();
						DEFENDER_EFFECT_DURATION = Math.min(HunterConstants.MAXIMUM_DEFENDER_EFFECT_DURATION, DEFENDER_EFFECT_DURATION);
						DEFENDER_EFFECT_DURATION = Math.max(HunterConstants.MINIMUM_DEFENDER_EFFECT_DURATION, DEFENDER_EFFECT_DURATION);
					} catch(Exception e) {
						System.err.println("defender effect duration value in hunter-settings.json is invalid");
					}
					
					// DEFENDER LIFE
					try {				
						DEFENDER_LIFE = json.get("defender_life").getAsInt();
						DEFENDER_LIFE = Math.min(HunterConstants.DEFENDER_MAXIMUM_LIFE, DEFENDER_LIFE);
						DEFENDER_LIFE = Math.max(HunterConstants.DEFENDER_MINIMUM_LIFE, DEFENDER_LIFE);
					} catch(Exception e) {
						System.err.println("defender life value in hunter-settings.json is invalid");
					}
					
					// DEFENDER TO WIN
					try {				
						DEFENDER_TO_WIN = json.get("defender_to_win").getAsInt();
						DEFENDER_TO_WIN = Math.min(HunterConstants.MAXIMUM_DEFENDER_TO_WIN, DEFENDER_TO_WIN);
						DEFENDER_TO_WIN = Math.max(HunterConstants.MINIMUM_DEFENDER_TO_WIN, DEFENDER_TO_WIN);
					} catch(Exception e) {
						System.err.println("defender to win value in hunter-settings.json is invalid");
					}
					
					// DIGGER NUMBER
					try {				
						DIGGER_NUMBER = Math.max(1, json.get("digger_number").getAsInt());
					} catch(Exception e) {
						System.err.println("digger number value in hunter-settings.json is invalid");
					}
					
					// HUNTER BEHAVIOUR
					try {				
						HUNTER_BEHAVIOUR = json.get("hunter_behaviour").getAsInt();
						if(HUNTER_BEHAVIOUR != HunterConstants.NORMAL_BEHAVIOUR
								&& HUNTER_BEHAVIOUR != HunterConstants.STRATEGIC_BEHAVIOUR) {
							HUNTER_BEHAVIOUR = HunterConstants.NORMAL_BEHAVIOUR;
						}
					} catch(Exception e) {
						System.err.println("hunter behaviour value in hunter-settings.json is invalid");
					}
					
					// HUNTER MINIMUM INITIAL DISTANCE
					try {				
						HUNTER_MINIMUM_INITIAL_DISTANCE = Math.max(2, json.get("hunter_minimum_initial_distance").getAsInt());
					} catch(Exception e) {
						System.err.println("hunter minimum initial distance value in hunter-settings.json is invalid");
					}
					
					// HUNTER NUMBER
					try {				
						HUNTER_NUMBER = json.get("hunter_number").getAsInt();
						HUNTER_NUMBER = Math.min(HunterConstants.MAXIMUM_HUNTER_NUMBER, HUNTER_NUMBER);
						HUNTER_NUMBER = Math.max(HunterConstants.MINIMUM_HUNTER_NUMBER, HUNTER_NUMBER);
					} catch(Exception e) {
						System.err.println("hunter number value in hunter-settings.json is invalid");
					}
					
					// HUNTER SPEED
					try {				
						HUNTER_SPEED = json.get("hunter_speed").getAsInt();
						HUNTER_SPEED = Math.min(HunterConstants.HUNTER_MINIMUM_SPEED, HUNTER_SPEED);
						HUNTER_SPEED = Math.max(HunterConstants.HUNTER_MAXIMUM_SPEED, HUNTER_SPEED);
					} catch(Exception e) {
						System.err.println("hunter speed value in hunter-settings.json is invalid");
					}
					
					// SHOW DISTANCE
					try {				
						SHOW_DISTANCE = json.get("show_distance").getAsBoolean();
					} catch(Exception e) {
						System.err.println("show distance construction value in hunter-settings.json is invalid");
					}
					
					// SHOW TERRAIN CONSTRUCTION
					try {				
						SHOW_TERRAIN_CONSTRUCTION = json.get("show_terrain_construction").getAsBoolean();
					} catch(Exception e) {
						System.err.println("show terrain construction value in hunter-settings.json is invalid");
					}
					
					// WALL PERCENT
					try {				
						WALL_PERCENT = json.get("wall_percent").getAsInt();
						WALL_PERCENT = Math.min(HunterConstants.MAXIMUM_WALL_PERCENT, WALL_PERCENT);
						WALL_PERCENT = Math.max(HunterConstants.MINIMUM_WALL_PERCENT, WALL_PERCENT);
					} catch(Exception e) {
						System.err.println("wall percent value in hunter-settings.json is invalid");
					}
					
					
					
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
				} catch (JsonIOException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		
	}

	public int getAvatarSpeed() {
		return AVATAR_SPEED;
	}
	
	public void setAvatarSpeed(final int avatarSpeed) {
		AVATAR_SPEED = avatarSpeed;
	}
	
	public int getDefenderEffectDuration() {
		return DEFENDER_EFFECT_DURATION;
	}
	
	public void setDefenderEffectDuration(final int defnderEffectDuration) {
		DEFENDER_EFFECT_DURATION = defnderEffectDuration;
	}
	
	public int getDefenderLife() {
		return DEFENDER_LIFE;
	}
	
	public void setDefenderLife(final int defenderLife) {
		DEFENDER_LIFE = defenderLife;
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
	
	public int getHunterBehaviour() {
		return HUNTER_BEHAVIOUR;
	}
	
	public void setHunterBehaviour(final int hunterBehaviour) {
		HUNTER_BEHAVIOUR = hunterBehaviour;
	}
	
	public int getHunterMinimumInitialDistance() {
		return HUNTER_MINIMUM_INITIAL_DISTANCE;
	}
	
	public void setHunterMinimumInitialDistance(final int hunterMinimumInitialDistance) {
		HUNTER_MINIMUM_INITIAL_DISTANCE = hunterMinimumInitialDistance;
	}
	
	public int getHunterNumber() {
		return HUNTER_NUMBER;
	}
	
	public void setHunterNumber(final int hunterNumber) {
		HUNTER_NUMBER = hunterNumber;
	}
	
	public int getHunterSpeed() {
		return HUNTER_SPEED;
	}
	
	public void setHunterSpeed(final int hunterSpeed) {
		HUNTER_SPEED = hunterSpeed;
	}
	
	public boolean showDistance() {
		return SHOW_DISTANCE;
	}
	
	public void setShowDistance(final boolean showDistance) {
		SHOW_DISTANCE = showDistance;
	}
	
	public boolean showTerrainConstruction() {
		return SHOW_TERRAIN_CONSTRUCTION;
	}
	
	public void setShowTerrainConstruction(final boolean showTerrainConstruction) {
		SHOW_TERRAIN_CONSTRUCTION = showTerrainConstruction;
	}
	
	public int getWallPercent() {
		return WALL_PERCENT;
	}
	
	public void setWallPercent(final int wallPercent) {
		WALL_PERCENT = wallPercent;
	}
	
}
