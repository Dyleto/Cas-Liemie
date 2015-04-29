package com.example.ppe4_android;

import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActExport extends Activity {

	private String sPatient="";
	
/*		@Override
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_export);
		modele modele = new modele();
		ArrayList<Patient> listPatient = modele.listePatient();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.S").create();
		int i = 0;
		for (Patient vpatient : listPatient) {
vpatient.setCommentaireVisite(new String(vpatient.getCommentaireVisite().getBytes(),Charset.forName("UTF-8")));
			sPatient = sPatient + gson.toJson(vpatient);
			i++;
			if (i < listPatient.size()) {
				sPatient = sPatient + "@@@";
			}
		}

	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_export, menu);
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
	
	/*public void retourExport(StringBuilder sb)
	{

		if (sb.toString().compareToIgnoreCase("Ok")==0)
		{
			modele modele = new modele();
			modele.deletePatient();
		}
		else 
		{
			Toast.makeText(getApplicationContext(),	"L'export c'est mal passer",Toast.LENGTH_LONG).show();
		}
		
	}*/
}
