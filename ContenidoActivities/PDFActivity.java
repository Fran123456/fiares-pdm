package com.fiares.ContenidoActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fiares.R;
import com.fiares.Utility.PDFStream;
import com.firebase.ui.auth.AuthUI;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class PDFActivity extends AppCompatActivity {

    ProgressBar progressBar;
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f);
        setTitle("FIARES - PDF");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String url = getIntent().getStringExtra("id");

        pdfView = findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.progressBar);
        new PDFStream(pdfView,progressBar).execute(url);


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