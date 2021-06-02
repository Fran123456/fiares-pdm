package com.fiares.Models;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarreraApi {


    @GET("api/getCarreras/{key}")
    public Call<List<Carrera>>carreras(@Path("key") String key);
}
