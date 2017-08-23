package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.DokterValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PasienValue;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by santoso on 8/15/17.
 */

public interface DokterRegisterApi {
    @FormUrlEncoded
    @POST("dokter_insert.php")
    Call<DokterValue> add(
            @Field("kodeDokter") String kodeDokter,
            @Field("nama") String nama,
            @Field("telepon") String telepon,
            @Field("spesialis") String spesialis
    );

    @GET("dokter_view.php")
    Call<DokterValue> view();

    @FormUrlEncoded
    @POST("dokter_search.php")
    Call<PasienValue> search(@Field("search") String search);

    @FormUrlEncoded
    @POST("dokter_delete.php")
    Call<DokterValue> delete(@Field("kodeDokter") String kodePasien);

    @FormUrlEncoded
    @POST("dokter_update.php")
    Call<DokterValue> update(
            @Field("kodeDokter") String kodeDokter,
            @Field("nama") String nama,
            @Field("telepon") String telepon,
            @Field("spesialis") String spesialis
    );
}
