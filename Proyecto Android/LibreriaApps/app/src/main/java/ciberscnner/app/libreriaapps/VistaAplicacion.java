package ciberscnner.app.libreriaapps;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class VistaAplicacion extends Activity {
	// -----------------------------------------------------------------------------------
	// Variables
	private TextView title,descripcion, propietario,pagina;
	private String[] datos = { "", "", "","","","","","","" };
	private ImageView foto;
	// -----------------------------------------------------------------------------------
	// Constructor

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vistaaplicacion);
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		title=(TextView)findViewById(R.id.txt_title);
		descripcion=(TextView)findViewById(R.id.txt_des);
		propietario=(TextView)findViewById(R.id.txt_creador);
		pagina=(TextView)findViewById(R.id.visitpage);
		
		pagina.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				gotoPage(datos[6]);
			}
		});
		
		
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
	private void gotoPage(String url){
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
	}
	public void volver(View v){
		overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
		finish();
	}
}