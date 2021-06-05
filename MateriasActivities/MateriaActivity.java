package com.fiares.MateriasActivities;

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

import com.fiares.Models.Materia;
import com.fiares.Models.MateriaApi;
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

public class MateriaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewMateria adapter;
    public List<Materia> menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String id = getIntent().getStringExtra("id"); //id carrera

        setTitle("FIARES - MATERIAS ");
        menuList = new ArrayList<>();
        getMaterias(id);
    }



    private void getMaterias(String id){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Help.url()).addConverterFactory(GsonConverterFactory.create()).build();
        MateriaApi carreraApi = retrofit.create(MateriaApi.class);
        Call<List<Materia>> call = carreraApi.materias( Integer.parseInt(id) ,Help.key());
        List<Materia> list = new ArrayList<>();
        call.enqueue(new Callback<List<Materia>>() {
            @Override
            public void onResponse(Call<List<Materia>> call, Response<List<Materia>> response) {
                try{
                    if(response.isSuccessful()){
                        list.addAll(response.body());
                        menuList = response.body();
                        recyclerView = (RecyclerView)findViewById(R.id.recycleMateriax);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter= new RecyclerViewMateria(list);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                //vamos a pasar a otra activity

                                try{
                                    Class<?>
                                            clase=Class.forName("com.fiares.UnidadesActivities.UnidadActivity");
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
            public void onFailure(Call<List<Materia>> call, Throwable t) {
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