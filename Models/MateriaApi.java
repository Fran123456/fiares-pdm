package com.fiares.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MateriaApi {
    @GET("api/getMaterias/{id}/{key}")
    public Call<List<Materia>> materias(@Path("id")int id,@Path("key") String key);
}
