package ciberscnner.app.libreriaapps;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;
import ciberscanner.app.utilities.AppManager;
import ciberscanner.app.utilities.NetworkState;
import ciberscanner.app.utilities.Server;

public class Splash extends Activity {
	// -----------------------------------------------------------------------------------
	// Variables
	private NetworkState nt;
	private AppManager appm;
	private String myjson;

	// -----------------------------------------------------------------------------------
	//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		

		nt = new NetworkState(this.getApplicationContext());
		appm = new AppManager(this.getApplicationContext());
		myjson="";

		ValidarInternet();
	}

	// -----------------------------------------------------------------------------------
	/** Obtener un Json de una url */
	private void getStringUrl(String json) {
		StringRequest stringRequest = new StringRequest(json, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				myjson = removeHeader(response);
				appm.saveJson(myjson);
				new ProgressClass().execute();
				
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(Splash.this, error.getMessage(), Toast.LENGTH_LONG).show();
			}
		});

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);
	}

	// -----------------------------------------------------------------------------------
	/** Remueve la cabecera de un json */
	private String removeHeader(String result) {
		int start = 0;
		for (int i = 0; i < result.length(); i++) {
			if (result.charAt(i) == '[') {
				start = i;
				i = result.length();
			}
		}
		return result.substring(start);
	}

	// -----------------------------------------------------------------------------------
	/**
	 * Si hay internet descarga json, sino hay revisa si hay registro previo con
	 * mensaje, sino lanza mensaje
	 */
	private void ValidarInternet() {
		if (nt.isConnection()) {
			getStringUrl(Server.URL_JSON);
		} else if (!appm.getJson().isEmpty()) {
			Toast.makeText(Splash.this, "No tienes conexión a internert, alguna información puede estar desactualizada", Toast.LENGTH_LONG).show();
			myjson=appm.getJson();
			new ProgressClass().execute();
		} else {
			Toast.makeText(Splash.this, "No tienes conexión a internert, ni una copia previa de la app", Toast.LENGTH_LONG).show();
		}
	}

	// -----------------------------------------------------------------------------------
	//
	class ProgressClass extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(String... params) {
			int uno = 4;
			while (uno > 0) {
				SystemClock.sleep(500);
				uno--;
			}
			return "listo";
		}

		@Override
		protected void onPostExecute(String result) {
			gotoActivity(myjson);
		}
	}

	// -----------------------------------------------------------------------------------
	//
	private void gotoActivity(String mss) {
		Intent ac0 = new Intent(this, Categorias.class);
		ac0.putExtra("json", mss);
		startActivity(ac0);
		overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
		finish();
	}

}