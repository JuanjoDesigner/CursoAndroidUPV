package org.example.mislugares;

import android.app.ListActivity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity implements LocationListener {
	private LocationManager manejador;
	private Location mejorLocaliz;
	private String proveedor;
	public BaseAdapter adaptador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adaptador = new AdaptadorLugares(this);
		setListAdapter(adaptador);

		// activar la localización GPS y GSM
		manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			actualizaMejorLocaliz(manejador
					.getLastKnownLocation(LocationManager.GPS_PROVIDER));
		}
		if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			actualizaMejorLocaliz(manejador
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
		}
	}

	@Override
	protected void onListItemClick(ListView listView, View vista, int position,
			long id) {
		super.onListItemClick(listView, vista, position, id);
		Intent intent = new Intent(this, VistaLugar.class);
		intent.putExtra("id", id);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//
		switch (item.getItemId()) {
		case R.id.mnPreferencias:
			lanzarPreferencias(null);
			break;
		case R.id.menu_mapa:
			Intent i = new Intent(this, Mapa.class);
			startActivity(i);
			break;
		}

		return false;
	}

	public void lanzarPreferencias(View view) {
		// lanzamos el menu de preferencias basico
		Intent intent = new Intent(this, Preferencias.class);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		activarProveedores();
	}

	@Override
	protected void onPause() {
		super.onPause();
		manejador.removeUpdates(this);
	}

	private void activarProveedores() {
		if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			manejador.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					20 * 1000, 5, this);
		}

		if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
					10 * 1000, 10, this);
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d(Lugares.TAG, "Nueva localización: " + location);
		actualizaMejorLocaliz(location);
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.d(Lugares.TAG, "Se deshabilita: " + proveedor);
		activarProveedores();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.d(Lugares.TAG, "Se habilita: " + proveedor);
		activarProveedores();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d(Lugares.TAG, "Cambia estado: " + proveedor);
		activarProveedores();
	}

	private static final long DOS_MINUTOS = 2 * 60 * 1000;

	private void actualizaMejorLocaliz(Location localiz) {
		if (mejorLocaliz == null
				|| localiz.getAccuracy() < 2 * mejorLocaliz.getAccuracy()
				|| localiz.getTime() - mejorLocaliz.getTime() > DOS_MINUTOS) {
			Log.d(Lugares.TAG, "Nueva mejor localización");
			mejorLocaliz = localiz;
			Lugares.posicionActual.setLatitud(localiz.getLatitude());
			Lugares.posicionActual.setLongitud(localiz.getLongitude());
		}
	}
}
