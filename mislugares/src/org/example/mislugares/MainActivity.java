package org.example.mislugares;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edicion_lugar);
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
