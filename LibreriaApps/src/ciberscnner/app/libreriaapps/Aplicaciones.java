package ciberscnner.app.libreriaapps;


import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import ciberscanner.app.model.App;
import ciberscanner.app.model.List_Adapter;

public class Aplicaciones extends Activity {
	// -----------------------------------------------------------------------------------
	// Variables
	private App app;
	private ListView lista;
	private String filtro;
	private String json;
	private Context ctx;
	private TextView titulo;
	private ArrayList<App>listaAux;
	// -----------------------------------------------------------------------------------
	// Constructor

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aplicaciones);

		app = new App();
		Bundle p = getIntent().getExtras();
		json = p.getString("json");
		
		filtro = p.getString("filtro");
		ctx = this.getApplicationContext();
		titulo=(TextView)findViewById(R.id.txt_title);
		titulo.setText(filtro);
		listaAux=new ArrayList<App>();
		lista = (ListView) findViewById(R.id.lista_apps);

		if (app.getAppsfromJson(json) == 1) {
			FiltrarLista();
		}

	}

	// -----------------------------------------------------------------------------------
	//
	private void FiltrarLista() {
		for (int i = 0; i < app.listaApps.size(); i++) {
			if (app.listaApps.get(i).getCategory().equals(filtro)){
				Log.v("paso: ", i +"");
				listaAux.add(app.listaApps.get(i));
				app.listaApps.remove(i);
			}
		}
		setLista() ;
	}

	// -----------------------------------------------------------------------------------
	//
	private void setLista() {
		lista.setAdapter(new List_Adapter(this, R.layout.entrada, listaAux) {
			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {
					TextView texto_superior_entrada = (TextView) view
							.findViewById(R.id.txttitulo);
					if (texto_superior_entrada != null)
						texto_superior_entrada.setText(((App) entrada)
								.getName());

					TextView texto_medio_entrada = (TextView) view
							.findViewById(R.id.txtciudad);
					if (texto_medio_entrada != null)
						texto_medio_entrada.setText(((App) entrada)
								.getSumary());

					TextView texto_inferior_entrada = (TextView) view
							.findViewById(R.id.txtfecha);
					if (texto_inferior_entrada != null)
						texto_inferior_entrada.setText(((App) entrada)
								.getRelasedate());

					ImageView imagen_entrada = (ImageView) view
							.findViewById(R.id.imageView_imagen);
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

				String[] v = { listaAux.get(posicion).getName(),listaAux.get(posicion).getSumary(), 
						listaAux.get(posicion).getRights(),listaAux.get(posicion).pictures.get(1), 
						listaAux.get(posicion).getContentype(), listaAux.get(posicion).getPrice(),
						listaAux.get(posicion).getLink(), listaAux.get(posicion).getArtist(),
						listaAux.get(posicion).getRelasedate()};

				gotoActivity(v);
			}
		});
	}

	// -----------------------------------------------------------------------------------
	//
	private void gotoActivity(String[] v) {
		Intent ac0 = new Intent(this, VistaAplicacion.class);
		ac0.putExtra("data", v);
		startActivity(ac0);
	}

}