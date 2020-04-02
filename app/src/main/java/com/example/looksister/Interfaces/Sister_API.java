package com.example.looksister.Interfaces;

import com.example.looksister.Sister;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Sister_API {
    @GET("{number}/{page}")
    Observable<Sister> getCall(@Path("number") int number,@Path("page") int page);
}
