package com.fiares.Utility;

import com.fiares.Models.Carrera;
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


    public static List<Carrera> getMenus(){
        List<Carrera> item = new ArrayList<>();
        item.add(new Carrera(1, "casa","x"));

        return item;
    }

    public static String cutString(String cadena){

        String sSubCadena = cadena.substring(17,cadena.length());
        return sSubCadena;
    }

    public static String url(){
        return "http://ccpcatalana.com/fiaresapi/public/";
    }
    public static String key(){return "EIGA7SBzsdho13g31052z9He0JiR-MAT115";}

}
