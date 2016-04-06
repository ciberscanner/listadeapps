package ciberscanner.app.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import ciberscanner.app.utilities.ObjectJson;

public class App {
	// -----------------------------------------------------------------------------------
	// Variables

	private String name;
	private String image;
	private String sumary;
	private String price;
	private String contentype;
	private String rights;
	private String link;
	private String id;
	private String artist;
	private String category;
	private String relasedate;

	private String[] labels = { "im:name", "im:image", "summary", "im:price", "im:contentType", "rights", "link", "id",
			"im:artist", "category", "im:releaseDate" };

	public ArrayList<App> listaApps = new ArrayList<App>();
	private ArrayList<ObjectJson> lista = new ArrayList<ObjectJson>();

	public Boolean errorJson = true;

	// -----------------------------------------------------------------------------------
	// Constructor

	public void setApp(String name, String image, String sumary, String price, String contentype, String rights,
			String link, String id, String artist, String category, String relasedate) {
		this.name = name;
		this.image = image;
		this.sumary = sumary;
		this.price = price;
		this.contentype = contentype;
		this.rights = rights;
		this.link = link;
		this.id = id;
		this.artist = artist;
		this.category = category;
		this.relasedate = relasedate;
	}

	// -----------------------------------------------------------------------------------
	/**
	 * Retorno 1 para json procesado correctamente, Retorna -1 si le llego un
	 * json vacio, Retorna -2 por error en el casting
	 */
	public int getAppsfromJson(String json) {
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
				App aux = new App();
				aux.setApp(lista.get(i).getData().get(0), lista.get(i).getData().get(1), lista.get(i).getData().get(2),
						lista.get(i).getData().get(3), lista.get(i).getData().get(4), lista.get(i).getData().get(5),
						lista.get(i).getData().get(6), lista.get(i).getData().get(7), lista.get(i).getData().get(8),
						lista.get(i).getData().get(9), lista.get(i).getData().get(10));

				filtros(aux);

				listaApps.add(aux);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
		return 1;
	}

	// -----------------------------------------------------------------------------------
	/**Filtra los json internos*/
	private void filtros(App aux) {
		aux.setName(getSubJson(aux.getName(), "label"));
		aux.setCategory(getSubJson(aux.getCategory(), "label"));
		aux.setSumary(getSubJson(aux.getSumary(), "label"));

	}

	// -----------------------------------------------------------------------------------
	//
	private String getSubJson(String json, String label) {
		try {
			JSONObject jsonObject = new JSONObject(removeString(json));
			//Log.v("Resultado: ", jsonObject.getString(label));
			return jsonObject.getString(label);
		} catch (Exception ex) {
			Log.v("Error: ", "pailas");
			return "";
		}
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
	// Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSumary() {
		return sumary;
	}

	public void setSumary(String sumary) {
		this.sumary = sumary;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContentype() {
		return contentype;
	}

	public void setContentype(String contentype) {
		this.contentype = contentype;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRelasedate() {
		return relasedate;
	}

	public void setRelasedate(String relasedate) {
		this.relasedate = relasedate;
	}

	public void printf() {
		Log.v("name", name);
	}

}