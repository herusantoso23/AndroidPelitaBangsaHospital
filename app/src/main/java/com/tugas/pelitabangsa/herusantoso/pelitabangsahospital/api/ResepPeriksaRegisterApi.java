package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ObatValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PasienValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ResepValue;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by santoso on 8/15/17.
 */

public interface ResepPeriksaRegisterApi {
    @FormUrlEncoded
    @POST("resep_insert.php")
    Call<ResepValue> add(
            @Field("nomor") String nomor,
            @Field("kodeObat") String kodeObat
    );

    @POST("resep_view.php")
    Call<ResepValue> view(
            @Field("nomor") String nomor
    );

    @FormUrlEncoded
    @POST("resep_search.php")
    Call<ResepValue> search(
            @Field("nomor") String nomor);
}
