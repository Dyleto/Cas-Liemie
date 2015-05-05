package com.example.ppe4_android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Async extends AsyncTask<String, String, Boolean> {

	private WeakReference<Activity> activityAppelante = null;
	private String classActivityAppelante;
	private StringBuilder stringBuilder = new StringBuilder();

	
    public Async (Activity pActivity) {
    	activityAppelante = new WeakReference<Activity>(pActivity);
    	classActivityAppelante = pActivity.getClass().toString();


      }

    @Override
    protected void onPreExecute () {// Au lancement, on envoie un message à l'appelant
	if (activityAppelante.get() != null)
			Toast.makeText(activityAppelante.get(), "Thread on démarre",
					Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onPostExecute (Boolean result) {
      if (activityAppelante.get() != null) {
		if (result) {
				Toast.makeText(activityAppelante.get(), "Fin ok",
						Toast.LENGTH_SHORT).show();
//pour exemple on appelle une méthode de l'appelant qui va gérer la fin ok du thread
			if (classActivityAppelante.contains("ActImport"))
    			{

    				((ActImport) activityAppelante.get()).retourImport (stringBuilder);

                    ((ActImport) activityAppelante.get()).alertmsg ("Importation","L'importation s'est déroulé avec succés");

    			}
			if (classActivityAppelante.contains("ActExport"))
			{
				//((ActExport) activityAppelante.get()).retourExport (stringBuilder);
			}
    		}
        	else 
          		Toast.makeText(activityAppelante.get(), "Fin ko",
						Toast.LENGTH_SHORT).show();		}
    }

    @Override
    protected Boolean doInBackground (String... params) {// Exécution en arrière plan

    	String vUrl=""; String vlistpatient="";String vlistvisite="";

  	if (classActivityAppelante.contains("ActImport")) {
    	        	vUrl = params[0];
		}
    	if (classActivityAppelante.contains("ActExport")) {
            vUrl = params[1];
            vlistpatient = params[1];
    			}

    	HttpURLConnection urlConnection = null;
		try {

			URL url = new URL(vUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");

			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			urlConnection.setConnectTimeout(2000);
			OutputStreamWriter out = new OutputStreamWriter(
					urlConnection.getOutputStream());
			
			if (classActivityAppelante.contains("ActExport") ) {
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("listeVisite", vlistvisite);
                out.write(jsonParam.toString());
                out.flush();
			}
			out.close();
			
			//if (classActivityAppelante.contains("ActExport") ) {
			//	JSONObject jsonParam = new JSONObject();
			//	jsonParam.put("listpatient", vlistpatient);
			//	out.write(jsonParam.toString());
			//	out.flush();
			//}
			//out.close();
			
int HttpResult = urlConnection.getResponseCode();
		
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
			}
			br.close();
			//String[] vstring0 = { "Reçu du serveur",stringBuilder.toString() };
		//	publishProgress(vstring0);

		} else {
			String[] vstring0 = { "Erreur",
					urlConnection.getResponseMessage() };
			publishProgress(vstring0);
		}
		
	} catch (MalformedURLException e) {

		String[] vstring0 = { "Erreur", "Pbs url" };
		publishProgress(vstring0);
		return false;
	} catch (java.net.SocketTimeoutException e) {
		String[] vstring0 = { "Erreur", "temps trop long" };
		publishProgress(vstring0);
		return false;
	} catch (IOException e) {

		String[] vstring0 = { "Erreur", "Pbs IO" };
		publishProgress(vstring0);
		return false;
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (urlConnection != null)
			urlConnection.disconnect();
	}
return true;
    }
 @Override
	protected void onProgressUpdate(String... param) {
		// utilisation de on progress pour afficher des message pendant le
		// doInBackground

		if (classActivityAppelante.contains("ActImport")) {
			((ActImport) activityAppelante.get()).alertmsg (
					param[0].toString(), param[1].toString());
		}
		
 }

    @Override
    protected void onCancelled () {
      if(activityAppelante.get() != null)
        Toast.makeText(activityAppelante.get(), "Annulation", Toast.LENGTH_SHORT).show();
    }
}



