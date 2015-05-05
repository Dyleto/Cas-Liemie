package com.example.ppe4_android;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModificationVisite extends Activity {

	Visite p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		Bundle b = getIntent().getExtras();
		String param1 = b.getString("param1"); 
		Toast.makeText(getApplicationContext(), param1, Toast.LENGTH_LONG)
		.show();

		p =(new modele()).trouveVisite(param1);
		
		TextView textView = (TextView) findViewById(R.id.c_idC);
		textView.setText(p.getIdV());
		textView = (TextView) findViewById(R.id.Nom);
		textView.setText(p.getNom());
		textView = (TextView) findViewById(R.id.Prenom);
		textView.setText(p.getPrenom());
		textView = (TextView) findViewById(R.id.AdrRue);
		textView.setText(p.getAdresse());
		textView = (TextView) findViewById(R.id.CP);
		textView.setText(p.getCodePostal());
		textView = (TextView) findViewById(R.id.Ville);
		textView.setText(p.getVille());

        textView = (TextView) findViewById(R.id.Civilite);
        textView.setText(p.getSexe());

        textView = (TextView) findViewById(R.id.DatePrev);
        String sDPB = p.getDatePrevuDeb().toString();
        sDPB = String.format("%s/%s/%s %s:%s", sDPB.substring(8, 10),
                sDPB.substring(5, 7), sDPB.substring(0, 4), sDPB.substring(11, 13),
                sDPB.substring(14, 16));

        textView.setText(sDPB);
		//textView = (TextView) findViewById(R.id.DatePrev);

		//
		// textView.setText(new SimpleDateFormat("dd/MM/yyyy").format(p.getDatePrevuDeb()));
		textView = (TextView) findViewById(R.id.Tel);

		String sTelephone = p.getTelephone().toString();
		//sTelephone = String.format("%s.%s.%s.%s.%s",
		//		sTelephone.substring(0, 2), sTelephone.substring(2, 4),
		//		sTelephone.substring(4, 6), sTelephone.substring(6, 8),
		//		sTelephone.substring(8, 10));
		textView.setText(sTelephone);
		EditText editText = (EditText) findViewById(R.id.Commentaire_text);
		editText.setText(p.getCommentaireVisite());
		
		Button buttonCancel = (Button) findViewById(R.id.bcanc);
		buttonCancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		Button buttonOk = (Button) findViewById(R.id.bok);
		buttonOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				p.setCommentaireVisite(((EditText) findViewById(R.id.Commentaire_text))
								.getText().toString());
				(new modele()).saveVisite(p);
				finish();
			}
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.modification_patient, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
