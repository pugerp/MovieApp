package com.pugerp.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PagingResp<M> {

	@Expose
	@SerializedName("page")
	private int page;

	@Expose
	@SerializedName("total_pages")
	private int totalPages;

	@Expose
	@SerializedName("results")
	private List<M> results;

	@Expose
	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<M> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}