package com.example.ppe4_android;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ActImport extends Activity {

	private Button mBouton = null;
	public AsyncTask<String, String, Boolean> mThreadCon = null;
    private modele model= new modele();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_import);

		mBouton = (Button)findViewById(R.id.vimport);
		mBouton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String[] mesparams = { "http://erwanquin1.freeheberg.org/Visite.php" };
				mThreadCon = new Async (ActImport.this).execute(mesparams);
			}
		});
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
	               // User clicked OK button
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
        ArrayList<Visite> listVisite = new ArrayList<Visite>();
        for (JsonElement obj : varray) {
            Visite visite = gson.fromJson(obj.getAsJsonObject(), Visite.class);
            listVisite.add(visite);

        }
        model.deleteVisite();
        model.addVisite(listVisite);


    }


	
	}


