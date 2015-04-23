package com.example.ppe4_android;

import android.app.Activity;
import android.os.Bundle;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText inputEmail;
    EditText inputPassword;
    CheckBox checkBox;
    Button btnLogin;
   

    /****************/
  /* Réponse JSON */
    /****************/

    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";

    /**********************************/
  /* Enregistrement des préférences */
    /**********************************/

    public static final String PREFS_NAME = ".Preferences";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_CHECKED = "checked";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /********************************/
    /* Définit le nom de l'Activity */
        /********************************/

        setTitle("Login");

        /**********************************************************/
    /* Importation des caractéristiques des champs et boutons */
        /**********************************************************/

        inputEmail = (EditText) findViewById(R.id.loginUser);
        inputPassword = (EditText) findViewById(R.id.loginPassword);
        checkBox = (CheckBox) findViewById(R.id.cbRememberMe);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        /***********************************************************************/
    /* Restauration des préférences sauvegardées si la checkbox est cochée */
        /***********************************************************************/

        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String email = pref.getString(PREF_EMAIL, "");
        String password = pref.getString(PREF_PASSWORD, "");
        String checked = pref.getString(PREF_CHECKED, "");

        inputEmail.setText(email);
        inputPassword.setText(password);
        checkBox.setChecked(Boolean.parseBoolean(checked));

        /****************************/
    /* Clic sur le bouton Login */
        /****************************/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /************************************************************/
        /* Enregistrement des préférences si la checkbox est cochée */
                /************************************************************/

                if (checkBox.isChecked()) {
                    getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                            .edit()
                            .putString(PREF_EMAIL, inputEmail.getText().toString())
                            .putString(PREF_PASSWORD, inputPassword.getText().toString())
                            .putString(PREF_CHECKED, "TRUE")
                            .commit();
                }

                /***********************/
        /* Sinon on les efface */
                /***********************/

                else if (!checkBox.isChecked()) {
                    getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit().clear().commit();
                }

                /***************************************/
        /* On récupère le contenu des EditText */
                /***************************************/

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                /******************************************************/
        /* On construit la liste des paramètres de la requête */
                /******************************************************/

                ArrayList nameValuePairs = new ArrayList();
                nameValuePairs.add(new BasicNameValuePair("tag", "login"));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("password", password));

                try {
                    /********************************************/
          /* Exécute la requête vers le serveur local */
                    /********************************************/

                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://liemie.hol.es/_file-manager/elfinder.php?access=2.0.40.5_e272f3abaa01a43af8b38264ceeb883cf8317648/android_login_api.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = reader.readLine();
                    sb.append(line + "\n");
                    is.close();

                    /***************************/
          /* Résultats de la requête */
                    /***************************/

                    String result = sb.toString();
                    JSONObject jObj = new JSONObject(result);

                    /**********************************************/
          /* Si le résultat de la requête n'est pas nul */
                    /**********************************************/

                    if (jObj.getString(KEY_SUCCESS) != null) {
                        String res = jObj.getString(KEY_SUCCESS);

                        /********************************************/
            /* Si il vaut 1, l'utilisateur est connecté */
                        /********************************************/

                        if (Integer.parseInt(res) == 1) {
                            /***************************************/
              /* Stocke les infos dans la BDD SQLite */
                            /***************************************/

                            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                            db.resetTables();
                            db.addUser(jObj.getString(KEY_NAME), jObj.getString(KEY_EMAIL), jObj.getString(KEY_UID), jObj.getString(KEY_CREATED_AT));
                            Toast.makeText(getApplicationContext(), "Success Login", Toast.LENGTH_SHORT).show();

                            /***************************************/
              /* Lancement de l'Activity "DashBoard" */
                            /***************************************/

                            Intent Planning = new Intent(getApplicationContext(), PlanningActivity.class);
                            Planning.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(Planning);

                            /****************************/
              /* Ferme l'Activity "Login" */
                            /****************************/

                            finish();
                        }
                        /*************************************/
            /* Si il vaut 0, erreur de connexion */
                        /*************************************/

                        else {
                            Toast.makeText(getApplicationContext(), jObj.get("error_msg").toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {

                }
            }
        });

    }}