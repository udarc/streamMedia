package com.streammedia.restapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The type Results item.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsItem {
	private String overview;
	private String originalLanguage;
	private String originalTitle;
	private boolean video;
	private String title;
	private List<Integer> genreIds;
	private String posterPath;
	private String backdropPath;
	private String releaseDate;
	private double popularity;
	private double voteAverage;
	private int id;
	private boolean adult;
	private int voteCount;

    /**
     * Set overview.
     *
     * @param overview the overview
     */
    public void setOverview(String overview){
		this.overview = overview;
	}

    /**
     * Get overview string.
     *
     * @return the string
     */
    public String getOverview(){
		return overview;
	}

    /**
     * Set original language.
     *
     * @param originalLanguage the original language
     */
    public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

    /**
     * Get original language string.
     *
     * @return the string
     */
    public String getOriginalLanguage(){
		return originalLanguage;
	}

    /**
     * Set original title.
     *
     * @param originalTitle the original title
     */
    public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

    /**
     * Get original title string.
     *
     * @return the string
     */
    public String getOriginalTitle(){
		return originalTitle;
	}

    /**
     * Set video.
     *
     * @param video the video
     */
    public void setVideo(boolean video){
		this.video = video;
	}

    /**
     * Is video boolean.
     *
     * @return the boolean
     */
    public boolean isVideo(){
		return video;
	}

    /**
     * Set title.
     *
     * @param title the title
     */
    public void setTitle(String title){
		this.title = title;
	}

    /**
     * Get title string.
     *
     * @return the string
     */
    public String getTitle(){
		return title;
	}

    /**
     * Set genre ids.
     *
     * @param genreIds the genre ids
     */
    public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

    /**
     * Get genre ids list.
     *
     * @return the list
     */
    public List<Integer> getGenreIds(){
		return genreIds;
	}

    /**
     * Set poster path.
     *
     * @param posterPath the poster path
     */
    public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

    /**
     * Get poster path string.
     *
     * @return the string
     */
    public String getPosterPath(){
		return posterPath;
	}

    /**
     * Set backdrop path.
     *
     * @param backdropPath the backdrop path
     */
    public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

    /**
     * Get backdrop path string.
     *
     * @return the string
     */
    public String getBackdropPath(){
		return backdropPath;
	}

    /**
     * Set release date.
     *
     * @param releaseDate the release date
     */
    public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

    /**
     * Get release date string.
     *
     * @return the string
     */
    public String getReleaseDate(){
		return releaseDate;
	}

    /**
     * Set popularity.
     *
     * @param popularity the popularity
     */
    public void setPopularity(double popularity){
		this.popularity = popularity;
	}

    /**
     * Get popularity double.
     *
     * @return the double
     */
    public double getPopularity(){
		return popularity;
	}

    /**
     * Set vote average.
     *
     * @param voteAverage the vote average
     */
    public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

    /**
     * Get vote average double.
     *
     * @return the double
     */
    public double getVoteAverage(){
		return voteAverage;
	}

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(int id){
		this.id = id;
	}

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getId(){
		return id;
	}

    /**
     * Set adult.
     *
     * @param adult the adult
     */
    public void setAdult(boolean adult){
		this.adult = adult;
	}

    /**
     * Is adult boolean.
     *
     * @return the boolean
     */
    public boolean isAdult(){
		return adult;
	}

    /**
     * Set vote count.
     *
     * @param voteCount the vote count
     */
    public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

    /**
     * Get vote count int.
     *
     * @return the int
     */
    public int getVoteCount(){
		return voteCount;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
 	public String toString(){
		return 
			"ResultsItem{" +
			"overview = '" + overview + '\'' + 
			",original_language = '" + originalLanguage + '\'' + 
			",original_title = '" + originalTitle + '\'' + 
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			",genre_ids = '" + genreIds + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",backdrop_path = '" + backdropPath + '\'' + 
			",release_date = '" + releaseDate + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",id = '" + id + '\'' + 
			",adult = '" + adult + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}
}