package partie1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class Configs {
	
	// Set some default values in case that we failed to read JSON
	private int AGENT_NUMBER = 200;
	private int BOX_SIZE = 10;
	private boolean GRID_DISPLAY = true;
	private int GRID_HEIGHT = 100;
	private int GRID_WIDTH = 100;
	private long SEED = 0;
	private boolean TORUS = false;
	
	public Configs() {
		// Read configs file
		Gson gson = new Gson();
		JsonObject json;
		try {
			URL url = getClass().getResource("../settings.json");
			File file = new File(url.getPath());
			json = gson.fromJson(new FileReader(file), JsonObject.class);
			
			// AGENT NUMBER
			try {				
				AGENT_NUMBER = json.get("agent_number").getAsInt();
				AGENT_NUMBER = Math.min(Constants.MAXIMUM_AGENT_NUMBER, AGENT_NUMBER);
				AGENT_NUMBER = Math.max(Constants.MINIMUM_AGENT_NUMBER, AGENT_NUMBER);
			} catch(Exception e) {
				System.err.println("agent number value in settings.json is invalid");
			}
			
			// BOX SIZE
			try {				
				BOX_SIZE = json.get("box_size").getAsInt();
				BOX_SIZE = Math.min(Constants.MAXIMUM_BOX_SIZE, BOX_SIZE);
				BOX_SIZE = Math.max(Constants.MINIMUM_BOX_SIZE, BOX_SIZE);
			} catch(Exception e) {
				System.err.println("agent number value in settings.json is invalid");
			}

			// GRID DISPLAY
			try {				
				GRID_DISPLAY = json.get("grid_display").getAsBoolean();
			} catch(Exception e) {
				System.err.println("grid display value in settings.json is invalid");
			}
			
			// GRID HEIGHT
			try {				
				GRID_HEIGHT = json.get("grid_height").getAsInt();
				GRID_HEIGHT = Math.min(Constants.GRID_MAXIMUM_DIMENSION, GRID_HEIGHT);
				GRID_HEIGHT = Math.max(Constants.GRID_MINIMUM_DIMENSION, GRID_HEIGHT);
			} catch(Exception e) {
				System.err.println("grid height value in settings.json is invalid");
			}
			
			// GRID WIDTH
			try {				
				GRID_WIDTH = json.get("grid_width").getAsInt();
				GRID_WIDTH = Math.min(Constants.GRID_MAXIMUM_DIMENSION, GRID_WIDTH);
				GRID_WIDTH = Math.max(Constants.GRID_MINIMUM_DIMENSION, GRID_WIDTH);
			} catch(Exception e) {
				System.err.println("grid width value in settings.json is invalid");
			}
			
			// SEED
			try {
				SEED = json.get("seed").getAsLong();				
			} catch(Exception e) {
				System.err.println("seed value in settings.json is invalid");
			}
			
			// TORUS
			try {
				TORUS = json.get("torus").getAsBoolean();				
			} catch(Exception e) {
				System.err.println("torus value in settings.json is invalid");
			}
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public int getAgentNumber() {
		return AGENT_NUMBER;
	}
	
	public void setAgentNumber(int agentNumber) {
		AGENT_NUMBER = agentNumber;
	}
	
	public int getBoxSize() {
		return BOX_SIZE;
	}
	
	public void setBoxSize(int boxSize) {
		BOX_SIZE = boxSize;
	}
	
	public boolean gridDisplay() {
		return GRID_DISPLAY;
	}
	
	public void setGridDisplay(boolean gridDisplay) {
		GRID_DISPLAY = gridDisplay;
	}
	
	public int getGridHeight() {
		return GRID_HEIGHT;
	}
	
	public void setGridHeight(int gridHeight) {
		GRID_HEIGHT = gridHeight;
	}
	
	public int getGridWidth() {
		return GRID_WIDTH;
	}
	
	public void setGridWidth(int gridWidth) {
		GRID_WIDTH = gridWidth;
	}
	
	public long getSeed() {
		return SEED;
	}
	
	public void setSeed(long seed) {
		SEED = seed;
	}
	
	public boolean isTorus() {
		return TORUS;
	}
	
	public void setTorus(boolean torus) {
		TORUS = torus;
	}
	
}
