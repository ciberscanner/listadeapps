package ciberscanner.app.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkState {
	// -----------------------------------------------------------------------------------
	// Variables
	Context ct;

	// -----------------------------------------------------------------------------------
	//
	public NetworkState(Context ct) {
		this.ct = ct;
	}

	// -----------------------------------------------------------------------------------
	/* Metodo que informa que retorna la primer conexion a red que encuentre */
	public String getTypeConnection() {
		if (MOBILConnection()) {
			return "Mobil";
		} else {
			if (WIFIConnection()) {
				return "WIFI";
			} else {
				return "There is no connection";
			}
		}
	}

	// -----------------------------------------------------------------------------------
	/* Metodo que informa si hay conexion true para si, false para no */
	public Boolean isConnection() {
		if (MOBILConnection() || WIFIConnection()) {
			return true;
		} else {
			return false;
		}
	}

	// -----------------------------------------------------------------------------------
	/* Comprueba si hay conexion movil */
	private Boolean MOBILConnection() {
		ConnectivityManager conn = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conn != null) {
			NetworkInfo ninfo = conn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (ninfo != null) {
				if (ninfo.isConnected()) {
					return true;
				}
			}
		}
		return false;
	}

	// -----------------------------------------------------------------------------------
	/* Comprueba si hay conexion WIFI */
	private Boolean WIFIConnection() {
		ConnectivityManager conn = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conn != null) {
			NetworkInfo ninfo = conn.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (ninfo != null) {
				if (ninfo.isConnected()) {
					return true;
				}
			}
		}
		return false;
	}
}