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
	private int DELAY = 40;
	private boolean GRID_DISPLAY = true;
	private int GRID_HEIGHT = 100;
	private int GRID_WIDTH = 100;
	private int NB_TICKS = 1000;
	private int REFRESH = 1;
	private int SCHEDULING = 1; // 0 = Equitable, 1 = Sequentiel, 2 = Aleatoire
	private long SEED = 0;
	private boolean TORUS = false;
	private boolean TRACE = true;
	
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
				System.err.println("box size value in settings.json is invalid");
			}
			
			//DELAY
			try {
				DELAY = json.get("delay").getAsInt();
				DELAY = Math.min(Constants.MAXIMUM_DELAY, DELAY);
				DELAY = Math.max(Constants.MINIMUM_DELAY, DELAY);
			} catch(Exception e) {
				System.err.println("delay value in settings.json is invalid");
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
			
			//NB TICKS
			try {				
				NB_TICKS = json.get("nb_ticks").getAsInt();
				NB_TICKS = Math.max(Constants.MINIMUM_NB_TICKS, NB_TICKS);
			} catch(Exception e) {
				System.err.println("nb ticks value in settings.json is invalid");
			}
			
			// REFRESH
			try {				
				REFRESH = json.get("refresh").getAsInt();
				REFRESH = Math.min(Constants.MAXIMUM_REFRESH, REFRESH);
				REFRESH = Math.max(Constants.MINIMUM_REFRESH, REFRESH);
			} catch(Exception e) {
				System.err.println("refresh value in settings.json is invalid");
			}
			
			// SCHEDULING
			try {
				SCHEDULING = json.get("scheduling").getAsInt();		
				if(SCHEDULING < 0 || SCHEDULING > 2) {
					SCHEDULING = 1;
					System.err.println("scheduling value in settings.json is invalid");
				}
			} catch(Exception e) {
				System.err.println("scheduling value in settings.json is invalid");
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
			
			// TRACE
			try {
				TRACE = json.get("trace").getAsBoolean();				
			} catch(Exception e) {
				System.err.println("trace value in settings.json is invalid");
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
	
	public int getDelay() {
		return DELAY;
	}
	
	public void setDelay(int delay) {
		DELAY = delay;
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
	
	public int getNbTicks() {
		return NB_TICKS;
	}
	
	public void setNbTicks(int nbTicks) {
		NB_TICKS = nbTicks;
	}
	
	public int getRefresh() {
		return REFRESH;
	}

	public void setRefresh(int refresh) {
		REFRESH = refresh;
	}
	
	public int getScheduling() {
		return SCHEDULING;
	}
	
	public void setScheduling(int scheduling) {
		SCHEDULING = scheduling;
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
	
	public boolean trace() {
		return TRACE;
	}
	
	public void setTrace(boolean trace) {
		TRACE = trace;
	}
	
}
