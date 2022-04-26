package com.pugerp.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company{

	@Expose
	@SerializedName("logo_path")
	private String logoPath;

	@Expose
	@SerializedName("name")
	private String name;

	@Expose
	@SerializedName("id")
	private int id;

	@Expose
	@SerializedName("origin_country")
	private String originCountry;

	public String getLogoPath(){
		return logoPath;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getOriginCountry(){
		return originCountry;
	}
}