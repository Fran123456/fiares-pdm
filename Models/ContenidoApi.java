package com.fiares.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ContenidoApi {
    @GET("api/contenidos/{id}/{key}")
    public Call<List<Contenido>> contenido(@Path("id")int id, @Path("key") String key);

    @GET("api/contenido/{id}/{key}")
    public Call<Contenido> contenidoIndividual(@Path("id")int id, @Path("key") String key);

    @GET("api/contenido/pdf/{id}/{key}")
    public Call<String> pdf(@Path("id")int id, @Path("key") String key);
}
