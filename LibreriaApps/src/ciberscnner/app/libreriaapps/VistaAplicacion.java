package ciberscnner.app.libreriaapps;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import ciberscanner.app.model.App;

public class VistaAplicacion extends Activity {
	// -----------------------------------------------------------------------------------
	// Variables
	private TextView title,descripcion, propietario;
	private String[] datos = { "", "", "","","","","","","" };
	private ImageView foto;
	// -----------------------------------------------------------------------------------
	// Constructor

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vistaaplicacion);
		
		title=(TextView)findViewById(R.id.txt_title);
		descripcion=(TextView)findViewById(R.id.txt_des);
		propietario=(TextView)findViewById(R.id.txt_creador);
		
		
		foto=(ImageView)findViewById(R.id.foto);
		
		Bundle bundle = getIntent().getExtras();
		datos = bundle.getStringArray("data");
		title.setText(datos[0]);
		descripcion.setText(datos[1]);
		Picasso.with(this).load(datos[3]).into(foto);
		propietario.setText(datos[2]);
		
	}
	// -----------------------------------------------------------------------------------
		//
}