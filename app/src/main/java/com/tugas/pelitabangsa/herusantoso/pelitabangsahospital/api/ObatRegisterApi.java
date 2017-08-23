package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.DokterValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ObatValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PasienValue;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by santoso on 8/15/17.
 */

public interface ObatRegisterApi {
    @FormUrlEncoded
    @POST("obat_insert.php")
    Call<ObatValue> add(
            @Field("kodeObat") String kodeObat,
            @Field("nama") String nama,
            @Field("jenis") String jenis
    );

    @GET("obat_view.php")
    Call<ObatValue> view();

    @FormUrlEncoded
    @POST("obat_search.php")
    Call<PasienValue> search(@Field("search") String search);

    @FormUrlEncoded
    @POST("obat_delete.php")
    Call<ObatValue> delete(@Field("kodeObat") String kodeObat);

    @FormUrlEncoded
    @POST("obat_update.php")
    Call<ObatValue> update(
            @Field("kodeObat") String kodeObat,
            @Field("nama") String nama,
            @Field("jenis") String jenis
    );
}
