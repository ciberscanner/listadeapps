package ciberscanner.app.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import ciberscanner.app.utilities.ObjectJson;

public class ImageApp {
	// -----------------------------------------------------------------------------------
	// Variables
	private String url;
	private String description;
	private String[] labels = { "label", "attributes" };
	
	public ArrayList<ImageApp>listimages=new ArrayList<ImageApp>();
	private ArrayList<ObjectJson> lista = new ArrayList<ObjectJson>();
	// -----------------------------------------------------------------------------------
	//

	public void setImageApp(String url, String description) {
		this.url = url;
		this.description = description;
	}
	
	// -----------------------------------------------------------------------------------
		//
	public int getImagesFromJson(String json){
		//json=removeString(json);
		try {
			JSONArray jsonArray = new JSONArray(json);

			if (jsonArray.equals(null)) {
				Log.v("informe: ", "Problemas con el servidor");
				return -1;
			}
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				ObjectJson ojson = new ObjectJson();
				for (int j = 0; j < labels.length; j++) {
					ojson.getData().add(jsonObject.getString(labels[j]));
				}
				lista.add(ojson);
			}

			for (int i = 0; i < lista.size(); i++) {
				ImageApp aux = new ImageApp();
				aux.setImageApp(lista.get(i).getData().get(0), lista.get(i).getData().get(1));

				listimages.add(aux);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
		return 1;
	}
	
	private String removeString(String txt) {
		int aux = 0;
		for (int i = 0; i < txt.length(); i++) {
			if (txt.charAt(i) == '{') {
				aux = i;
			}
		}
		return txt.substring(aux);
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
