package com.fiares.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TemarioApi {
    @GET("api/temariosByUnidades/{id}/{key}")
    public Call<List<Temario>> temarios(@Path("id")int id, @Path("key") String key);
}
