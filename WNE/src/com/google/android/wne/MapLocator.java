/**
 * 
 */
package com.google.android.wne;

import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Point;

/**
 * @author Jose Enrique
 *
 */
public class MapLocator extends MapActivity {

	protected MapView MV;
	
	
	/**
	 * 
	 */
	public MapLocator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle icicle) {
	super.onCreate(icicle);

	//creamos una vista de mapa
	 MV = new MapView(this);
	//creamos un controlador de ese mapa
	MapController MC = MV.getController();
	//Especificamos el zoom con el que lo vamos a mostrar
	MC.zoomTo(15);

	//Unas coordenadas, latitud longitud en gratos * 10^6
	Point point = new Point(48811100, 2328000);
	//centramos la vista del mapa en este punto
	MC.centerMapTo(point, true);

	//le decimos que lo muestre
	setContentView(MV);
	}
	 /* Controlamos las acciones que se llevan a cabo, según que
	* tecla hemos pulsado.
	*/
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	// Zoom In
	if (keyCode == KeyEvent.KEYCODE_I) {
	// miramos que zoom hay, y le sumamos uno.
	int level = MV.getZoomLevel();
	MV.getController().zoomTo(level + 1);
	return true;
	}
	//Zoom Out
	else if (keyCode == KeyEvent.KEYCODE_O) {
	int level = MV.getZoomLevel();
	MV.getController().zoomTo(level - 1);
	return true;
	}
	//Activar/Desactivar la vista de satelite
	else if (keyCode == KeyEvent.KEYCODE_S) {
	MV.toggleSatellite();
	return true;
	}
	//Activar la capa de trafico
	else if (keyCode == KeyEvent.KEYCODE_T) {
	MV.toggleTraffic();
	return true;
	}
	return false;
	}
}
