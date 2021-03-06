package com.example.ppe4_android;

import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class AfficheListeVisite extends Activity {

	
	private ListView listView;
	public ArrayList<Visite> listeVisite;
	public modele mon_modele;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		mon_modele= new modele();
		super.onCreate(savedInstanceState);
        listeVisite = mon_modele.listeVisite();
		setContentView(R.layout.activity_affiche_liste_visite);
		
		listView = (ListView)findViewById(R.id.lvListe);
        VisiteAdapter visiteAdapter = new VisiteAdapter(this, listeVisite);
		listView.setAdapter(visiteAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			 public void onItemClick(AdapterView<?> a, View v, int position, long id) {

			Intent myIntent = new Intent(getApplicationContext(), ModificationVisite.class);
			myIntent.putExtra("param1", listeVisite.get(position).getIdV());
			startActivity(myIntent);

			}
			}); 

	
	}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.planning, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Menu_Principal:

                Intent myIntent = new Intent(getApplicationContext(), UserPage.class);
                startActivity(myIntent);
                return true;

            case R.id.menu_import:

                Intent myIntent2 = new Intent(getApplicationContext(), ActImport.class);
                startActivity(myIntent2);
                return true;
            case R.id.menu_export:


                Intent myIntent3 = new Intent(getApplicationContext(), ActExport.class);
                startActivity(myIntent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
