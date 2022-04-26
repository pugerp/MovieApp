package com.pugerp.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author{

	@Expose
	@SerializedName("avatar_path")
	private String avatarPath;

	@Expose
	@SerializedName("name")
	private String name;

	@Expose
	@SerializedName("rating")
	private Object rating;

	@Expose
	@SerializedName("username")
	private String username;

	public String getAvatarPath(){
		return avatarPath;
	}

	public String getName(){
		return name;
	}

	public Object getRating(){
		return rating;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"AuthorDetails{" + 
			"avatar_path = '" + avatarPath + '\'' + 
			",name = '" + name + '\'' + 
			",rating = '" + rating + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}