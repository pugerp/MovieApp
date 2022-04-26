package com.pugerp.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country{

	@Expose
	@SerializedName("iso_3166_1")
	private String iso31661;

	@Expose
	@SerializedName("name")
	private String name;

	public String getIso31661(){
		return iso31661;
	}

	public String getName(){
		return name;
	}
}