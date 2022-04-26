package com.pugerp.movieapp.network;

import com.pugerp.movieapp.BuildConfig;
import com.pugerp.movieapp.data.Genre;
import com.pugerp.movieapp.data.Movie;
import com.pugerp.movieapp.data.MovieDetail;
import com.pugerp.movieapp.data.PagingResp;
import com.pugerp.movieapp.data.Review;
import com.pugerp.movieapp.data.Video;


import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkInterface {

    @GET(BuildConfig.BASE_PATH + "/genre/movie/list")
    Observable<Response<Genre>> getGenres(@Query("api_key") String key, @Query("language") String lng);

    @GET(BuildConfig.BASE_PATH + "/discover/movie")
    Observable<Response<PagingResp<Movie>>> getMoviesByGenre(@Query("api_key") String key, @Query("language") String lng, @Query("with_genres") String genre, @Query("page") int page);

    @GET(BuildConfig.BASE_PATH + "/movie/{id}")
    Observable<Response<MovieDetail>> getMovieDetail(@Path("id") int movieId, @Query("api_key") String key, @Query("language") String lng);

    @GET(BuildConfig.BASE_PATH + "/movie/upcoming")
    Observable<Response<PagingResp<Movie>>> getMoviesByUpComing(@Query("api_key") String key, @Query("language") String lng);

    @GET(BuildConfig.BASE_PATH + "/movie/now_playing")
    Observable<Response<PagingResp<Movie>>> getMoviesByNowPlaying(@Query("api_key") String key, @Query("language") String lng);

    @GET(BuildConfig.BASE_PATH + "/movie/{id}/videos")
    Observable<Response<PagingResp<Video>>> getVideosById(@Path("id") int movieId, @Query("api_key") String key, @Query("language") String lng);

    @GET(BuildConfig.BASE_PATH + "/movie/{id}/reviews")
    Observable<Response<PagingResp<Review>>> getReviews(@Path("id") int movieId, @Query("api_key") String key, @Query("language") String lng, @Query("page") int page);
}
