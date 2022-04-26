package com.pugerp.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review{

	@Expose
	@SerializedName("author_details")
	private Author authorDetails;

	@Expose
	@SerializedName("updated_at")
	private String updatedAt;

	@Expose
	@SerializedName("author")
	private String author;

	@Expose
	@SerializedName("created_at")
	private String createdAt;

	@Expose
	@SerializedName("id")
	private String id;

	@Expose
	@SerializedName("content")
	private String content;

	@Expose
	@SerializedName("url")
	private String url;

	public Author getAuthorDetails(){
		return authorDetails;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getAuthor(){
		return author;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getId(){
		return id;
	}

	public String getContent(){
		return content;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Review{" + 
			"author_details = '" + authorDetails + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",author = '" + author + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",content = '" + content + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}