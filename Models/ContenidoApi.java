package com.fiares.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ContenidoApi {
    @GET("api/contenidos/{id}/{key}")
    public Call<List<Contenido>> contenido(@Path("id")int id, @Path("key") String key);
}
