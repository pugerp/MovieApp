package com.pugerp.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenresItem{

	@Expose
	@SerializedName("name")
	public String name;

	@Expose
	@SerializedName("id")
	public int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"GenresItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}