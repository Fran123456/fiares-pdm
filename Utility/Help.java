package com.fiares.Utility;

import com.fiares.R;

import java.util.ArrayList;
import java.util.List;

public class Help {

    public Help() {
    }

    public static List<Menu> getMenusHome(){
        List<Menu> item = new ArrayList<>();
        item.add(new Menu("CARRERAS","Carreras disponibles con recursos", R.drawable.carreers,"CarrerasActivities.CarreraActivity"));
        item.add(new Menu("SOBRE NOSOTROS","Información general de la aplicación", R.drawable.info,"CareerActivities.CareerMenuActivity"));
        return item;
    }

}
