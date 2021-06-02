package com.fiares.UnidadesActivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fiares.MateriasActivities.RecyclerViewMateria;
import com.fiares.Models.Materia;
import com.fiares.Models.Unidad;
import com.fiares.R;

import java.util.List;

public class RecyclerViewUnidad extends RecyclerView.Adapter<RecyclerViewUnidad.ViewHolder> implements View.OnClickListener{

    public List<Unidad> menuList;
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

    public RecyclerViewUnidad(List<Unidad> menuList){
        this.menuList = menuList;
    }

    @Override
    public RecyclerViewUnidad.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_, parent,false);
        RecyclerViewUnidad.ViewHolder viewHolder = new RecyclerViewUnidad.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titulo.setText( menuList.get(position).getTitulo() );
        holder.des.setText( menuList.get(position).getDescripcion() );
        holder.image.setImageResource( R.drawable.read);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }


}
