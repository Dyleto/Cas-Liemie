package com.example.ppe4_android;

/**
 * Created by Erwan on 23/04/2015.
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Activity {
    Button con, decon;
    EditText et,pass;
    TextView tv;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;


    /**********************************/
  /* Enregistrement des préférences */
    /**********************************/

    public static final String PREFS_NAME = ".Preferences";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_CHECKED = "checked";
    private CheckBox checkBox;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        con = (Button) findViewById(R.id.btnLogin);

        et = (EditText) findViewById(R.id.loginUser);
        pass = (EditText) findViewById(R.id.loginPassword);
        tv = (TextView) findViewById(R.id.tv);
        checkBox = (CheckBox) findViewById(R.id.cbRememberMe);

        /***********************************************************************/
    /* Restauration des préférences sauvegardées si la checkbox est cochée */
        /***********************************************************************/

        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String email = pref.getString(PREF_EMAIL, "");
        String checked = pref.getString(PREF_CHECKED, "");

        et.setText(email);
        checkBox.setChecked(Boolean.parseBoolean(checked));



        con.setOnClickListener(

                new Button.OnClickListener() {
                    public void onClick(View v) {
                        /************************************************************/
        /* Enregistrement des préférences si la checkbox est cochée */
                        /************************************************************/

                        if (checkBox.isChecked()) {
                            getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                                    .edit()
                                    .putString(PREF_EMAIL, et.getText().toString())
                                    .putString(PREF_CHECKED, "TRUE")
                                    .commit();
                        }

                        /***********************/
        /* Sinon on les efface */
                        /***********************/

                        else if (!checkBox.isChecked()) {
                            getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit().clear().commit();
                        }

                        dialog = ProgressDialog.show(Login.this, "",
                                "En cours...", true);
                        new Thread(new Runnable() {
                            public void run() {
                                doLogin();
                            }
                        }).start();

                    }
                }
        );



    }



    void doLogin(){
        try{

            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://erwanquin1.freeheberg.org/Connexion.php"); // make sure the url is correct.
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
            nameValuePairs.add(new BasicNameValuePair("username",et.getText().toString().trim())); // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("password",pass.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
            // edited by James from coderzheaven.. from here....
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
                    //tv.setText("Response from PHP : " + response);
                    dialog.dismiss();
                }
            });

            if(response.equalsIgnoreCase("User Found")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(Login.this,"Connexion réussie", Toast.LENGTH_SHORT).show();
                    }
                });

                startActivity(new Intent(Login.this, UserPage.class));
            }else{
                showAlert();
            }

        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }



    public void showAlert(){
        Login.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Login Error.");
                builder.setMessage("User not Found.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list:
                Toast.makeText(getApplicationContext(),	"clic sur list",Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(getApplicationContext(), AfficheListeVisiteOFF.class);
                startActivity(myIntent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

}