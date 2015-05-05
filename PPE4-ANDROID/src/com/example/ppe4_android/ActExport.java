package com.example.ppe4_android;

import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class ActExport extends Activity {

	private String sVisite="";
    public AsyncTask<String, String, Boolean> mThreadCon = null;
	private Button IBouton = null;
    private modele model= new modele();
		@Override
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_export);



            IBouton = (Button)findViewById(R.id.vexport);
            IBouton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    ArrayList<Visite> listVisite = model.listeVisite();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.S")
                            .create();
                    int i = 0;
                    for (Visite visite : listVisite) {
                        visite.setCommentaireVisite(new String(visite.getCommentaireVisite().getBytes(), Charset.forName("UTF-8")));
                        sVisite = sVisite + gson.toJson(visite);
                        i++;
                        if (i < listVisite.size()) {
                            sVisite = sVisite + "@@@";
                        }
                    }
                    String[] mesparams = { "http://erwanquin1.freeheberg.org/export.php",sVisite};
                    mThreadCon = new Async (ActExport.this).execute(mesparams);
                    model.deleteVisite();
                }
            });

        }

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
	
	public void retourExport(StringBuilder sb)
	{

		if (sb.toString().compareToIgnoreCase("Ok")==0)
		{
			modele modele = new modele();

		}
		else 
		{
			Toast.makeText(getApplicationContext(),	"L'export c'est mal passer",Toast.LENGTH_LONG).show();
		}
		
	}

    public void alertmsg(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(msg)
                .setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent myIntent = new Intent(getApplicationContext(), AfficheListeVisite.class);
                startActivity(myIntent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.
                TYPE_SYSTEM_ALERT);
        dialog.show();
    }
}
