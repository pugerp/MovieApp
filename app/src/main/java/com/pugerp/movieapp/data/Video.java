package com.pugerp.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video{

	@Expose
	@SerializedName("site")
	private String site;

	@Expose
	@SerializedName("size")
	private int size;

	@Expose
	@SerializedName("iso_3166_1")
	private String iso31661;

	@Expose
	@SerializedName("name")
	private String name;

	@Expose
	@SerializedName("official")
	private boolean official;

	@Expose
	@SerializedName("id")
	private String id;

	@Expose
	@SerializedName("type")
	private String type;

	@Expose
	@SerializedName("published_at")
	private String publishedAt;

	@Expose
	@SerializedName("iso_639_1")
	private String iso6391;

	@Expose
	@SerializedName("key")
	private String key;

	public String getSite(){
		return site;
	}

	public int getSize(){
		return size;
	}

	public String getIso31661(){
		return iso31661;
	}

	public String getName(){
		return name;
	}

	public boolean isOfficial(){
		return official;
	}

	public String getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public String getIso6391(){
		return iso6391;
	}

	public String getKey(){
		return key;
	}

	@Override
	public String toString() {
		return "Video{" +
				"site='" + site + '\'' +
				", size=" + size +
				", iso31661='" + iso31661 + '\'' +
				", name='" + name + '\'' +
				", official=" + official +
				", id='" + id + '\'' +
				", type='" + type + '\'' +
				", publishedAt='" + publishedAt + '\'' +
				", iso6391='" + iso6391 + '\'' +
				", key='" + key + '\'' +
				'}';
	}
}