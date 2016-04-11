package ciberscnner.app.libreriaapps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import ciberscanner.app.adapter.List_Adapter;
import ciberscanner.app.model.App;

public class Categorias extends Activity {
	// -----------------------------------------------------------------------------------
	// Variables
	private String json;
	private App app;
	private TextView title;
	private HashMap<String, String> map;
	private ArrayList<String> categorias;
	private ListView lista;

	// -----------------------------------------------------------------------------------
	//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categorias);
		
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}

		title = (TextView) findViewById(R.id.txt_title);
		title.setText("Categorías");

		map = new HashMap<String, String>();
		app = new App();

		Bundle p = getIntent().getExtras();
		json = p.getString("json");
		categorias = new ArrayList<String>();

		lista = (ListView) findViewById(R.id.listacategorias);

		if (app.getAppsfromJson(json) == 1) {
			getCategories();
		}
	}

	// -----------------------------------------------------------------------------------
	//
	private void getCategories() {
		for (int i = 0; i < app.listaApps.size(); i++) {
			map.put(app.listaApps.get(i).getCategory(), app.listaApps.get(i).getCategory());
		}

		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = it.next();
			categorias.add(e.getValue() + "");
		}

		lista.setAdapter(new List_Adapter(this, R.layout.item_categoria, categorias) {
			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {
					TextView texto_superior_entrada = (TextView) view.findViewById(R.id.txt_title);
					// if (texto_superior_entrada != null)
					texto_superior_entrada.setText(((String) entrada).toString());

				}
			}
		});

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {

				gotoActivity(json, categorias.get(posicion));
			}
		});
	}
	
	public void volver(View v){
		overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
		finish();
	}

	// -----------------------------------------------------------------------------------
	//
	private void gotoActivity(String mss, String filtro) {
		Intent ac0 = new Intent(this, Aplicaciones.class);
		ac0.putExtra("json", mss);
		ac0.putExtra("filtro", filtro);
		startActivity(ac0);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
	}

}