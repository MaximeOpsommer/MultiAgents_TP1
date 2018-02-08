package particules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import core.Configs;

public class ParticleConfigs extends Configs {
	
	// Set some default values in case that we failed to read JSON
	private int PARTICLE_NUMBER = 200;
	private int COMPORTEMENT = 1;
	
	public ParticleConfigs() {
		
		SETTINGS_FILE = "particle-settings.json";
		
		// Read configs file
		Gson gson = new Gson();
		JsonObject json;
		try {
			URL url = getClass().getResource(SETTINGS_FILE);
			File file = new File(url.getPath());
			json = gson.fromJson(new FileReader(file), JsonObject.class);
			
			// AGENT NUMBER
			try {				
				PARTICLE_NUMBER = json.get("particle_number").getAsInt();
				PARTICLE_NUMBER = Math.min(ParticleConstants.MAXIMUM_PARTICLE_NUMBER, PARTICLE_NUMBER);
				PARTICLE_NUMBER = Math.max(ParticleConstants.MINIMUM_PARTICLE_NUMBER, PARTICLE_NUMBER);
			} catch(Exception e) {
				System.err.println("particle number value in particle-settings.json is invalid");
			}
			
			// AGENT NUMBER
			try {				
				COMPORTEMENT = json.get("comportement").getAsInt();
				if(COMPORTEMENT > 3 || COMPORTEMENT < 1) {
					throw new Exception();
				}
			} catch(Exception e) {
				System.err.println("comportement value in particle-settings.json is invalid");
			}
			
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public int getParticleNumber() {
		return PARTICLE_NUMBER;
	}
	
	public void setParticleNumber(int particleNumber) {
		PARTICLE_NUMBER = particleNumber;
	}
	
	public int getComportement() {
		return COMPORTEMENT;
	}
	
	
}
