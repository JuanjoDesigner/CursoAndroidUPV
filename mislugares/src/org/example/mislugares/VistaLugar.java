package org.example.mislugares;

import java.text.DateFormat;
import java.util.Date;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class VistaLugar extends Activity {
	private long id;
	private Lugar lugar;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = Lugares.elemento((int) id);
        TextView txtTituloVL = (TextView) findViewById(R.id.txtTituloVL);
        txtTituloVL.setText(lugar.getNombre());
        //ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
        //logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView txtTipoVL = (TextView) findViewById(R.id.txtTipoVL);
        txtTipoVL.setText(lugar.getTipo().getTexto());
        TextView txtDireccionVL = (TextView) findViewById(R.id.txtDireccionVL);
        txtDireccionVL.setText(lugar.getDireccion());
        TextView txtTelefonoVL = (TextView) findViewById(R.id.txtTelefonoVL);
        txtTelefonoVL.setText(Integer.toString(lugar.getTelefono()));
        TextView txtUrlVL = (TextView) findViewById(R.id.txtUrlVL);
        txtUrlVL.setText(lugar.getUrl());
        TextView txtComentarioVL = (TextView) findViewById(R.id.txtComentarioVL);
        txtComentarioVL.setText(lugar.getComentario());
        TextView txtFechaVL = (TextView) findViewById(R.id.txtFechaVL);
        txtFechaVL.setText(DateFormat.getDateInstance().format(
                           new Date(lugar.getFecha())));
        TextView txtHoraVL = (TextView) findViewById(R.id.txtHoraVL);
        txtHoraVL.setText(DateFormat.getTimeInstance().format(
                           new Date(lugar.getFecha())));
        RatingBar ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar1.setRating(lugar.getValoracion());
        ratingBar1.setOnRatingBarChangeListener(
            new OnRatingBarChangeListener() {
                @Override public void onRatingChanged(RatingBar ratingBar,
                                                float valor, boolean fromUser) {
                    lugar.setValoracion(valor);
                }
        });
    }
}
