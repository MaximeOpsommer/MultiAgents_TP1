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
	
	private boolean TORUS = false;
	
	public Configs() {
		// Read configs file
		Gson gson = new Gson();
		JsonObject json;
		try {
			URL url = getClass().getResource("../settings.json");
			File file = new File(url.getPath());
			json = gson.fromJson(new FileReader(file), JsonObject.class);
			TORUS = json.get("torus").getAsBoolean();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public boolean isTorus() {
		return TORUS;
	}
	
	public void setTorus(boolean torus) {
		TORUS = torus;
	}
	
}
