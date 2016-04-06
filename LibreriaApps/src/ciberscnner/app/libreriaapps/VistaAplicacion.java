package ciberscnner.app.libreriaapps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VistaAplicacion extends Activity {
	// -----------------------------------------------------------------------------------
	// Variables
	private TextView title;
	private String[] datos = { "", "", "","","","","","" };
	private ImageView foto;
	// -----------------------------------------------------------------------------------
	// Constructor

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vistaaplicacion);
		title=(TextView)findViewById(R.id.txt_title);
		foto=(ImageView)findViewById(R.id);
		
	}
	// -----------------------------------------------------------------------------------
		//
}