package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PasienValue;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by santoso on 8/15/17.
 */

public interface PasienRegisterApi {
    @FormUrlEncoded
    @POST("pasien_insert.php")
    Call<PasienValue> add(
            @Field("kodePasien") String kodePasien,
            @Field("nama") String nama,
            @Field("umur") String umur,
            @Field("alamat") String alamat
    );

    @GET("pasien_view.php")
    Call<PasienValue> view();

    @FormUrlEncoded
    @POST("pasien_search.php")
    Call<PasienValue> search(@Field("search")String search);

    @FormUrlEncoded
    @POST("pasien_delete.php")
    Call<PasienValue> delete(@Field("kodePasien")String kodePasien);

    @FormUrlEncoded
    @POST("pasien_update.php")
    Call<PasienValue> update(
            @Field("kodePasien") String kodePasien,
            @Field("nama") String nama,
            @Field("umur") String umur,
            @Field("alamat") String alamat
    );
}
