package com.fiares.SearchActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fiares.ContenidoActivities.RecyclerViewContenido;
import com.fiares.Models.Contenido;
import com.fiares.Models.ContenidoApi;
import com.fiares.R;
import com.fiares.Utility.Help;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchContenidoActivity extends AppCompatActivity {
    static final int check=1111;
    ImageButton Voice;
    String text;
    EditText editString;
    private RecyclerView recyclerView;
    private RecyclerViewContenido adapter;
    private List<Contenido> menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contenido);
        Voice= findViewById(R.id.btnvoiceContenido);
        editString= findViewById(R.id.txtSearchContenido);
        setTitle("FIARES - BUSCA CONTENIDO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        text="$";
        getContenido(text);

    }

    public void refreshContenido(View view){

        getContenido(editString.getText().toString());
    }



    private void getContenido(String string){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Help.url()).addConverterFactory(GsonConverterFactory.create()).build();
        ContenidoApi contenidoApi = retrofit.create( ContenidoApi.class);
        Call<List<Contenido>> call =   contenidoApi.contenidoLike( string ,Help.key());
        List<Contenido> list = new ArrayList<>();
        call.enqueue(new Callback<List<Contenido>>() {
            @Override
            public void onResponse(Call<List<Contenido>> call, Response<List<Contenido>> response) {
                try{
                    if(response.isSuccessful()){
                        list.addAll(response.body());
                        menuList = response.body();
                        recyclerView = (RecyclerView)findViewById(R.id.recycleSearchContent);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter= new RecyclerViewContenido(list);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                //vamos a pasar a otra activity

                                try{
                                    Class<?>
                                            clase=Class.forName("com.fiares.ContenidoActivities.InfoActivity");
                                    Intent inte = new Intent(getApplicationContext(), clase);
                                    inte.putExtra("id", String.valueOf( menuList.get(recyclerView.getChildAdapterPosition(v)).getId()    ) );
                                    //inte.putExtra("id",  "Hola" );
                                    startActivity(inte);
                                }catch(ClassNotFoundException e){
                                    e.printStackTrace();
                                }

                            }
                        });
                        //  Toast.makeText(getApplicationContext(),list.size() , Toast.LENGTH_LONG).show();
                    }
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Contenido>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }















    public void search(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.btnvoiceContenido) {
            // Si entramos a dar clic en el boton
            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora ");
            startActivityForResult(i, check);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode==check && resultCode==RESULT_OK){
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
          //  Toast.makeText(getApplicationContext(), results.get(0).toString()  , Toast.LENGTH_LONG).show();
            text =results.get(0).toString();
            getContenido(text);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void onDestroy(){
        super.onDestroy();
    }
}