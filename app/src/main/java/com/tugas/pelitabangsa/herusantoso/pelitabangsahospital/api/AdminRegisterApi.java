package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Admin;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.AdminValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.DokterValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PasienValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by santoso on 8/15/17.
 */

public interface AdminRegisterApi {

    @GET("admin_search.php")
    Call<List<Admin>> search(
            @Field("username") String username);
}
