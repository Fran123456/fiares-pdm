package com.fiares.CarrerasActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fiares.Models.Carrera;
import com.fiares.Models.CarreraApi;
import com.fiares.R;
import com.fiares.Utility.Help;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarreraActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewCarrera adapter;
    public List<Carrera> menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrera);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menuList = new ArrayList<>();
        menuList =getCarreras();
        setTitle("FIARES - CARRERAS ");
    }

    private List<Carrera> getCarreras(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Help.url()).addConverterFactory(GsonConverterFactory.create()).build();
        CarreraApi carreraApi = retrofit.create(CarreraApi.class);
        Call<List<Carrera>> call = carreraApi.carreras(Help.key());
        List<Carrera> list = new ArrayList<>();
        call.enqueue(new Callback<List<Carrera>>() {
            @Override
            public void onResponse(Call<List<Carrera>> call, Response<List<Carrera>> response) {
                try{
                    if(response.isSuccessful()){
                        list.addAll(response.body());
                        recyclerView = (RecyclerView)findViewById(R.id.recycleCarrera);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter= new RecyclerViewCarrera(list);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                //vamos a pasar a otra activity

                                try{
                                    Class<?>
                                            clase=Class.forName("com.fiares.CareerActivities.CareerEditActivity");
                                    Intent inte = new Intent(getApplicationContext(), clase);
                                    inte.putExtra("id", String.valueOf( menuList.get(recyclerView.getChildAdapterPosition(v)).getId() ) );
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
            public void onFailure(Call<List<Carrera>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return list;
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