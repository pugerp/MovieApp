package com.pugerp.movieapp.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre{

	@Expose
	@SerializedName("genres")
	public List<GenresItem> genres;

	public List<GenresItem> getGenres(){
		return genres;
	}

	@Override
 	public String toString(){
		return 
			"Genre{" + 
			"genres = '" + genres + '\'' + 
			"}";
		}
}