package com.fiares.ContenidoActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
import com.fiares.Utility.Help;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

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
   ImageButton btnpdf;
   ImageButton compartir;
   ImageButton ws, fb, twitter;

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
        contenido(id);


    }

    public void compartir(String url){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "El mejor blog de android http://javaheros.blogspot.pe/");
        startActivity(Intent.createChooser(intent, "Share with"));
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
                        titulo.setText(  response.body().getTitulo()  );
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
                        }else{
                            btnpdf.setVisibility(View.GONE);
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