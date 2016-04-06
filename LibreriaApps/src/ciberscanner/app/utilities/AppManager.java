package ciberscanner.app.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class AppManager {
	// -----------------------------------------------------------------------------------
	// Variables
	private Context context;
	private SharedPreferences mPreferences;
	private SharedPreferences.Editor mEditor;

	// -----------------------------------------------------------------------------------
	//
	public AppManager(Context ct) {
		this.context = ct;
		this.mPreferences = context.getSharedPreferences("Libreriadeapss", Context.MODE_PRIVATE);
		this.mEditor = mPreferences.edit();
	}

	// -----------------------------------------------------------------------------------
	//
	public void saveJson(String json) {
		mEditor.putString("json", json);
		mEditor.putBoolean("savejson", true);
		mEditor.apply();
	}

	// -----------------------------------------------------------------------------------
	//
	public String getJson() {
		boolean savejson = mPreferences.getBoolean("savejson", false);
		if (savejson)
			return mPreferences.getString("json", "");
		else
			return "";

	}

}
