package ciberscanner.app.model;

import java.util.ArrayList;
import android.util.Log;
import ciberscanner.app.utilities.VJson;

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
	public ArrayList<String> pictures = new ArrayList<String>();

	private String[] labels = { "im:name", "im:image", "summary", "im:price", "im:contentType", "rights", "link", "id",
			"im:artist", "category", "im:releaseDate" };

	public ArrayList<App> listaApps = new ArrayList<App>();
	public Boolean errorJson = true;
	private VJson vjson = new VJson();

	// -----------------------------------------------------------------------------------
	//
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
	 * Retorno 1 para json procesado correctamente, Retorna -1 si hay problemas
	 */
	public int getAppsfromJson(String json) {
		
		if(vjson.fillObjectJsonfromJson(json, labels)==1){
			for (int i = 0; i < vjson.lista.size(); i++) {
				App aux = new App();
				aux.setApp(vjson.lista.get(i).getData().get(0), vjson.lista.get(i).getData().get(1),
						vjson.lista.get(i).getData().get(2), vjson.lista.get(i).getData().get(3),
						vjson.lista.get(i).getData().get(4), vjson.lista.get(i).getData().get(5),
						vjson.lista.get(i).getData().get(6), vjson.lista.get(i).getData().get(7),
						vjson.lista.get(i).getData().get(8), vjson.lista.get(i).getData().get(9),
						vjson.lista.get(i).getData().get(10));

				filtros(aux);

				listaApps.add(aux);
			}
			return 1;
		}else{
			return -1;
		}
		
	}

	// -----------------------------------------------------------------------------------
	/** Filtra los json internos */
	private void filtros(App aux) {
		aux.setName(vjson.getSubJson(aux.getName(), "label"));

		ImageApp ima = new ImageApp();

		if (ima.getImagesFromJson(aux.getImage()) == 1) {
			for (int i = 0; i < ima.listimages.size(); i++) {
				aux.pictures.add(ima.listimages.get(i).getUrl());
			}
		}
		
		aux.setRights(vjson.getSubJson(aux.getRights(), "label"));
		aux.setArtist(vjson.getSubJson1(aux.getArtist(), "label"));
		aux.setCategory(vjson.getSubJson(aux.getCategory(), "label"));
		aux.setSumary(vjson.getSubJson(aux.getSumary(), "label"));
		aux.setLink(vjson.getSubJson(aux.getLink(), "href"));
		
		aux.setPrice(vjson.getSubJson(vjson.removeString(aux.getPrice()), "amount"));
		
		

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