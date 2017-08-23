package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.DokterValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PasienValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Periksa;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PeriksaValue;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by santoso on 8/15/17.
 */

public interface PeriksaRegisterApi {
    @FormUrlEncoded
    @POST("periksa_insert.php")
    Call<PeriksaValue> add(
            @Field("nomor") String nomor,
            @Field("tanggal") String tanggal,
            @Field("kodeDokter") String kodeDokter,
            @Field("kodePasien") String kodePasien,
            @Field("penyakit") String penyakit
    );

    @GET("dokter_get_count.php")
    Call<PeriksaValue> countData();

    @GET("periksa_view.php")
    Call<PeriksaValue> view();
}
