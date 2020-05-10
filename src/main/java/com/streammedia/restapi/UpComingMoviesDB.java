package com.streammedia.restapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The type Up coming movies db.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpComingMoviesDB{
	private Dates dates;
	private int page;
	private int totalPages;
	private List<ResultsItem> results;
	private int totalResults;

    /**
     * Set dates.
     *
     * @param dates the dates
     */
    public void setDates(Dates dates){
		this.dates = dates;
	}

    /**
     * Get dates dates.
     *
     * @return the dates
     */
    public Dates getDates(){
		return dates;
	}

    /**
     * Set page.
     *
     * @param page the page
     */
    public void setPage(int page){
		this.page = page;
	}

    /**
     * Get page int.
     *
     * @return the int
     */
    public int getPage(){
		return page;
	}

    /**
     * Set total pages.
     *
     * @param totalPages the total pages
     */
    public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

    /**
     * Get total pages int.
     *
     * @return the int
     */
    public int getTotalPages(){
		return totalPages;
	}

    /**
     * Set results.
     *
     * @param results the results
     */
    public void setResults(List<ResultsItem> results){
		this.results = results;
	}

    /**
     * Get results list.
     *
     * @return the list
     */
    public List<ResultsItem> getResults(){
		return results;
	}

    /**
     * Set total results.
     *
     * @param totalResults the total results
     */
    public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

    /**
     * Get total results int.
     *
     * @return the int
     */
    public int getTotalResults(){
		return totalResults;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
 	public String toString(){
		return 
			"UpComingMoviesDB{" + 
			"dates = '" + dates + '\'' + 
			",page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}