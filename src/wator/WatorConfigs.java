package wator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import core.Configs;

public class WatorConfigs extends Configs{
	
	private int FISH_NUMBER = 100;
	private int FISH_BREED_TIME = 5;
	private int SHARK_NUMBER = 50;
	private int SHARK_BREED_TIME = 7;
	private int SHARK_STARVE_TIME = 15;
	private int COMPORTEMENT = 1;
	
	public WatorConfigs(){
		
		SETTINGS_FILE = "wator-settings.json";
		
		// Read configs file
		Gson gson = new Gson();
		JsonObject json;
		try {
			URL url = getClass().getResource(SETTINGS_FILE);
			File file = new File(url.getPath());
			json = gson.fromJson(new FileReader(file), JsonObject.class);
			
			// FISH NUMBER
			try {				
				FISH_NUMBER = json.get("fish_number").getAsInt();
				FISH_NUMBER = Math.min(WatorConstants.MAXIMUM_FISH_NUMBER, FISH_NUMBER);
				FISH_NUMBER = Math.max(WatorConstants.MINIMUM_FISH_NUMBER, FISH_NUMBER);
			} catch(Exception e) {
				System.err.println("fish number value in wator_settings.json is invalid");
			}
			
			// FISH BREED TIME
			try {				
				FISH_BREED_TIME = json.get("fish_breed_time").getAsInt();
				FISH_BREED_TIME = Math.min(WatorConstants.MAXIMUM_FISH_BREED_TIME, FISH_BREED_TIME);
				FISH_BREED_TIME = Math.max(WatorConstants.MINIMUM_FISH_BREED_TIME, FISH_BREED_TIME);
			} catch(Exception e) {
				System.err.println("fish breed time value in wator_settings.json is invalid");
			}
			
			// SHARK NUMBER
			try {				
				SHARK_NUMBER = json.get("shark_number").getAsInt();
				SHARK_NUMBER = Math.min(WatorConstants.MAXIMUM_SHARK_NUMBER, SHARK_NUMBER);
				SHARK_NUMBER = Math.max(WatorConstants.MINIMUM_SHARK_NUMBER, SHARK_NUMBER);
			} catch(Exception e) {
				System.err.println("shark number value in wator_settings.json is invalid");
			}
			
			// SHARK BREED TIME
			try {				
				SHARK_BREED_TIME = json.get("shark_breed_time").getAsInt();
				SHARK_BREED_TIME = Math.min(WatorConstants.MAXIMUM_SHARK_BREED_TIME, SHARK_BREED_TIME);
				SHARK_BREED_TIME = Math.max(WatorConstants.MINIMUM_SHARK_BREED_TIME, SHARK_BREED_TIME);
			} catch(Exception e) {
				System.err.println("shark breed time value in wator_settings.json is invalid");
			}
			
			// SHARK STARVE TIME
			try {				
				SHARK_STARVE_TIME = json.get("shark_starve_time").getAsInt();
				SHARK_STARVE_TIME = Math.min(WatorConstants.MAXIMUM_SHARK_STARVE_TIME, SHARK_STARVE_TIME);
				SHARK_STARVE_TIME = Math.max(WatorConstants.MINIMUM_SHARK_STARVE_TIME, SHARK_STARVE_TIME);
			} catch(Exception e) {
				System.err.println("shark starve time value in wator_settings.json is invalid");
			}
			
			// FISH NUMBER
			try {				
				COMPORTEMENT = json.get("comportement").getAsInt();
				if(COMPORTEMENT > 3 || COMPORTEMENT < 1) {
					throw new Exception();
				}
			} catch(Exception e) {
				System.err.println("comportement value in wator_settings.json is invalid");
			}
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public int getFishNumber(){
		return FISH_NUMBER;
	}
	
	public void setFishNumber(int fishNumber){
		FISH_NUMBER = fishNumber;
	}
	
	public int getFishBreedTime(){
		return FISH_BREED_TIME;
	}
	
	public void setFishBreedTime(int fishBreedTime){
		FISH_BREED_TIME = fishBreedTime;
	}
	
	public int getSharkNumber(){
		return SHARK_NUMBER;
	}
	
	public void setSharkNumber(int sharkNumber){
		SHARK_NUMBER = sharkNumber;
	}
	
	public int getSharkBreedTime(){
		return SHARK_BREED_TIME;
	}
	
	public void setSharkBreedTime(int sharkBreedTime){
		SHARK_BREED_TIME = sharkBreedTime;
	}
	
	public int getSharkStarveTime(){
		return SHARK_STARVE_TIME;
	}
	
	public void setSharkStarveTime(int sharkStarveTime){
		SHARK_STARVE_TIME = sharkStarveTime;
	}

	public int getComportement() {
		return COMPORTEMENT;
	}

}
