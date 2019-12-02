package com.example.capteursaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.owlike.genson.Genson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txtId;
    private EditText NomCapteur;
    private EditText PlaceCapteur;
    private EditText ValeurCapteur;
    private EditText updateCap;
    private EditText deleteCap;
    private EditText getCapteur;
    private Button btnGET;
    private Button btnPOST;
    private Button btnUPDATE;
    private Button btnDELETE;
    private Button btnGETALL;
    private EditText getallCap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = (EditText) findViewById(R.id.zone1);
        NomCapteur= (EditText) findViewById(R.id.zone2);
        PlaceCapteur= (EditText) findViewById(R.id.zone3);
        ValeurCapteur=(EditText) findViewById(R.id.zone4);
        getallCap=(EditText) findViewById(R.id.getallCapteur);
        updateCap=(EditText) findViewById(R.id.zoneup);
        deleteCap= (EditText) findViewById(R.id.zonedel);
        getCapteur= (EditText) findViewById(R.id.zoneget);

         findViewById(R.id.getbyid).setOnClickListener(this);
         findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.post).setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.get:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection urlConnection=null;
                    try{
                        URL url =new URL("http://192.168.1.8:8080/RestfullSmarthouse/rest/capteurs");
                        urlConnection=(HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("GET");

                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        Scanner scanner=new Scanner(in);
                        final String cap= scanner.nextLine();
                        Log.i("Exchange.JSON","Result=="+ cap);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                    getallCap.setText(cap);


                            }
                        });
                        in.close();



                    }catch (Exception e){
                        Log.i("Exchange.JSON", "Cannot find http server", e);

                    }finally {
                        if (urlConnection != null) urlConnection.disconnect();
                    }
                }

            }).start();
            break;
        case R.id.getbyid:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection urlConnection=null;
                    try{
                        URL url =new URL("http://192.168.1.8:8080/RestfullSmarthouse/rest/capteurs/"+getCapteur.getText());
                        urlConnection=(HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("GET");

                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        Scanner scanner=new Scanner(in);
                        final Capteurs capteurs= new Genson().deserialize(scanner.nextLine(),Capteurs.class);
                        Log.i("Exchange.JSON","Result=="+ capteurs);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtId.setText(""+capteurs.getIdCapteur());
                                NomCapteur.setText(capteurs.getNomCapteur());
                                PlaceCapteur.setText(capteurs.getPlaceCapteur());
                                ValeurCapteur.setText(""+capteurs.getValeur());
                                getallCap.setText(""+capteurs.toString());
                            }
                        });
                        in.close();



                    }catch (Exception e){
                        Log.i("Exchange.JSON", "Cannot find http server", e);

                    }finally {
                        if (urlConnection != null) urlConnection.disconnect();
                    }
                }

            }).start();
            break;

       case R.id.update:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Capteurs cap=new Capteurs(
                            Integer.parseInt(txtId.getText().toString()),
                            NomCapteur.getText().toString(),
                            PlaceCapteur.getText().toString(),
                            Double.parseDouble(ValeurCapteur.getText().toString()));
                    String message=new Genson().serialize(cap);
                    Log.i("Exchange.JSON", "Message =="+message);

                      HttpURLConnection urlConnection=null;
                    try{
                        URL url = new URL("http://192.168.1.8:8080/RestfullSmarthouse/rest/capteurs/"+updateCap.getText());
                        urlConnection =(HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("PUT");
                        urlConnection.setDoOutput(true);
                        urlConnection.setRequestProperty("Content-Type","application/json");
                        OutputStream out =new BufferedOutputStream(urlConnection.getOutputStream());
                        out.write(message.getBytes());
                        out.close();

                        InputStream in =new BufferedInputStream(urlConnection.getInputStream());
                        Scanner scanner =new Scanner(in);
                        Log.i("Exchange-JSON","Result ==" +scanner.nextLine());
                        in.close();
                    }catch (Exception e){
                        Log.e("Exchange.JSON", "Cannot find http server", e);
                    }finally {
                        if(urlConnection != null) urlConnection.disconnect();
                    }
                }
            }).start();
            break;

        case R.id.post:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Capteurs cap=new Capteurs(
                            Integer.parseInt(txtId.getText().toString()),
                            NomCapteur.getText().toString(),
                            PlaceCapteur.getText().toString(),
                            Double.parseDouble(ValeurCapteur.getText().toString()));
                    String message=new Genson().serialize(cap);
                    Log.i("Exchange.JSON", "Message =="+message);

                    HttpURLConnection urlConnection=null;
                    try{
                        URL url = new URL("http://192.168.1.8:8080/RestfullSmarthouse/rest/capteurs/");
                        urlConnection =(HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);
                        urlConnection.setRequestProperty("Content-Type","application/json");
                        OutputStream out =new BufferedOutputStream(urlConnection.getOutputStream());
                        out.write(message.getBytes());
                        out.close();

                        InputStream in =new BufferedInputStream(urlConnection.getInputStream());
                        Scanner scanner =new Scanner(in);
                        Log.i("Exchange-JSON","Result ==" +scanner.nextLine());
                        in.close();
                    }catch (Exception e){
                        Log.e("Exchange.JSON", "Cannot find http server", e);
                    }finally {
                        if(urlConnection != null) urlConnection.disconnect();
                    }
                }
            }).start();
            break;

        case R.id.delete:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection urlConnection=null;
                    try{
                        URL url =new URL("http://192.168.1.8:8080/RestfullSmarthouse/rest/capteurs/"+deleteCap.getText());
                        urlConnection=(HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("DELETE");

                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        Scanner scanner=new Scanner(in);
                        final Capteurs capteurs= new Genson().deserialize(scanner.nextLine(),Capteurs.class);
                        Log.i("Exchange.JSON","Result=="+ capteurs);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                getallCap.setText(""+capteurs.toString());
                            }
                        });
                        in.close();



                    }catch (Exception e){
                        Log.i("Exchange.JSON", "Cannot find http server", e);

                    }finally {
                        if (urlConnection != null) urlConnection.disconnect();
                    }
                }

            }).start();
            break;

    }
    }

}




