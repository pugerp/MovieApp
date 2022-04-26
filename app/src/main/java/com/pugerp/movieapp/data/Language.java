package com.pugerp.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

	@Expose
	@SerializedName("name")
	private String name;

	@Expose
	@SerializedName("iso_639_1")
	private String iso6391;

	public String getName(){
		return name;
	}

	public String getIso6391(){
		return iso6391;
	}
}