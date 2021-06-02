package com.fiares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fiares.Utility.Help;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    ImageView photoUser;
    TextView nameUser;
    TextView mailUser;

    private RecyclerView recyclerView;
    private RecyclerViewAdaptador adapter;
    private List<com.fiares.Utility.Menu> menuList;

    public static final int REQUEST_CODE=1000;
    List<AuthUI.IdpConfig> provider = Arrays.asList(
            new AuthUI.IdpConfig.FacebookBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build(),
            new AuthUI.IdpConfig.EmailBuilder().build()
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("FIARES");
        photoUser = (ImageView)findViewById(R.id.photoUser);
        nameUser = (TextView)findViewById(R.id.nameUser);
        mailUser = (TextView)findViewById(R.id.mailUser);

        mfirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user !=null){

                      Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(photoUser);
                            nameUser.setText(user.getDisplayName());
                             mailUser.setText(user.getEmail());
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                }else{
                    startActivityForResult(
                            AuthUI.getInstance().createSignInIntentBuilder()
                            .setAvailableProviders(provider)
                            .setLogo(R.drawable.fiares)
                            .build(), REQUEST_CODE
                    );
                }
            }
        };




        //para el listado

        menuList = Help.getMenusHome();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.recycleUnidad);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new RecyclerViewAdaptador(menuList);
        recyclerView.setAdapter(adapter);


        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //vamos a pasar a otra activity
                if(menuList.get(recyclerView.getChildAdapterPosition(v)).getUrl().equals("db") ){
                    Toast.makeText(getApplicationContext(),"xd", Toast.LENGTH_LONG ).show();
                }else{
                    try{
                        Class<?>
                                clase=Class.forName("com.fiares."+menuList.get(recyclerView.getChildAdapterPosition(v)).getUrl());
                        Intent inte = new Intent(getApplicationContext(), clase);
                        inte.putExtra("titulo",  "Hola" );
                        startActivity(inte);
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        //para el listado

    }

    @Override
    protected void onResume() {
        super.onResume();
        mfirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mfirebaseAuth.removeAuthStateListener(mAuthListener);
    }


    public void cerrarSesion(View view){
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "Hasta pronto", Toast.LENGTH_LONG).show();
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