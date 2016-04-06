package ciberscanner.app.model;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class List_Adapter extends BaseAdapter {
	// -------------------------------------------------------
	// Variables
	private ArrayList<?> entradas;
	private int R_layout_IdView;
	private Context contexto;

	// ------------------------------------------------------
	//
	public List_Adapter(Context contexto, int r_layout_IdView,
			ArrayList<?> entradas) {
		super();
		this.entradas = entradas;
		R_layout_IdView = r_layout_IdView;
		this.contexto = contexto;
	}

	// ------------------------------------------------------
	//

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entradas.size();
	}

	// ------------------------------------------------------
	//
	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return entradas.get(pos);
	}

	// ------------------------------------------------------
	//
	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	// ------------------------------------------------------
	//
	@Override
	public View getView(int pos, View view, ViewGroup viewg) {
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R_layout_IdView, null);
		}
		onEntrada(entradas.get(pos), view);
		return view;
	}

	// ------------------------------------------------------
	//
	/**
	 * Devuelve cada una de las entradas con cada una de las vistas a la que
	 * debe de ser asociada
	 * 
	 * @param entrada
	 *            La entrada que ser� la asociada a la view. La entrada es del
	 *            tipo del paquete/handler
	 * @param view
	 *            View particular que contendr� los datos del paquete/handler
	 */
	public abstract void onEntrada(Object entrada, View view);

}