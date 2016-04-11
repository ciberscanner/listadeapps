package ciberscanner.app.utilities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class VJson {
	// -----------------------------------------------------------------------------------
	// Variables
	public ArrayList<ObjectJson> lista = new ArrayList<ObjectJson>();

	// -----------------------------------------------------------------------------------
	// Constructor
	public VJson() {
		//lista = new ArrayList<ObjectJson>();
	}

	// -----------------------------------------------------------------------------------
	//
	public int fillObjectJsonfromJson(String json, String[] labels) {
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

		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
		return 1;
	}

	// -----------------------------------------------------------------------------------
	// Delete header
	public String removeString(String txt) {
		int aux = 0;
		for (int i = 0; i < txt.length(); i++) {
			if (txt.charAt(i) == '{') {
				aux = i;
			}
		}
		return txt.substring(aux);
	}

	// -----------------------------------------------------------------------------------
	//
	public String getSubJson(String json, String label) {
		try {
			JSONObject jsonObject = new JSONObject(removeString(json));
			return jsonObject.getString(label);
		} catch (Exception ex) {
			Log.v("Error: ", "pailas" + ex);
			return "";
		}
	}
	// -----------------------------------------------------------------------------------
		//
		public String getSubJson1(String json, String label) {
			try {
				JSONObject jsonObject = new JSONObject(json);
				return jsonObject.getString(label);
			} catch (Exception ex) {
				Log.v("Error: ", "pailas" + ex);
				return "";
			}
		}

}
