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
					} catch(Exception e) {
						System.err.println("avatar speed value in hunter-settings.json is invalid");
					}
					
					// DEFENDER EFFECT DURATION
					try {				
						DEFENDER_EFFECT_DURATION = json.get("defender_effect_duration").getAsInt();
					} catch(Exception e) {
						System.err.println("defender effect duration value in hunter-settings.json is invalid");
					}
					
					// DEFENDER LIFE
					try {				
						DEFENDER_LIFE = json.get("defender_life").getAsInt();
					} catch(Exception e) {
						System.err.println("defender life value in hunter-settings.json is invalid");
					}
					
					// DEFENDER TO WIN
					try {				
						DEFENDER_TO_WIN = json.get("defender_to_win").getAsInt();
					} catch(Exception e) {
						System.err.println("defender to win value in hunter-settings.json is invalid");
					}
					
					// DIGGER NUMBER
					try {				
						DIGGER_NUMBER = json.get("digger_number").getAsInt();
					} catch(Exception e) {
						System.err.println("digger number value in hunter-settings.json is invalid");
					}
					
					// HUNTER BEHAVIOUR
					try {				
						HUNTER_BEHAVIOUR = json.get("hunter_behaviour").getAsInt();
					} catch(Exception e) {
						System.err.println("hunter behaviour value in hunter-settings.json is invalid");
					}
					
					// HUNTER MINIMUM INITIAL DISTANCE
					try {				
						HUNTER_MINIMUM_INITIAL_DISTANCE = json.get("hunter_minimum_initial_distance").getAsInt();
					} catch(Exception e) {
						System.err.println("hunter minimum initial distance value in hunter-settings.json is invalid");
					}
					
					// HUNTER NUMBER
					try {				
						HUNTER_NUMBER = json.get("hunter_number").getAsInt();
					} catch(Exception e) {
						System.err.println("hunter number value in hunter-settings.json is invalid");
					}
					
					// HUNTER SPEED
					try {				
						HUNTER_SPEED = json.get("hunter_speed").getAsInt();
					} catch(Exception e) {
						System.err.println("hunter speed value in hunter-settings.json is invalid");
					}
					
					// WALL PERCENT
					try {				
						SHOW_TERRAIN_CONSTRUCTION = json.get("show_terrain_construction").getAsBoolean();
					} catch(Exception e) {
						System.err.println("show terrain construction value in hunter-settings.json is invalid");
					}
					
					// WALL PERCENT
					try {				
						WALL_PERCENT = json.get("wall_percent").getAsInt();
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
