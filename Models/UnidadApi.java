package com.fiares.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UnidadApi {
        @GET("api/unidadesByMateria/{id}/{key}")
        public Call<List<Unidad>> unidades(@Path("id")int id, @Path("key") String key);
}

