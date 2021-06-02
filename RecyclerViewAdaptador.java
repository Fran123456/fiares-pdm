package com.fiares;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fiares.Utility.Menu;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> implements View.OnClickListener {

    public List<Menu> menuList;
    private View.OnClickListener listener;
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title, description;
        ImageView image;

        public ViewHolder( View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.menu_title);
            description = (TextView)itemView.findViewById(R.id.menu_des);
            image=(ImageView)itemView.findViewById(R.id.imgMenu);
        }
    }


    public RecyclerViewAdaptador(List<Menu> menuList){
        this.menuList = menuList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.title.setText( menuList.get(position).getTitle() );
        holder.description.setText( menuList.get(position).getDescription() );
        holder.image.setImageResource( menuList.get(position).getImage() );
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

}
