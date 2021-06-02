package com.fiares.CarrerasActivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fiares.Models.Carrera;
import com.fiares.R;
import com.fiares.RecyclerViewAdaptador;

import java.util.List;

public class RecyclerViewCarrera extends RecyclerView.Adapter<RecyclerViewCarrera.ViewHolder> implements View.OnClickListener{
    public List<Carrera> menuList;
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

    public RecyclerViewCarrera(List<Carrera> menuList){
        this.menuList = menuList;
    }

    @Override
    public RecyclerViewCarrera.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_, parent,false);
        RecyclerViewCarrera.ViewHolder viewHolder = new RecyclerViewCarrera.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewCarrera.ViewHolder holder, int position) {
        holder.titulo.setText( menuList.get(position).getCarrera() );
        holder.des.setText( menuList.get(position).getDescripcion() );
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
