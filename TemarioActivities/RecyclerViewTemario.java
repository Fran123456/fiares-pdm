package com.fiares.TemarioActivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fiares.Models.Temario;
import com.fiares.Models.Unidad;
import com.fiares.R;
import com.fiares.UnidadesActivities.RecyclerViewUnidad;

import java.util.List;

public class RecyclerViewTemario extends RecyclerView.Adapter<RecyclerViewTemario.ViewHolder> implements View.OnClickListener{
    public List<Temario> menuList;
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

    public RecyclerViewTemario(List<Temario> menuList){
        this.menuList = menuList;
    }

    @Override
    public RecyclerViewTemario.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_, parent,false);
        RecyclerViewTemario.ViewHolder viewHolder = new RecyclerViewTemario.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewTemario.ViewHolder holder, int position) {
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
