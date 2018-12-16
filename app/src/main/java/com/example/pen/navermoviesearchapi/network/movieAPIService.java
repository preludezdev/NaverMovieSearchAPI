package com.example.pen.navermoviesearchapi.network;

import com.example.pen.navermoviesearchapi.VO.MovieListVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface movieAPIService {

    @GET("v1/search/movie.json")
    Call<MovieListVO> getMovieListByName(@Header("X-Naver-Client-Id") String id,
                                         @Header("X-Naver-Client-Secret") String secret,
                                         @Query("query") String movieName,
                                         @Query("display") int movieDisplay);
}
