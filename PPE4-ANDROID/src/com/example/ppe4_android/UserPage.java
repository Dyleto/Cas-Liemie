package com.example.ppe4_android;

/**
 * Created by Erwan on 26/04/2015.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TableLayout;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.ParseException;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class UserPage extends Activity {

    public static final String PREFS_NAME = ".Preferences";
    private static final String PREF_EMAIL = "email";
    Button deco, rdv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String Nom = pref.getString(PREF_EMAIL, "");

        deco = (Button) findViewById(R.id.btnLogout);
        rdv = (Button) findViewById(R.id.RDV);



        deco.setOnClickListener(

                new Button.OnClickListener() {

                    public void onClick(View v) {
                        startActivity(new Intent(UserPage.this, MainActivity.class));
                    }

                }
        );

        rdv.setOnClickListener(

                new Button.OnClickListener() {

                    public void onClick(View v) {
                        startActivity(new Intent(UserPage.this, AfficheListeVisite.class));
                    }

                }
        );




        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("visite", Nom));
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://erwanquin1.freeheberg.org/Visite_PL3.php");


        TextView textView = (TextView) findViewById(R.id.heure1);
        TextView textView2 = (TextView) findViewById(R.id.sexe1);
        TextView textView3 = (TextView) findViewById(R.id.nom1);
        TextView textView4 = (TextView) findViewById(R.id.adresse1);
        TextView textView5 = (TextView) findViewById(R.id.cp1);
        TextView textView6 = (TextView) findViewById(R.id.ville1);
        TextView textView7 = (TextView) findViewById(R.id.telephone1);


        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            JSONArray mArray = new JSONArray(jsonResult);
            for (int i = 0; i < mArray.length(); i++) {
                JSONObject object = mArray.getJSONObject(i);

                String nom = object.getString("nom");
                String s = object.getString("DatePrevuDeb");
                String heure;
                heure = String.format("%s/%s/%s %s:%s", s.substring(8, 10),
                        s.substring(5, 7), s.substring(0, 4), s.substring(11, 13),
                        s.substring(14, 16));


                String sexe = object.getString("sexe");

                if (sexe == "0") {
                    sexe = "Monsieur";
                }
                else  {
                    sexe = "Madame";
                }
                String adresse = object.getString("adrRue");
                String cp = object.getString("CP");
                String ville = object.getString("ville");
                String tel = object.getString("telephone");

                textView.setText(heure);
                textView2.setText(sexe);
                textView3.setText(nom);
                textView4.setText(adresse);
                textView5.setText(cp);
                textView6.setText(ville);
                textView7.setText(tel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        HttpPost httppost2 = new HttpPost("http://erwanquin1.freeheberg.org/VIsite_Pl2.php");


        TextView textView_5 = (TextView) findViewById(R.id.heure2);
        TextView textView2_5 = (TextView) findViewById(R.id.sexe2);
        TextView textView3_5 = (TextView) findViewById(R.id.nom2);
        TextView textView4_5 = (TextView) findViewById(R.id.adresse2);
        TextView textView5_5 = (TextView) findViewById(R.id.cp2);
        TextView textView6_5 = (TextView) findViewById(R.id.ville2);
        TextView textView7_5 = (TextView) findViewById(R.id.telephone2);

        try {
            httppost2.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost2);
            String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            JSONArray mArray = new JSONArray(jsonResult);
            for (int i = 0; i < mArray.length(); i++) {
                JSONObject object = mArray.getJSONObject(i);

                String nom = object.getString("nom");
                String s = object.getString("DatePrevuDeb");
                String heure;
                heure = String.format("%s/%s/%s %s:%s", s.substring(8, 10),
                        s.substring(5, 7), s.substring(0, 4), s.substring(11, 13),
                        s.substring(14, 16));

                String sexe2 = object.getString("sexe");

                if (sexe2 == "0") {
                    sexe2 = "Monsieur";
                }
                else  {
                    sexe2 = "Madame";
                }
                String adresse = object.getString("adrRue");
                String cp = object.getString("CP");
                String ville = object.getString("ville");
                String tel = object.getString("telephone");

                textView_5.setText(heure);
                textView2_5.setText(sexe2);
                textView3_5.setText(nom);
                textView4_5.setText(adresse);
                textView5_5.setText(cp);
                textView6_5.setText(ville);
                textView7_5.setText(tel);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private StringBuilder inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
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
            case R.id.menu_import:
                Toast.makeText(getApplicationContext(),	"clic sur import",Toast.LENGTH_LONG).show();
                Intent myIntent2 = new Intent(getApplicationContext(), ActImport.class);
                startActivity(myIntent2);
                return true;
            case R.id.menu_export:

                Toast.makeText(getApplicationContext(),	"clic sur export",Toast.LENGTH_LONG).show();
                Intent myIntent3 = new Intent(getApplicationContext(), ActExport.class);
                startActivity(myIntent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}