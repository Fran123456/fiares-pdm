package com.fiares.MateriasActivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fiares.CarrerasActivities.RecyclerViewCarrera;
import com.fiares.Models.Carrera;
import com.fiares.Models.Materia;
import com.fiares.R;

import java.util.List;

public class RecyclerViewMateria extends RecyclerView.Adapter<RecyclerViewMateria.ViewHolder> implements View.OnClickListener{

    public List<Materia> menuList;
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

    public RecyclerViewMateria(List<Materia> menuList){
        this.menuList = menuList;
    }

    @Override
    public RecyclerViewMateria.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_, parent,false);
        RecyclerViewMateria.ViewHolder viewHolder = new RecyclerViewMateria.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewMateria.ViewHolder holder, int position) {
        holder.titulo.setText( menuList.get(position).getSiglas() );
        holder.des.setText( menuList.get(position).getTitulo() );
        holder.image.setImageResource( R.drawable.carreers);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

}
