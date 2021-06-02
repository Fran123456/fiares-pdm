package com.fiares.Models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarreraApi {
    public String key ="EIGA7SBzsdho13g31052z9He0JiR-MAT115";

    @GET("api/getCarreras/{key}")
    public Call<Carrera>find(@Path("key") String id);
}
