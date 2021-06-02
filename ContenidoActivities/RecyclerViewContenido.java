package com.fiares.ContenidoActivities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.fiares.Models.Contenido;
import com.fiares.Models.Temario;
import com.fiares.R;
import com.fiares.TemarioActivities.RecyclerViewTemario;

import java.util.List;

public class RecyclerViewContenido extends RecyclerView.Adapter<RecyclerViewContenido.ViewHolder> implements View.OnClickListener {

    public List<Contenido> menuList;
    private View.OnClickListener listener;
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titulo;
        private TextView des;
        ImageView image;

        public ViewHolder( View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.menu_title);
            des = (TextView)itemView.findViewById(R.id.menu_des);
            image=(ImageView)itemView.findViewById(R.id.imgMenu);
        }
    }

    public RecyclerViewContenido(List<Contenido> menuList){
        this.menuList = menuList;
    }

    @Override
    public RecyclerViewContenido.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_, parent,false);
        RecyclerViewContenido.ViewHolder viewHolder = new RecyclerViewContenido.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewContenido.ViewHolder holder, int position) {
        holder.titulo.setText( menuList.get(position).getTitulo() );
        holder.des.setText( menuList.get(position).getDescripcion() );
        holder.image.setImageResource( R.drawable.topic);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

}
