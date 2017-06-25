package com.lyqdhgo.environment.entity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by QiDeHong on 2017/6/24.
 */

public interface UerService {
    //sync/syncUser.json
    @FormUrlEncoded
    @POST("syncUser.json")
    Call<Result> listRepos(@Field("userId") String id);
}
