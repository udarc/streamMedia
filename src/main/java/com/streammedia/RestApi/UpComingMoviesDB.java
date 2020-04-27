package com.streammedia.RestApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpComingMoviesDB{
	private Dates dates;
	private int page;
	private int totalPages;
	private List<ResultsItem> results;
	private int totalResults;

	public void setDates(Dates dates){
		this.dates = dates;
	}

	public Dates getDates(){
		return dates;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

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