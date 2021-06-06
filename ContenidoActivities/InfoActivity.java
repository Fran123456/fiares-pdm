package com.fiares.ContenidoActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fiares.Models.Contenido;
import com.fiares.Models.ContenidoApi;
import com.fiares.R;
import com.fiares.Utility.DonwloadCompleteReceiver;
import com.fiares.Utility.Help;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoActivity extends AppCompatActivity {
   TextView titulo, des;
   YouTubePlayerView player;
   ImageButton btnpdf, btndonwload;
   ImageButton compartir;
   ImageButton ws, fb, twitter, btnExtern;
    private static final short REQUEST_CODE = 6545;
    public String name, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle("FIARES - CONTENIDO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String id = getIntent().getStringExtra("id");
        titulo = (TextView)findViewById(R.id.tituloContenido_unit);
        des = (TextView)findViewById(R.id.desContenido_unit);
        player = findViewById(R.id.youtube_player_view);
        btnpdf = findViewById(R.id.btnpdf);
        compartir= findViewById(R.id.compartirbtn);
        ws= findViewById(R.id.whatsappbtn);
        fb=findViewById(R.id.fbbtn);
        twitter = findViewById(R.id.twitterbtn);
        btndonwload=findViewById(R.id.downloadbtn);
        btnExtern = findViewById(R.id.btnExtern);
        contenido(id);


    }



    private void contenido(String id){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Help.url()).addConverterFactory(GsonConverterFactory.create()).build();
        ContenidoApi contenidoApi = retrofit.create( ContenidoApi.class);
        Call<Contenido> call =   contenidoApi.contenidoIndividual( Integer.parseInt(id) ,Help.key());
        Contenido contenidox = new Contenido();
        call.enqueue(new Callback<Contenido>() {
            @Override
            public void onResponse(Call<Contenido> call, Response<Contenido> response) {
                try{
                    if(response.isSuccessful()){
                        contenidox.setId(response.body().getId());
                        contenidox.setDescripcion(response.body().getDescripcion());
                        contenidox.setPdf(response.body().getPdf());
                        contenidox.setTitulo(response.body().getTitulo());
                        contenidox.setTemario_id(response.body().getTemario_id());
                        contenidox.setUrl(response.body().getUrl());
                        contenidox.setVideo(response.body().getVideo());
                        contenidox.setVistas(response.body().getVistas());
                        titulo.setText(  response.body().getTitulo()+".pdf"  );
                        des.setText(contenidox.getDescripcion());

                        if(contenidox.getDescripcion()==null ||contenidox.getDescripcion()=="") des.setVisibility(View.GONE);

                        if(response.body().getPdf() !=null){
                            player.setVisibility(View.GONE);

                            btnpdf.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try{
                                        Class<?>
                                                clase=Class.forName("com.fiares.ContenidoActivities.PDFActivity");
                                        Intent inte = new Intent(getApplicationContext(), clase);
                                        inte.putExtra("id",response.body().getPdf()  );
                                        startActivity(inte);
                                    }catch(ClassNotFoundException e){
                                        e.printStackTrace();
                                    }

                                }
                            });

                            btndonwload.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                   // DescargarPDF(response.body().getPdf());
                                    name = response.body().getTitulo()+".pdf";
                                    url = response.body().getPdf();
                                    download();
                                }
                            });

                            btnExtern.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    DescargarPDF(response.body().getPdf());
                                }
                            });
                        }else{
                            btnpdf.setVisibility(View.GONE);
                            btndonwload.setVisibility(View.GONE);
                            btnExtern.setVisibility(View.GONE);
                            player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                    String videoId = Help.cutString(contenidox.getUrl() );
                                    youTubePlayer.loadVideo(videoId, 0);
                                }
                            });

                        }



                        compartir.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_TEXT,   response.body().getPdf() !=null ? response.body().getPdf() : response.body().getUrl()      );
                                startActivity(Intent.createChooser(intent, "Share with"));

                            }
                        });

                        ws.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_TEXT,   response.body().getPdf() !=null ? response.body().getPdf() : response.body().getUrl()      );
                                intent.setPackage("com.whatsapp");
                                startActivity(intent);

                            }
                        });

                        fb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_TEXT,   response.body().getPdf() !=null ? response.body().getPdf() : response.body().getUrl()      );
                                intent.setPackage("com.facebook.katana");
                                startActivity(intent);

                            }
                        });

                        twitter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_TEXT,   response.body().getPdf() !=null ? response.body().getPdf() : response.body().getUrl()      );
                                intent.setPackage("com.twitter.android");
                                startActivity(intent);

                            }
                        });




                         // Toast.makeText(getApplicationContext(), contenidox.getTitulo() , Toast.LENGTH_LONG).show();
                    }
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Contenido> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public void DescargarPDF(String urlstring) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlstring)));

    }


    public void download() {
        if (isDownloadManagerAvailable()) {
            checkSelfPermission();
        } else {
            Toast.makeText(this, "Download manager is not available", Toast.LENGTH_LONG).show();
        }

    }

    private static boolean isDownloadManagerAvailable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return true;
        }
        return false;
    }


    private void checkSelfPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);

        } else {

            executeDownload();

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted! Do the work
                    executeDownload();
                } else {
                    // permission denied!
                    Toast.makeText(this, "Please give permissions ", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void executeDownload() {

        // registrer receiver in order to verify when download is complete
        registerReceiver(new DonwloadCompleteReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Downloading file " + name);
        request.setTitle(name);
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }















    public void cerrarSesion(View view){
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Hasta pronto", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        if(id==R.id.menu_salir_menu){
            this.cerrarSesion(item.getActionView());
            // startActivity(new Intent(this , MainActivity.class));
            // finish();
            // Toast.makeText(this, "xxx", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}