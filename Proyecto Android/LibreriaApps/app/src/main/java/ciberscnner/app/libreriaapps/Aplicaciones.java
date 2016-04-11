package ciberscnner.app.libreriaapps;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import ciberscanner.app.adapter.List_Adapter;
import ciberscanner.app.model.App;

public class Aplicaciones extends Activity {
	// -----------------------------------------------------------------------------------
	// Variables
	private App app;
	private ListView lista;
	private String filtro;
	private String json;
	private Context ctx;
	private TextView titulo;
	private ArrayList<App> listaAux;
	private GridView gd;
	// -----------------------------------------------------------------------------------
	// Constructor

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aplicaciones);
		
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}

		app = new App();
		Bundle p = getIntent().getExtras();
		json = p.getString("json");

		filtro = p.getString("filtro");
		ctx = this.getApplicationContext();
		titulo = (TextView) findViewById(R.id.txt_title);
		titulo.setText(filtro);
		listaAux = new ArrayList<App>();
		lista = (ListView) findViewById(R.id.lista_apps);
		gd = (GridView) findViewById(R.id.grid_apps);
		
		//getScreenOrientation();
		
		
		

		if (app.getAppsfromJson(json) == 1) {
			FiltrarLista();
		}

	}

	// -----------------------------------------------------------------------------------
	//
	private void FiltrarLista() {
		for (int i = 0; i < app.listaApps.size(); i++) {
			if (app.listaApps.get(i).getCategory().equals(filtro)) {
				Log.v("paso: ", i + "");
				listaAux.add(app.listaApps.get(i));
				app.listaApps.remove(i);
			}
		}
		setLista();
	}

	// -----------------------------------------------------------------------------------
	//
	private void setLista() {
		lista.setAdapter(new List_Adapter(this, R.layout.entrada, listaAux) {
			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {
					TextView texto_superior_entrada = (TextView) view.findViewById(R.id.txttitulo);
					if (texto_superior_entrada != null)
						texto_superior_entrada.setText(((App) entrada).getName());

					TextView texto_medio_entrada = (TextView) view.findViewById(R.id.txtciudad);
					if (texto_medio_entrada != null)
						texto_medio_entrada.setText(((App) entrada).getArtist());

					TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.txtfecha);
					if (texto_inferior_entrada != null)
						texto_inferior_entrada.setText("Precio: " + ((App) entrada).getPrice());

					ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
					if (imagen_entrada != null) {
						if (imagen_entrada != null) {
							Picasso.with(ctx).load(((App) entrada).pictures.get(1)).into(imagen_entrada);
						}
					}
				}
			}
		});

		gd.setAdapter(new List_Adapter(this, R.layout.item_grid, listaAux) {
			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {
					TextView texto_superior_entrada = (TextView) view.findViewById(R.id.txtgrid);
					if (texto_superior_entrada != null)
						texto_superior_entrada.setText(((App) entrada).getName());

					ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imggrid);
					if (imagen_entrada != null) {
						if (imagen_entrada != null) {
							Picasso.with(ctx).load(((App) entrada).pictures.get(1)).into(imagen_entrada);
						}
					}
				}
			}
		});

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {

				String[] v = { listaAux.get(posicion).getName(), listaAux.get(posicion).getSumary(),
						listaAux.get(posicion).getRights(), listaAux.get(posicion).pictures.get(1),
						listaAux.get(posicion).getContentype(), listaAux.get(posicion).getPrice(),
						listaAux.get(posicion).getLink(), listaAux.get(posicion).getArtist(),
						listaAux.get(posicion).getRelasedate() };

				gotoActivity(v);
			}
		});

		gd.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {

				String[] v = { listaAux.get(posicion).getName(), listaAux.get(posicion).getSumary(),
						listaAux.get(posicion).getRights(), listaAux.get(posicion).pictures.get(1),
						listaAux.get(posicion).getContentype(), listaAux.get(posicion).getPrice(),
						listaAux.get(posicion).getLink(), listaAux.get(posicion).getArtist(),
						listaAux.get(posicion).getRelasedate() };

				gotoActivity(v);
			}
		});
	}

	public int getScreenOrientation() {

		// Query what the orientation currently really is.
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			showList();
			return 1; // Portrait Mode

		} else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			showGrid();
			return 2; // Landscape mode
		}
		return 0;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		// Checks the orientation of the screen
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
			showGrid();

		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			// Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
			showList();
		}
	}

	private void showList() {
		lista.setVisibility(View.VISIBLE);
		gd.setVisibility(View.INVISIBLE);
	}

	private void showGrid() {
		lista.setVisibility(View.INVISIBLE);
		gd.setVisibility(View.VISIBLE);
	}

	// -----------------------------------------------------------------------------------
	//
	private void gotoActivity(String[] v) {
		Intent ac0 = new Intent(this, VistaAplicacion.class);
		ac0.putExtra("data", v);
		startActivity(ac0);
		overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	public void volver(View v){
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}

}