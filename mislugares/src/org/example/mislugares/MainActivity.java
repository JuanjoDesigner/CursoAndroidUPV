package org.example.mislugares;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	public BaseAdapter adaptador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adaptador = new AdaptadorLugares(this);
		setListAdapter(adaptador);
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
		}

		return false;
	}

	public void lanzarPreferencias(View view) {
		// lanzamos el menu de preferencias basico
		Intent intent = new Intent(this, Preferencias.class);
		startActivity(intent);
	}
}
