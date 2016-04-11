package ciberscanner.app.model;

import java.util.ArrayList;

import ciberscanner.app.utilities.VJson;

public class ImageApp {
	// -----------------------------------------------------------------------------------
	// Variables
	private String url;
	private String description;
	private String[] labels = { "label", "attributes" };

	public ArrayList<ImageApp> listimages = new ArrayList<ImageApp>();
	private VJson vjson = new VJson();
	// -----------------------------------------------------------------------------------
	//
	public void setImageApp(String url, String description) {
		this.url = url;
		this.description = description;
	}

	// -----------------------------------------------------------------------------------
	//
	public int getImagesFromJson(String json) {
		if (vjson.fillObjectJsonfromJson(json, labels) == 1) {
			for (int i = 0; i < vjson.lista.size(); i++) {
				ImageApp aux = new ImageApp();
				aux.setImageApp(vjson.lista.get(i).getData().get(0), vjson.lista.get(i).getData().get(1));

				listimages.add(aux);
			}
			return 1;
		} else {
			return -1;
		}
	}

	// -----------------------------------------------------------------------------------
	// Setters and Getters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}