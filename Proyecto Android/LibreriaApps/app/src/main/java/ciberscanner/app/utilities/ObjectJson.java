package ciberscanner.app.utilities;

import java.util.ArrayList;

public class ObjectJson {
	// -----------------------------------------------------------------------------------
	// Variables
	private ArrayList<String> data;

	// -----------------------------------------------------------------------------------
	// Constructor
	public ObjectJson() {
		data = new ArrayList<String>();
	}

	// -----------------------------------------------------------------------------------
	// Setters and Getters
	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}

}