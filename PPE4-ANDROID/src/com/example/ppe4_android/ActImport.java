package com.example.ppe4_android;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ActImport extends Activity {

	private Button mBouton = null;
	public AsyncTask<String, String, Boolean> mThreadCon = null;
    private modele model= new modele();
    public static final String PREFS_NAME = ".Preferences";
    private static final String PREF_EMAIL = "email";
    private String nom="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {



		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_import);

        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        nom = pref.getString(PREF_EMAIL, "");


				String[] mesparams = { "http://erwanquin1.freeheberg.org/Visite.php",nom };
				mThreadCon = new Async (ActImport.this).execute(mesparams);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_import, menu);
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

	public void retourImport(StringBuilder sb) {
        JsonElement json = new JsonParser().parse(sb.toString());
        JsonArray varray = json.getAsJsonArray();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();

        ArrayList<Visite> listeVisite = new ArrayList<Visite>();
        for (JsonElement obj : varray) {
            Visite visite = gson.fromJson(obj.getAsJsonObject(), Visite.class);
            listeVisite.add(visite);

        }
        model.deleteVisite();
        model.addVisite(listeVisite);


    }


	
	}


