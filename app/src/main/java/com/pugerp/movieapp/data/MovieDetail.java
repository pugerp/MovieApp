package com.pugerp.movieapp.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetail{

	@Expose
	@SerializedName("original_language")
	private String originalLanguage;

	@Expose
	@SerializedName("imdb_id")
	private String imdbId;

	@Expose
	@SerializedName("video")
	private boolean video;

	@Expose
	@SerializedName("title")
	private String title;

	@Expose
	@SerializedName("backdrop_path")
	private String backdropPath;

	@Expose
	@SerializedName("revenue")
	private int revenue;

	@Expose
	@SerializedName("genres")
	private List<GenresItem> genres;

	@Expose
	@SerializedName("popularity")
	private double popularity;

	@Expose
	@SerializedName("production_countries")
	private List<Country> productionCountries;

	@Expose
	@SerializedName("id")
	private int id;

	@Expose
	@SerializedName("vote_count")
	private int voteCount;

	@Expose
	@SerializedName("budget")
	private int budget;

	@Expose
	@SerializedName("overview")
	private String overview;

	@Expose
	@SerializedName("original_title")
	private String originalTitle;

	@Expose
	@SerializedName("runtime")
	private int runtime;

	@Expose
	@SerializedName("poster_path")
	private Object posterPath;

	@Expose
	@SerializedName("spoken_languages")
	private List<Language> spokenLanguages;

	@Expose
	@SerializedName("production_companies")
	private List<Company> productionCompanies;

	@Expose
	@SerializedName("release_date")
	private String releaseDate;

	@Expose
	@SerializedName("vote_average")
	private double voteAverage;

	@Expose
	@SerializedName("belongs_to_collection")
	private Object belongsToCollection;

	@Expose
	@SerializedName("tagline")
	private String tagline;

	@Expose
	@SerializedName("adult")
	private boolean adult;

	@Expose
	@SerializedName("homepage")
	private String homepage;

	@Expose
	@SerializedName("status")
	private String status;

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public String getImdbId(){
		return imdbId;
	}

	public boolean isVideo(){
		return video;
	}

	public String getTitle(){
		return title;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public int getRevenue(){
		return revenue;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public double getPopularity(){
		return popularity;
	}

	public List<Country> getProductionCountries(){
		return productionCountries;
	}

	public int getId(){
		return id;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public int getBudget(){
		return budget;
	}

	public String getOverview(){
		return overview;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public int getRuntime(){
		return runtime;
	}

	public Object getPosterPath(){
		return posterPath;
	}

	public List<Language> getSpokenLanguages(){
		return spokenLanguages;
	}

	public List<Company> getProductionCompanies(){
		return productionCompanies;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public Object getBelongsToCollection(){
		return belongsToCollection;
	}

	public String getTagline(){
		return tagline;
	}

	public boolean isAdult(){
		return adult;
	}

	public String getHomepage(){
		return homepage;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public String toString() {
		return "MovieDetail{" +
				"originalLanguage='" + originalLanguage + '\'' +
				", imdbId='" + imdbId + '\'' +
				", video=" + video +
				", title='" + title + '\'' +
				", backdropPath='" + backdropPath + '\'' +
				", revenue=" + revenue +
				", genres=" + genres +
				", popularity=" + popularity +
				", productionCountries=" + productionCountries +
				", id=" + id +
				", voteCount=" + voteCount +
				", budget=" + budget +
				", overview='" + overview + '\'' +
				", originalTitle='" + originalTitle + '\'' +
				", runtime=" + runtime +
				", posterPath=" + posterPath +
				", spokenLanguages=" + spokenLanguages +
				", productionCompanies=" + productionCompanies +
				", releaseDate='" + releaseDate + '\'' +
				", voteAverage=" + voteAverage +
				", belongsToCollection=" + belongsToCollection +
				", tagline='" + tagline + '\'' +
				", adult=" + adult +
				", homepage='" + homepage + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}