package com.example.flowers.network;

import com.example.flowers.model.FlowersModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(Constants.FLOWER_LIST_API)
    Observable<List <FlowersModel>> getFlowersList();

}
